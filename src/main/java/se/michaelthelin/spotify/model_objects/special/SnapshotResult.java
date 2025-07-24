package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Snapshot Result objects by building instances from this class. These objects contain a
 * playlist snapshot ID, which is created after adding or removing tracks from a playlist.
 * <p>
 * <a href="https://developer.spotify.com/documentation/web-api/concepts/playlists">
 * Spotify: Working With Playlists</a>
 */
@JsonDeserialize(builder = SnapshotResult.Builder.class)
public class SnapshotResult extends AbstractModelObject {
  /** The snapshot_id can be used to identify your playlist version in future requests. */
  private final String snapshotId;

  private SnapshotResult(final Builder builder) {
    super(builder);

    this.snapshotId = builder.snapshotId;
  }

  /**
   * Get the snapshot ID.
   *
   * @return The snapshot ID.
   */
  public String getSnapshotId() {
    return snapshotId;
  }

  @Override
  public String toString() {
    return "SnapshotResult(snapshotId=" + snapshotId + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SnapshotResult} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    /** The snapshot ID of the playlist. */
    public String snapshotId;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Sets the snapshot ID of the playlist.
     *
     * @param snapshotId the snapshot ID
     * @return this builder instance for method chaining
     */
    public Builder setSnapshotId(String snapshotId) {
      this.snapshotId = snapshotId;
      return this;
    }

    @Override
    public SnapshotResult build() {
      return new SnapshotResult(this);
    }
  }

  /**
   * JsonUtil class for building {@link SnapshotResult} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SnapshotResult> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public SnapshotResult createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new SnapshotResult.Builder()
        .setSnapshotId(
          hasAndNotNull(jsonObject, "snapshot_id")
            ? jsonObject.get("snapshot_id").getAsString()
            : null)
        .build();
    }
  }
}
