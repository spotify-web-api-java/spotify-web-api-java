package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Audio Analysis Section objects by creating instances from this class. <br>
 * Sections are defined by large variations in rhythm or timbre, e.g. chorus, verse, bridge, guitar solo, etc. Each
 * section contains its own descriptions of tempo, key, mode, time_signature, and loudness.
 */
public class AudioAnalysisSection extends AbstractModelObject {
  private final AudioAnalysisMeasure measure;
  private final Float loudness;
  private final Float tempo;
  private final Float tempoConfidence;
  private final Integer key;
  private final Float keyConfidence;
  private final Modality mode;
  private final Float modeConfidence;
  private final Integer timeSignature;
  private final Float timeSignatureConfidence;

  private AudioAnalysisSection(final Builder builder) {
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

  /**
   * Get the measure of the audio analysis section object. This measure contains the start point, duration and
   * confidence of the section.
   *
   * @return The measure of the audio analysis section object.
   */
  public AudioAnalysisMeasure getMeasure() {
    return measure;
  }

  /**
   * Get the (average) loudness of the section in decibels.
   *
   * @return The loudness of the section.
   */
  public Float getLoudness() {
    return loudness;
  }

  /**
   * Get the (estimated) tempo of the section in beats per minute.
   *
   * @return The tempo of the section.
   */
  public Float getTempo() {
    return tempo;
  }

  /**
   * Get the tempo confidence of the section.
   *
   * @return The tempo confidence of the section.
   */
  public Float getTempoConfidence() {
    return tempoConfidence;
  }

  /**
   * Get the main key of the section.
   *
   * @return Main key of the section.
   * @see <a href="https://en.wikipedia.org/wiki/Pitch_class">Wikipedia: Pitch class notation</a>
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Get the key confidence of the section.
   *
   * @return The key confidence of the section.
   */
  public Float getKeyConfidence() {
    return keyConfidence;
  }

  /**
   * Get the modality of the section. (either "major" or "minor")
   *
   * @return The modality type of the section.
   * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (music)</a>
   */
  public Modality getMode() {
    return mode;
  }

  /**
   * Get the modality confidence of the section.
   *
   * @return The modality confidence of the section.
   */
  public Float getModeConfidence() {
    return modeConfidence;
  }

  /**
   * Get the estimated overall time signature of the section. The time signature (or meter) is the number of beats in a
   * bar. <br>
   * Example: A Viennese waltz has a three-quarters beat, so this method would return the value 3 in this case.
   *
   * @return Time signature value of the section.
   */
  public Integer getTimeSignature() {
    return timeSignature;
  }

  /**
   * Get the time signature confidence of the section.
   *
   * @return The time signature confidence of the section.
   */
  public Float getTimeSignatureConfidence() {
    return timeSignatureConfidence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysisSection} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure measure;
    private Float loudness;
    private Float tempo;
    private Float tempoConfidence;
    private Integer key;
    private Float keyConfidence;
    private Modality mode;
    private Float modeConfidence;
    private Integer timeSignature;
    private Float timeSignatureConfidence;

    /**
     * The measure setter.
     *
     * @param measure The measure of the audio analysis section object.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setMeasure(AudioAnalysisMeasure measure) {
      this.measure = measure;
      return this;
    }

    /**
     * The loudness setter.
     *
     * @param loudness The loudness of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    /**
     * The tempo setter.
     *
     * @param tempo The tempo of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * The tempo confidence setter.
     *
     * @param tempoConfidence The tempo confidence of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setTempoConfidence(Float tempoConfidence) {
      this.tempoConfidence = tempoConfidence;
      return this;
    }

    /**
     * The key setter.
     *
     * @param key Main key of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    /**
     * The key confidence setter.
     *
     * @param keyConfidence The key confidence of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setKeyConfidence(Float keyConfidence) {
      this.keyConfidence = keyConfidence;
      return this;
    }

    /**
     * The mode setter.
     *
     * @param mode The modality type of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    /**
     * The mode confidence setter.
     *
     * @param modeConfidence The modality confidence of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setModeConfidence(Float modeConfidence) {
      this.modeConfidence = modeConfidence;
      return this;
    }

    /**
     * The time signature setter.
     *
     * @param timeSignature Time signature value of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    /**
     * The time signature confidence setter.
     *
     * @param timeSignatureConfidence The time signature confidence of the section.
     * @return An {@link AudioAnalysisSection.Builder}.
     */
    public Builder setTimeSignatureConfidence(Float timeSignatureConfidence) {
      this.timeSignatureConfidence = timeSignatureConfidence;
      return this;
    }

    @Override
    public AudioAnalysisSection build() {
      return new AudioAnalysisSection(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysisSection} instances.
   */
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
                      new AudioAnalysisMeasure.JsonUtil().createModelObject(jsonObject))
              .setMode(
                      hasAndNotNull(jsonObject, "type")
                              ? Modality.keyOf(
                              jsonObject.get("mode").getAsInt())
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
