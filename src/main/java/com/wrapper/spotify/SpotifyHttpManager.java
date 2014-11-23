package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.ServerErrorException;
import com.wrapper.spotify.exceptions.WebApiException;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.EmptyResponseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyHttpManager implements HttpManager {

  private HttpConnectionManager connectionManager = null;

  /**
   * Construct a new SpotifyHttpManager instance.
   * @param builder The builder.
   */
  public SpotifyHttpManager(Builder builder) {
    if (builder.connectionManager != null) {
      connectionManager = builder.connectionManager;
    } else {
      connectionManager = new MultiThreadedHttpConnectionManager();
    }
  }

  @Override
  public String get(Url url) throws WebApiException, IOException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
    final GetMethod method = new GetMethod(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }
    method.setQueryString(getParametersAsNamedValuePairArray(url));
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

    return execute(method);
  }

  @Override
  public String post(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
    final PostMethod method = new PostMethod(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }

    if (url.hasJsonBody()) {

      StringRequestEntity requestEntity = new StringRequestEntity(
              url.getJsonBody(),
              "application/json",
              "UTF-8");
      method.setRequestEntity(requestEntity);
    } else {
      method.setRequestBody(getBodyParametersAsNamedValuePairArray(url));
    }
    method.setQueryString(getParametersAsNamedValuePairArray(url));
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

    return execute(method);
  }

  @Override
  public String put(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
    final PutMethod method = new PutMethod(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }

    if (url.hasJsonBody()) {

      StringRequestEntity requestEntity = new StringRequestEntity(
          url.getJsonBody(),
          "application/json",
          "UTF-8");
      method.setRequestEntity(requestEntity);
    } else {
      method.setRequestBody(String.valueOf(getBodyParametersAsNamedValuePairArray(url)));
    }
    method.setQueryString(getParametersAsNamedValuePairArray(url));
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

    return execute(method);
  }


  // TODO(michael): Allow JSON body to be sent.
  @Override
  public String delete(UtilProtos.Url url) throws IOException, WebApiException {
    assert (url != null);

    final String uri = UrlUtil.assemble(url);
    final DeleteMethod method = new DeleteMethod(uri);

    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }

    method.setQueryString(getParametersAsNamedValuePairArray(url));
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

    return execute(method);
  }

  private NameValuePair[] getParametersAsNamedValuePairArray(Url url) {
    List<NameValuePair> out = new ArrayList<NameValuePair>();
    for (Url.Parameter parameter : url.getParametersList()) {
      if (parameter.hasName() && parameter.hasValue()) {
        out.add(new NameValuePair(parameter.getName(), parameter.getValue().toString()));
      }
    }
    return out.toArray(new NameValuePair[out.size()]);
  }

  private NameValuePair[] getBodyParametersAsNamedValuePairArray(Url url) {
    List<NameValuePair> out = new ArrayList<NameValuePair>();
    for (Url.Parameter parameter : url.getBodyParametersList()) {
      if (parameter.hasName() && parameter.hasValue()) {
        out.add(new NameValuePair(parameter.getName(), parameter.getValue().toString()));
      }
    }
    return out.toArray(new NameValuePair[out.size()]);
  }

  private String execute(HttpMethod method) throws WebApiException, IOException {
    final HttpClient httpClient = new HttpClient(connectionManager);
    try {
      httpClient.executeMethod(method);

      handleErrorStatusCode(method);
      String responseBody = method.getResponseBodyAsString();

      handleErrorResponseBody(responseBody);
      return responseBody;

    } catch (IOException e) {
      throw new IOException();
    } finally {
      method.releaseConnection();
    }
  }

  /*
   * Todo: Error handling could be more granular and throw a different exception depending on status code.
   * It could also look into the JSON object to find an error message.
   */
  private void handleErrorStatusCode(HttpMethod method) throws BadRequestException, ServerErrorException {
    int statusCode = method.getStatusCode();

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

    private HttpConnectionManager connectionManager = null;

    public Builder() {}

    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }

  }

}
