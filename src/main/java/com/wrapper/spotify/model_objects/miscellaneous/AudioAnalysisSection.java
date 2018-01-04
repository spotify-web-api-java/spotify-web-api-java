package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioAnalysisSection extends AbstractModelObject {
  private final AudioAnalysisMeasure measure;
  private final Float loudness;
  private final Float tempo;
  private final Float tempoConfidence;
  private final Integer key;
  private final Float keyConfidence;
  private final Integer mode;
  private final Float modeConfidence;
  private final Integer timeSignature;
  private final Float timeSignatureConfidence;

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

  public Float getLoudness() {
    return loudness;
  }

  public Float getTempo() {
    return tempo;
  }

  public Float getTempoConfidence() {
    return tempoConfidence;
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

  public Integer getTimeSignature() {
    return timeSignature;
  }

  public Float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure measure;
    private Float loudness;
    private Float tempo;
    private Float tempoConfidence;
    private Integer key;
    private Float keyConfidence;
    private Integer mode;
    private Float modeConfidence;
    private Integer timeSignature;
    private Float timeSignatureConfidence;

    public Builder setMeasure(AudioAnalysisMeasure measure) {
      this.measure = measure;
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

    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    public Builder setTimeSignatureConfidence(Float timeSignatureConfidence) {
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
