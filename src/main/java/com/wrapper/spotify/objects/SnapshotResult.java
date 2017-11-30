package com.wrapper.spotify.objects;

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

  public static final class Builder extends AbstractModelObject.Builder<SnapshotResult.Builder> {
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
}
