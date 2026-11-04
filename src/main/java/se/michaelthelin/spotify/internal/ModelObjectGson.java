package se.michaelthelin.spotify.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.Action;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.CopyrightType;
import se.michaelthelin.spotify.enums.CountryCode;
import se.michaelthelin.spotify.enums.CurrentlyPlayingType;
import se.michaelthelin.spotify.enums.Modality;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.special.PlaybackQueue;
import se.michaelthelin.spotify.model_objects.specification.Cursor;
import se.michaelthelin.spotify.model_objects.specification.Disallows;
import se.michaelthelin.spotify.model_objects.specification.ExternalId;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.utils.PlaylistItemFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.logging.Level;

/**
 * The shared {@link Gson} instance every model object is deserialized with.
 * <p>
 * Spotify's JSON uses snake_case, so the field naming policy covers the bulk of the mapping and a
 * field only needs {@code @SerializedName} where the wire name is not the snake_case form of the
 * Java field. The adapters below cover the cases reflection cannot express on its own: the wire
 * formats of the enums, Spotify's date format, the polymorphic playlist item, the three model
 * objects whose JSON shape does not mirror their fields, and the playback queue's empty default.
 * <p>
 * This package is not exported, so nothing here is public API.
 */
public final class ModelObjectGson {

  /**
   * The instance every model object is read with.
   */
  public static final Gson GSON = new GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .registerTypeAdapter(Date.class, date())
    .registerTypeAdapter(AlbumType.class, fromString(AlbumType::keyOf))
    .registerTypeAdapter(CopyrightType.class, fromString(CopyrightType::keyOf))
    .registerTypeAdapter(CountryCode.class, fromString(CountryCode::getByCode))
    .registerTypeAdapter(CurrentlyPlayingType.class, fromString(CurrentlyPlayingType::keyOf))
    .registerTypeAdapter(ModelObjectType.class, fromString(ModelObjectType::keyOf))
    .registerTypeAdapter(ReleaseDatePrecision.class, fromString(ReleaseDatePrecision::keyOf))
    .registerTypeAdapter(Modality.class, fromInt(Modality::keyOf))
    .registerTypeAdapter(IPlaylistItem.class, object(PlaylistItemFactory::createPlaylistItem))
    .registerTypeAdapter(Cursor[].class, cursors())
    .registerTypeAdapter(Disallows.class, object(ModelObjectGson::disallows))
    .registerTypeAdapter(ExternalId.class, object(ModelObjectGson::externalId))
    .registerTypeAdapter(ExternalUrl.class, object(ModelObjectGson::externalUrl))
    .registerTypeAdapter(PlaybackQueue.class, object(ModelObjectGson::playbackQueue))
    .create();

  private ModelObjectGson() {
  }

  /**
   * Adapts a type whose JSON shape does not mirror its fields by handing the whole object to a
   * hand-written reader. The reader must not read the same type through {@link #GSON} again, or it
   * recurses forever.
   */
  private static <T> TypeAdapter<T> object(Function<JsonObject, T> reader) {
    return new NullSafeAdapter<T>() {
      @Override
      T readValue(JsonReader in) throws IOException {
        JsonElement element = JsonParser.parseReader(in);
        return element.isJsonObject() ? reader.apply(element.getAsJsonObject()) : null;
      }
    };
  }

  /**
   * Spotify sends enums as their lower-case wire name. Each enum already owns that mapping in its
   * {@code keyOf}, which returns {@code null} for a value this library does not know yet.
   */
  private static <E extends Enum<E>> TypeAdapter<E> fromString(Function<String, E> keyOf) {
    return new NullSafeAdapter<E>() {
      @Override
      E readValue(JsonReader in) throws IOException {
        return keyOf.apply(in.nextString().toLowerCase(Locale.ROOT));
      }
    };
  }

  private static <E extends Enum<E>> TypeAdapter<E> fromInt(IntFunction<E> keyOf) {
    return new NullSafeAdapter<E>() {
      @Override
      E readValue(JsonReader in) throws IOException {
        return keyOf.apply(in.nextInt());
      }
    };
  }

  /**
   * {@code PagingCursorbased} exposes its cursors as an array, but Spotify sends a single
   * {@code cursors} object. The hand-written parser wrapped that one object in a one-element
   * array and callers depend on the shape, so the adapter keeps doing exactly that.
   */
  private static TypeAdapter<Cursor[]> cursors() {
    return new NullSafeAdapter<Cursor[]>() {
      @Override
      Cursor[] readValue(JsonReader in) throws IOException {
        JsonElement element = JsonParser.parseReader(in);
        if (!element.isJsonObject()) {
          return null;
        }
        return new Cursor[]{GSON.fromJson(element, Cursor.class)};
      }
    };
  }

  /**
   * A malformed date nulls the field rather than the whole object, which is what the hand-written
   * parsers used to do.
   */
  private static TypeAdapter<Date> date() {
    return new NullSafeAdapter<Date>() {
      @Override
      Date readValue(JsonReader in) throws IOException {
        String raw = in.nextString();
        try {
          return SpotifyApi.parseDefaultDate(raw);
        } catch (ParseException e) {
          SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
          return null;
        }
      }
    };
  }

  /**
   * Spotify sends the disallowed actions as an object of flags, of which only the set ones count.
   */
  private static Disallows disallows(JsonObject jsonObject) {
    EnumSet<Action> disallowedActions = EnumSet.noneOf(Action.class);
    for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
      if (entry.getValue().getAsJsonPrimitive().getAsBoolean()) {
        disallowedActions.add(Action.keyOf(entry.getKey().toLowerCase()));
      }
    }

    return new Disallows.Builder()
      .setDisallowedActions(disallowedActions)
      .build();
  }

  @SuppressWarnings("unchecked")
  private static ExternalId externalId(JsonObject jsonObject) {
    return new ExternalId.Builder()
      .setExternalIds(new Gson().fromJson(jsonObject, Map.class))
      .build();
  }

  @SuppressWarnings("unchecked")
  private static ExternalUrl externalUrl(JsonObject jsonObject) {
    return new ExternalUrl.Builder()
      .setExternalUrls(new Gson().fromJson(jsonObject, Map.class))
      .build();
  }

  /**
   * A missing {@code queue} reads as an empty list rather than {@code null}, which callers rely on.
   */
  private static PlaybackQueue playbackQueue(JsonObject jsonObject) {
    IPlaylistItem currentlyPlaying = hasAndNotNull(jsonObject, "currently_playing")
      ? PlaylistItemFactory.createPlaylistItem(jsonObject.getAsJsonObject("currently_playing"))
      : null;

    List<IPlaylistItem> queue = new ArrayList<>();
    if (hasAndNotNull(jsonObject, "queue")) {
      for (JsonElement jsonElement : jsonObject.getAsJsonArray("queue")) {
        queue.add(PlaylistItemFactory.createPlaylistItem(jsonElement.getAsJsonObject()));
      }
    }

    return new PlaybackQueue.Builder()
      .setCurrentlyPlaying(currentlyPlaying)
      .setQueue(queue)
      .build();
  }

  /**
   * Whether the object has the member with a value other than JSON null.
   *
   * @param jsonObject The object to look in.
   * @param memberName The member to look for.
   * @return Whether the member is present and not null.
   */
  public static boolean hasAndNotNull(JsonObject jsonObject, String memberName) {
    return jsonObject.has(memberName) && !jsonObject.get(memberName).isJsonNull();
  }

  private abstract static class NullSafeAdapter<T> extends TypeAdapter<T> {

    abstract T readValue(JsonReader in) throws IOException;

    @Override
    public final T read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }
      return readValue(in);
    }

    /**
     * Model objects are only ever read from the API, never sent back to it, so the write side was
     * never implemented. Failing loudly beats emitting JSON that silently disagrees with the wire
     * format. Implement the inverse of {@link #readValue} here if a request ever needs to send one.
     */
    @Override
    public final void write(JsonWriter out, T value) {
      throw new UnsupportedOperationException(
        "Spotify model objects are read-only; serializing them is not supported");
    }
  }
}
