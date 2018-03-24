package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Audio Analysis Segments by creating instances from this class. <br>
 * Segments are sound entities (typically under a second) each relatively uniform in timbre and harmony. They are
 * characterized by their perceptual onsets and duration in seconds, loudness (dB), pitch and timbral content.
 */
public class AudioAnalysisSegment extends AbstractModelObject {
  private final AudioAnalysisMeasure measure;
  private final Float loudnessStart;
  private final Float loudnessMaxTime;
  private final Float loudnessMax;
  private final Float loudnessEnd;
  private final float[] pitches;
  private final float[] timbre;

  private AudioAnalysisSegment(final Builder builder) {
    super(builder);

    this.measure = builder.measure;
    this.loudnessStart = builder.loudnessStart;
    this.loudnessMaxTime = builder.loudnessMaxTime;
    this.loudnessMax = builder.loudnessMax;
    this.loudnessEnd = builder.loudnessEnd;
    this.pitches = builder.pitches;
    this.timbre = builder.timbre;
  }

  /**
   * Get the measure of the audio analysis segment object. This measure contains the start point, duration and
   * confidence of the segment.
   *
   * @return The measure of the audio analysis segment object.
   */
  public AudioAnalysisMeasure getMeasure() {
    return measure;
  }

  /**
   * Get the loudness level at the start of the segment.
   *
   * @return The loudness level at the start of the segment.
   */
  public Float getLoudnessStart() {
    return loudnessStart;
  }

  /**
   * Get the offset within the segment of the point of maximum loudness.
   *
   * @return The offset within the segment of the point of maximum loudness.
   */
  public Float getLoudnessMaxTime() {
    return loudnessMaxTime;
  }

  /**
   * Get the peak loudness value within the segment.
   *
   * @return The peak loudness value within the segment.
   */
  public Float getLoudnessMax() {
    return loudnessMax;
  }

  /**
   * Get the loudness level at the end of the segment. This is only specified in the last segment of the audio analysis.
   *
   * @return The loudness level at the end of the segment.
   */
  public Float getLoudnessEnd() {
    return loudnessEnd;
  }

  /**
   * Get the pitches of the segment. <br><br>
   * <p>
   * Pitch content is given by a "chroma" vector, corresponding to the 12 pitch classes C, C#, D to B, with values
   * ranging from 0 to 1 that describe the relative dominance of every pitch in the chromatic scale. For example a C
   * Major chord would likely be represented by large values of C, E and G (i.e. classes 0, 4, and 7). Vectors are
   * normalized to 1 by their strongest dimension, therefore noisy sounds are likely represented by values that are all
   * close to 1, while pure tones are described by one value at 1 (the pitch) and others near
   *
   * @return The pitches of the segment.
   */
  public float[] getPitches() {
    return pitches;
  }

  /**
   * Get the timbre of the segment. <br><br>
   * <p>
   * The timbre is the quality of a musical note or sound that distinguishes different types of musical instruments, or
   * voices. It is a complex notion also referred to as sound color, texture, or tone quality, and is derived from the
   * shape of a segments spectro-temporal surface, independently of pitch and loudness.
   *
   * @return The timbre of the track.
   */
  public float[] getTimbre() {
    return timbre;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysisSegment} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure measure;
    private Float loudnessStart;
    private Float loudnessMaxTime;
    private Float loudnessMax;
    private Float loudnessEnd;
    private float[] pitches;
    private float[] timbre;

    /**
     * The measure setter.
     *
     * @param measure The measure of the audio analysis segment object.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setMeasure(AudioAnalysisMeasure measure) {
      this.measure = measure;
      return this;
    }

    /**
     * The start loudness setter.
     *
     * @param loudnessStart The loudness level at the start of the segment.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setLoudnessStart(Float loudnessStart) {
      this.loudnessStart = loudnessStart;
      return this;
    }

    /**
     * The max loudness time setter.
     *
     * @param loudnessMaxTime The offset within the segment of the point of maximum loudness.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setLoudnessMaxTime(Float loudnessMaxTime) {
      this.loudnessMaxTime = loudnessMaxTime;
      return this;
    }

    /**
     * The max loudness setter.
     *
     * @param loudnessMax The peak loudness value within the segment.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setLoudnessMax(Float loudnessMax) {
      this.loudnessMax = loudnessMax;
      return this;
    }

    /**
     * The end loudness setter.
     *
     * @param loudnessEnd The loudness level at the end of the segment.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setLoudnessEnd(Float loudnessEnd) {
      this.loudnessEnd = loudnessEnd;
      return this;
    }

    /**
     * The pitches setter.
     *
     * @param pitches The pitches of the segment.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setPitches(float[] pitches) {
      this.pitches = pitches;
      return this;
    }

    /**
     * The timbre setter.
     *
     * @param timbre The timbre of the track.
     * @return An {@link AudioAnalysisSegment.Builder}.
     */
    public Builder setTimbre(float[] timbre) {
      this.timbre = timbre;
      return this;
    }

    @Override
    public AudioAnalysisSegment build() {
      return new AudioAnalysisSegment(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysisSegment} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisSegment> {
    public AudioAnalysisSegment createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysisSegment.Builder()
              .setLoudnessEnd(
                      hasAndNotNull(jsonObject, "loudness_end")
                              ? jsonObject.get("loudness_end").getAsFloat()
                              : null)
              .setLoudnessMax(
                      hasAndNotNull(jsonObject, "loudness_max")
                              ? jsonObject.get("loudness_max").getAsFloat()
                              : null)
              .setLoudnessMaxTime(
                      hasAndNotNull(jsonObject, "loudness_max_time")
                              ? jsonObject.get("loudness_max_time").getAsFloat()
                              : null)
              .setLoudnessStart(
                      hasAndNotNull(jsonObject, "loudness_start")
                              ? jsonObject.get("loudness_start").getAsFloat()
                              : null)
              .setMeasure(
                      new AudioAnalysisMeasure.JsonUtil().createModelObject(jsonObject))
              .setPitches(
                      hasAndNotNull(jsonObject, "pitches")
                              ? new Gson().fromJson(jsonObject.getAsJsonArray("pitches"), float[].class)
                              : null)
              .setTimbre(
                      hasAndNotNull(jsonObject, "timbre")
                              ? new Gson().fromJson(jsonObject.getAsJsonArray("timbre"), float[].class)
                              : null)
              .build();
    }
  }
}
