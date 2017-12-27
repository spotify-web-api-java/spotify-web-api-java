package com.wrapper.spotify.requests;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.HttpManager;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;

public interface Request {

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  <T> SettableFuture<T> executeAsync(T value);

  HttpManager getHttpManager();

  URI getUri();

  List<Header> getHeaders();

  List<NameValuePair> getFormParameters();

  List<NameValuePair> getBodyParameters();

  interface Builder {

    Builder setHttpManager(final HttpManager httpManager);

    Builder setScheme(final String scheme);

    Builder setHost(final String host);

    Builder setPort(final Integer port);

    Builder setPath(final String path);

    Builder setPathParameter(final String name, final String value);

    <T> Builder setQueryParameter(final String name, final T value);

    <T> Builder setHeader(final String name, final T value);

    <T> Builder setFormParameter(final String name, final T value);

    <T> Builder setBodyParameter(final String name, final T value);

    AbstractRequest build();
  }
}
