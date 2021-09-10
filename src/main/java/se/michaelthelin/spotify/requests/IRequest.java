package se.michaelthelin.spotify.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.hc.core5.http.*;
import se.michaelthelin.spotify.IHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

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
  interface Builder<T, BT extends Builder<T, ?>> {

    BT setHttpManager(final IHttpManager httpManager);

    BT setScheme(final String scheme);

    BT setHost(final String host);

    BT setPort(final Integer port);

    BT setPath(final String path);

    BT setPathParameter(final String name, final String value);

    BT setDefaults(final IHttpManager httpManager,
                   final String scheme,
                   final String host,
                   final Integer port);

    <ST> BT setQueryParameter(final String name, final ST value);

    <ST> BT setHeader(final String name, final ST value);

    BT setContentType(final ContentType contentType);

    BT setBody(final HttpEntity httpEntity);

    <ST> BT setBodyParameter(final String name, final ST value);

    IRequest<T> build();
  }
}
