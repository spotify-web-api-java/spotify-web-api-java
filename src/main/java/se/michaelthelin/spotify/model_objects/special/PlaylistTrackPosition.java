package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Arrays;
import java.util.Objects;

/**
 * Retrieve information about Playlist Track Position objects by building instances from this class. These objects
 * contain the position in a playlist, where tracks should be added in a request.
 */
@JsonDeserialize(builder = PlaylistTrackPosition.Builder.class)
public class PlaylistTrackPosition extends AbstractModelObject {
  /** The Spotify URI for the track. */
  private final String uri;
  /** An array of the track's positions within the playlist. */
  private final int[] positions;

  /**
   * Creates a new PlaylistTrackPosition with the specified builder.
   *
   * @param builder the builder containing track URI and position configuration
   */
  public PlaylistTrackPosition(final Builder builder) {
    super(builder);

    this.uri = builder.uri;
    this.positions = builder.positions;
  }

  /**
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> of the
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
  public String toString() {
    return "PlaylistTrackPosition(uri=" + uri + ", positions=" + Arrays.toString(positions) + ")";
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

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Sets the Spotify URI of the track.
     *
     * @param uri the Spotify URI of the track
     * @return this builder instance for method chaining
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Sets the positions where the track should be placed in the playlist.
     *
     * @param positions the zero-based positions in the playlist
     * @return this builder instance for method chaining
     */
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

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaylistTrackPosition that = (PlaylistTrackPosition) o;
    return Objects.equals(uri, that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri);
  }
}
