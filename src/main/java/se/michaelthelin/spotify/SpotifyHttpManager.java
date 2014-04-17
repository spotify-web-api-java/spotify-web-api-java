package se.michaelthelin.spotify;

public class SpotifyHttpManager implements HttpManager {

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean hasCredentials() {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void clearCredentials() {
    throw new RuntimeException("Not implemented");
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

  public static class Builder {

  }

}
