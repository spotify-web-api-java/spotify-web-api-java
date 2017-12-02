package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.ServerErrorException;
import com.wrapper.spotify.exceptions.WebApiException;
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
  public String get(Url url) throws WebApiException, IOException {
    assert (url != null);

    String uri = UrlUtil.assemble(url);

    if (url.getParametersList() != null && url.getParametersList().size() > 0) {
      uri = uri + parseQueryStringFromParameters(url.getParametersList());
    }

    final HttpGet method = new HttpGet(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    return execute(method);
  }

  @Override
  public String post(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
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
  public String put(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
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
  public String delete(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
    final HttpDelete method = new HttpDelete(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setHeader(header.getName(), header.getValue());
    }

    return execute(method);
  }

  private String parseQueryStringFromParameters(List<Url.Parameter> parameterList) {
    StringBuilder queryStrBuilder = new StringBuilder();
    String queryStr;

    queryStrBuilder.append("?");

    for (Url.Parameter param : parameterList)
    {
      queryStrBuilder.append(param.getName())
                     .append("=")
                     .append(param.getValue())
                     .append("&");
    }

    queryStr = queryStrBuilder.toString()
                              .substring(0, queryStrBuilder.length() - 1);

    return queryStr;
  }

  private List<NameValuePair> getBodyParametersAsNamedValuePairArray(Url url) {
    List<NameValuePair> out = new ArrayList<>();

    for (Url.Parameter parameter : url.getBodyParametersList()) {
      if (parameter.hasName() && parameter.hasValue()) {
        out.add(new BasicNameValuePair(parameter.getName(), parameter.getValue().toString()));
      }
    }

    return out;
  }

  private String execute(HttpRequestBase method) throws WebApiException, IOException {
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

  /*
   * TODO: Error handling could be more granular and throw a different exception depending on status code.
   * It could also look into the JSON object to find an error message.
   */
  private void handleErrorStatusCode(CloseableHttpResponse httpResponse) throws BadRequestException, ServerErrorException {
    int statusCode = httpResponse.getStatusLine().getStatusCode();

    if (statusCode >= 400 && statusCode < 500) {
      throw new BadRequestException(String.valueOf(statusCode));
    }
    if (statusCode >= 500) {
      throw new ServerErrorException(String.valueOf(statusCode));
    }

  }

  private void handleErrorResponseBody(String responseBody) throws WebApiException {
    if (responseBody == null) {
      throw new EmptyResponseException("No response body");
    }

    if (!responseBody.equals("") && responseBody.startsWith("{")) {
      final JSONObject jsonObject = JSONObject.fromObject(responseBody);

      if (jsonObject.has("error")) {
        throw new WebApiException(jsonObject.getString("error"));
      }
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
