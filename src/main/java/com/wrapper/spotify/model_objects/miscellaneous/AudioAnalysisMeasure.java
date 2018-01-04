package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisMeasure extends AbstractModelObject {
  private final float confidence;
  private final float duration;
  private final float start;

  private AudioAnalysisMeasure(final AudioAnalysisMeasure.Builder builder) {
    super(builder);

    this.confidence = builder.confidence;
    this.duration = builder.duration;
    this.start = builder.start;
  }

  public float getConfidence() {
    return confidence;
  }

  public float getDuration() {
    return duration;
  }

  public float getStart() {
    return start;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private float confidence;
    private float duration;
    private float start;

    public Builder setConfidence(float confidence) {
      this.confidence = confidence;
      return this;
    }

    public Builder setDuration(float duration) {
      this.duration = duration;
      return this;
    }

    public Builder setStart(float start) {
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
