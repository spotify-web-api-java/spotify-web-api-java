package se.michaelthelin.spotify.methods;

import net.sf.json.JSONObject;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.UrlUtil;
import se.michaelthelin.spotify.UtilProtos.Url;
import se.michaelthelin.spotify.exceptions.*;
import se.michaelthelin.spotify.models.TokenResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private final boolean authenticationRequired;
  private Url url;

  private HttpManager httpManager;

  public Url toUrl() {
    return url;
  }

  public String toString() {
    return UrlUtil.assemble(url);
  }

  public String getJson() throws IOException, UnexpectedResponseException, ErrorResponseException, NoCredentialsException {
    if (authenticationRequired && !httpManager.hasAccessToken() && !httpManager.hasBaseCredentials()) {
      throw new NoCredentialsException("Request requires authentication");
    } else if (authenticationRequired && !httpManager.hasAccessToken()) {
      String clientId = httpManager.getClientId();
      String clientSecret = httpManager.getClientSecret();
      String code = httpManager.getCode();
      String redirectUri = httpManager.getRedirectUri();
      TokenResponse response = TokenRequest
              .builder()
              .authorizationHeader(clientId, clientSecret)
              .grantType("authorization_code")
              .code(code)
              .redirectUri(redirectUri)
              .build()
              .post();
      httpManager.setAccessToken(response.getAccessToken());
      return httpManager.authenticatedGet(url);
    } else if (authenticationRequired) {
      return httpManager.authenticatedGet(url);
    } else {
      return httpManager.get(url);
    }
  }

  public String postJson() throws IOException, UnexpectedResponseException, ErrorResponseException, NoCredentialsException {
    if (authenticationRequired && !httpManager.hasAccessToken() && !httpManager.hasBaseCredentials()) {
      throw new NoCredentialsException("Request requires authentication");
    } else if (authenticationRequired && !httpManager.hasAccessToken()) {
      String clientId = httpManager.getClientId();
      String clientSecret = httpManager.getClientSecret();
      String code = httpManager.getCode();
      String redirectUri = httpManager.getRedirectUri();
      TokenResponse response = TokenRequest
              .builder()
              .authorizationHeader(clientId, clientSecret)
              .grantType("authorization_code")
              .code(code)
              .redirectUri(redirectUri)
              .build()
              .post();
      httpManager.setAccessToken(response.getAccessToken());
      return httpManager.authenticatedPost(url);
    } else if (authenticationRequired) {
      return httpManager.authenticatedPost(url);
    } else {
      return httpManager.post(url);
    }
  }

  protected boolean errorInJson(JSONObject jsonObject) {
    return (!jsonObject.isNullObject() && jsonObject.has("error"));
  }

  protected Exception getExceptionFromJson(JSONObject jsonObject) {
    assert (jsonObject != null);
    assert (errorInJson(jsonObject));

    JSONObject error = jsonObject.getJSONObject("error");
    if (error.getString("type").equals("bad_field")) {
      return new BadFieldException();
    }
    if (error.getString("type").equals("not_found")) {
      return new NotFoundException();
    }
    return new IllegalStateException("Should not get here");
  }

  protected void throwIfErrorsInResponse(JSONObject jsonObject) throws NotFoundException, BadFieldException {
    assert (jsonObject != null);

    if (errorInJson(jsonObject)) {
      JSONObject error = jsonObject.getJSONObject("error");
      if (error.getString("type").equals("bad_field")) {
        throw new BadFieldException();
      }
      if (error.getString("type").equals("not_found")) {
        throw new NotFoundException();
      }
    }
  }

  public AbstractRequest(Builder<?> builder) {
    assert (builder.path != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.scheme != null);
    assert (builder.parameters != null);
    assert (builder.parts != null);
    assert (builder.bodyParameters != null);
    assert (builder.headerParameters != null);

    if (builder.httpManager == null) {
      httpManager = Api.DEFAULT_HTTP_MANAGER;
    } else {
      httpManager = builder.httpManager;
    }

    authenticationRequired = builder.authenticationRequired;

    url = Url.newBuilder()
             .setScheme(builder.scheme)
             .setHost(builder.host)
             .setPort(builder.port)
             .setPath(builder.path)
             .addAllParameters(builder.parameters)
             .addAllBodyParameters(builder.bodyParameters)
             .addAllHeaderParameters(builder.headerParameters)
             .addAllParts(builder.parts)
             .build();
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    protected Url.Scheme scheme = Api.DEFAULT_SCHEME;
    protected String host = Api.DEFAULT_HOST;
    protected int port = Api.DEFAULT_PORT;
    protected String path = null;
    protected HttpManager httpManager;
    protected List<Url.Parameter> parameters = new ArrayList<Url.Parameter>();
    protected List<Url.Parameter> headerParameters = new ArrayList<Url.Parameter>();
    protected List<Url.Part> parts = new ArrayList<Url.Part>();
    protected List<Url.Parameter> bodyParameters = new ArrayList<Url.Parameter>();

    private boolean authenticationRequired = false;

    public BuilderType httpManager(HttpManager httpManager) {
      this.httpManager = httpManager;
      return (BuilderType) this;
    }

    public BuilderType host(String host) {
      this.host = host;
      return (BuilderType) this;
    }

    public BuilderType port(int port) {
      this.port = port;
      return (BuilderType) this;
    }

    public BuilderType scheme(Url.Scheme scheme) {
      this.scheme = scheme;
      return (BuilderType) this;
    }

    public BuilderType parameter(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter = Url.Parameter.newBuilder().setName(name).setValue(value).build();
      parameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType body(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter = Url.Parameter.newBuilder().setName(name).setValue(value).build();
      bodyParameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType header(String name, String value) {
      assert (name != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter= Url.Parameter.newBuilder().setName(name).setValue(value).build();
      headerParameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType part(Url.Part part) {
      assert (part != null);
      parts.add(part);
      return (BuilderType) this;
    }

    public BuilderType path(String path) {
      this.path = path;
      return (BuilderType) this;
    }

    public BuilderType authenticationRequired(boolean authenticationRequired) {
      this.authenticationRequired = authenticationRequired;
      return (BuilderType) this;
    }

  }

}
