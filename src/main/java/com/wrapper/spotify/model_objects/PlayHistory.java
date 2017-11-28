package com.wrapper.spotify.model_objects;

import java.util.Date;

public class PlayHistory {

  private TrackSimplified track;
  private Date playedAt;
  private Context context;

  public TrackSimplified getTrack() {
    return track;
  }

  public void setTrack(TrackSimplified track) {
    this.track = track;
  }

  public Date getPlayedAt() {
    return playedAt;
  }

  public void setPlayedAt(Date playedAt) {
    this.playedAt = playedAt;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }
}
