package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
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

  public static final class Builder extends AbstractModelObject.Builder {
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlayHistory> {
    public PlayHistory createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
                .setTrack(
                        hasAndNotNull(jsonObject, "track")
                                ? new TrackSimplified.JsonUtil().createModelObject(
                                jsonObject.getAsJsonObject("track"))
                                : null)
                .setPlayedAt(
                        hasAndNotNull(jsonObject, "played_at")
                                ? simpleDateFormat.parse(jsonObject.get("played_at").getAsString())
                                : null)
                .setContext(
                        hasAndNotNull(jsonObject, "context")
                                ? new Context.JsonUtil().createModelObject(
                                jsonObject.getAsJsonObject("context"))
                                : null)
                .build();
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
}
