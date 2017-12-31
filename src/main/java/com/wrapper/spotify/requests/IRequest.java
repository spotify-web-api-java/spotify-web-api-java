package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.IHttpManager;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public interface IRequest {

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  <T> SettableFuture<T> executeAsync(T value);

  IHttpManager getHttpManager();

  URI getUri();

  Map<String, String> getHeaders();

  Map<String, String> getFormParameters();

  Map<String, String> getBodyParameters();

  String getBody();

  interface Builder {

    Builder setHttpManager(final IHttpManager httpManager);

    Builder setScheme(final String scheme);

    Builder setHost(final String host);

    Builder setPort(final Integer port);

    Builder setPath(final String path);

    Builder setPathParameter(final String name, final String value);

    Builder setDefaults(final IHttpManager httpManager,
                        final String scheme,
                        final String host,
                        final Integer port);

    <T> Builder setQueryParameter(final String name, final T value);

    <T> Builder setHeader(final String name, final T value);

    <T> Builder setFormParameter(final String name, final T value);

    <T> Builder setBodyParameter(final String name, final T value);

    Builder setBody(final String value);

    AbstractRequest build();
  }
}
