package se.michaelthelin.spotify.model_objects.miscellaneous;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.enums.CurrentlyPlayingType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.special.Actions;
import se.michaelthelin.spotify.model_objects.specification.Context;
import se.michaelthelin.spotify.model_objects.specification.Disallows;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/get-the-users-currently-playing-track/">
 * Currently Playing objects</a> by creating instances from this class.
 */
@JsonDeserialize(builder = CurrentlyPlaying.Builder.class)
public class CurrentlyPlaying extends AbstractModelObject {
  private final Context context;
  private final Long timestamp;
  private final Integer progress_ms;
  private final Boolean is_playing;
  private final IPlaylistItem item;
  private final CurrentlyPlayingType currentlyPlayingType;
  private final Actions actions;

  private CurrentlyPlaying(final Builder builder) {
    super(builder);

    this.context = builder.context;
    this.timestamp = builder.timestamp;
    this.progress_ms = builder.progress_ms;
    this.is_playing = builder.is_playing;
    this.item = builder.item;
    this.currentlyPlayingType = builder.currentlyPlayingType;
    this.actions = builder.actions;
  }

  /**
   * Get the context the item was played from.
   *
   * @return The context the item was played from. Can be {@code null}.
   */
  public Context getContext() {
    return context;
  }

  /**
   * Get the timestamp when the received data was fetched.
   *
   * @return Unix Millisecond Timestamp when data was fetched.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Get the progress of the current played item.
   *
   * @return Progress into the currently playing item. Can be {@code null}.
   */
  public Integer getProgress_ms() {
    return progress_ms;
  }

  /**
   * Check if something is played at the moment.
   *
   * @return If something is currently playing.
   */
  public Boolean getIs_playing() {
    return is_playing;
  }

  /**
   * Get the currently played track or episode.
   *
   * @return The currently playing track or episode. Can be {@code null}.
   */
  public IPlaylistItem getItem() {
    return item;
  }

  /**
   * Get the type of the currently playing item.
   *
   * @return The type of the currently playing item.
   */
  public CurrentlyPlayingType getCurrentlyPlayingType() {
    return currentlyPlayingType;
  }

  /**
   * Get which playback actions are available within the current context.
   *
   * @return A {@link Actions} object which contains a {@link Disallows} object.
   */
  public Actions getActions() {
    return actions;
  }

  @Override
  public String toString() {
    return "CurrentlyPlaying(context=" + context + ", timestamp=" + timestamp + ", progress_ms=" + progress_ms
        + ", is_playing=" + is_playing + ", item=" + item + ", currentlyPlayingType=" + currentlyPlayingType
        + ", actions=" + actions + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link CurrentlyPlaying} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Context context;
    private Long timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private IPlaylistItem item;
    private CurrentlyPlayingType currentlyPlayingType;
    private Actions actions;

    /**
     * The playing context setter.
     *
     * @param context The context the track was played from. Can be {@code null}.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    /**
     * The timestamp setter.
     *
     * @param timestamp Unix Millisecond Timestamp when data was fetched.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    /**
     * The current track progress setter.
     *
     * @param progress_ms Progress into the currently playing track. Can be {@code null}.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setProgress_ms(Integer progress_ms) {
      this.progress_ms = progress_ms;
      return this;
    }

    /**
     * The playing state setter.
     *
     * @param is_playing If something is currently playing.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setIs_playing(Boolean is_playing) {
      this.is_playing = is_playing;
      return this;
    }

    /**
     * The currently playing item setter.
     *
     * @param item The currently playing item. Can be {@code null}.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setItem(IPlaylistItem item) {
      this.item = item;
      return this;
    }

    /**
     * The currently playing type setter.
     *
     * @param currentlyPlayingType The type of the currently playing item.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setCurrentlyPlayingType(CurrentlyPlayingType currentlyPlayingType) {
      this.currentlyPlayingType = currentlyPlayingType;
      return this;
    }

    /**
     * The actions setter.
     *
     * @param actions A {@link Actions} object which contains a {@link Disallows} object.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setActions(Actions actions) {
      this.actions = actions;
      return this;
    }

    @Override
    public CurrentlyPlaying build() {
      return new CurrentlyPlaying(this);
    }
  }

  /**
   * JsonUtil class for building {@link CurrentlyPlaying} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<CurrentlyPlaying> {
    public CurrentlyPlaying createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new CurrentlyPlaying.Builder()
        .setContext(
          hasAndNotNull(jsonObject, "context")
            ? new Context.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("context"))
            : null)
        .setTimestamp(
          hasAndNotNull(jsonObject, "timestamp")
            ? jsonObject.get("timestamp").getAsLong()
            : null)
        .setProgress_ms(
          hasAndNotNull(jsonObject, "progress_ms")
            ? jsonObject.get("progress_ms").getAsInt()
            : null)
        .setIs_playing(
          hasAndNotNull(jsonObject, "is_playing")
            ? jsonObject.get("is_playing").getAsBoolean()
            : null)
        .setItem(
          hasAndNotNull(jsonObject, "item") && hasAndNotNull(jsonObject, "currently_playing_type")
            ? (jsonObject.get("currently_playing_type").getAsString().equals("track")
            ? new Track.JsonUtil().createModelObject(jsonObject.getAsJsonObject("item"))
            : jsonObject.get("currently_playing_type").getAsString().equals("episode")
            ? new Episode.JsonUtil().createModelObject(jsonObject.getAsJsonObject("item"))
            : null)
            : null)
        .setCurrentlyPlayingType(
          hasAndNotNull(jsonObject, "currently_playing_type")
            ? CurrentlyPlayingType.keyOf(
            jsonObject.get("currently_playing_type").getAsString().toLowerCase())
            : null)
        .setActions(
          hasAndNotNull(jsonObject, "actions")
            ? new Actions.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("actions"))
            : null)
        .build();
    }
  }
}
