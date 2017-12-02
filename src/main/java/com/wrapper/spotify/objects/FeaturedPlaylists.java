package com.wrapper.spotify.objects;

import com.google.gson.JsonObject;

public class FeaturedPlaylists extends AbstractModelObject {
  private final String message;
  private final Paging<PlaylistSimplified> playlists;

  private FeaturedPlaylists(final FeaturedPlaylists.Builder builder) {
    super(builder);

    this.message = builder.message;
    this.playlists = builder.playlists;
  }

  public String getMessage() {
    return message;
  }

  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String message;
    private Paging<PlaylistSimplified> playlists;

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setPlaylists(Paging<PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    @Override
    public FeaturedPlaylists build() {
      return new FeaturedPlaylists(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<FeaturedPlaylists> {
    public FeaturedPlaylists createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new FeaturedPlaylists.Builder()
              .setMessage(jsonObject.get("message").getAsString())
              .setPlaylists(new PlaylistSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("playlists")))
              .build();
    }
  }
}
