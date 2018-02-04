package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Context;
import com.wrapper.spotify.model_objects.specification.Track;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/get-information-about-the-users-current-playback/">Currently Playing
 * Context objects</a> by creating instances from this class.
 */
public class CurrentlyPlayingContext extends AbstractModelObject {
  private final Device device;
  private final String repeat_state;
  private final Boolean shuffle_state;
  private final Context context;
  private final Long timestamp;
  private final Integer progress_ms;
  private final Boolean is_playing;
  private final Track item;

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
   * Get the context from where the currently playing track is played from.
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
   * Get the progress of the currently playing track.
   *
   * @return Progress into the currently playing track. Can be {@code null}.
   */
  public Integer getProgress_ms() {
    return progress_ms;
  }

  /**
   * Check whether a track is playing on the device or not.
   *
   * @return If something is currently playing.
   */
  public Boolean getIs_playing() {
    return is_playing;
  }

  /**
   * Get the currently playing track, if the device is playing something.
   *
   * @return The currently playing track. Can be {@code null}.
   */
  public Track getItem() {
    return item;
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
    private Track item;

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
     * The track progress setter.
     *
     * @param progress_ms Progress into the currently playing track. Can be {@code null}.
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
     * The currently playing track setter.
     *
     * @param item If something is currently playing.
     * @return A {@link CurrentlyPlayingContext.Builder}.
     */
    public Builder setItem(Track item) {
      this.item = item;
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
                      hasAndNotNull(jsonObject, "item")
                              ? new Track.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("item"))
                              : null)
              .build();
    }
  }
}
