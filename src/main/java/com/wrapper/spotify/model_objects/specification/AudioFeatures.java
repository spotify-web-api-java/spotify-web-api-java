package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about audio features by building instances from this class.
 */
public class AudioFeatures extends AbstractModelObject {
  private final double acousticness;
  private final String analysisUrl;
  private final double danceability;
  private final Integer durationMs;
  private final double energy;
  private final String id;
  private final double instrumentalness;
  private final Integer key;
  private final double liveness;
  private final double loudness;
  private final Modality mode;
  private final double speechiness;
  private final double tempo;
  private final Integer timeSignature;
  private final String trackHref;
  private final ModelObjectType type;
  private final String uri;
  private final double valence;

  private AudioFeatures(final AudioFeatures.Builder builder) {
    super(builder);

    this.acousticness = builder.acousticness;
    this.analysisUrl = builder.analysisUrl;
    this.danceability = builder.danceability;
    this.durationMs = builder.durationMs;
    this.energy = builder.energy;
    this.id = builder.id;
    this.instrumentalness = builder.instrumentalness;
    this.key = builder.key;
    this.liveness = builder.liveness;
    this.loudness = builder.loudness;
    this.mode = builder.mode;
    this.speechiness = builder.speechiness;
    this.tempo = builder.tempo;
    this.timeSignature = builder.timeSignature;
    this.trackHref = builder.trackHref;
    this.type = builder.type;
    this.uri = builder.uri;
    this.valence = builder.valence;
  }

  /**
   * Get the acousticness of a track as a value between 0.0 and 1.0.<br>
   * The higher the value, the higher the chance a track is acoustic.
   *
   * @return Acousticness value.
   */
  public double getAcousticness() {
    return acousticness;
  }

  /**
   * Get the api url to a full audio analysis. It contains additional information to
   * audio feature objects.
   *
   * @return Audio analysis api url.
   */
  public String getAnalysisUrl() {
    return analysisUrl;
  }

  /**
   * Get the danceability of a track as a value between 0.0 and 1.0.<br>
   * The danceability depends on factors like tempo and rhythm stability. Higher is better.
   *
   * @return Danceability value.
   */
  public double getDanceability() {
    return danceability;
  }

  /**
   * Get the duration of a track in milliseconds.
   *
   * @return Track duration.
   */
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Get the energy of a track as a value between 0.0 and 1.0.<br>
   * The energetic value of a track depends on factors like speed and loudness. Fast
   * and loud tracks feel more energetic than slow and quiet tracks.
   *
   * @return Energetic value.
   */
  public double getEnergy() {
    return energy;
  }

  /**
   * Get the Spotify id of a track.
   *
   * @return Spotify track id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the instrumentalness of a track as a value between 0.0 and 1.0.<br>
   * The higher the value, the higher the chance a track contains no vocals.
   *
   * @return Instrumentalness value.
   * @see #getSpeechiness()
   * @see #getSpeechiness()
   */
  public double getInstrumentalness() {
    return instrumentalness;
  }

  /**
   * Get the main key of the track.
   *
   * @return Main key.
   * @see <a href="https://en.wikipedia.org/wiki/Pitch_class">Wikipedia: Pitch class notation</a>
   * @see <a href="https://en.wikipedia.org/wiki/Pitch_class">Wikipedia: Pitch class notation</a>
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Get the liveness of a track as a value between 0.0 and 1.0.<br>
   * The liveness depends on ambient sounds like the presence of an audience. The higher
   * the value, the higher the chance a track was performed live.
   *
   * @return Liveness value.
   */
  public double getLiveness() {
    return liveness;
  }

  /**
   * Get the average loudness of a track. These values have mostly a range between
   * -60 and 0 decibels.
   *
   * @return Loudness value.
   */
  public double getLoudness() {
    return loudness;
  }

  /**
   * Get the modality of a track. (either "major" or "minor")
   *
   * @return Modality type.
   * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (music)</a>
   * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (music)</a>
   */
  public Modality getMode() {
    return mode;
  }

  /**
   * Get the speechiness of a track as a value between 0.0 and 1.0.<br>
   * The higher the value, the higher the chance a track only consists of spoken words.
   *
   * @return Speechiness value.
   * @see #getInstrumentalness()
   * @see #getInstrumentalness()
   */
  public double getSpeechiness() {
    return speechiness;
  }

  /**
   * Get the estimated tempo of a track in beats per minute.
   *
   * @return Tempo value.
   */
  public double getTempo() {
    return tempo;
  }

  /**
   * Get the estimated overall time signature of a track. The time signature (or meter)
   * is the number of beats in a bar. <br>
   * Example: A Viennese waltz has a three-quarters beat, so this method would
   * return the value 3 in this case.
   *
   * @return Time signature value.
   */
  public Integer getTimeSignature() {
    return timeSignature;
  }

  /**
   * Get the full Spotify API endpoint url of a track.
   *
   * @return A Spotify API endpoint url.
   */
  public String getTrackHref() {
    return trackHref;
  }

  /**
   * Get the model object type. In this case "audio_features".
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the Spotify uri of a track.
   *
   * @return Spotify album uri.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Get the valence of a track as a value between 0.0 and 1.0.<br>
   * A track with a high valence sounds more positive (happy, cheerful, euphoric)
   * like a track with a low valence. (sad, depressed, angry)
   *
   * @return Valence value.
   */
  public double getValence() {
    return valence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building audio feature instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Float acousticness;
    private String analysisUrl;
    private Float danceability;
    private Integer durationMs;
    private Float energy;
    private String id;
    private Float instrumentalness;
    private Integer key;
    private Float liveness;
    private Float loudness;
    private Modality mode;
    private Float speechiness;
    private Float tempo;
    private Integer timeSignature;
    private String trackHref;
    private ModelObjectType type;
    private String uri;
    private Float valence;

    /**
     * Set the acousticness of the audio features object to be built.
     *
     * @param acousticness Acousticness value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setAcousticness(Float acousticness) {
      this.acousticness = acousticness;
      return this;
    }

    /**
     * Set the audio analysis url of the audio features object to be built.
     *
     * @param analysisUrl Audio analysis api url.
     * @return A builder object.
     */
    public Builder setAnalysisUrl(String analysisUrl) {
      this.analysisUrl = analysisUrl;
      return this;
    }

    /**
     * Set the danceability of the audio features object to be built.
     *
     * @param danceability Danceability value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setDanceability(Float danceability) {
      this.danceability = danceability;
      return this;
    }

    /**
     * Set the duration in milliseconds of the audio features object to be built.
     *
     * @param durationMs Duration in milliseconds.
     * @return A builder object.
     */
    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set the energy of the audio features object to be built.
     *
     * @param energy Energy value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setEnergy(Float energy) {
      this.energy = energy;
      return this;
    }

    /**
     * Set the Spotify track id of the audio features object to be built.
     *
     * @param id Spotify track id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the instrumentalness of the audio features object to be built.
     *
     * @param instrumentalness Instrumentalness value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setInstrumentalness(Float instrumentalness) {
      this.instrumentalness = instrumentalness;
      return this;
    }

    /**
     * Set the key of the audio features object to be built.
     *
     * @param key Track key.
     * @return A builder object.
     */
    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    /**
     * Set the liveness of the audio features object to be built.
     *
     * @param liveness Liveness value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setLiveness(Float liveness) {
      this.liveness = liveness;
      return this;
    }

    /**
     * Set the loudness of the audio features object to be built.
     *
     * @param loudness Loudness value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    /**
     * Set the mode of the audio features object to be built.
     *
     * @param mode Track mode.
     * @return A builder object.
     */
    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    /**
     * Set the speechiness of the audio features object to be built.
     *
     * @param speechiness Speechiness value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setSpeechiness(Float speechiness) {
      this.speechiness = speechiness;
      return this;
    }

    /**
     * Set the tempo of the audio features object to be built.
     *
     * @param tempo Tempo value in beats per minute.
     * @return A builder object.
     */
    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * Set the time signature of the audio features object to be built.
     *
     * @param timeSignature Time signature of the track.
     * @return A builder object.
     */
    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    /**
     * Set the track href to the Web API endpoint of the audio features object to be built.
     *
     * @param trackHref Spotify api endpoint url.
     * @return A builder object.
     */
    public Builder setTrackHref(String trackHref) {
      this.trackHref = trackHref;
      return this;
    }

    /**
     * Set the type of the model object. In this case "audio_features".
     *
     * @param type The model object type.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify uri of the audio feature objects track to be built.
     *
     * @param uri The Spotify album uri.
     * @return A builder object.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Set the valence of the audio features object to be built.
     *
     * @param valence Valence value between 0.0 and 1.0.
     * @return A builder object.
     */
    public Builder setValence(Float valence) {
      this.valence = valence;
      return this;
    }

    @Override
    public AudioFeatures build() {
      return new AudioFeatures(this);
    }
  }

  /**
   * JsonUtil class for building audio feature instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioFeatures> {
    public AudioFeatures createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioFeatures.Builder()
              .setAcousticness(
                      hasAndNotNull(jsonObject, "acousticness")
                              ? jsonObject.get("acousticness").getAsFloat()
                              : null)
              .setAnalysisUrl(
                      hasAndNotNull(jsonObject, "analysis_url")
                              ? jsonObject.get("analysis_url").getAsString()
                              : null)
              .setDanceability(
                      hasAndNotNull(jsonObject, "danceability")
                              ? jsonObject.get("danceability").getAsFloat()
                              : null)
              .setDurationMs(
                      hasAndNotNull(jsonObject, "duration_ms")
                              ? jsonObject.get("duration_ms").getAsInt()
                              : null)
              .setEnergy(
                      hasAndNotNull(jsonObject, "energy")
                              ? jsonObject.get("energy").getAsFloat()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setInstrumentalness(
                      hasAndNotNull(jsonObject, "instrumentalness")
                              ? jsonObject.get("instrumentalness").getAsFloat()
                              : null)
              .setKey(
                      hasAndNotNull(jsonObject, "key")
                              ? jsonObject.get("key").getAsInt()
                              : null)
              .setLiveness(
                      hasAndNotNull(jsonObject, "liveness")
                              ? jsonObject.get("liveness").getAsFloat()
                              : null)
              .setLoudness(
                      hasAndNotNull(jsonObject, "loudness")
                              ? jsonObject.get("loudness").getAsFloat()
                              : null)
              .setMode(
                      hasAndNotNull(jsonObject, "mode")
                              ? Modality.valueOf(
                              jsonObject.get("mode").getAsString().toUpperCase())
                              : null)
              .setSpeechiness(
                      hasAndNotNull(jsonObject, "speechiness")
                              ? jsonObject.get("speechiness").getAsFloat()
                              : null)
              .setTempo(
                      hasAndNotNull(jsonObject, "tempo")
                              ? jsonObject.get("tempo").getAsFloat()
                              : null)
              .setTimeSignature(
                      hasAndNotNull(jsonObject, "time_signature")
                              ? jsonObject.get("time_signature").getAsInt()
                              : null)
              .setTrackHref(
                      hasAndNotNull(jsonObject, "track_href")
                              ? jsonObject.get("track_href").getAsString()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.valueOf(
                              jsonObject.get("type").getAsString().toUpperCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .setValence(
                      hasAndNotNull(jsonObject, "valence")
                              ? jsonObject.get("valence").getAsFloat()
                              : null)
              .build();
    }
  }
}
