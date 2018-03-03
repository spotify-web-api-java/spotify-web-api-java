package com.wrapper.spotify.requests;

import com.google.gson.*;
import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyApiThreading;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
import java.util.logging.Level;

public abstract class AbstractRequest implements IRequest {

  private IHttpManager httpManager;
  private URI uri;
  private List<Header> headers;
  private ContentType contentType;
  private HttpEntity body;
  private List<NameValuePair> bodyParameters;

  protected AbstractRequest(Builder<?> builder) {
    assert (builder != null);
    assert (builder.path != null);
    assert (!builder.path.equals(""));

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
      SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
    }

    this.headers = builder.headers;
    this.contentType = builder.contentType;
    this.body = builder.body;
    this.bodyParameters = builder.bodyParameters;
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

  public void initializeBody() throws UnsupportedEncodingException {
    if (body == null && contentType != null) {
      switch (contentType.getMimeType()) {
        case "application/json":
          body = new StringEntity(
                  bodyParametersToJson(bodyParameters),
                  ContentType.APPLICATION_JSON);
          break;
        case "application/x-www-form-urlencoded":
          body = new UrlEncodedFormEntity(bodyParameters);
          break;
      }
    }
  }

  public String bodyParametersToJson(List<NameValuePair> bodyParameters) {
    JsonObject jsonObject = new JsonObject();
    JsonElement jsonElement;

    for (NameValuePair nameValuePair : bodyParameters) {
      try {
        jsonElement = new JsonParser().parse(nameValuePair.getValue());
      } catch (JsonSyntaxException e) {
        jsonElement = new JsonPrimitive(nameValuePair.getValue());
      }

      jsonObject.add(nameValuePair.getName(), jsonElement);
    }

    return jsonObject.toString();
  }

  public String getJson() throws
          IOException,
          SpotifyWebApiException {
    String json = httpManager.get(uri, headers.toArray(new Header[headers.size()]));

    if (json == null || json.equals("")) {
      return null;
    } else {
      return json;
    }
  }

  public String postJson() throws
          IOException,
          SpotifyWebApiException {
    initializeBody();

    String json = httpManager.post(uri, headers.toArray(new Header[headers.size()]), body);

    if (json == null || json.equals("")) {
      return null;
    } else {
      return json;
    }
  }

  public String putJson() throws
          IOException,
          SpotifyWebApiException {
    initializeBody();

    String json = httpManager.put(uri, headers.toArray(new Header[headers.size()]), body);

    if (json == null || json.equals("")) {
      return null;
    } else {
      return json;
    }
  }

  public String deleteJson() throws
          IOException,
          SpotifyWebApiException {
    initializeBody();

    String json = httpManager.delete(uri, headers.toArray(new Header[headers.size()]), body);

    if (json == null || json.equals("")) {
      return null;
    } else {
      return json;
    }
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

  public ContentType getContentType() {
    return contentType;
  }

  public HttpEntity getBody() {
    return body;
  }

  public List<NameValuePair> getBodyParameters() {
    return bodyParameters;
  }

  public static abstract class Builder<T extends Builder<?>> implements IRequest.Builder {

    private final List<NameValuePair> pathParameters = new ArrayList<>();
    private final List<NameValuePair> queryParameters = new ArrayList<>();
    private final List<Header> headers = new ArrayList<>();
    private final List<NameValuePair> bodyParameters = new ArrayList<>();
    private IHttpManager httpManager = SpotifyApi.DEFAULT_HTTP_MANAGER;
    private String scheme = SpotifyApi.DEFAULT_SCHEME;
    private String host = SpotifyApi.DEFAULT_HOST;
    private Integer port = SpotifyApi.DEFAULT_PORT;
    private String path = null;
    private ContentType contentType = null;
    private HttpEntity body = null;

    protected Builder() {
    }

    @SuppressWarnings("unchecked")
    public T setHttpManager(final IHttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setScheme(final String scheme) {
      assert (scheme != null);
      assert (!scheme.equals(""));
      this.scheme = scheme;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setHost(final String host) {
      assert (host != null);
      assert (!scheme.equals(""));
      this.host = host;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setPort(final Integer port) {
      assert (port != null);
      assert (port >= 0);
      this.port = port;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setPath(final String path) {
      assert (path != null);
      assert (!path.equals(""));

      String builtPath = path;

      for (NameValuePair nameValuePair : pathParameters) {
        builtPath = builtPath.replaceAll("\\{" + nameValuePair.getName() + "}", nameValuePair.getValue());
      }

      this.path = builtPath;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setPathParameter(final String name, final String value) {
      assert (name != null && value != null);
      assert (!name.equals("") && !value.equals(""));

      String encodedValue = null;

      try {
        encodedValue = URLEncoder.encode(value, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
      }

      this.pathParameters.add(new BasicNameValuePair(name, encodedValue));
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setDefaults(final IHttpManager httpManager,
                         final String scheme,
                         final String host,
                         final Integer port) {
      setHttpManager(httpManager);
      setScheme(scheme);
      setHost(host);
      setPort(port);

      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <X> T setQueryParameter(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      this.queryParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <X> T setHeader(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      this.headers.add(new BasicHeader(name, String.valueOf(value)));
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setContentType(final ContentType contentType) {
      this.contentType = contentType;
      setHeader("Content-Type", contentType.getMimeType());
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setBody(final HttpEntity httpEntity) {
      this.body = httpEntity;
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <X> T setBodyParameter(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      this.bodyParameters.add(new BasicNameValuePair(name, String.valueOf(value)));
      return (T) this;
    }
  }
}
