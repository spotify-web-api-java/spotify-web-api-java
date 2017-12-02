package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class PlaylistTrack extends AbstractModelObject {
  private final Date addedAt;
  private final User addedBy;
  private final boolean isLocal;
  private final Track track;

  private PlaylistTrack(final PlaylistTrack.Builder builder) {
    super(builder);

    this.addedAt = builder.addedAt;
    this.addedBy = builder.addedBy;
    this.isLocal = builder.isLocal;
    this.track = builder.track;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public User getAddedBy() {
    return addedBy;
  }

  public boolean getIsLocal() {
    return isLocal;
  }

  public Track getTrack() {
    return track;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private Date addedAt;
    private User addedBy;
    private boolean isLocal;
    private Track track;

    public Builder setAddedAt(Date addedAt) {
      this.addedAt = addedAt;
      return this;
    }

    public Builder setAddedBy(User addedBy) {
      this.addedBy = addedBy;
      return this;
    }

    public Builder setIsLocal(boolean isLocal) {
      this.isLocal = isLocal;
      return this;
    }

    public Builder setTrack(Track track) {
      this.track = track;
      return this;
    }

    @Override
    public PlaylistTrack build() {
      return new PlaylistTrack(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistTrack> {
    public PlaylistTrack createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      try {
        return new Builder()
                .setAddedAt(simpleDateFormat.parse(jsonObject.getString("added_at")))
                .setAddedBy(new User.JsonUtil().createModelObject(jsonObject.getJSONObject("added_by")))
                .setIsLocal(jsonObject.getBoolean("is_local"))
                .setTrack(new Track.JsonUtil().createModelObject(jsonObject.getJSONObject("track")))
                .build();
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
}
