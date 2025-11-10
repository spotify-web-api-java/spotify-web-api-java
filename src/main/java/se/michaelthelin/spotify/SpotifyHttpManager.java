package se.michaelthelin.spotify;

import com.google.gson.*;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.cache.CacheResponseStatus;
import org.apache.hc.client5.http.cache.HttpCacheContext;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.cache.CacheConfig;
import org.apache.hc.client5.http.impl.cache.CachingHttpClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.*;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

/**
 * Default implementation of the {@link IHttpManager} interface.
 * Manages HTTP requests to the Spotify Web API using Apache HttpClient.
 */
public class SpotifyHttpManager implements IHttpManager, Closeable {

  private static final Gson GSON = new Gson();
  private final CloseableHttpClient httpClient;
  private final CloseableHttpClient httpClientCaching;
  private final HttpClientConnectionManager connectionManager;
  private final HttpHost proxy;
  private final UsernamePasswordCredentials proxyCredentials;
  private final Integer cacheMaxEntries;
  private final Integer cacheMaxObjectSize;
  private final Boolean cacheShared;
  private final Integer connectionRequestTimeout;
  private final Integer socketTimeout;

  /**
   * Construct a new SpotifyHttpManager instance.
   *
   * @param builder The builder.
   */
  public SpotifyHttpManager(Builder builder) {
    this.connectionManager = builder.connectionManager;
    this.proxy = builder.proxy;
    this.proxyCredentials = builder.proxyCredentials;
    this.cacheMaxEntries = builder.cacheMaxEntries;
    this.cacheMaxObjectSize = builder.cacheMaxObjectSize;
    this.cacheShared = builder.cacheShared;
    this.connectionRequestTimeout = builder.connectionRequestTimeout;
    this.socketTimeout = builder.socketTimeout;

    CacheConfig cacheConfig = CacheConfig.custom()
      .setMaxCacheEntries(cacheMaxEntries)
      .setMaxObjectSize(cacheMaxObjectSize)
      .setSharedCache(cacheShared)
      .build();

    BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

    if (proxy != null && proxyCredentials != null) {
      credentialsProvider.setCredentials(
        new AuthScope(null, proxy.getHostName(), proxy.getPort(), null, proxy.getSchemeName()),
        proxyCredentials
      );
    }

    RequestConfig requestConfig = RequestConfig
      .custom()
      .setCookieSpec(StandardCookieSpec.STRICT)
      .setConnectionRequestTimeout(builder.connectionRequestTimeout != null
        ? Timeout.ofMilliseconds(builder.connectionRequestTimeout)
        : RequestConfig.DEFAULT.getConnectionRequestTimeout())
      .setResponseTimeout(builder.socketTimeout != null
        ? Timeout.ofMilliseconds(builder.socketTimeout)
        : RequestConfig.DEFAULT.getResponseTimeout())
      .build();
    HttpRequestRetryStrategy retryStrategy = new SpotifyHttpRequestRetryStrategy();

    this.httpClient = HttpClients
      .custom()
      .disableContentCompression()
      .setConnectionManager(connectionManager)
      .setDefaultCredentialsProvider(credentialsProvider)
      .setDefaultRequestConfig(requestConfig)
      .setProxy(proxy)
      .setRetryStrategy(retryStrategy)
      .build();

    this.httpClientCaching = CachingHttpClients
      .custom()
      .setCacheConfig(cacheConfig)
      .disableContentCompression()
      .setConnectionManager(connectionManager)
      .setDefaultCredentialsProvider(credentialsProvider)
      .setDefaultRequestConfig(requestConfig)
      .setProxy(proxy)
      .setRetryStrategy(retryStrategy)
      .build();
  }

  /**
   * Creates a URI from a string, handling any syntax exceptions.
   *
   * @param uriString the URI string to parse
   * @return the parsed URI, or null if the string is invalid
   */
  public static URI makeUri(String uriString) {
    try {
      return new URI(uriString);
    } catch (URISyntaxException e) {
      SpotifyApi.LOGGER.log(
        Level.SEVERE,
        "URI Syntax Exception for \"" + uriString + "\"");
      return null;
    }
  }

  /**
   * Gets the HTTP client connection manager.
   *
   * @return the connection manager
   */
  public HttpClientConnectionManager getConnectionManager() {
    return connectionManager;
  }

  /**
   * Gets the HTTP proxy configuration.
   *
   * @return the HTTP proxy host, or null if no proxy is configured
   */
  public HttpHost getProxy() {
    return proxy;
  }

  /**
   * Gets the proxy authentication credentials.
   *
   * @return the proxy credentials, or null if no credentials are configured
   */
  public UsernamePasswordCredentials getProxyCredentials() {
    return proxyCredentials;
  }

  /**
   * Gets the maximum number of cache entries.
   *
   * @return the maximum cache entries
   */
  public Integer getCacheMaxEntries() {
    return cacheMaxEntries;
  }

  /**
   * Gets the maximum size of cached objects.
   *
   * @return the maximum cache object size
   */
  public Integer getCacheMaxObjectSize() {
    return cacheMaxObjectSize;
  }

  /**
   * Gets whether the cache is shared between multiple HTTP clients.
   *
   * @return true if the cache is shared, false otherwise
   */
  public Boolean getCacheShared() {
    return cacheShared;
  }

  /**
   * Gets the connection request timeout in milliseconds.
   *
   * @return the connection request timeout
   */
  public Integer getConnectionRequestTimeout() {
    return connectionRequestTimeout;
  }

  /**
   * Gets the socket timeout in milliseconds.
   *
   * @return the socket timeout
   */
  public Integer getSocketTimeout() {
    return socketTimeout;
  }

  @Override
  public String get(URI uri, Header[] headers) throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    assert (uri != null);
    assert (!uri.toString().isEmpty());

    final HttpGet httpGet = new HttpGet(uri);

    httpGet.setHeaders(headers);
    SpotifyApi.LOGGER.log(
      Level.FINE,
      "GET request uses these headers: " + GSON.toJson(headers));

    String responseBody = getResponseBody(execute(httpClientCaching, httpGet));

    httpGet.reset();

    return responseBody;
  }

  @Override
  public String post(URI uri, Header[] headers, HttpEntity body) throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    assert (uri != null);
    assert (!uri.toString().isEmpty());

    final HttpPost httpPost = new HttpPost(uri);

    httpPost.setHeaders(headers);
    httpPost.setEntity(body);
    SpotifyApi.LOGGER.log(
      Level.FINE,
      "POST request uses these headers: " + GSON.toJson(headers));

    String responseBody = getResponseBody(execute(httpClient, httpPost));

    httpPost.reset();

    return responseBody;
  }

  @Override
  public String put(URI uri, Header[] headers, HttpEntity body) throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    assert (uri != null);
    assert (!uri.toString().isEmpty());

    final HttpPut httpPut = new HttpPut(uri);

    httpPut.setHeaders(headers);
    httpPut.setEntity(body);
    SpotifyApi.LOGGER.log(
      Level.FINE,
      "PUT request uses these headers: " + GSON.toJson(headers));

    String responseBody = getResponseBody(execute(httpClient, httpPut));

    httpPut.reset();

    return responseBody;
  }

  @Override
  public String delete(URI uri, Header[] headers, HttpEntity body) throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    assert (uri != null);
    assert (!uri.toString().isEmpty());

    final HttpDelete httpDelete = new HttpDelete(uri);

    httpDelete.setHeaders(headers);
    httpDelete.setEntity(body);
    SpotifyApi.LOGGER.log(
      Level.FINE,
      "DELETE request uses these headers: " + GSON.toJson(headers));

    String responseBody = getResponseBody(execute(httpClient, httpDelete));

    httpDelete.reset();

    return responseBody;
  }

  private CloseableHttpResponse execute(CloseableHttpClient httpClient, ClassicHttpRequest method) throws
    IOException {
    HttpCacheContext context = HttpCacheContext.create();
    CloseableHttpResponse response = httpClient.execute(method, context);

    try {
      CacheResponseStatus responseStatus = context.getCacheResponseStatus();

      if (responseStatus != null) {
        switch (responseStatus) {
          case CACHE_HIT:
            SpotifyApi.LOGGER.log(
              Level.CONFIG,
              "A response was generated from the cache with no requests sent upstream");
            break;
          case CACHE_MODULE_RESPONSE:
            SpotifyApi.LOGGER.log(
              Level.CONFIG,
              "The response was generated directly by the caching module");
            break;
          case CACHE_MISS:
            SpotifyApi.LOGGER.log(
              Level.CONFIG,
              "The response came from an upstream server");
            break;
          case VALIDATED:
            SpotifyApi.LOGGER.log(
              Level.CONFIG,
              "The response was generated from the cache after validating the entry with the origin server");
            break;
          case FAILURE:
            SpotifyApi.LOGGER.log(
              Level.CONFIG,
              "The response came from an upstream server after a cache failure");
            break;
        }
      }
    } catch (Exception e) {
      SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
    }

    return response;
  }

  @Override
  public void close() throws IOException {
    httpClient.close();
    httpClientCaching.close();
    connectionManager.close();
  }

  private String getResponseBody(CloseableHttpResponse httpResponse) throws
    IOException,
    SpotifyWebApiException,
    ParseException {

    final String responseBody = httpResponse.getEntity() != null
      ? EntityUtils.toString(httpResponse.getEntity(), "UTF-8")
      : null;
    String errorMessage = httpResponse.getReasonPhrase();

    SpotifyApi.LOGGER.log(
      Level.FINE,
      "The http response has body " + responseBody);

    if (responseBody != null && !responseBody.isEmpty()) {
      try {
        final JsonElement jsonElement = JsonParser.parseString(responseBody);

        if (jsonElement.isJsonObject()) {
          final JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

          if (jsonObject.has("error")) {
            if (jsonObject.has("error_description")) {
              errorMessage = jsonObject.get("error_description").getAsString();
            } else if (jsonObject.get("error").isJsonObject() && jsonObject.getAsJsonObject("error").has("message")) {
              errorMessage = jsonObject.getAsJsonObject("error").get("message").getAsString();
            }
          }
        }
      } catch (JsonSyntaxException e) {
        // Not necessary
      }
    }

    SpotifyApi.LOGGER.log(
      Level.FINE,
      "The http response has status code " + httpResponse.getCode());

    switch (httpResponse.getCode()) {
      case HttpStatus.SC_BAD_REQUEST:
        throw new BadRequestException(errorMessage);
      case HttpStatus.SC_UNAUTHORIZED:
        throw new UnauthorizedException(errorMessage);
      case HttpStatus.SC_FORBIDDEN:
        throw new ForbiddenException(errorMessage);
      case HttpStatus.SC_NOT_FOUND:
        throw new NotFoundException(errorMessage);
      case 429: // TOO_MANY_REQUESTS (additional status code, RFC 6585)
        // Sets "Retry-After" header as described at https://beta.developer.spotify.com/documentation/web-api/#rate-limiting
        Header header = httpResponse.getFirstHeader("Retry-After");

        if (header != null) {
          throw new TooManyRequestsException(errorMessage, Integer.parseInt(header.getValue()));
        } else {
          throw new TooManyRequestsException(errorMessage);
        }
      case HttpStatus.SC_INTERNAL_SERVER_ERROR:
        throw new InternalServerErrorException(errorMessage);
      case HttpStatus.SC_BAD_GATEWAY:
        throw new BadGatewayException(errorMessage);
      case HttpStatus.SC_SERVICE_UNAVAILABLE:
        throw new ServiceUnavailableException(errorMessage);
      default:
        if (httpResponse.getCode() >= 400 && httpResponse.getCode() < 500) {
          throw new BadRequestException(errorMessage);
        } else if (httpResponse.getCode() >= 500) {
          throw new InternalServerErrorException(errorMessage);
        }
        return responseBody;
    }
  }

  /**
   * Builder class for creating {@link SpotifyHttpManager} instances.
   * Provides a fluent interface for configuring HTTP client settings.
   */
  public static class Builder {
    private HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    private HttpHost proxy;
    private UsernamePasswordCredentials proxyCredentials;
    private Integer cacheMaxEntries = CacheConfig.DEFAULT_MAX_CACHE_ENTRIES;
    private Integer cacheMaxObjectSize = CacheConfig.DEFAULT_MAX_OBJECT_SIZE_BYTES;
    private Boolean cacheShared = Boolean.FALSE;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Sets the HTTP client connection manager.
     *
     * @param connectionManager the connection manager to use
     * @return this builder instance for method chaining
     */
    public Builder setConnectionManager(HttpClientConnectionManager connectionManager) {
      this.connectionManager = connectionManager;
      return this;
    }

    /**
     * Sets the HTTP proxy configuration.
     *
     * @param proxy the HTTP proxy host
     * @return this builder instance for method chaining
     */
    public Builder setProxy(HttpHost proxy) {
      this.proxy = proxy;
      return this;
    }

    /**
     * Sets the proxy authentication credentials.
     *
     * @param proxyCredentials the proxy credentials
     * @return this builder instance for method chaining
     */
    public Builder setProxyCredentials(UsernamePasswordCredentials proxyCredentials) {
      this.proxyCredentials = proxyCredentials;
      return this;
    }

    /**
     * Sets the maximum number of cache entries.
     *
     * @param cacheMaxEntries the maximum cache entries
     * @return this builder instance for method chaining
     */
    public Builder setCacheMaxEntries(Integer cacheMaxEntries) {
      this.cacheMaxEntries = cacheMaxEntries;
      return this;
    }

    /**
     * Sets the maximum size of cached objects.
     *
     * @param cacheMaxObjectSize the maximum cache object size
     * @return this builder instance for method chaining
     */
    public Builder setCacheMaxObjectSize(Integer cacheMaxObjectSize) {
      this.cacheMaxObjectSize = cacheMaxObjectSize;
      return this;
    }

    /**
     * Sets whether the cache is shared between multiple HTTP clients.
     *
     * @param cacheShared true if the cache should be shared, false otherwise
     * @return this builder instance for method chaining
     */
    public Builder setCacheShared(Boolean cacheShared) {
      this.cacheShared = cacheShared;
      return this;
    }

    /**
     * Sets the connection request timeout in milliseconds.
     *
     * @param connectionRequestTimeout the connection request timeout
     * @return this builder instance for method chaining
     */
    public Builder setConnectionRequestTimeout(Integer connectionRequestTimeout) {
      this.connectionRequestTimeout = connectionRequestTimeout;
      return this;
    }

    /**
     * Sets the socket timeout in milliseconds.
     *
     * @param socketTimeout the socket timeout
     * @return this builder instance for method chaining
     */
    public Builder setSocketTimeout(Integer socketTimeout) {
      this.socketTimeout = socketTimeout;
      return this;
    }

    /**
     * Builds a new {@link SpotifyHttpManager} instance with the configured settings.
     *
     * @return a new SpotifyHttpManager instance
     */
    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }
  }
}
