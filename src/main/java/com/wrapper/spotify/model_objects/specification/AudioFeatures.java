package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#audio-features-object">
 * Audio Feature objects</a> by building instances from this class.
 */
public class AudioFeatures extends AbstractModelObject {
  private final Float acousticness;
  private final String analysisUrl;
  private final Float danceability;
  private final Integer durationMs;
  private final Float energy;
  private final String id;
  private final Float instrumentalness;
  private final Integer key;
  private final Float liveness;
  private final Float loudness;
  private final Modality mode;
  private final Float speechiness;
  private final Float tempo;
  private final Integer timeSignature;
  private final String trackHref;
  private final ModelObjectType type;
  private final String uri;
  private final Float valence;

  private AudioFeatures(final Builder builder) {
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
   * Get the acousticness of the track as a value between 0.0 and 1.0. <br>
   * The higher the value, the higher the chance the track is acoustic.
   *
   * @return Acousticness value.
   */
  public Float getAcousticness() {
    return acousticness;
  }

  /**
   * Get the Spotify Web API endpoint URL for a full audio analysis. An audio analysis contains additional information
   * to audio feature objects.
   *
   * @return Spotify Web API endpoint URL.
   * @see com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis
   */
  public String getAnalysisUrl() {
    return analysisUrl;
  }

  /**
   * Get the danceability of the track as a value between 0.0 and 1.0. <br>
   * The danceability depends on factors like tempo and rhythm stability. Higher is better.
   *
   * @return Danceability value.
   */
  public Float getDanceability() {
    return danceability;
  }

  /**
   * Get the duration of the track in milliseconds.
   *
   * @return Track duration.
   */
  public Integer getDurationMs() {
    return durationMs;
  }

  /**
   * Get the energy of the track as a value between 0.0 and 1.0.<br>
   * The energetic value of the track depends on factors like speed and loudness. Fast and loud tracks feel more
   * energetic than slow and quiet tracks.
   *
   * @return Energetic value.
   */
  public Float getEnergy() {
    return energy;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a>
   * of the track.
   *
   * @return Spotify track ID.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the instrumentalness of the track as a value between 0.0 and 1.0. <br>
   * The higher the value, the higher the chance the track contains no vocals.
   *
   * @return Instrumentalness value.
   * @see #getSpeechiness()
   */
  public Float getInstrumentalness() {
    return instrumentalness;
  }

  /**
   * Get the main key of the track.
   *
   * @return Main key.
   * @see <a href="https://en.wikipedia.org/wiki/Pitch_class">Wikipedia: Pitch class notation</a>
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Get the liveness of the track as a value between 0.0 and 1.0.<br>
   * The liveness depends on ambient sounds like the presence of an audience. The higher the value, the higher the
   * chance the track was performed live.
   *
   * @return Liveness value.
   */
  public Float getLiveness() {
    return liveness;
  }

  /**
   * Get the average loudness of the track. These values have mostly a range between -60 and 0 decibels.
   *
   * @return Loudness value.
   */
  public Float getLoudness() {
    return loudness;
  }

  /**
   * Get the modality of the track. (either "major" or "minor")
   *
   * @return {@link Modality} type.
   * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (music)</a>
   */
  public Modality getMode() {
    return mode;
  }

  /**
   * Get the speechiness of the track as a value between 0.0 and 1.0. <br>
   * The higher the value, the higher the chance the track only consists of spoken words.
   *
   * @return Speechiness value.
   * @see #getInstrumentalness()
   */
  public Float getSpeechiness() {
    return speechiness;
  }

  /**
   * Get the estimated tempo of the track in beats per minute.
   *
   * @return Tempo value.
   */
  public Float getTempo() {
    return tempo;
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
   * Get the  Spotify Web API endpoint URL of the track.
   *
   * @return A Spotify Web API endpoint URL.
   */
  public String getTrackHref() {
    return trackHref;
  }

  /**
   * Get the model object type. In this case "audio_features".
   *
   * @return A {@link ModelObjectType}.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a>
   * of the track.
   *
   * @return Spotify track URI.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Get the valence of the track as a value between 0.0 and 1.0.<br>
   * A track with a high valence sounds more positive (happy, cheerful, euphoric) like the track with a low valence.
   * (sad, depressed, angry)
   *
   * @return Valence value.
   */
  public Float getValence() {
    return valence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioFeatures} instances.
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
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setAcousticness(Float acousticness) {
      this.acousticness = acousticness;
      return this;
    }

    /**
     * Set the Spotify Web API audio analysis endpoint URL of the audio features object to be built.
     *
     * @param analysisUrl Spotify Web API endpoint URL.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setAnalysisUrl(String analysisUrl) {
      this.analysisUrl = analysisUrl;
      return this;
    }

    /**
     * Set the danceability of the audio features object to be built.
     *
     * @param danceability Danceability value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setDanceability(Float danceability) {
      this.danceability = danceability;
      return this;
    }

    /**
     * Set the duration in milliseconds of the audio features object to be built.
     *
     * @param durationMs Duration in milliseconds.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    /**
     * Set the energy of the audio features object to be built.
     *
     * @param energy Energy value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setEnergy(Float energy) {
      this.energy = energy;
      return this;
    }

    /**
     * Set the Spotify track ID of the audio features object to be built.
     *
     * @param id Spotify track ID.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the instrumentalness of the audio features object to be built.
     *
     * @param instrumentalness Instrumentalness value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setInstrumentalness(Float instrumentalness) {
      this.instrumentalness = instrumentalness;
      return this;
    }

    /**
     * Set the key of the audio features object to be built.
     *
     * @param key Track key.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    /**
     * Set the liveness of the audio features object to be built.
     *
     * @param liveness Liveness value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setLiveness(Float liveness) {
      this.liveness = liveness;
      return this;
    }

    /**
     * Set the loudness of the audio features object to be built.
     *
     * @param loudness Loudness value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    /**
     * Set the mode of the audio features object to be built.
     *
     * @param mode Track mode.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    /**
     * Set the speechiness of the audio features object to be built.
     *
     * @param speechiness Speechiness value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setSpeechiness(Float speechiness) {
      this.speechiness = speechiness;
      return this;
    }

    /**
     * Set the tempo of the audio features object to be built.
     *
     * @param tempo Tempo value in beats per minute.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * Set the time signature of the audio features object to be built.
     *
     * @param timeSignature Time signature of the track.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    /**
     * Set the track href to the Spotify Web API endpoint of the audio features object to be built.
     *
     * @param trackHref Spotify Web API endpoint URL.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setTrackHref(String trackHref) {
      this.trackHref = trackHref;
      return this;
    }

    /**
     * Set the type of the model object. In this case "audio_features".
     *
     * @param type The {@link ModelObjectType}.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a>
     * of the audio feature objects track to be built.
     *
     * @param uri The Spotify track URI.
     * @return An {@link AudioFeatures.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Set the valence of the audio features object to be built.
     *
     * @param valence Valence value between 0.0 and 1.0.
     * @return An {@link AudioFeatures.Builder}.
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
   * JsonUtil class for building {@link AudioFeatures} instances.
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
                              ? Modality.keyOf(
                              jsonObject.get("mode").getAsInt())
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
                              ? ModelObjectType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
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
