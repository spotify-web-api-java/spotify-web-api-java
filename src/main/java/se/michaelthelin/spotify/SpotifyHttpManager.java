package se.michaelthelin.spotify;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import se.michaelthelin.spotify.UtilProtos.Url;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyHttpManager implements HttpManager {

  private final String clientId;
  private final String clientSecret;
  private final String code;
  private final String redirectUri;
  private String accessToken;
  private HttpConnectionManager connectionManager = null;

  /**
   * Construct a new SpotifyHttpManager instance.
   */
  public SpotifyHttpManager(Builder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.redirectUri = builder.redirectUri;
    this.code = builder.code;
    this.accessToken = builder.accessToken;

    if (builder.connectionManager != null) {
      connectionManager = builder.connectionManager;
    } else {
      connectionManager = new MultiThreadedHttpConnectionManager();
    }
  }

  @Override
  public String get(Url url) throws UnexpectedResponseException, IOException {
    assert (url != null);
    final String uri = UrlUtil.assemble(url);
    final GetMethod method = new GetMethod(uri);
    method.setQueryString(getParametersAsNamedValuePairArray(url));
    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(
            HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    return execute(method);
  }

  @Override
  public String post(UtilProtos.Url url) throws IOException, UnexpectedResponseException {
    assert (url != null);
    String uri = UrlUtil.assemble(url);
    PostMethod method = new PostMethod(uri);
    method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    method.setQueryString(getParametersAsNamedValuePairArray(url));
    method.setRequestBody(getBodyParametersAsNamedValuePairArray(url));
    for (Url.Parameter header : url.getHeaderParametersList()) {
      method.setRequestHeader(header.getName(), header.getValue());
    }
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

  private String execute(HttpMethod method) throws UnexpectedResponseException, IOException {
    HttpClient httpClient = new HttpClient(connectionManager);
    try {
      httpClient.executeMethod(method);
      String responseBody = method.getResponseBodyAsString();
      if (responseBody == null) {
        throw new UnexpectedResponseException();
      }
      return responseBody;
    } catch (IOException e) {
      throw new IOException();
    } finally {
      method.releaseConnection();
    }
  }

  @Override
  public String delete(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String put(UtilProtos.Url url) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public boolean hasAccessToken() {
    return accessToken != null;
  }

  @Override
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public boolean hasBaseCredentials() {
    return this.clientId != null &&
            this.clientSecret != null &&
            this.code != null &&
            this.redirectUri != null;
  }

  @Override
  public String authenticatedGet(Url urlPrototype) throws IOException, UnexpectedResponseException {
    Url.Builder urlBuilder = Url.newBuilder(urlPrototype);
    Url decoratedUrl = decorateWithAuthentication(urlBuilder);
    return get(decoratedUrl);
  }

  @Override
  public String authenticatedPost(Url url) throws IOException, UnexpectedResponseException {
    Url.Builder urlBuilder = Url.newBuilder(url);
    Url decoratedUrl = decorateWithAuthentication(urlBuilder);
    return post(decoratedUrl);
  }

  @Override
  public String getClientId() {
    return clientId;
  }

  @Override
  public String getClientSecret() {
    return clientSecret;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getRedirectUri() {
    return redirectUri;
  }

  private Url decorateWithAuthentication(Url.Builder urlBuilder) {
    List<Url.Parameter> headerParameters = new ArrayList<Url.Parameter>();
    headerParameters.add(Url.Parameter.newBuilder().setName("Authorization").setValue("Bearer " + accessToken).build());
    return urlBuilder.addAllHeaderParameters(headerParameters).build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private String accessToken;

    private HttpConnectionManager connectionManager = null;

    public Builder() {}

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder redirectUri(String refreshUri) {
      this.redirectUri = refreshUri;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public SpotifyHttpManager build() {
      return new SpotifyHttpManager(this);
    }

  }

}
