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

    Builder setHttpManager(final HttpManager httpManager);

    Builder setScheme(final Url.Scheme scheme);

    Builder setHost(final String host);

    Builder setPort(final Integer port);

    Builder setPath(final String path);

    Builder setParameter(final String name, final String value);

    Builder setHeaderParameter(final String name, final String value);

    Builder setBodyParameter(final String name, final String value);

    Builder setPart(final Url.Part part);

    Builder setBodyParameter(final JsonElement jsonBody);
  }

}
