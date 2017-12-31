package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

public class AudioAnalysisTrack extends AbstractModelObject {
  private final long numSamples;
  private final float duration;
  private final String sampleMd5;
  private final int offsetSeconds;
  private final int windowSeconds;
  private final long analysisSampleRate;
  private final int analysisChannels;
  private final float endOfFadeIn;
  private final float startOfFadeOut;
  private final float loudness;
  private final float tempo;
  private final float tempoConfidence;
  private final int timeSignature;
  private final float timeSignatureConfidence;
  private final int key;
  private final float keyConfidence;
  private final int mode;
  private final float modeConfidence;
  private final String codeString;
  private final float codeVersion;
  private final String echoprintString;
  private final float echoprintVersion;
  private final String synchString;
  private final float synchVersion;
  private final String rythmString;
  private final float rythmVersion;

  private AudioAnalysisTrack(final AudioAnalysisTrack.Builder builder) {
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

  public long getNumSamples() {
    return numSamples;
  }

  public float getDuration() {
    return duration;
  }

  public String getSampleMd5() {
    return sampleMd5;
  }

  public int getOffsetSeconds() {
    return offsetSeconds;
  }

  public int getWindowSeconds() {
    return windowSeconds;
  }

  public long getAnalysisSampleRate() {
    return analysisSampleRate;
  }

  public int getAnalysisChannels() {
    return analysisChannels;
  }

  public float getEndOfFadeIn() {
    return endOfFadeIn;
  }

  public float getStartOfFadeOut() {
    return startOfFadeOut;
  }

  public float getLoudness() {
    return loudness;
  }

  public float getTempo() {
    return tempo;
  }

  public float getTempoConfidence() {
    return tempoConfidence;
  }

  public int getTimeSignature() {
    return timeSignature;
  }

  public float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  public int getKey() {
    return key;
  }

  public float getKeyConfidence() {
    return keyConfidence;
  }

  public int getMode() {
    return mode;
  }

  public float getModeConfidence() {
    return modeConfidence;
  }

  public String getCodeString() {
    return codeString;
  }

  public float getCodeVersion() {
    return codeVersion;
  }

  public String getEchoprintString() {
    return echoprintString;
  }

  public float getEchoprintVersion() {
    return echoprintVersion;
  }

  public String getSynchString() {
    return synchString;
  }

  public float getSynchVersion() {
    return synchVersion;
  }

  public String getRythmString() {
    return rythmString;
  }

  public float getRythmVersion() {
    return rythmVersion;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private long numSamples;
    private float duration;
    private String sampleMd5;
    private int offsetSeconds;
    private int windowSeconds;
    private long analysisSampleRate;
    private int analysisChannels;
    private float endOfFadeIn;
    private float startOfFadeOut;
    private float loudness;
    private float tempo;
    private float tempoConfidence;
    private int timeSignature;
    private float timeSignatureConfidence;
    private int key;
    private float keyConfidence;
    private int mode;
    private float modeConfidence;
    private String codeString;
    private float codeVersion;
    private String echoprintString;
    private float echoprintVersion;
    private String synchString;
    private float synchVersion;
    private String rythmString;
    private float rythmVersion;

    public Builder setNumSamples(long numSamples) {
      this.numSamples = numSamples;
      return this;
    }

    public Builder setDuration(float duration) {
      this.duration = duration;
      return this;
    }

    public Builder setSampleMd5(String sampleMd5) {
      this.sampleMd5 = sampleMd5;
      return this;
    }

    public Builder setOffsetSeconds(int offsetSeconds) {
      this.offsetSeconds = offsetSeconds;
      return this;
    }

    public Builder setWindowSeconds(int windowSeconds) {
      this.windowSeconds = windowSeconds;
      return this;
    }

    public Builder setAnalysisSampleRate(long analysisSampleRate) {
      this.analysisSampleRate = analysisSampleRate;
      return this;
    }

    public Builder setAnalysisChannels(int analysisChannels) {
      this.analysisChannels = analysisChannels;
      return this;
    }

    public Builder setEndOfFadeIn(float endOfFadeIn) {
      this.endOfFadeIn = endOfFadeIn;
      return this;
    }

    public Builder setStartOfFadeOut(float startOfFadeOut) {
      this.startOfFadeOut = startOfFadeOut;
      return this;
    }

    public Builder setLoudness(float loudness) {
      this.loudness = loudness;
      return this;
    }

    public Builder setTempo(float tempo) {
      this.tempo = tempo;
      return this;
    }

    public Builder setTempoConfidence(float tempoConfidence) {
      this.tempoConfidence = tempoConfidence;
      return this;
    }

    public Builder setTimeSignature(int timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    public Builder setTimeSignatureConfidence(float timeSignatureConfidence) {
      this.timeSignatureConfidence = timeSignatureConfidence;
      return this;
    }

    public Builder setKey(int key) {
      this.key = key;
      return this;
    }

    public Builder setKeyConfidence(float keyConfidence) {
      this.keyConfidence = keyConfidence;
      return this;
    }

    public Builder setMode(int mode) {
      this.mode = mode;
      return this;
    }

    public Builder setModeConfidence(float modeConfidence) {
      this.modeConfidence = modeConfidence;
      return this;
    }

    public Builder setCodeString(String codeString) {
      this.codeString = codeString;
      return this;
    }

    public Builder setCodeVersion(float codeVersion) {
      this.codeVersion = codeVersion;
      return this;
    }

    public Builder setEchoprintString(String echoprintString) {
      this.echoprintString = echoprintString;
      return this;
    }

    public Builder setEchoprintVersion(float echoprintVersion) {
      this.echoprintVersion = echoprintVersion;
      return this;
    }

    public Builder setSynchString(String synchString) {
      this.synchString = synchString;
      return this;
    }

    public Builder setSynchVersion(float synchVersion) {
      this.synchVersion = synchVersion;
      return this;
    }

    public Builder setRythmString(String rythmString) {
      this.rythmString = rythmString;
      return this;
    }

    public Builder setRythmVersion(float rythmVersion) {
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
              .setAnalysisChannels(jsonObject.get("analysis_channels").getAsInt())
              .setAnalysisSampleRate(jsonObject.get("analysis_sample_rate").getAsLong())
              .setCodeString(jsonObject.get("code_string").getAsString())
              .setCodeVersion(jsonObject.get("code_version").getAsFloat())
              .setDuration(jsonObject.get("duration").getAsFloat())
              .setEchoprintString(jsonObject.get("echoprintstring").getAsString())
              .setEchoprintVersion(jsonObject.get("echoprint_version").getAsFloat())
              .setEndOfFadeIn(jsonObject.get("end_of_face_in").getAsFloat())
              .setKey(jsonObject.get("key").getAsInt())
              .setKeyConfidence(jsonObject.get("key_confidence").getAsFloat())
              .setLoudness(jsonObject.get("loudness").getAsFloat())
              .setMode(jsonObject.get("mode").getAsInt())
              .setModeConfidence(jsonObject.get("mode_confidence").getAsFloat())
              .setNumSamples(jsonObject.get("num_samples").getAsLong())
              .setOffsetSeconds(jsonObject.get("offset_seconds").getAsInt())
              .setRythmString(jsonObject.get("rythmstring").getAsString())
              .setRythmVersion(jsonObject.get("rythm_version").getAsFloat())
              .setSampleMd5(jsonObject.get("sample_md5").getAsString())
              .setStartOfFadeOut(jsonObject.get("start_of_fade_out").getAsFloat())
              .setSynchString(jsonObject.get("synchstring").getAsString())
              .setSynchVersion(jsonObject.get("synch_version").getAsFloat())
              .setTempo(jsonObject.get("tempo").getAsFloat())
              .setTempoConfidence(jsonObject.get("tempo_confidence").getAsFloat())
              .setTimeSignature(jsonObject.get("time_sidnature").getAsInt())
              .setTimeSignatureConfidence(jsonObject.get("time_signature_confidence").getAsFloat())
              .setWindowSeconds(jsonObject.get("windows_seconds").getAsInt())
              .build();
    }
  }
}
