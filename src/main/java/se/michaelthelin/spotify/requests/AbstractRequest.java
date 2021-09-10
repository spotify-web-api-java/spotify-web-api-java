package se.michaelthelin.spotify.requests;

import com.google.gson.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import se.michaelthelin.spotify.IHttpManager;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyApiThreading;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class AbstractRequest<T> implements IRequest<T> {

  private final IHttpManager httpManager;
  private final List<Header> headers;
  private final ContentType contentType;
  private final List<NameValuePair> bodyParameters;
  private URI uri;
  private HttpEntity body;

  protected AbstractRequest(Builder<T, ?> builder) {
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
   * @return A {@link CompletableFuture} for a generic.
   */
  public CompletableFuture<T> executeAsync() {
    return SpotifyApiThreading.executeAsync(
      this::execute);
  }

  public void initializeBody() {
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
        jsonElement = JsonParser.parseString(nameValuePair.getValue());
      } catch (JsonSyntaxException e) {
        jsonElement = new JsonPrimitive(nameValuePair.getValue());
      }

      jsonObject.add(nameValuePair.getName(), jsonElement);
    }

    return jsonObject.toString();
  }

  public String getJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException {

    String json = httpManager.get(uri, headers.toArray(new Header[0]));

    return returnJson(json);
  }

  public String postJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    initializeBody();

    String json = httpManager.post(uri, headers.toArray(new Header[0]), body);

    return returnJson(json);
  }

  public String putJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    initializeBody();

    String json = httpManager.put(uri, headers.toArray(new Header[0]), body);

    return returnJson(json);
  }

  public String deleteJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    initializeBody();

    String json = httpManager.delete(uri, headers.toArray(new Header[0]), body);

    return returnJson(json);
  }

  private String returnJson(String json) {
    if (json == null) {
      SpotifyApi.LOGGER.log(
        Level.FINE,
        "The httpManager returned json == null.");
      return null;
    } else if (json.equals("")) {
      SpotifyApi.LOGGER.log(
        Level.FINE,
        "The httpManager returned json == \"\".");
      return null;
    } else {
      SpotifyApi.LOGGER.log(
        Level.FINE,
        "The httpManager returned json == " + json + ".");
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

  public static abstract class Builder<T, BT extends Builder<T, ?>> implements IRequest.Builder<T, BT> {

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

    public BT setHttpManager(final IHttpManager httpManager) {
      assert (httpManager != null);
      this.httpManager = httpManager;
      return self();
    }

    public BT setScheme(final String scheme) {
      assert (scheme != null);
      assert (!scheme.equals(""));
      this.scheme = scheme;
      return self();
    }

    public BT setHost(final String host) {
      assert (host != null);
      assert (!scheme.equals(""));
      this.host = host;
      return self();
    }

    public BT setPort(final Integer port) {
      assert (port != null);
      assert (port >= 0);
      this.port = port;
      return self();
    }

    public BT setPath(final String path) {
      assert (path != null);
      assert (!path.equals(""));

      String builtPath = path;

      for (NameValuePair nameValuePair : pathParameters) {
        // Don't remove the "\\" before the "}" to prevent a regex issue on Android.
        String key = "\\{" + nameValuePair.getName() + "\\}";
        builtPath = builtPath.replaceAll(key, nameValuePair.getValue());
      }

      this.path = builtPath;
      return self();
    }

    public BT setPathParameter(final String name, final String value) {
      assert (name != null && value != null);
      assert (!name.equals("") && !value.equals(""));

      listAddOnce(this.pathParameters, new BasicNameValuePair(name, value));
      return self();
    }

    public BT setDefaults(final IHttpManager httpManager,
                          final String scheme,
                          final String host,
                          final Integer port) {
      setHttpManager(httpManager);
      setScheme(scheme);
      setHost(host);
      setPort(port);

      return self();
    }

    public <X> BT setQueryParameter(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      listAddOnce(this.queryParameters, new BasicNameValuePair(name, String.valueOf(value)));
      return self();
    }

    public <X> BT setHeader(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      listAddOnce(this.headers, new BasicHeader(name, String.valueOf(value)));
      return self();
    }

    public BT setContentType(final ContentType contentType) {
      this.contentType = contentType;
      setHeader("Content-Type", contentType.getMimeType());
      return self();
    }

    public BT setBody(final HttpEntity httpEntity) {
      this.body = httpEntity;
      return self();
    }

    public <X> BT setBodyParameter(final String name, final X value) {
      assert (name != null);
      assert (!name.equals(""));
      assert (value != null);
      listAddOnce(this.bodyParameters, new BasicNameValuePair(name, String.valueOf(value)));
      return self();
    }

    private void listAddOnce(List<NameValuePair> nameValuePairs, NameValuePair newNameValuePair) {
      nameValuePairs.removeAll(nameValuePairs.stream()
        .filter(nameValuePair -> nameValuePair.getName().equals(newNameValuePair.getName()))
        .collect(Collectors.toList()));
      nameValuePairs.add(newNameValuePair);
    }

    private void listAddOnce(List<Header> headers, Header newHeader) {
      headers.removeAll(headers.stream()
        .filter(header -> header.getName().equals(newHeader.getName()))
        .collect(Collectors.toList()));
      headers.add(newHeader);
    }

    /**
     * Return this instance to simulate a self-type.
     *
     * @return This instance.
     */
    protected abstract BT self();
  }
}
