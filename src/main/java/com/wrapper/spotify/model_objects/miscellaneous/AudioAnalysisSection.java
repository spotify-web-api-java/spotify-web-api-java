package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisSection extends AbstractModelObject {
  private final AudioAnalysisMeasure measure;
  private final float loudness;
  private final float tempo;
  private final float tempoConfidence;
  private final int key;
  private final float keyConfidence;
  private final int mode;
  private final float modeConfidence;
  private final int timeSignature;
  private final float timeSignatureConfidence;

  private AudioAnalysisSection(final AudioAnalysisSection.Builder builder) {
    super(builder);

    this.measure = builder.measure;
    this.loudness = builder.loudness;
    this.tempo = builder.tempo;
    this.tempoConfidence = builder.tempoConfidence;
    this.key = builder.key;
    this.keyConfidence = builder.keyConfidence;
    this.mode = builder.mode;
    this.modeConfidence = builder.modeConfidence;
    this.timeSignature = builder.timeSignature;
    this.timeSignatureConfidence = builder.timeSignatureConfidence;
  }

  public AudioAnalysisMeasure getMeasure() {
    return measure;
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

  public int getTimeSignature() {
    return timeSignature;
  }

  public float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure measure;
    private float loudness;
    private float tempo;
    private float tempoConfidence;
    private int key;
    private float keyConfidence;
    private int mode;
    private float modeConfidence;
    private int timeSignature;
    private float timeSignatureConfidence;

    public Builder setMeasure(AudioAnalysisMeasure measure) {
      this.measure = measure;
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

    public Builder setTimeSignature(int timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    public Builder setTimeSignatureConfidence(float timeSignatureConfidence) {
      this.timeSignatureConfidence = timeSignatureConfidence;
      return this;
    }

    @Override
    public AudioAnalysisSection build() {
      return new AudioAnalysisSection(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysisSection> {
    public AudioAnalysisSection createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysisSection.Builder()
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
              .setMeasure(
                      hasAndNotNull(jsonObject, "measure")
                              ? new AudioAnalysisMeasure.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("measure"))
                              : null)
              .setMode(
                      hasAndNotNull(jsonObject, "mode")
                              ? jsonObject.get("mode").getAsInt()
                              : null)
              .setModeConfidence(
                      hasAndNotNull(jsonObject, "mode_confidence")
                              ? jsonObject.get("mode_confidence").getAsFloat()
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
                      hasAndNotNull(jsonObject, "time_signature")
                              ? jsonObject.get("time_signature").getAsInt()
                              : null)
              .setTimeSignatureConfidence(
                      hasAndNotNull(jsonObject, "time_signature_confidence")
                              ? jsonObject.get("time_signature_confidence").getAsFloat()
                              : null)
              .build();
    }
  }
}
