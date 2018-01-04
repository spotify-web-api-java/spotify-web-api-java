package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisMeta extends AbstractModelObject {
  private final String analyzerVersion;
  private final String platform;
  private final String detailedStatus;
  private final int statusCode;
  private final long timestamp;
  private final float analysisTime;
  private final String inputProcess;

  private AudioAnalysisMeta(final AudioAnalysisMeta.Builder builder) {
    super(builder);

    this.analyzerVersion = builder.analyzerVersion;
    this.platform = builder.platform;
    this.detailedStatus = builder.detailedStatus;
    this.statusCode = builder.statusCode;
    this.timestamp = builder.timestamp;
    this.analysisTime = builder.analysisTime;
    this.inputProcess = builder.inputProcess;
  }

  public String getAnalyzerVersion() {
    return analyzerVersion;
  }

  public String getPlatform() {
    return platform;
  }

  public String getDetailedStatus() {
    return detailedStatus;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public float getAnalysisTime() {
    return analysisTime;
  }

  public String getInputProcess() {
    return inputProcess;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String analyzerVersion;
    private String platform;
    private String detailedStatus;
    private int statusCode;
    private long timestamp;
    private float analysisTime;
    private String inputProcess;

    public Builder setAnalyzerVersion(String analyzerVersion) {
      this.analyzerVersion = analyzerVersion;
      return this;
    }

    public Builder setPlatform(String platform) {
      this.platform = platform;
      return this;
    }

    public Builder setDetailedStatus(String detailedStatus) {
      this.detailedStatus = detailedStatus;
      return this;
    }

    public Builder setStatusCode(int statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    public Builder setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder setAnalysisTime(float analysisTime) {
      this.analysisTime = analysisTime;
      return this;
    }

    public Builder setInputProcess(String inputProcess) {
      this.inputProcess = inputProcess;
      return this;
    }

    @Override
    public AudioAnalysisMeta build() {
      return new AudioAnalysisMeta(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisMeta> {
    public AudioAnalysisMeta createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysisMeta.Builder()
              .setAnalysisTime(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("analysis_time").getAsFloat()
                              : null)
              .setAnalyzerVersion(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("analyzer_version").getAsString()
                              : null)
              .setDetailedStatus(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("detailed_status").getAsString()
                              : null)
              .setInputProcess(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("input_process").getAsString()
                              : null)
              .setPlatform(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("platform").getAsString()
                              : null)
              .setStatusCode(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("status_code").getAsInt()
                              : null)
              .setTimestamp(
                      hasAndNotNull(jsonObject, "analysis_time")
                              ? jsonObject.get("timestamp").getAsLong()
                              : null)
              .build();
    }
  }
}
