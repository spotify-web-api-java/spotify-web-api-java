package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;
import com.wrapper.spotify.objects.model_objects.AbstractModelObject;

public class SnapshotResult extends AbstractModelObject {
  public final String snapshotId;

  private SnapshotResult(final SnapshotResult.Builder builder) {
    super(builder);

    this.snapshotId = builder.snapshotId;
  }

  public String getSnapshotId() {
    return snapshotId;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<SnapshotResult> {
    public SnapshotResult createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new SnapshotResult.Builder()
              .setSnapshotId(jsonObject.get("snapshot_id").getAsString())
              .build();
    }
  }
}
