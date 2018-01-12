package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Audio Analysis Measure objects by building instances from this class. <br>
 * These objects can store the data of various {@link AudioAnalysis} measurements, like {@code tatums}, {@code bars} and
 * {@code beats}.
 */
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

  /**
   * Get the confidence value of the measurement. <br>
   * The confidence indicates the reliability of its corresponding attribute. Elements carrying a small confidence value
   * should be considered speculative.
   *
   * @return The confidence value of the measurement between 0.0 and 1.0.
   */
  public Float getConfidence() {
    return confidence;
  }

  /**
   * Get the duration of the measurement in seconds. <br>
   * Example: Get the duration of a bar.
   *
   * @return The duration of the measurement in seconds.
   */
  public Float getDuration() {
    return duration;
  }

  /**
   * Get the start point of the measurement, eg. when the measured part of the track begins.
   *
   * @return The start point of the measurement.
   */
  public Float getStart() {
    return start;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysisMeasure} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Float confidence;
    private Float duration;
    private Float start;

    /**
     * The confidence setter.
     *
     * @param confidence The confidence value of the measurement between 0.0 and 1.0.
     * @return An {@link AudioAnalysisMeasure.Builder}.
     */
    public Builder setConfidence(Float confidence) {
      this.confidence = confidence;
      return this;
    }

    /**
     * The duration setter.
     *
     * @param duration The duration of the measurement in seconds.
     * @return An {@link AudioAnalysisMeasure.Builder}.
     */
    public Builder setDuration(Float duration) {
      this.duration = duration;
      return this;
    }

    /**
     * The start point setter.
     *
     * @param start The start point of the measurement.
     * @return An {@link AudioAnalysisMeasure.Builder}.
     */
    public Builder setStart(Float start) {
      this.start = start;
      return this;
    }

    @Override
    public AudioAnalysisMeasure build() {
      return new AudioAnalysisMeasure(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysisMeasure} instances.
   */
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
