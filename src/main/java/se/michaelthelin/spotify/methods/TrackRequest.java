package se.michaelthelin.spotify.methods;

import com.google.common.base.Joiner;

public class TrackRequest extends AbstractRequest {

  public TrackRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The track with the given id.
     *
     * @param id The id for the track.
     * @return Track Request
     */
    public Builder id(String id) {
      assert (id != null);
      return path(String.format("/v1/tracks/%s", id));
    }

    public Builder id(String... ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids).toString();
      path("/v1/tracks");
      return parameter("ids", idsParameter);
    }

    public TrackRequest build() {
      return new TrackRequest(this);
    }

  }

}
