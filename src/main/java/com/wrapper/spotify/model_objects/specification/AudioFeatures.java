package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.Modality;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AudioFeature extends AbstractModelObject {
  private final double acousticness;
  private final String analysisUrl;
  private final double danceability;
  private final int durationMs;
  private final double energy;
  private final String id;
  private final double instrumentalness;
  private final int key;
  private final double liveness;
  private final double loudness;
  private final Modality mode;
  private final double speechiness;
  private final double tempo;
  private final int timeSignature;
  private final String trackHref;
  private final ModelObjectType type;
  private final String uri;
  private final double valence;

  private AudioFeature(final AudioFeature.Builder builder) {
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

  public double getAcousticness() {
    return acousticness;
  }

  public String getAnalysisUrl() {
    return analysisUrl;
  }

  public double getDanceability() {
    return danceability;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public double getEnergy() {
    return energy;
  }

  public String getId() {
    return id;
  }

  public double getInstrumentalness() {
    return instrumentalness;
  }

  public int getKey() {
    return key;
  }

  public double getLiveness() {
    return liveness;
  }

  public double getLoudness() {
    return loudness;
  }

  public Modality getMode() {
    return mode;
  }

  public double getSpeechiness() {
    return speechiness;
  }

  public double getTempo() {
    return tempo;
  }

  public int getTimeSignature() {
    return timeSignature;
  }

  public String getTrackHref() {
    return trackHref;
  }

  public ModelObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  public double getValence() {
    return valence;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private double acousticness;
    private String analysisUrl;
    private double danceability;
    private int durationMs;
    private double energy;
    private String id;
    private double instrumentalness;
    private int key;
    private double liveness;
    private double loudness;
    private Modality mode;
    private double speechiness;
    private double tempo;
    private int timeSignature;
    private String trackHref;
    private ModelObjectType type;
    private String uri;
    private double valence;

    public Builder setAcousticness(double acousticness) {
      this.acousticness = acousticness;
      return this;
    }

    public Builder setAnalysisUrl(String analysisUrl) {
      this.analysisUrl = analysisUrl;
      return this;
    }

    public Builder setDanceability(double danceability) {
      this.danceability = danceability;
      return this;
    }

    public Builder setDurationMs(int durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    public Builder setEnergy(double energy) {
      this.energy = energy;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setInstrumentalness(double instrumentalness) {
      this.instrumentalness = instrumentalness;
      return this;
    }

    public Builder setKey(int key) {
      this.key = key;
      return this;
    }

    public Builder setLiveness(double liveness) {
      this.liveness = liveness;
      return this;
    }

    public Builder setLoudness(double loudness) {
      this.loudness = loudness;
      return this;
    }

    public Builder setMode(Modality mode) {
      this.mode = mode;
      return this;
    }

    public Builder setSpeechiness(double speechiness) {
      this.speechiness = speechiness;
      return this;
    }

    public Builder setTempo(double tempo) {
      this.tempo = tempo;
      return this;
    }

    public Builder setTimeSignature(int timeSignature) {
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

    public Builder setValence(double valence) {
      this.valence = valence;
      return this;
    }

    @Override
    public AudioFeature build() {
      return new AudioFeature(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioFeature> {
    public AudioFeature createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioFeature.Builder()
              .setAcousticness(jsonObject.get("acousticness").getAsDouble())
              .setAnalysisUrl(jsonObject.get("analysis_url").getAsString())
              .setDanceability(jsonObject.get("danceability").getAsDouble())
              .setDurationMs(jsonObject.get("duration_ms").getAsInt())
              .setEnergy(jsonObject.get("energy").getAsDouble())
              .setId(jsonObject.get("id").getAsString())
              .setInstrumentalness(jsonObject.get("instrumentalness").getAsDouble())
              .setKey(jsonObject.get("key").getAsInt())
              .setLiveness(jsonObject.get("liveness").getAsDouble())
              .setLoudness(jsonObject.get("loudness").getAsDouble())
              .setMode(Modality.valueOf(jsonObject.get("mode").getAsString().toUpperCase()))
              .setSpeechiness(jsonObject.get("speechiness").getAsDouble())
              .setTempo(jsonObject.get("tempo").getAsDouble())
              .setTimeSignature(jsonObject.get("time_signature").getAsInt())
              .setTrackHref(jsonObject.get("track_href").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .setValence(jsonObject.get("valence").getAsDouble())
              .build();
    }
  }
}
