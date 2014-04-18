package se.michaelthelin.spotify;

import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class SpotifyHttpManager implements HttpManager {

  private HttpConnectionManager connectionManager = null;

  /**
   * Construct a new SpotifyHttpManager instance.
   */
  public SpotifyHttpManager(Builder builder) {
    if (builder.connectionManager != null) {
      connectionManager = builder.connectionManager;
    } else {
      connectionManager = new MultiThreadedHttpConnectionManager();
    }
  }

  @Override
  public String get(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String post(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String delete(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String put(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private HttpConnectionManager connectionManager = null;

    public Builder() {}

    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }

  }

}
