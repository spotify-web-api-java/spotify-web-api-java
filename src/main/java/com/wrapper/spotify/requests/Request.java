package com.wrapper.spotify.requests;

import com.google.gson.JsonElement;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;

import java.text.SimpleDateFormat;

public interface Request {
  
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  Url toUrl();

  String toString();

  String toString(final boolean withQueryParameters);

  interface Builder {
    AbstractRequest build();

    Builder setHttpManager(HttpManager httpManager);

    Builder setScheme(Url.Scheme scheme);

    Builder setHost(String host);

    Builder setPort(int port);

    Builder setPath(String path);

    Builder setParameter(String name, String value);

    Builder setHeaderParameter(String name, String value);

    Builder setBodyParameter(String name, String value);

    Builder setPart(Url.Part part);

    Builder setBodyParameter(JsonElement jsonBody);
  }

}
