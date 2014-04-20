package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;

public class TracksRequest extends AbstractRequest {

  public TracksRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/tracks");
      return parameter("ids", idsParameter);
    }

    public TracksRequest build() {
      return new TracksRequest(this);
    }

  }

}
