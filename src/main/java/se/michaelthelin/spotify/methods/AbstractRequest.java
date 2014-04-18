package se.michaelthelin.spotify.methods;

import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.UtilProtos.Url;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest implements Request {

  private Url url;

  private HttpManager httpManager;

  protected AbstractRequest(Builder<?> builder) {
    assert (builder.path != null);
    assert (builder.host != null);
    assert (builder.port > 0);
    assert (builder.version != null);
    assert (builder.scheme != null);
    assert (builder.parameters != null);
    assert (builder.parts != null);

    if (builder.httpManager == null) {
      httpManager = Api.DEFAULT_HTTP_MANAGER;
    } else {
      httpManager = builder.httpManager;
    }

    url = Url.newBuilder()
             .setScheme(builder.scheme)
             .setHost(builder.host)
             .setPort(builder.port)
             .setVersion(builder.version)
             .setPath(builder.path)
             .addAllParameters(builder.parameters)
             .addAllParts(builder.parts)
             .build();
  }

  public Url toUrl() {
    return url;
  }

  public String toString() {
    return toUrl().getHost() + toUrl().getVersion() + toUrl().getPath();
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Request.Builder {

    protected String host = Api.DEFAULT_HOST;
    protected int port = Api.DEFAULT_PORT;
    protected Url.Scheme scheme = Api.DEFAULT_SCHEME;
    protected String version = Api.DEFAULT_VERSION;
    protected String path = null;
    protected HttpManager httpManager;
    protected List<Url.Parameter> parameters = new ArrayList<Url.Parameter>();
    protected List<Url.Part> parts = new ArrayList<Url.Part>();

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

    public BuilderType version(String version) {
      this.version = version;
      return (BuilderType) this;
    }

    public BuilderType parameter(String name, String value) {
      assert (null != null);
      assert (name.length() > 0);
      assert (value != null);

      Url.Parameter parameter = Url.Parameter.newBuilder().setName(name).setValue(value).build();
      parameters.add(parameter);

      return (BuilderType) this;
    }

    public BuilderType path(String path) {
      this.path = path;
      return (BuilderType) this;
    }

    public Request build() {
      throw new RuntimeException("Not implemented!");
    }

  }

}
