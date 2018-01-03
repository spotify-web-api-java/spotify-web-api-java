package com.wrapper.spotify;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.exceptions.detailed.*;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

public class SpotifyHttpManager implements IHttpManager {

  private final HttpHost proxy;
  private final UsernamePasswordCredentials proxyCredentials;

  /**
   * Construct a new SpotifyHttpManager instance.
   *
   * @param builder The builder.
   */
  public SpotifyHttpManager(Builder builder) {
    this.proxy = builder.proxy;
    this.proxyCredentials = builder.proxyCredentials;
  }

  public HttpHost getProxy() {
    return proxy;
  }

  public UsernamePasswordCredentials getProxyCredentials() {
    return proxyCredentials;
  }

  @Override
  public String get(URI uri, Header[] headers) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (headers != null);

    final HttpGet httpGet = new HttpGet();

    httpGet.setURI(uri);
    httpGet.setHeaders(headers);

    String responseBody = getResponseBody(execute(httpGet));

    httpGet.releaseConnection();

    return responseBody;
  }

  @Override
  public String post(URI uri, Header[] headers, List<NameValuePair> postParameters) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (headers != null);
    assert (postParameters != null);

    final HttpPost httpPost = new HttpPost();

    httpPost.setURI(uri);
    httpPost.setHeaders(headers);
    httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

    String responseBody = getResponseBody(execute(httpPost));

    httpPost.releaseConnection();

    return responseBody;
  }

  @Override
  public String put(URI uri, Header[] headers, List<NameValuePair> putParameters) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (headers != null);
    assert (putParameters != null);

    final HttpPut httpPut = new HttpPut();

    httpPut.setURI(uri);
    httpPut.setHeaders(headers);
    httpPut.setEntity(new UrlEncodedFormEntity(putParameters));

    String responseBody = getResponseBody(execute(httpPut));
    httpPut.releaseConnection();

    return responseBody;
  }

  @Override
  public String delete(URI uri, Header[] headers) throws
          IOException,
          SpotifyWebApiException {
    assert (uri != null);
    assert (headers != null);

    final HttpDelete httpDelete = new HttpDelete();

    httpDelete.setURI(uri);
    httpDelete.setHeaders(headers);

    String responseBody = getResponseBody(execute(httpDelete));

    httpDelete.releaseConnection();

    return responseBody;
  }

  private CloseableHttpResponse execute(HttpRequestBase method) throws
          IOException {
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
            new AuthScope(proxy.getHostName(), proxy.getPort(), null, proxy.getSchemeName()),
            proxyCredentials
    );
    final ConnectionConfig connectionConfig = ConnectionConfig
            .custom()
            .setCharset(Charset.forName("UTF-8"))
            .build();
    final RequestConfig requestConfig = RequestConfig
            .custom()
            .setCookieSpec(CookieSpecs.DEFAULT)
            .setProxy(proxy)
            .build();
    final CloseableHttpClient httpClient = HttpClients
            .custom()
            .setDefaultConnectionConfig(connectionConfig)
            .setDefaultCredentialsProvider(credentialsProvider)
            .setDefaultRequestConfig(requestConfig)
            .build();

    return httpClient.execute(method);
  }

  private String getResponseBody(CloseableHttpResponse httpResponse) throws
          IOException,
          SpotifyWebApiException {
    StatusLine statusLine = httpResponse.getStatusLine();
    String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

    final JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();

    switch (statusLine.getStatusCode()) {
      case HttpStatus.SC_OK:
        return responseBody;
      case HttpStatus.SC_CREATED:
        return responseBody;
      case HttpStatus.SC_ACCEPTED:
        return responseBody;
      case HttpStatus.SC_NO_CONTENT:
        throw new NoContentException(statusLine.getReasonPhrase());
      case HttpStatus.SC_NOT_MODIFIED:
        return responseBody;
      case HttpStatus.SC_BAD_REQUEST:
        if (jsonObject.has("error")) {
          throw new BadRequestException(jsonObject.get("error").getAsString());
        }
      case HttpStatus.SC_UNAUTHORIZED:
        throw new UnauthorizedException(statusLine.getReasonPhrase());
      case HttpStatus.SC_FORBIDDEN:
        throw new ForbiddenException(statusLine.getReasonPhrase());
      case HttpStatus.SC_NOT_FOUND:
        throw new NotFoundException(statusLine.getReasonPhrase());
      case 429: // TOO_MANY_REQUESTS (additional status code, RFC 6585)
        throw new TooManyRequestsException(statusLine.getReasonPhrase());
      case HttpStatus.SC_INTERNAL_SERVER_ERROR:
        throw new InternalServerErrorException(statusLine.getReasonPhrase());
      case HttpStatus.SC_BAD_GATEWAY:
        throw new BadGatewayException(statusLine.getReasonPhrase());
      case HttpStatus.SC_SERVICE_UNAVAILABLE:
        throw new ServiceUnavailableException(statusLine.getReasonPhrase());
      default:
        return responseBody;
    }
  }

  public static class Builder {
    private HttpHost proxy;
    private UsernamePasswordCredentials proxyCredentials;

    public Builder setProxy(HttpHost proxy) {
      this.proxy = proxy;
      return this;
    }

    public Builder setProxyCredentials(UsernamePasswordCredentials proxyCredentials) {
      this.proxyCredentials = proxyCredentials;
      return this;
    }

    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }
  }
}
