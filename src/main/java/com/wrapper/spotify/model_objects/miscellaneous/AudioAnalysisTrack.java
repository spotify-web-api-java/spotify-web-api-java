package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Audio Analysis Track objects by creating instances from this class.
 */
public class AudioAnalysisTrack extends AbstractModelObject {
  private final Long numSamples;
  private final Float duration;
  private final String sampleMd5;
  private final Integer offsetSeconds;
  private final Integer windowSeconds;
  private final Long analysisSampleRate;
  private final Integer analysisChannels;
  private final Float endOfFadeIn;
  private final Float startOfFadeOut;
  private final Float loudness;
  private final Float tempo;
  private final Float tempoConfidence;
  private final Integer timeSignature;
  private final Float timeSignatureConfidence;
  private final Integer key;
  private final Float keyConfidence;
  private final Modality mode;
  private final Float modeConfidence;
  private final String codeString;
  private final Float codeVersion;
  private final String echoprintString;
  private final Float echoprintVersion;
  private final String synchString;
  private final Float synchVersion;
  private final String rhythmString;
  private final Float rhythmVersion;

  private AudioAnalysisTrack(final Builder builder) {
    super(builder);
    this.numSamples = builder.numSamples;
    this.duration = builder.duration;
    this.sampleMd5 = builder.sampleMd5;
    this.offsetSeconds = builder.offsetSeconds;
    this.windowSeconds = builder.windowSeconds;
    this.analysisSampleRate = builder.analysisSampleRate;
    this.analysisChannels = builder.analysisChannels;
    this.endOfFadeIn = builder.endOfFadeIn;
    this.startOfFadeOut = builder.startOfFadeOut;
    this.loudness = builder.loudness;
    this.tempo = builder.tempo;
    this.tempoConfidence = builder.tempoConfidence;
    this.timeSignature = builder.timeSignature;
    this.timeSignatureConfidence = builder.timeSignatureConfidence;
    this.key = builder.key;
    this.keyConfidence = builder.keyConfidence;
    this.mode = builder.mode;
    this.modeConfidence = builder.modeConfidence;
    this.codeString = builder.codeString;
    this.codeVersion = builder.codeVersion;
    this.echoprintString = builder.echoprintString;
    this.echoprintVersion = builder.echoprintVersion;
    this.synchString = builder.synchString;
    this.synchVersion = builder.synchVersion;
    this.rhythmString = builder.rhythmString;
    this.rhythmVersion = builder.rhythmVersion;
  }

  /**
   * Get the number of samples in the track. <br>
   * The total number of samples is calculated by multiplying the duration of the track with the sample rate. <br>
   *
   * @return The total number of samples in the track.
   */
  public Long getNumSamples() {
    return numSamples;
  }

  /**
   * Get the duration of the track in seconds.
   *
   * @return The duration of the track in seconds.
   */
  public Float getDuration() {
    return duration;
  }

  /**
   * Get the sample MD5. <br><br>
   * <p>
   * <b>Note:</b> The sample MD5 is <b>probably</b> the MD5 of the track file. In the documentaion of the Analyzer
   * software, this field is mentioned in an example and contains a value, but it seems that audio analysis objects
   * returned by the Spotify Web API doesn't include a value in the field anymore. <br>
   *
   * @return The sample MD5.
   */
  public String getSampleMd5() {
    return sampleMd5;
  }

  /**
   * Get the offset seconds. <br>
   * <b>Note:</b> There is no public documentation available for this field. <br>
   *
   * @return The offset seconds.
   */
  public Integer getOffsetSeconds() {
    return offsetSeconds;
  }

  /**
   * Get the window seconds. <br>
   * <b>Note:</b> There is no public documentation available for this field. <br>
   *
   * @return The window seconds.
   */
  public Integer getWindowSeconds() {
    return windowSeconds;
  }

  /**
   * Get the sample rate in which the audio analysis was performed.
   *
   * @return The analysis sample rate.
   */
  public Long getAnalysisSampleRate() {
    return analysisSampleRate;
  }

  /**
   * Get the analysis channels.
   *
   * @return The analysis channels.
   */
  public Integer getAnalysisChannels() {
    return analysisChannels;
  }

  /**
   * Get the end of fade in introduction of the track.
   *
   * @return The end of fade in introduction in seconds.
   */
  public Float getEndOfFadeIn() {
    return endOfFadeIn;
  }

  /**
   * Get the start of the fade out in seconds.
   *
   * @return The start of the fade out in seconds.
   */
  public Float getStartOfFadeOut() {
    return startOfFadeOut;
  }

  /**
   * Get the average loudness of the track in decibels. These values are mostly in a range between -60 and 0 decibels.
   *
   * @return Average loudness of the track.
   */
  public Float getLoudness() {
    return loudness;
  }

  /**
   * Get the estimated tempo of the track in beats per minute.
   *
   * @return The estimated tempo of the track.
   */
  public Float getTempo() {
    return tempo;
  }

  /**
   * Get the tempo confidence of the track.
   *
   * @return The tempo confidence of the track.
   */
  public Float getTempoConfidence() {
    return tempoConfidence;
  }

  /**
   * Get the estimated overall time signature of the track. The time signature (or meter) is the number of beats in a
   * bar. <br>
   * Example: A Viennese waltz has a three-quarters beat, so this method would return the value 3 in this case.
   *
   * @return Time signature value.
   */
  public Integer getTimeSignature() {
    return timeSignature;
  }

  /**
   * Get the time signature confidence of the track.
   *
   * @return The time signature confidence.
   */
  public Float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  /**
   * Get the estimated main key of the track.
   *
   * @return Main key of the track.
   * @see <a href="https://en.wikipedia.org/wiki/Pitch_class">Wikipedia: Pitch class notation</a>
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Get the key confidence of the track.
   *
   * @return The key confidence of the track.
   */
  public Float getKeyConfidence() {
    return keyConfidence;
  }

  /**
   * Get the modality of the track. (either "major" or "minor")
   *
   * @return The modality type of the track.
   * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (music)</a>
   */
  public Modality getMode() {
    return mode;
  }

  /**
   * Get the modality confidence of the track.
   *
   * @return The modality confidence of the track.
   */
  public Float getModeConfidence() {
    return modeConfidence;
  }

  /**
   * Get the code string of the track. <br><br>
   * <p>
   * <b>Note:</b> The code string is a fingerprint computed on the audio and were used by the Echo Nest services for
   * song identification, which are no longer available.
   *
   * @return The code string of the track.
   */
  public String getCodeString() {
    return codeString;
  }

  /**
   * Get the version of the code string.
   *
   * @return The version of the code string.
   */
  public Float getCodeVersion() {
    return codeVersion;
  }

  /**
   * Get the echoprint string of the track. <br><br>
   * <p>
   * <b>Note:</b> The echoprint string is a fingerprint computed on the audio and were used by the Echo Nest services
   * for song identification, which are no longer available.
   *
   * @return The echoprint string of the track.
   */
  public String getEchoprintString() {
    return echoprintString;
  }

  /**
   * Get the version of the echoprint string.
   *
   * @return The version of the echoprint string.
   */
  public Float getEchoprintVersion() {
    return echoprintVersion;
  }

  /**
   * Get the synch string of the track. <br>
   * <p>
   * It works with a simple synchronization algorithm to be implemented on the client side, which generates offset
   * values in numbers of samples for 3 locations in the decoded waveform, the beginning, the middle, and the end. These
   * offsets allow the client application to detect decoding errors (when offsets mismatch). They provide for synching
   * with sample accuracy, the JSON timing data with the waveform, regardless of which mp3 decoder was used on the
   * client side (quicktime, ffmpeg, mpg123, etc).
   *
   * @return The synch string.
   */
  public String getSynchString() {
    return synchString;
  }

  /**
   * Get the version of the synch string.
   *
   * @return The synch string version.
   */
  public Float getSynchVersion() {
    return synchVersion;
  }

  /**
   * Get the rhythm string of the track.
   * <p>
   *
   * @return The rhythm string of the track.
   */
  public String getRhythmString() {
    return rhythmString;
  }

  /**
   * Get the version of the rhythm string.
   *
   * @return The rhythm string version.
   */
  public Float getRhythmVersion() {
    return rhythmVersion;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysisTrack} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Long numSamples;
    private Float duration;
    private String sampleMd5;
    private Integer offsetSeconds;
    private Integer windowSeconds;
    private Long analysisSampleRate;
    private Integer analysisChannels;
    private Float endOfFadeIn;
    private Float startOfFadeOut;
    private Float loudness;
    private Float tempo;
    private Float tempoConfidence;
    private Integer timeSignature;
    private Float timeSignatureConfidence;
    private Integer key;
    private Float keyConfidence;
    private Modality mode;
    private Float modeConfidence;
    private String codeString;
    private Float codeVersion;
    private String echoprintString;
    private Float echoprintVersion;
    private String synchString;
    private Float synchVersion;
    private String rhythmString;
    private Float rhythmVersion;

    /**
     * The sample number setter.
     *
     * @param numSamples The total number of samples in the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setNumSamples(Long numSamples) {
      this.numSamples = numSamples;
      return this;
    }

    /**
     * The track duration setter.
     *
     * @param duration The duration of the track in seconds.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setDuration(Float duration) {
      this.duration = duration;
      return this;
    }

    /**
     * The sample MD5 setter.
     *
     * @param sampleMd5 The sample MD5.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setSampleMd5(String sampleMd5) {
      this.sampleMd5 = sampleMd5;
      return this;
    }

    /**
     * The offset seconds setter.
     *
     * @param offsetSeconds The offset seconds.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setOffsetSeconds(Integer offsetSeconds) {
      this.offsetSeconds = offsetSeconds;
      return this;
    }

    /**
     * The window seconds setter.
     *
     * @param windowSeconds The window seconds.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setWindowSeconds(Integer windowSeconds) {
      this.windowSeconds = windowSeconds;
      return this;
    }

    /**
     * The analysis sample rate setter.
     *
     * @param analysisSampleRate The analysis sample rate.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setAnalysisSampleRate(Long analysisSampleRate) {
      this.analysisSampleRate = analysisSampleRate;
      return this;
    }

    /**
     * The analysis channels setter.
     *
     * @param analysisChannels The analysis channels.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setAnalysisChannels(Integer analysisChannels) {
      this.analysisChannels = analysisChannels;
      return this;
    }

    /**
     * The end of fade in introduction setter.
     *
     * @param endOfFadeIn The end of fade in introduction in seconds.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setEndOfFadeIn(Float endOfFadeIn) {
      this.endOfFadeIn = endOfFadeIn;
      return this;
    }

    /**
     * The start of fade out setter.
     *
     * @param startOfFadeOut The start of the fade out in seconds.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setStartOfFadeOut(Float startOfFadeOut) {
      this.startOfFadeOut = startOfFadeOut;
      return this;
    }

    /**
     * The average loudness setter.
     *
     * @param loudness Average loudness of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    /**
     * The estimated tempo setter.
     *
     * @param tempo The estimated tempo of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * The tempo confidence setter.
     *
     * @param tempoConfidence The tempo confidence of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setTempoConfidence(Float tempoConfidence) {
      this.tempoConfidence = tempoConfidence;
      return this;
    }

    /**
     * The time signature setter.
     *
     * @param timeSignature Time signature value.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    /**
     * The time signature confidence setter.
     *
     * @param timeSignatureConfidence The time signature confidence.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setTimeSignatureConfidence(Float timeSignatureConfidence) {
      this.timeSignatureConfidence = timeSignatureConfidence;
      return this;
    }

    /**
     * The track key setter.
     *
     * @param key Main key of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    /**
     * The key confidence setter.
     *
     * @param keyConfidence The key confidence of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setKeyConfidence(Float keyConfidence) {
      this.keyConfidence = keyConfidence;
      return this;
    }

    /**
     * The track modality setter.
     *
     * @param mode The modality type of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    /**
     * The modality confidence setter.
     *
     * @param modeConfidence The modality confidence of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setModeConfidence(Float modeConfidence) {
      this.modeConfidence = modeConfidence;
      return this;
    }

    /**
     * The code string setter.
     *
     * @param codeString The code string of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setCodeString(String codeString) {
      this.codeString = codeString;
      return this;
    }

    /**
     * The code string version setter.
     *
     * @param codeVersion The version of the code string.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setCodeVersion(Float codeVersion) {
      this.codeVersion = codeVersion;
      return this;
    }

    /**
     * The echoprint string setter.
     *
     * @param echoprintString The echoprint string of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setEchoprintString(String echoprintString) {
      this.echoprintString = echoprintString;
      return this;
    }

    /**
     * The echoprint string version setter.
     *
     * @param echoprintVersion The version of the echoprint string.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setEchoprintVersion(Float echoprintVersion) {
      this.echoprintVersion = echoprintVersion;
      return this;
    }

    /**
     * The synch string setter.
     *
     * @param synchString The synch string.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setSynchString(String synchString) {
      this.synchString = synchString;
      return this;
    }

    /**
     * The synch string version setter.
     *
     * @param synchVersion The synch string version.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setSynchVersion(Float synchVersion) {
      this.synchVersion = synchVersion;
      return this;
    }

    /**
     * The rhythm string setter.
     *
     * @param rhythmString The rhythm string of the track.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setRhythmString(String rhythmString) {
      this.rhythmString = rhythmString;
      return this;
    }

    /**
     * The rhythm string version setter.
     *
     * @param rhythmVersion The rhythm string version.
     * @return An {@link AudioAnalysisTrack.Builder}.
     */
    public Builder setRhythmVersion(Float rhythmVersion) {
      this.rhythmVersion = rhythmVersion;
      return this;
    }

    @Override
    public AudioAnalysisTrack build() {
      return new AudioAnalysisTrack(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysisTrack} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisTrack> {
    public AudioAnalysisTrack createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysisTrack.Builder()
              .setAnalysisChannels(
                      hasAndNotNull(jsonObject, "analysis_channels")
                              ? jsonObject.get("analysis_channels").getAsInt()
                              : null)
              .setAnalysisSampleRate(
                      hasAndNotNull(jsonObject, "analysis_sample_rate")
                              ? jsonObject.get("analysis_sample_rate").getAsLong()
                              : null)
              .setCodeString(
                      hasAndNotNull(jsonObject, "code_string")
                              ? jsonObject.get("code_string").getAsString()
                              : null)
              .setCodeVersion(
                      hasAndNotNull(jsonObject, "code_version")
                              ? jsonObject.get("code_version").getAsFloat()
                              : null)
              .setDuration(
                      hasAndNotNull(jsonObject, "duration")
                              ? jsonObject.get("duration").getAsFloat()
                              : null)
              .setEchoprintString(
                      hasAndNotNull(jsonObject, "echoprintstring")
                              ? jsonObject.get("echoprintstring").getAsString()
                              : null)
              .setEchoprintVersion(
                      hasAndNotNull(jsonObject, "echoprint_version")
                              ? jsonObject.get("echoprint_version").getAsFloat()
                              : null)
              .setEndOfFadeIn(
                      hasAndNotNull(jsonObject, "end_of_face_in")
                              ? jsonObject.get("end_of_face_in").getAsFloat()
                              : null)
              .setKey(
                      hasAndNotNull(jsonObject, "key")
                              ? jsonObject.get("key").getAsInt()
                              : null)
              .setKeyConfidence(
                      hasAndNotNull(jsonObject, "key_confidence")
                              ? jsonObject.get("key_confidence").getAsFloat()
                              : null)
              .setLoudness(
                      hasAndNotNull(jsonObject, "loudness")
                              ? jsonObject.get("loudness").getAsFloat()
                              : null)
              .setMode(
                      hasAndNotNull(jsonObject, "type")
                              ? Modality.keyOf(
                              jsonObject.get("mode").getAsInt())
                              : null)
              .setModeConfidence(
                      hasAndNotNull(jsonObject, "mode_confidence")
                              ? jsonObject.get("mode_confidence").getAsFloat()
                              : null)
              .setNumSamples(
                      hasAndNotNull(jsonObject, "num_samples")
                              ? jsonObject.get("num_samples").getAsLong()
                              : null)
              .setOffsetSeconds(
                      hasAndNotNull(jsonObject, "offset_seconds")
                              ? jsonObject.get("offset_seconds").getAsInt()
                              : null)
              .setRhythmString(
                      hasAndNotNull(jsonObject, "rhythmstring")
                              ? jsonObject.get("rhythmstring").getAsString()
                              : null)
              .setRhythmVersion(
                      hasAndNotNull(jsonObject, "rhythm_version")
                              ? jsonObject.get("rhythm_version").getAsFloat()
                              : null)
              .setSampleMd5(
                      hasAndNotNull(jsonObject, "sample_md5")
                              ? jsonObject.get("sample_md5").getAsString()
                              : null)
              .setStartOfFadeOut(
                      hasAndNotNull(jsonObject, "start_of_fade_out")
                              ? jsonObject.get("start_of_fade_out").getAsFloat()
                              : null)
              .setSynchString(
                      hasAndNotNull(jsonObject, "synchstring")
                              ? jsonObject.get("synchstring").getAsString()
                              : null)
              .setSynchVersion(
                      hasAndNotNull(jsonObject, "synch_version")
                              ? jsonObject.get("synch_version").getAsFloat()
                              : null)
              .setTempo(
                      hasAndNotNull(jsonObject, "tempo")
                              ? jsonObject.get("tempo").getAsFloat()
                              : null)
              .setTempoConfidence(
                      hasAndNotNull(jsonObject, "tempo_confidence")
                              ? jsonObject.get("tempo_confidence").getAsFloat()
                              : null)
              .setTimeSignature(
                      hasAndNotNull(jsonObject, "time_sidnature")
                              ? jsonObject.get("time_sidnature").getAsInt()
                              : null)
              .setTimeSignatureConfidence(
                      hasAndNotNull(jsonObject, "time_signature_confidence")
                              ? jsonObject.get("time_signature_confidence").getAsFloat()
                              : null)
              .setWindowSeconds(
                      hasAndNotNull(jsonObject, "windows_seconds")
                              ? jsonObject.get("windows_seconds").getAsInt()
                              : null)
              .build();
    }
  }
}
