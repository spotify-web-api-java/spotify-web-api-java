package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.Context;
import com.wrapper.spotify.model_objects.specification.Track;

public class CurrentlyPlaying extends AbstractModelObject {
  private final Context context;
  private final Integer timestamp;
  private final Integer progress_ms;
  private final Boolean is_playing;
  private final Track item;

  private CurrentlyPlaying(final CurrentlyPlaying.Builder builder) {
    super(builder);

    this.context = builder.context;
    this.timestamp = builder.timestamp;
    this.progress_ms = builder.progress_ms;
    this.is_playing = builder.is_playing;
    this.item = builder.item;
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

  public static final class Builder extends AbstractModelObject.Builder {
    private Context context;
    private Integer timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private Track item;

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
    public CurrentlyPlaying build() {
      return new CurrentlyPlaying(this);
    }
  }

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
