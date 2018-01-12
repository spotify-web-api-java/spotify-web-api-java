package com.wrapper.spotify.model_objects.special;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Playlist Track Position objects by building instances from this class. These objects
 * contain the position in a playlist, where tracks should be added in a request.
 */
public class PlaylistTrackPosition extends AbstractModelObject {
  private final String uri;
  private final int[] positions;

  public PlaylistTrackPosition(final Builder builder) {
    super(builder);

    this.uri = builder.uri;
    this.positions = builder.positions;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> of the
   * track.
   *
   * @return Spotify track URI.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Get the position, where the track should be added in the playlist.
   *
   * @return Track position.
   */
  public int[] getPositions() {
    return positions;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link PlaylistTrackPosition} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String uri;
    private int[] positions;

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    public Builder setPositions(int... positions) {
      this.positions = positions;
      return this;
    }

    @Override
    public PlaylistTrackPosition build() {
      return new PlaylistTrackPosition(this);
    }
  }

  /**
   * JsonUtil class for building {@link PlaylistTrackPosition} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistTrackPosition> {
    public PlaylistTrackPosition createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new PlaylistTrackPosition.Builder()
              .setPositions(
                      hasAndNotNull(jsonObject, "positions")
                              ? new Gson().fromJson(
                              jsonObject.getAsJsonArray("positions"), int[].class)
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
