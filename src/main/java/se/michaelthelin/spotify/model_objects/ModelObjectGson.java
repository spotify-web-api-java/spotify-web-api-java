package se.michaelthelin.spotify.model_objects;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.CopyrightType;
import se.michaelthelin.spotify.enums.CurrentlyPlayingType;
import se.michaelthelin.spotify.enums.Modality;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.enums.ReleaseDatePrecision;
import se.michaelthelin.spotify.model_objects.specification.Cursor;
import se.michaelthelin.spotify.model_objects.specification.Disallows;
import se.michaelthelin.spotify.model_objects.specification.ExternalId;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.utils.PlaylistItemFactory;

import java.io.IOException;
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
 * formats of the enums, Spotify's date format, the polymorphic playlist item, and the three model
 * objects whose JSON shape does not mirror their fields.
 */
final class ModelObjectGson {

  static final Gson GSON = new GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .registerTypeAdapter(Date.class, date())
    .registerTypeAdapter(AlbumType.class, fromString(AlbumType::keyOf))
    .registerTypeAdapter(CopyrightType.class, fromString(CopyrightType::keyOf))
    .registerTypeAdapter(CurrentlyPlayingType.class, fromString(CurrentlyPlayingType::keyOf))
    .registerTypeAdapter(ModelObjectType.class, fromString(ModelObjectType::keyOf))
    .registerTypeAdapter(ReleaseDatePrecision.class, fromString(ReleaseDatePrecision::keyOf))
    .registerTypeAdapter(Modality.class, fromInt(Modality::keyOf))
    .registerTypeAdapter(IPlaylistItem.class, object(PlaylistItemFactory::createPlaylistItem))
    .registerTypeAdapter(Cursor[].class, cursors())
    .registerTypeAdapter(Disallows.class, object(new Disallows.JsonUtil()::createModelObject))
    .registerTypeAdapter(ExternalId.class, object(new ExternalId.JsonUtil()::createModelObject))
    .registerTypeAdapter(ExternalUrl.class, object(new ExternalUrl.JsonUtil()::createModelObject))
    .create();

  private ModelObjectGson() {
  }

  /**
   * Adapts a type whose JSON shape does not mirror its fields by handing the whole object to a
   * hand-written reader. Only register this for a model that overrides
   * {@link AbstractModelObject.JsonUtil#createModelObject(com.google.gson.JsonObject)}, otherwise
   * the default implementation calls back into {@link #GSON} and recurses forever.
   */
  private static <T> TypeAdapter<T> object(Function<com.google.gson.JsonObject, T> reader) {
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
