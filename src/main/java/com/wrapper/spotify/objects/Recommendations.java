package com.wrapper.spotify.objects;

import java.util.List;

public class Recommendations extends AbstractModelObject {
  private final RecommendationsSeed seeds;
  private final List<TrackSimplified> tracks;

  private Recommendations(final Recommendations.Builder builder) {
    super(builder);

    this.seeds = builder.seeds;
    this.tracks = builder.tracks;
  }

  public RecommendationsSeed getSeeds() {
    return seeds;
  }

  public List<TrackSimplified> getTracks() {
    return tracks;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<Recommendations.Builder> {
    private RecommendationsSeed seeds;
    private List<TrackSimplified> tracks;

    public Builder setSeeds(RecommendationsSeed seeds) {
      this.seeds = seeds;
      return this;
    }

    public Builder setTracks(List<TrackSimplified> tracks) {
      this.tracks = tracks;
      return this;
    }

    @Override
    public Recommendations build() {
      return new Recommendations(this);
    }
  }
}
