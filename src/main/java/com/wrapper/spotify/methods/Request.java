package com.wrapper.spotify.methods;

import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;

public interface Request {

  public static interface Builder {
    Builder httpManager(HttpManager httpManager);
    Builder host(String host);
    Builder port(int port);
    Builder scheme(Url.Scheme scheme);
    AbstractRequest build();
  }

  Url toUrl();

}
