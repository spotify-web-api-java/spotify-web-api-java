package se.michaelthelin.spotify.model_objects.miscellaneous;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Audio Analysis Metadata objects by creating instances from this class. <br>
 * These objects only contain metadata created by the Analyzer software.
 */
@JsonDeserialize(builder = AudioAnalysisMeta.Builder.class)
public class AudioAnalysisMeta extends AbstractModelObject {
  /** The version of the analyzer used. */
  private final String analyzerVersion;
  /** The platform used for analysis. */
  private final String platform;
  /** Detailed status of the analysis. */
  private final String detailedStatus;
  /** The HTTP status code of the analysis. */
  private final Integer statusCode;
  /** Timestamp when the analysis was performed. */
  private final Long timestamp;
  /** Time taken to perform the analysis. */
  private final Float analysisTime;
  /** The input process used for analysis. */
  private final String inputProcess;

  private AudioAnalysisMeta(final Builder builder) {
    super(builder);

    this.analyzerVersion = builder.analyzerVersion;
    this.platform = builder.platform;
    this.detailedStatus = builder.detailedStatus;
    this.statusCode = builder.statusCode;
    this.timestamp = builder.timestamp;
    this.analysisTime = builder.analysisTime;
    this.inputProcess = builder.inputProcess;
  }

  /**
   * Get the version of the Analyzer software, which is used to create audio analysis data.
   *
   * @return Analyzer software version.
   */
  public String getAnalyzerVersion() {
    return analyzerVersion;
  }

  /**
   * Get the platform, on which the audio analysis was created.
   *
   * @return The platform name.
   */
  public String getPlatform() {
    return platform;
  }

  /**
   * Get the detailed status of the Analyzer software after creating the audio analysis.
   *
   * @return The detailed status of the Analyzer software.
   */
  public String getDetailedStatus() {
    return detailedStatus;
  }

  /**
   * Get the exit status code of the Analyzer software.
   *
   * @return The exit status code. (Should be 0)
   */
  public Integer getStatusCode() {
    return statusCode;
  }

  /**
   * Get the timestamp when the audio analysis object has been created by the Analyzer software.
   *
   * @return Timestamp of audio analysis.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Get the duration of the audio analysis, eg. in how many seconds the audio analysis has been created by the
   * software.
   *
   * @return Duration of the audio analysis.
   */
  public Float getAnalysisTime() {
    return analysisTime;
  }

  /**
   * Get the input process of the audio analysis. The input process is most times the command
   * {@code libvorbisfile L+R 44100->22050}, which lowers the sample rate of the track. (probably to reduce the duration
   * of the audio analysis)
   *
   * @return The input process of the audio analysis.
   */
  public String getInputProcess() {
    return inputProcess;
  }

  @Override
  public String toString() {
    return "AudioAnalysisMeta(analyzerVersion=" + analyzerVersion + ", platform=" + platform + ", detailedStatus="
        + detailedStatus + ", statusCode=" + statusCode + ", timestamp=" + timestamp + ", analysisTime=" + analysisTime
        + ", inputProcess=" + inputProcess + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysisMeta} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String analyzerVersion;
    private String platform;
    private String detailedStatus;
    private Integer statusCode;
    private Long timestamp;
    private Float analysisTime;
    private String inputProcess;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * The Analyzer software version setter.
     *
     * @param analyzerVersion Analyzer software version.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setAnalyzerVersion(String analyzerVersion) {
      this.analyzerVersion = analyzerVersion;
      return this;
    }

    /**
     * The platform setter.
     *
     * @param platform The platform name.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setPlatform(String platform) {
      this.platform = platform;
      return this;
    }

    /**
     * The detailed status setter.
     *
     * @param detailedStatus The detailed status of the Analyzer software.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setDetailedStatus(String detailedStatus) {
      this.detailedStatus = detailedStatus;
      return this;
    }

    /**
     * The status code setter.
     *
     * @param statusCode The exit status code. (Should be 0)
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setStatusCode(Integer statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    /**
     * The timestamp setter.
     *
     * @param timestamp Timestamp of audio analysis.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    /**
     * The analysis time setter.
     *
     * @param analysisTime Duration of the audio analysis.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setAnalysisTime(Float analysisTime) {
      this.analysisTime = analysisTime;
      return this;
    }

    /**
     * The input process setter.
     *
     * @param inputProcess The input process of the audio analysis.
     * @return An {@link AudioAnalysisMeta.Builder}.
     */
    public Builder setInputProcess(String inputProcess) {
      this.inputProcess = inputProcess;
      return this;
    }

    @Override
    public AudioAnalysisMeta build() {
      return new AudioAnalysisMeta(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysisMeta} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisMeta> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

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
          hasAndNotNull(jsonObject, "analyzer_version")
            ? jsonObject.get("analyzer_version").getAsString()
            : null)
        .setDetailedStatus(
          hasAndNotNull(jsonObject, "detailed_status")
            ? jsonObject.get("detailed_status").getAsString()
            : null)
        .setInputProcess(
          hasAndNotNull(jsonObject, "input_process")
            ? jsonObject.get("input_process").getAsString()
            : null)
        .setPlatform(
          hasAndNotNull(jsonObject, "platform")
            ? jsonObject.get("platform").getAsString()
            : null)
        .setStatusCode(
          hasAndNotNull(jsonObject, "status_code")
            ? jsonObject.get("status_code").getAsInt()
            : null)
        .setTimestamp(
          hasAndNotNull(jsonObject, "timestamp")
            ? jsonObject.get("timestamp").getAsLong()
            : null)
        .build();
    }
  }
}
