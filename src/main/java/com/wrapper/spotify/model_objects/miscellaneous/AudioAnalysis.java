package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysis extends AbstractModelObject {
  private final AudioAnalysisMeasure[] bars;
  private final AudioAnalysisMeasure[] beats;
  private final AudioAnalysisMeta meta;
  private final AudioAnalysisSection[] sections;
  private final AudioAnalysisSegment[] segments;
  private final AudioAnalysisMeasure[] tatums;
  private final AudioAnalysisTrack track;

  private AudioAnalysis(final Builder builder) {
    super(builder);

    this.bars = builder.bars;
    this.beats = builder.beats;
    this.meta = builder.meta;
    this.sections = builder.sections;
    this.segments = builder.segments;
    this.tatums = builder.tatums;
    this.track = builder.track;
  }

  public AudioAnalysisMeasure[] getBars() {
    return bars;
  }

  public AudioAnalysisMeasure[] getBeats() {
    return beats;
  }

  public AudioAnalysisMeta getMeta() {
    return meta;
  }

  public AudioAnalysisSection[] getSections() {
    return sections;
  }

  public AudioAnalysisSegment[] getSegments() {
    return segments;
  }

  public AudioAnalysisMeasure[] getTatums() {
    return tatums;
  }

  public AudioAnalysisTrack getTrack() {
    return track;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure[] bars;
    private AudioAnalysisMeasure[] beats;
    private AudioAnalysisMeta meta;
    private AudioAnalysisSection[] sections;
    private AudioAnalysisSegment[] segments;
    private AudioAnalysisMeasure[] tatums;
    private AudioAnalysisTrack track;

    public Builder setBars(AudioAnalysisMeasure[] bars) {
      this.bars = bars;
      return this;
    }

    public Builder setBeats(AudioAnalysisMeasure[] beats) {
      this.beats = beats;
      return this;
    }

    public Builder setMeta(AudioAnalysisMeta meta) {
      this.meta = meta;
      return this;
    }

    public Builder setSections(AudioAnalysisSection[] sections) {
      this.sections = sections;
      return this;
    }

    public Builder setSegments(AudioAnalysisSegment[] segments) {
      this.segments = segments;
      return this;
    }

    public Builder setTatums(AudioAnalysisMeasure[] tatums) {
      this.tatums = tatums;
      return this;
    }

    public Builder setTrack(AudioAnalysisTrack track) {
      this.track = track;
      return this;
    }

    @Override
    public AudioAnalysis build() {
      return new AudioAnalysis(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysis> {
    public AudioAnalysis createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysis.Builder()
              .setBars(
                      hasAndNotNull(jsonObject, "bars")
                              ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("bars"))
                              : null)
              .setBeats(
                      hasAndNotNull(jsonObject, "beats")
                              ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("beats"))
                              : null)
              .setMeta(
                      hasAndNotNull(jsonObject, "meta")
                              ? new AudioAnalysisMeta.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("meta"))
                              : null)
              .setSections(
                      hasAndNotNull(jsonObject, "sections")
                              ? new AudioAnalysisSection.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("sections"))
                              : null)
              .setSegments(
                      hasAndNotNull(jsonObject, "segments")
                              ? new AudioAnalysisSegment.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("segments"))
                              : null)
              .setTatums(
                      hasAndNotNull(jsonObject, "tatums")
                              ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("tatums"))
                              : null)
              .setTrack(
                      hasAndNotNull(jsonObject, "track")
                              ? new AudioAnalysisTrack.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("track"))
                              : null)
              .build();
    }
  }
}
