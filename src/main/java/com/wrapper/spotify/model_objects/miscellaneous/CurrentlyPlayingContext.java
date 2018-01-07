package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Context;
import com.wrapper.spotify.model_objects.specification.Track;

public class CurrentlyPlayingContext extends AbstractModelObject {
  private final Device device;
  private final String repeat_state;
  private final Boolean shuffle_state;
  private final Context context;
  private final Integer timestamp;
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

  public Device getDevice() {
    return device;
  }

  public String getRepeat_state() {
    return repeat_state;
  }

  public Boolean getShuffle_state() {
    return shuffle_state;
  }

  public Context getContext() {
    return context;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public Integer getProgress_ms() {
    return progress_ms;
  }

  public Boolean getIs_playing() {
    return is_playing;
  }

  public Track getItem() {
    return item;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private Device device;
    private String repeat_state;
    private Boolean shuffle_state;
    private Context context;
    private Integer timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private Track item;

    public Builder setDevice(Device device) {
      this.device = device;
      return this;
    }

    public Builder setRepeat_state(String repeat_state) {
      this.repeat_state = repeat_state;
      return this;
    }

    public Builder setShuffle_state(Boolean shuffle_state) {
      this.shuffle_state = shuffle_state;
      return this;
    }

    public Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    public Builder setTimestamp(Integer timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder setProgress_ms(Integer progress_ms) {
      this.progress_ms = progress_ms;
      return this;
    }

    public Builder setIs_playing(Boolean is_playing) {
      this.is_playing = is_playing;
      return this;
    }

    public Builder setItem(Track item) {
      this.item = item;
      return this;
    }

    @Override
    public CurrentlyPlayingContext build() {
      return new CurrentlyPlayingContext(this);
    }
  }

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
                              ? jsonObject.get("timestamp").getAsInt()
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
