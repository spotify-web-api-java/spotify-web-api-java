package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.enums.Modality;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#audio-features-object">
 * Audio Feature objects</a> by building instances from this class.
 */
public class AudioFeatures extends AbstractModelObject {
  /** A confidence measure from 0.0 to 1.0 of whether the track is acoustic. */
  private final Float acousticness;
  /** A URL to access the full audio analysis of this track. */
  private final String analysisUrl;
  /** Danceability describes how suitable a track is for dancing. */
  private final Float danceability;
  /** The duration of the track in milliseconds. */
  private final Integer durationMs;
  /** Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and power. */
  private final Float energy;
  /** The Spotify ID for the track. */
  private final String id;
  /** Predicts whether a track contains no vocals. */
  private final Float instrumentalness;
  /** The key the track is in (0 = C, 1 = C♯/D♭, 2 = D, etc.). */
  private final Integer key;
  /** Detects the presence of an audience in the recording. */
  private final Float liveness;
  /** The overall loudness of a track in decibels (dB). */
  private final Float loudness;
  /** Mode indicates the modality (major or minor) of a track. */
  private final Modality mode;
  /** Speechiness detects the presence of spoken words in a track. */
  private final Float speechiness;
  /** The overall estimated tempo of a track in beats per minute (BPM). */
  private final Float tempo;
  /** An estimated overall time signature of a track. */
  private final Integer timeSignature;
  /** A link to the Web API endpoint providing full details of the track. */
  private final String trackHref;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the track. */
  private final String uri;
  /** A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. */
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
   * @see AudioAnalysis
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a>
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a>
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
  public String toString() {
    return "AudioFeatures(acousticness=" + acousticness + ", analysisUrl=" + analysisUrl + ", danceability="
        + danceability + ", durationMs=" + durationMs + ", energy=" + energy + ", id=" + id + ", instrumentalness="
        + instrumentalness + ", key=" + key + ", liveness=" + liveness + ", loudness=" + loudness + ", mode=" + mode
        + ", speechiness=" + speechiness + ", tempo=" + tempo + ", timeSignature=" + timeSignature + ", trackHref="
        + trackHref + ", type=" + type + ", uri=" + uri + ", valence=" + valence + ")";
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

    public Builder() {
      super();
    }

    public Builder setAcousticness(Float acousticness) {
      this.acousticness = acousticness;
      return this;
    }

    public Builder setAnalysisUrl(String analysisUrl) {
      this.analysisUrl = analysisUrl;
      return this;
    }

    public Builder setDanceability(Float danceability) {
      this.danceability = danceability;
      return this;
    }

    public Builder setDurationMs(Integer durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    public Builder setEnergy(Float energy) {
      this.energy = energy;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setInstrumentalness(Float instrumentalness) {
      this.instrumentalness = instrumentalness;
      return this;
    }

    public Builder setKey(Integer key) {
      this.key = key;
      return this;
    }

    public Builder setLiveness(Float liveness) {
      this.liveness = liveness;
      return this;
    }

    public Builder setLoudness(Float loudness) {
      this.loudness = loudness;
      return this;
    }

    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    public Builder setSpeechiness(Float speechiness) {
      this.speechiness = speechiness;
      return this;
    }

    public Builder setTempo(Float tempo) {
      this.tempo = tempo;
      return this;
    }

    public Builder setTimeSignature(Integer timeSignature) {
      this.timeSignature = timeSignature;
      return this;
    }

    public Builder setTrackHref(String trackHref) {
      this.trackHref = trackHref;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

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

    public JsonUtil() {
      super();
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AudioFeatures that = (AudioFeatures) o;
    return Objects.equals(analysisUrl, that.analysisUrl) && Objects.equals(id, that.id) &&
      Objects.equals(key, that.key) && Objects.equals(uri, that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(analysisUrl, id, key, uri);
  }
}
