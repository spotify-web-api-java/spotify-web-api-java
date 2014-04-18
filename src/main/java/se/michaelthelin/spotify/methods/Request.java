package se.michaelthelin.spotify.methods;

import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.UtilProtos.Url;

public interface Request {

  public static interface Builder {
    Builder httpManager(HttpManager httpManager);
    Builder host(String host);
    Builder port(int port);
    Builder scheme(Url.Scheme scheme);
    Request build();
  }

  Url toUrl();

}
