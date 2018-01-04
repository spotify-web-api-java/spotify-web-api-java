package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisSegment extends AbstractModelObject {
  private final AudioAnalysisMeasure measure;
  private final float loudnessStart;
  private final float loudnessMaxTime;
  private final float loudnessMax;
  private final float loudnessEnd;
  private final float[] pitches;
  private final float[] timbre;

  private AudioAnalysisSegment(final AudioAnalysisSegment.Builder builder) {
    super(builder);

    this.measure = builder.measure;
    this.loudnessStart = builder.loudnessStart;
    this.loudnessMaxTime = builder.loudnessMaxTime;
    this.loudnessMax = builder.loudnessMax;
    this.loudnessEnd = builder.loudnessEnd;
    this.pitches = builder.pitches;
    this.timbre = builder.timbre;
  }

  public AudioAnalysisMeasure getMeasure() {
    return measure;
  }

  public float getLoudnessStart() {
    return loudnessStart;
  }

  public float getLoudnessMaxTime() {
    return loudnessMaxTime;
  }

  public float getLoudnessMax() {
    return loudnessMax;
  }

  public float getLoudnessEnd() {
    return loudnessEnd;
  }

  public float[] getPitches() {
    return pitches;
  }

  public float[] getTimbre() {
    return timbre;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure measure;
    private float loudnessStart;
    private float loudnessMaxTime;
    private float loudnessMax;
    private float loudnessEnd;
    private float[] pitches;
    private float[] timbre;

    public Builder setMeasure(AudioAnalysisMeasure measure) {
      this.measure = measure;
      return this;
    }

    public Builder setLoudnessStart(float loudnessStart) {
      this.loudnessStart = loudnessStart;
      return this;
    }

    public Builder setLoudnessMaxTime(float loudnessMaxTime) {
      this.loudnessMaxTime = loudnessMaxTime;
      return this;
    }

    public Builder setLoudnessMax(float loudnessMax) {
      this.loudnessMax = loudnessMax;
      return this;
    }

    public Builder setLoudnessEnd(float loudnessEnd) {
      this.loudnessEnd = loudnessEnd;
      return this;
    }

    public Builder setPitches(float[] pitches) {
      this.pitches = pitches;
      return this;
    }

    public Builder setTimbre(float[] timbre) {
      this.timbre = timbre;
      return this;
    }

    @Override
    public AudioAnalysisSegment build() {
      return new AudioAnalysisSegment(this);
    }
  }

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
                      hasAndNotNull(jsonObject, "measure")
                              ? new AudioAnalysisMeasure.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("measure"))
                              : null)
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
