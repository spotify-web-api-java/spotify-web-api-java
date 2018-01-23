package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Context;
import com.wrapper.spotify.model_objects.specification.Track;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/get-the-users-currently-playing-track/">
 * Currently Playing objects</a> by creating instances from this class.
 */
public class CurrentlyPlaying extends AbstractModelObject {
  private final Context context;
  private final Long timestamp;
  private final Integer progress_ms;
  private final Boolean is_playing;
  private final Track item;

  private CurrentlyPlaying(final Builder builder) {
    super(builder);

    this.context = builder.context;
    this.timestamp = builder.timestamp;
    this.progress_ms = builder.progress_ms;
    this.is_playing = builder.is_playing;
    this.item = builder.item;
  }

  /**
   * Get the context the track was played from.
   *
   * @return The context the track was played from. Can be {@code null}.
   */
  public Context getContext() {
    return context;
  }

  /**
   * Get the timestamp when the recieved data was fetched.
   *
   * @return Unix Millisecond Timestamp when data was fetched.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Get the progress of the current played track.
   *
   * @return Progress into the currently playing track. Can be {@code null}.
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
   * Get the currently played track.
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
   * Helper method to compare two {@link CurrentlyPlaying} objects. Both objects only have to contain the same track URI
   * to be equal. (eg. the same track object)
   *
   * @param obj Another {@link CurrentlyPlaying} object.
   * @return Whether the objects are equal or not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof CurrentlyPlaying))
      return false;

    CurrentlyPlaying other = (CurrentlyPlaying) obj;

    return this.getItem() != null
            && other.getItem() != null
            && this.getItem().getUri() != null
            && other.getItem().getUri() != null
            && this.getItem().getUri().equals(other.getItem().getUri());
  }

  /**
   * Builder class for building {@link CurrentlyPlaying} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Context context;
    private Long timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private Track item;

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
     * The currently playing track setter.
     *
     * @param item The currently playing track. Can be {@code null}.
     * @return A {@link CurrentlyPlaying.Builder}.
     */
    public Builder setItem(Track item) {
      this.item = item;
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
                      hasAndNotNull(jsonObject, "item")
                              ? new Track.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("item"))
                              : null)
              .build();
    }
  }
}
