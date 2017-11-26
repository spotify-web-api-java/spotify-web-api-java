package com.wrapper.spotify.objects;

import java.util.List;

public class Recommendations {

  private RecommendationsSeed seeds;
  private List<TrackSimplified> tracks;

  public RecommendationsSeed getSeeds() {
    return seeds;
  }

  public void setSeeds(RecommendationsSeed seeds) {
    this.seeds = seeds;
  }

  public List<TrackSimplified> getTracks() {
    return tracks;
  }

  public void setTracks(List<TrackSimplified> tracks) {
    this.tracks = tracks;
  }
}
