package com.wrapper.spotify.models;

public class AudioFeature {
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
  private ObjectType type = ObjectType.AUDIO_FEATURES;
  private String uri;
  private double valence;

  public double getAcousticness() {
    return acousticness;
  }

  public void setAcousticness(double acousticness) {
    this.acousticness = acousticness;
  }

  public String getAnalysisUrl() {
    return analysisUrl;
  }

  public void setAnalysisUrl(String analysisUrl) {
    this.analysisUrl = analysisUrl;
  }

  public double getDanceability() {
    return danceability;
  }

  public void setDanceability(double danceability) {
    this.danceability = danceability;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(int durationMs) {
    this.durationMs = durationMs;
  }

  public double getEnergy() {
    return energy;
  }

  public void setEnergy(double energy) {
    this.energy = energy;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getInstrumentalness() {
    return instrumentalness;
  }

  public void setInstrumentalness(double instrumentalness) {
    this.instrumentalness = instrumentalness;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public double getLiveness() {
    return liveness;
  }

  public void setLiveness(double liveness) {
    this.liveness = liveness;
  }

  public double getLoudness() {
    return loudness;
  }

  public void setLoudness(double loudness) {
    this.loudness = loudness;
  }

  public Modality getMode() {
    return mode;
  }

  public void setMode(Modality mode) {
    this.mode = mode;
  }

  public double getSpeechiness() {
    return speechiness;
  }

  public void setSpeechiness(double speechiness) {
    this.speechiness = speechiness;
  }

  public double getTempo() {
    return tempo;
  }

  public void setTempo(double tempo) {
    this.tempo = tempo;
  }

  public int getTimeSignature() {
    return timeSignature;
  }

  public void setTimeSignature(int timeSignature) {
    this.timeSignature = timeSignature;
  }

  public String getTrackHref() {
    return trackHref;
  }

  public void setTrackHref(String trackHref) {
    this.trackHref = trackHref;
  }

  public ObjectType getType() {
    return type;
  }

  public void setType(ObjectType type) {
    this.type = type;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public double getValence() {
    return valence;
  }

  public void setValence(double valence) {
    this.valence = valence;
  }
}
