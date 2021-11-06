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
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/get-information-about-the-users-current-playback/">Currently Playing
 * Context objects</a> by creating instances from this class.
 */
@JsonDeserialize(builder = CurrentlyPlayingContext.Builder.class)
public class CurrentlyPlayingContext extends AbstractModelObject {
  private final Device device;
  private final String repeat_state;
  private final Boolean shuffle_state;
  private final Context context;
  private final Long timestamp;
  private final Integer progress_ms;
  private final Boolean is_playing;
  private final IPlaylistItem item;
  private final CurrentlyPlayingType currentlyPlayingType;
  private final Actions actions;

  private CurrentlyPlayingContext(final Builder builder) {
    super(builder);

    this.device = builder.device;
    this.repeat_state = builder.repeat_state;
    this.shuffle_state = builder.shuffle_state;
    this.context = builder.context;
    this.timestamp = builder.timestamp;
    this.progress_ms = builder.progress_ms;
    this.is_playing = builder.is_playing;
    this.item = builder.item;
    this.currentlyPlayingType = builder.currentlyPlayingType;
    this.actions = builder.actions;
  }

  /**
   * Get the currently active device.
   *
   * @return The device that is currently active.
   */
  public Device getDevice() {
    return device;
  }

  /**
   * Get the repeat state of the device. (No repeat, track repeat, context repeat)
   *
   * @return The repeat state.
   */
  public String getRepeat_state() {
    return repeat_state;
  }

  /**
   * Get the shuffle state of the device.
   *
   * @return If shuffle is on or off.
   */
  public Boolean getShuffle_state() {
    return shuffle_state;
  }

  /**
   * Get the context from where the currently playing item is played from.
   *
   * @return A Context Object. Can be {@code null}.
   */
  public Context getContext() {
    return context;
  }

  /**
   * Get the Unix timestamp in milliseconds when the time was fetched.
   *
   * @return Unix Millisecond Timestamp when data was fetched.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Get the progress of the currently playing item.
   *
   * @return Progress into the currently playing item. Can be {@code null}.
   */
  public Integer getProgress_ms() {
    return progress_ms;
  }

  /**
   * Check whether a track or episode is playing on the device or not.
   *
   * @return If something is currently playing.
   */
  public Boolean getIs_playing() {
    return is_playing;
  }

  /**
   * Get the currently playing track or episode, if the device is playing something.
   *
   * @return The currently playing item. Can be {@code null}.
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
    return "CurrentlyPlayingContext(device=" + device + ", repeat_state=" + repeat_state + ", shuffle_state="
        + shuffle_state + ", context=" + context + ", timestamp=" + timestamp + ", progress_ms=" + progress_ms
        + ", is_playing=" + is_playing + ", item=" + item + ", currentlyPlayingType=" + currentlyPlayingType
        + ", actions=" + actions + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link CurrentlyPlayingContext} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Device device;
    private String repeat_state;
    private Boolean shuffle_state;
    private Context context;
    private Long timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private IPlaylistItem item;
    private CurrentlyPlayingType currentlyPlayingType;
    private Actions actions;

    /**
     * The active device setter.
     *
     * @param device The device that is currently active.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setDevice(Device device) {
      this.device = device;
      return this;
    }

    /**
     * The repeat state setter.
     *
     * @param repeat_state The repeat state.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setRepeat_state(String repeat_state) {
      this.repeat_state = repeat_state;
      return this;
    }

    /**
     * The shuffle state setter.
     *
     * @param shuffle_state If shuffle is on or off.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setShuffle_state(Boolean shuffle_state) {
      this.shuffle_state = shuffle_state;
      return this;
    }

    /**
     * The playing context setter.
     *
     * @param context A Context Object. Can be {@code null}.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    /**
     * The timestamp setter.
     *
     * @param timestamp Unix Millisecond Timestamp when data was fetched.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    /**
     * The item progress setter.
     *
     * @param progress_ms Progress into the currently playing item. Can be {@code null}.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setProgress_ms(Integer progress_ms) {
      this.progress_ms = progress_ms;
      return this;
    }

    /**
     * The playing state setter.
     *
     * @param is_playing If something is currently playing.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setIs_playing(Boolean is_playing) {
      this.is_playing = is_playing;
      return this;
    }

    /**
     * The currently playing item setter.
     *
     * @param item If something is currently playing.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setItem(IPlaylistItem item) {
      this.item = item;
      return this;
    }

    /**
     * The currently playing type setter.
     *
     * @param currentlyPlayingType The type of the currently playing item.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setCurrentlyPlayingType(CurrentlyPlayingType currentlyPlayingType) {
      this.currentlyPlayingType = currentlyPlayingType;
      return this;
    }

    /**
     * The actions setter.
     *
     * @param actions A {@link Actions} object which contains a {@link Disallows} object.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setActions(Actions actions) {
      this.actions = actions;
      return this;
    }

    @Override
    public CurrentlyPlayingContext build() {
      return new CurrentlyPlayingContext(this);
    }
  }

  /**
   * JsonUtil class for building {@link CurrentlyPlayingContext} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<CurrentlyPlayingContext> {
    public CurrentlyPlayingContext createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setDevice(
          hasAndNotNull(jsonObject, "device")
            ? new Device.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("device"))
            : null)
        .setRepeat_state(
          hasAndNotNull(jsonObject, "repeat_state")
            ? jsonObject.get("repeat_state").getAsString()
            : null)
        .setShuffle_state(
          hasAndNotNull(jsonObject, "shuffle_state")
            ? jsonObject.get("shuffle_state").getAsBoolean()
            : null)
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
