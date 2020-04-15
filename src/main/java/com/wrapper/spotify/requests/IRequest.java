package com.wrapper.spotify.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IRequest<T> {

  IHttpManager getHttpManager();

  URI getUri();

  List<Header> getHeaders();

  ContentType getContentType();

  HttpEntity getBody();

  List<NameValuePair> getBodyParameters();

  T execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  CompletableFuture<T> executeAsync();

  String getJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  String postJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  String putJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  String deleteJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  @JsonPOJOBuilder(withPrefix = "set")
  interface Builder<T, X> {

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

    <ST> Builder setQueryParameter(final String name, final ST value);

    <ST> Builder setHeader(final String name, final ST value);

    Builder setContentType(final ContentType contentType);

    Builder setBody(final HttpEntity httpEntity);

    <ST> Builder setBodyParameter(final String name, final ST value);

    AbstractRequest<T> build();
  }
}
