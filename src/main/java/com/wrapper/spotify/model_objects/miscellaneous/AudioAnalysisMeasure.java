package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisMeasure extends AbstractModelObject {
  private final Float confidence;
  private final Float duration;
  private final Float start;

  private AudioAnalysisMeasure(final Builder builder) {
    super(builder);

    this.confidence = builder.confidence;
    this.duration = builder.duration;
    this.start = builder.start;
  }

  public Float getConfidence() {
    return confidence;
  }

  public Float getDuration() {
    return duration;
  }

  public Float getStart() {
    return start;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private Float confidence;
    private Float duration;
    private Float start;

    public Builder setConfidence(Float confidence) {
      this.confidence = confidence;
      return this;
    }

    public Builder setDuration(Float duration) {
      this.duration = duration;
      return this;
    }

    public Builder setStart(Float start) {
      this.start = start;
      return this;
    }

    @Override
    public AudioAnalysisMeasure build() {
      return new AudioAnalysisMeasure(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisMeasure> {
    public AudioAnalysisMeasure createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysisMeasure.Builder()
              .setConfidence(
                      hasAndNotNull(jsonObject, "confidence")
                              ? jsonObject.get("confidence").getAsFloat()
                              : null)
              .setDuration(
                      hasAndNotNull(jsonObject, "duration")
                              ? jsonObject.get("duration").getAsFloat()
                              : null)
              .setStart(
                      hasAndNotNull(jsonObject, "start")
                              ? jsonObject.get("start").getAsFloat()
                              : null)
              .build();
    }
  }
}
