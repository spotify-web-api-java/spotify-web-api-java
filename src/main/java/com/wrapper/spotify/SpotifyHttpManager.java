package com.wrapper.spotify;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.exceptions.detailed.*;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.cache.CacheResponseStatus;
import org.apache.http.client.cache.HttpCacheContext;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.logging.Level;

public class SpotifyHttpManager implements IHttpManager {

  private static final int DEFAULT_CACHE_MAX_ENTRIES = 1000;
  private static final int DEFAULT_CACHE_MAX_OBJECT_SIZE = 8192;
  private final CloseableHttpClient httpClient;
  private final HttpHost proxy;
  private final UsernamePasswordCredentials proxyCredentials;
  private final Integer cacheMaxEntries;
  private final Integer cacheMaxObjectSize;
  private final Integer connectionRequestTimeout;
  private final Integer connectTimeout;
  private final Integer socketTimeout;

  /**
   * Construct a new SpotifyHttpManager instance.
   *
   * @param builder The builder.
   */
  public SpotifyHttpManager(Builder builder) {
    this.proxy = builder.proxy;
    this.proxyCredentials = builder.proxyCredentials;
    this.cacheMaxEntries = builder.cacheMaxEntries;
    this.cacheMaxObjectSize = builder.cacheMaxObjectSize;
    this.connectionRequestTimeout = builder.connectionRequestTimeout;
    this.connectTimeout = builder.connectTimeout;
    this.socketTimeout = builder.socketTimeout;


    CacheConfig cacheConfig = CacheConfig.custom()
            .setMaxCacheEntries(cacheMaxEntries != null ? cacheMaxEntries : DEFAULT_CACHE_MAX_ENTRIES)
            .setMaxObjectSize(cacheMaxObjectSize != null ? cacheMaxObjectSize : DEFAULT_CACHE_MAX_OBJECT_SIZE)
            .setSharedCache(false)
            .build();

    ConnectionConfig connectionConfig = ConnectionConfig
            .custom()
            .setCharset(Charset.forName("UTF-8"))
            .build();

    new BasicCredentialsProvider();
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    if (proxy != null) {
      credentialsProvider.setCredentials(
              new AuthScope(proxy.getHostName(), proxy.getPort(), null, proxy.getSchemeName()),
              proxyCredentials
      );
    }

    RequestConfig requestConfig = RequestConfig
            .custom()
            .setCookieSpec(CookieSpecs.DEFAULT)
            .setProxy(proxy)
            .setConnectionRequestTimeout(builder.connectionRequestTimeout != null
                    ? builder.connectionRequestTimeout
                    : RequestConfig.DEFAULT.getConnectionRequestTimeout())
            .setConnectTimeout(builder.connectTimeout != null
                    ? builder.connectTimeout
                    : RequestConfig.DEFAULT.getConnectTimeout())
            .setSocketTimeout(builder.socketTimeout != null
                    ? builder.socketTimeout
                    : RequestConfig.DEFAULT.getSocketTimeout())
            .build();

    this.httpClient = CachingHttpClients
            .custom()
            .setCacheConfig(cacheConfig)
            .setDefaultConnectionConfig(connectionConfig)
            .setDefaultCredentialsProvider(credentialsProvider)
            .setDefaultRequestConfig(requestConfig)
            .build();
  }

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

  public HttpHost getProxy() {
    return proxy;
  }

  public UsernamePasswordCredentials getProxyCredentials() {
    return proxyCredentials;
  }

  public Integer getCacheMaxEntries() {
    return cacheMaxEntries;
  }

  public Integer getCacheMaxObjectSize() {
    return cacheMaxObjectSize;
  }

  public Integer getConnectionRequestTimeout() {
    return connectionRequestTimeout;
  }

  public Integer getConnectTimeout() {
    return connectTimeout;
  }

  public Integer getSocketTimeout() {
    return socketTimeout;
  }

  @Override
  public String get(URI uri, Header[] headers) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (!uri.toString().equals(""));

    final HttpGet httpGet = new HttpGet();

    httpGet.setURI(uri);
    httpGet.setHeaders(headers);

    String responseBody = getResponseBody(execute(httpGet));

    httpGet.releaseConnection();

    return responseBody;
  }

  @Override
  public String post(URI uri, Header[] headers, HttpEntity body) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (!uri.toString().equals(""));

    final HttpPost httpPost = new HttpPost();

    httpPost.setURI(uri);
    httpPost.setHeaders(headers);
    httpPost.setEntity(body);

    String responseBody = getResponseBody(execute(httpPost));

    httpPost.releaseConnection();

    return responseBody;
  }

  @Override
  public String put(URI uri, Header[] headers, HttpEntity body) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (!uri.toString().equals(""));

    final HttpPut httpPut = new HttpPut();

    httpPut.setURI(uri);
    httpPut.setHeaders(headers);
    httpPut.setEntity(body);

    String responseBody = getResponseBody(execute(httpPut));

    httpPut.releaseConnection();

    return responseBody;
  }

  @Override
  public String delete(URI uri, Header[] headers, HttpEntity body) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (!uri.toString().equals(""));

    final HttpDeleteBody httpDelete = new HttpDeleteBody();

    httpDelete.setURI(uri);
    httpDelete.setHeaders(headers);
    httpDelete.setEntity(body);

    String responseBody = getResponseBody(execute(httpDelete));

    httpDelete.releaseConnection();

    return responseBody;
  }

  private HttpResponse execute(HttpRequestBase method) throws
          IOException {
    HttpCacheContext context = HttpCacheContext.create();
    HttpResponse response = httpClient.execute(method, context);

    try {
      CacheResponseStatus responseStatus = context.getCacheResponseStatus();
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
      }
    } catch (Exception e) {
      SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
    }

    return response;
  }

  private String getResponseBody(HttpResponse httpResponse) throws
          IOException,
          SpotifyWebApiException {
    final StatusLine statusLine = httpResponse.getStatusLine();
    final String responseBody = httpResponse.getEntity() != null
            ? EntityUtils.toString(httpResponse.getEntity(), "UTF-8")
            : null;
    String errorMessage = statusLine.getReasonPhrase();

    if (responseBody != null && !responseBody.equals("")) {
      try {
        final JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();

        if (jsonObject.has("error")) {
          if (jsonObject.has("error_description")) {
            errorMessage = jsonObject.get("error_description").getAsString();
          } else if (jsonObject.get("error").isJsonObject() && jsonObject.getAsJsonObject("error").has("message")) {
            errorMessage = jsonObject.getAsJsonObject("error").get("message").getAsString();
          }
        }
      } catch (JsonSyntaxException e) {
        // Not necessary
      }
    }

    switch (statusLine.getStatusCode()) {
      case HttpStatus.SC_OK:
        return responseBody;
      case HttpStatus.SC_CREATED:
        return responseBody;
      case HttpStatus.SC_ACCEPTED:
        return responseBody;
      case HttpStatus.SC_NO_CONTENT:
        return responseBody;
      case HttpStatus.SC_NOT_MODIFIED:
        return responseBody;
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
        return responseBody;
    }
  }

  public static class Builder {
    private HttpHost proxy;
    private UsernamePasswordCredentials proxyCredentials;
    private Integer cacheMaxEntries;
    private Integer cacheMaxObjectSize;
    private Integer connectionRequestTimeout;
    private Integer connectTimeout;
    private Integer socketTimeout;

    public Builder setProxy(HttpHost proxy) {
      this.proxy = proxy;
      return this;
    }

    public Builder setProxyCredentials(UsernamePasswordCredentials proxyCredentials) {
      this.proxyCredentials = proxyCredentials;
      return this;
    }

    public Builder setCacheMaxEntries(Integer cacheMaxEntries) {
      this.cacheMaxEntries = cacheMaxEntries;
      return this;
    }

    public Builder setCacheMaxObjectSize(Integer cacheMaxObjectSize) {
      this.cacheMaxObjectSize = cacheMaxObjectSize;
      return this;
    }

    public Builder setConnectionRequestTimeout(Integer connectionRequestTimeout) {
      this.connectionRequestTimeout = connectionRequestTimeout;
      return this;
    }

    public Builder setConnectTimeout(Integer connectTimeout) {
      this.connectTimeout = connectTimeout;
      return this;
    }

    public Builder setSocketTimeout(Integer socketTimeout) {
      this.socketTimeout = socketTimeout;
      return this;
    }

    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }
  }
}
