package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.*;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SpotifyHttpManager implements HttpManager {

  private HttpClientConnectionManager connectionManager = null;

  /**
   * Construct a new SpotifyHttpManager instance.
   * @param builder The builder.
   */
  public SpotifyHttpManager(Builder builder) {
    if (builder.connectionManager != null) {
      connectionManager = builder.connectionManager;
    } else {
      connectionManager = new PoolingHttpClientConnectionManager();
    }
  }

  @Override
  public String get(Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url, false);
    final HttpGet method = new HttpGet(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    return execute(method);
  }

  @Override
  public String post(UtilProtos.Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url, false);
    final HttpPost method = new HttpPost(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    if (url.hasJsonBody()) {
      StringEntity stringEntity = new StringEntity(
              url.getJsonBody(),
              ContentType.APPLICATION_JSON);
      method.setEntity(stringEntity);
    } else {
      method.setEntity(new UrlEncodedFormEntity(getBodyParametersAsNamedValuePairArray(url)));
    }

    return execute(method);
  }

  @Override
  public String put(UtilProtos.Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url, false);
    final HttpPut method = new HttpPut(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    if (url.hasJsonBody()) {
      StringEntity stringEntity = new StringEntity(
              url.getJsonBody(),
              ContentType.APPLICATION_JSON);
      method.setEntity(stringEntity);
    } else {
      method.setEntity(new UrlEncodedFormEntity(getBodyParametersAsNamedValuePairArray(url)));
    }

    return execute(method);
  }


  // TODO(michael): Allow JSON body to be sent.
  @Override
  public String delete(UtilProtos.Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url, false);
    final HttpDelete method = new HttpDelete(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    return execute(method);
  }

  private List<NameValuePair> getBodyParametersAsNamedValuePairArray(Url url) {
    List<NameValuePair> out = new ArrayList<>();

    for (Url.Parameter parameter : url.getBodyParametersList()) {
      if (parameter.hasName() && parameter.hasValue()) {
        out.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
      }
    }

    return out;
  }

  private CloseableHttpResponse execute(HttpRequestBase method) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    final ConnectionConfig connectionConfig = ConnectionConfig
            .custom()
            .setCharset(Charset.forName("UTF-8"))
            .build();
    final RequestConfig requestConfig = RequestConfig
            .custom()
            .setCookieSpec(CookieSpecs.DEFAULT)
            .build();
    final CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultConnectionConfig(connectionConfig)
            .setDefaultRequestConfig(requestConfig)
            .build();

    try {
      CloseableHttpResponse httpResponse = httpClient.execute(method);

      handleErrorStatusCode(httpResponse);

      String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

      handleErrorResponseBody(responseBody);

      return responseBody;
    } catch (IOException e) {
      throw new IOException();
    } finally {
      method.releaseConnection();
    }
  }

    if (statusCode >= 400 && statusCode < 500) {
      throw new BadRequestException(String.valueOf(statusCode));
    }
    if (statusCode >= 500) {
      throw new ServerErrorException(String.valueOf(statusCode));
    }

  }

  private String getResponseBody(CloseableHttpResponse httpResponse) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    StatusLine statusLine = httpResponse.getStatusLine();
    String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

    final JSONObject jsonObject = JSONObject.fromObject(responseBody);

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
          throw new BadRequestException(jsonObject.getString("error"));
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private PoolingHttpClientConnectionManager connectionManager = null;
    public Builder() {}
    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }
  }
}
