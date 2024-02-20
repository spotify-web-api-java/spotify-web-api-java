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
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.cache.CacheConfig;
import org.apache.hc.client5.http.impl.cache.CachingHttpClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

public class SpotifyHttpManager implements IHttpManager {

  private static final int DEFAULT_CACHE_MAX_ENTRIES = 1000;
  private static final int DEFAULT_CACHE_MAX_OBJECT_SIZE = 8192;
  private static final Gson GSON = new Gson();
  private final CloseableHttpClient httpClient;
  private final CloseableHttpClient httpClientCaching;
  private final HttpHost proxy;
  private final UsernamePasswordCredentials proxyCredentials;
  private final Integer cacheMaxEntries;
  private final Integer cacheMaxObjectSize;
  private final Integer connectionRequestTimeout;
  private final Integer connectTimeout;
  private final Integer socketTimeout;
  private final SpotifyHttpManagerExecute httpExecute;


  /**
   * Construct a new SpotifyHttpManager instance.
   *
   * @param builder The builder.
   */
  public SpotifyHttpManager(SpotifyHttpManagerBuilder builder) {
    this.proxy = builder.getProxy();
    this.proxyCredentials = builder.getProxyCredentials();
    this.cacheMaxEntries = builder.getCacheMaxEntries();
    this.cacheMaxObjectSize = builder.getCacheMaxObjectSize();
    this.connectionRequestTimeout = builder.getConnectionRequestTimeout();
    this.connectTimeout = builder.getConnectTimeout();
    this.socketTimeout = builder.getSocketTimeout();

    CacheConfig cacheConfig = CacheConfig.custom()
      .setMaxCacheEntries(cacheMaxEntries != null ? cacheMaxEntries : DEFAULT_CACHE_MAX_ENTRIES)
      .setMaxObjectSize(cacheMaxObjectSize != null ? cacheMaxObjectSize : DEFAULT_CACHE_MAX_OBJECT_SIZE)
      .setSharedCache(false)
      .build();

    BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    this.httpExecute = new SpotifyHttpManagerExecute();

    if (proxy != null) {
      credentialsProvider.setCredentials(
        new AuthScope(null, proxy.getHostName(), proxy.getPort(), null, proxy.getSchemeName()),
        proxyCredentials
      );
    }

    ConnectionConfig connectionConfig = ConnectionConfig
      .custom()
      .setConnectTimeout(builder.getConnectTimeout() != null
        ? Timeout.ofMilliseconds(builder.getConnectTimeout())
        : ConnectionConfig.DEFAULT.getConnectTimeout())
      .build();
    BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();
    connectionManager.setConnectionConfig(connectionConfig);
    RequestConfig requestConfig = RequestConfig
      .custom()
      .setCookieSpec(StandardCookieSpec.STRICT)
      .setConnectionRequestTimeout(builder.getConnectionRequestTimeout() != null
        ? Timeout.ofMilliseconds(builder.getConnectionRequestTimeout())
        : RequestConfig.DEFAULT.getConnectionRequestTimeout())
      .setResponseTimeout(builder.getSocketTimeout() != null
        ? Timeout.ofMilliseconds(builder.getSocketTimeout())
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
    SpotifyWebApiException,
    ParseException {
    assert (uri != null);
    assert (!uri.toString().isEmpty());

    final HttpGet httpGet = new HttpGet(uri);

    httpGet.setHeaders(headers);
    SpotifyApi.LOGGER.log(
      Level.FINE,
      "GET request uses these headers: " + GSON.toJson(headers));

    String responseBody = httpExecute.getResponseBody(httpExecute.execute(httpClientCaching, httpGet));

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

    String responseBody = httpExecute.getResponseBody(httpExecute.execute(httpClient, httpPost));

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

    String responseBody = httpExecute.getResponseBody(httpExecute.execute(httpClient, httpPut));

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

    String responseBody = httpExecute.getResponseBody(httpExecute.execute(httpClient, httpDelete));

    httpDelete.reset();

    return responseBody;
  }
}
