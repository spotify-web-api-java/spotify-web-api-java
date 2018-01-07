package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

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
  private final Integer mode;
  private final Float modeConfidence;
  private final String codeString;
  private final Float codeVersion;
  private final String echoprintString;
  private final Float echoprintVersion;
  private final String synchString;
  private final Float synchVersion;
  private final String rythmString;
  private final Float rythmVersion;

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
    this.rythmString = builder.rythmString;
    this.rythmVersion = builder.rythmVersion;
  }

  public Long getNumSamples() {
    return numSamples;
  }

  public Float getDuration() {
    return duration;
  }

  public String getSampleMd5() {
    return sampleMd5;
  }

  public Integer getOffsetSeconds() {
    return offsetSeconds;
  }

  public Integer getWindowSeconds() {
    return windowSeconds;
  }

  public Long getAnalysisSampleRate() {
    return analysisSampleRate;
  }

  public Integer getAnalysisChannels() {
    return analysisChannels;
  }

  public Float getEndOfFadeIn() {
    return endOfFadeIn;
  }

  public Float getStartOfFadeOut() {
    return startOfFadeOut;
  }

  public Float getLoudness() {
    return loudness;
  }

  public Float getTempo() {
    return tempo;
  }

  public Float getTempoConfidence() {
    return tempoConfidence;
  }

  public Integer getTimeSignature() {
    return timeSignature;
  }

  public Float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  public Integer getKey() {
    return key;
  }

  public Float getKeyConfidence() {
    return keyConfidence;
  }

  public Integer getMode() {
    return mode;
  }

  public Float getModeConfidence() {
    return modeConfidence;
  }

  public String getCodeString() {
    return codeString;
  }

  public Float getCodeVersion() {
    return codeVersion;
  }

  public String getEchoprintString() {
    return echoprintString;
  }

  public Float getEchoprintVersion() {
    return echoprintVersion;
  }

  public String getSynchString() {
    return synchString;
  }

  public Float getSynchVersion() {
    return synchVersion;
  }

  public String getRythmString() {
    return rythmString;
  }

  public Float getRythmVersion() {
    return rythmVersion;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

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
    private Integer mode;
    private Float modeConfidence;
    private String codeString;
    private Float codeVersion;
    private String echoprintString;
    private Float echoprintVersion;
    private String synchString;
    private Float synchVersion;
    private String rythmString;
    private Float rythmVersion;

    public Builder setNumSamples(Long numSamples) {
      this.numSamples = numSamples;
      return this;
    }

    public Builder setDuration(Float duration) {
      this.duration = duration;
      return this;
    }

    public Builder setSampleMd5(String sampleMd5) {
      this.sampleMd5 = sampleMd5;
      return this;
    }

    public Builder setOffsetSeconds(Integer offsetSeconds) {
      this.offsetSeconds = offsetSeconds;
      return this;
    }

    public Builder setWindowSeconds(Integer windowSeconds) {
      this.windowSeconds = windowSeconds;
      return this;
    }

    public Builder setAnalysisSampleRate(Long analysisSampleRate) {
      this.analysisSampleRate = analysisSampleRate;
      return this;
    }

    public Builder setAnalysisChannels(Integer analysisChannels) {
      this.analysisChannels = analysisChannels;
      return this;
    }

    public Builder setEndOfFadeIn(Float endOfFadeIn) {
      this.endOfFadeIn = endOfFadeIn;
      return this;
    }

    public Builder setStartOfFadeOut(Float startOfFadeOut) {
      this.startOfFadeOut = startOfFadeOut;
      return this;
    }

    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    public Builder setTempoConfidence(Float tempoConfidence) {
      this.tempoConfidence = tempoConfidence;
      return this;
    }

    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    public Builder setTimeSignatureConfidence(Float timeSignatureConfidence) {
      this.timeSignatureConfidence = timeSignatureConfidence;
      return this;
    }

    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    public Builder setKeyConfidence(Float keyConfidence) {
      this.keyConfidence = keyConfidence;
      return this;
    }

    public Builder setMode(Integer mode) {
      this.mode = mode;
      return this;
    }

    public Builder setModeConfidence(Float modeConfidence) {
      this.modeConfidence = modeConfidence;
      return this;
    }

    public Builder setCodeString(String codeString) {
      this.codeString = codeString;
      return this;
    }

    public Builder setCodeVersion(Float codeVersion) {
      this.codeVersion = codeVersion;
      return this;
    }

    public Builder setEchoprintString(String echoprintString) {
      this.echoprintString = echoprintString;
      return this;
    }

    public Builder setEchoprintVersion(Float echoprintVersion) {
      this.echoprintVersion = echoprintVersion;
      return this;
    }

    public Builder setSynchString(String synchString) {
      this.synchString = synchString;
      return this;
    }

    public Builder setSynchVersion(Float synchVersion) {
      this.synchVersion = synchVersion;
      return this;
    }

    public Builder setRythmString(String rythmString) {
      this.rythmString = rythmString;
      return this;
    }

    public Builder setRythmVersion(Float rythmVersion) {
      this.rythmVersion = rythmVersion;
      return this;
    }

    @Override
    public AudioAnalysisTrack build() {
      return new AudioAnalysisTrack(this);
    }
  }

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
                      hasAndNotNull(jsonObject, "mode")
                              ? jsonObject.get("mode").getAsInt()
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
              .setRythmString(
                      hasAndNotNull(jsonObject, "rythmstring")
                              ? jsonObject.get("rythmstring").getAsString()
                              : null)
              .setRythmVersion(
                      hasAndNotNull(jsonObject, "rythm_version")
                              ? jsonObject.get("rythm_version").getAsFloat()
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
