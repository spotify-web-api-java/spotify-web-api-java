package com.wrapper.spotify.objects;

import java.util.Date;

public class PlayHistory extends AbstractModelObject {
  private final TrackSimplified track;
  private final Date playedAt;
  private final Context context;

  private PlayHistory(final PlayHistory.Builder builder) {
    super(builder);

    this.track = builder.track;
    this.playedAt = builder.playedAt;
    this.context = builder.context;
  }

  public TrackSimplified getTrack() {
    return track;
  }

  public Date getPlayedAt() {
    return playedAt;
  }

  public Context getContext() {
    return context;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<PlayHistory.Builder> {
    private TrackSimplified track;
    private Date playedAt;
    private Context context;

    public Builder setTrack(TrackSimplified track) {
      this.track = track;
      return this;
    }

    public Builder setPlayedAt(Date playedAt) {
      this.playedAt = playedAt;
      return this;
    }

    public Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    @Override
    public PlayHistory build() {
      return new PlayHistory(this);
    }
  }
}
