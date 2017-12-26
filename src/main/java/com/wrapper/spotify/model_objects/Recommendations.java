package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

public class Recommendations extends AbstractModelObject {
  private final RecommendationsSeed[] seeds;
  private final TrackSimplified[] tracks;

  private Recommendations(final Recommendations.Builder builder) {
    super(builder);

    this.seeds = builder.seeds;
    this.tracks = builder.tracks;
  }

  public RecommendationsSeed[] getSeeds() {
    return seeds;
  }

  public TrackSimplified[] getTracks() {
    return tracks;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private RecommendationsSeed[] seeds;
    private TrackSimplified[] tracks;

    public Builder setSeeds(RecommendationsSeed[] seeds) {
      this.seeds = seeds;
      return this;
    }

    public Builder setTracks(TrackSimplified[] tracks) {
      this.tracks = tracks;
      return this;
    }

    @Override
    public Recommendations build() {
      return new Recommendations(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Recommendations> {
    public Recommendations createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Recommendations.Builder()
              .setSeeds(new RecommendationsSeed.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("seeds")))
              .setTracks(new TrackSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("tracks")))
              .build();
    }
  }
}
