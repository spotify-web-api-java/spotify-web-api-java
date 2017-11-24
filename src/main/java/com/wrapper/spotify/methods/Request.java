package com.wrapper.spotify.methods;

import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;

public interface Request {

  Url toUrl();

  String toString();

  String toString(final boolean withQueryParameters);

  interface Builder {
    Builder setHttpManager(HttpManager httpManager);

    Builder setHost(String host);

    Builder setPort(int port);

    Builder setScheme(Url.Scheme scheme);

    AbstractRequest build();
  }

}
