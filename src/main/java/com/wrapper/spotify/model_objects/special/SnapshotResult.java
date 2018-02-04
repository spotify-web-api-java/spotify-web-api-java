package com.wrapper.spotify.model_objects.special;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Snapshot Result objects by building instances from this class. These objects contain a
 * playlist snapshot ID, which is created after adding or removing tracks from a playlsit.
 * <p>
 * <a href="https://developer.spotify.com/web-api/working-with-playlists/#version-control-and-snapshots">
 * Spotify: Working With Playlists</a>
 */
public class SnapshotResult extends AbstractModelObject {
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
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link SnapshotResult} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    public String snapshotId;

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
