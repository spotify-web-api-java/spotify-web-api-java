package com.wrapper.spotify.requests;

import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyApiThreading;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class AbstractRequest implements IRequest {

  private IHttpManager httpManager;
  private URI uri;
  private List<Header> headers;
  private List<NameValuePair> formParameters;
  private List<NameValuePair> bodyParameters;
  private String body;

  protected AbstractRequest(Builder<?> builder) {
    assert (builder.httpManager != null);
    assert (builder.scheme != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.path != null);
    assert (builder.pathParameters != null);
    assert (builder.queryParameters != null);
    assert (builder.headers != null);
    assert (builder.formParameters != null);
    assert (builder.bodyParameters != null);
    assert (builder.body != null);

    this.httpManager = builder.httpManager;

    URIBuilder uriBuilder = new URIBuilder();
    uriBuilder
            .setScheme(builder.scheme)
            .setHost(builder.host)
            .setPort(builder.port)
            .setPath(builder.path);
    if (builder.queryParameters.size() > 0) {
      uriBuilder
              .setParameters(builder.queryParameters);
    }

    try {
      this.uri = uriBuilder.build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    this.headers = builder.headers;
    this.formParameters = builder.formParameters;
    this.bodyParameters = builder.bodyParameters;
    this.body = builder.body;
  }

  /**
   * Get something asynchronously.
   *
   * @return A {@link Future} for a generic.
   */
  public <T> Future<T> executeAsync() {
    return SpotifyApiThreading.executeAsync(
            new Callable<T>() {
              public T call() throws IOException, SpotifyWebApiException {
                return execute();
              }
            });
  }

  public String getJson() throws
          IOException,
          SpotifyWebApiException {
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.get(uri, headerArray);
  }

  public String postJson() throws
          IOException,
          SpotifyWebApiException {
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.post(uri, headerArray, formParameters);
  }

  public String putJson() throws
          IOException,
          SpotifyWebApiException {
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.put(uri, headerArray, formParameters);
  }

  public String deleteJson() throws
          IOException,
          SpotifyWebApiException {
    Header[] headerArray = new Header[]{};
    headers.toArray(headerArray);

    return httpManager.delete(uri, headerArray);
  }

  public IHttpManager getHttpManager() {
    return httpManager;
  }

  public URI getUri() {
    return uri;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public List<NameValuePair> getFormParameters() {
    return formParameters;
  }

  public List<NameValuePair> getBodyParameters() {
    return bodyParameters;
  }

  public String getBody() {
    return body;
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements IRequest.Builder {

    private IHttpManager httpManager = SpotifyApi.DEFAULT_HTTP_MANAGER;
    private String scheme = SpotifyApi.DEFAULT_SCHEME;
    private String host = SpotifyApi.DEFAULT_HOST;
    private Integer port = SpotifyApi.DEFAULT_PORT;
    private String path = null;
    private List<NameValuePair> pathParameters = new ArrayList<>();
    private List<NameValuePair> queryParameters = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private List<NameValuePair> formParameters = new ArrayList<>();
    private List<NameValuePair> bodyParameters = new ArrayList<>();
    private String body = "";

    protected Builder() {
      setHeader("Content-Type", "application/json");
    }

    public BuilderType setHttpManager(final IHttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType setScheme(final String scheme) {
      assert (scheme != null);
      assert (!scheme.equals(""));
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType setHost(final String host) {
      assert (host != null);
      assert (!scheme.equals(""));
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType setPort(final Integer port) {
      assert (port != null);
      assert (port >= 0);
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType setPath(final String path) {
      assert (path != null);
      assert (!path.equals(""));

      String builtPath = path;

      for (NameValuePair nameValuePair : pathParameters) {
        builtPath = builtPath.replaceAll("\\{" + nameValuePair.getName() + "\\}", nameValuePair.getValue());
      }

      this.path = builtPath;
      return (BuilderType) this;
    }

    public BuilderType setPathParameter(final String name, final String value) {
      assert (name != null && value != null);
      assert (!name.equals("") && !value.equals(""));

      String encodedValue = null;

      try {
        encodedValue = URLEncoder.encode(value, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      this.pathParameters.add(new BasicNameValuePair(name, encodedValue));
      return (BuilderType) this;
    }

    public BuilderType setDefaults(final IHttpManager httpManager,
                                   final String scheme,
                                   final String host,
                                   final Integer port) {
      setHttpManager(httpManager);
      setScheme(scheme);
      setHost(host);
      setPort(port);

      return (BuilderType) this;
    }

    public <T> BuilderType setQueryParameter(final String name, final T value) {
      assert (name != null);
      assert (value != null);
      this.queryParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setHeader(final String name, final T value) {
      this.headers.add(new BasicHeader(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setFormParameter(final String name, final T value) {
      this.formParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public <T> BuilderType setBodyParameter(final String name, final T value) {
      this.bodyParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (BuilderType) this;
    }

    public BuilderType setBody(final String value) {
      this.body = value;
      return (BuilderType) this;
    }
  }
}
