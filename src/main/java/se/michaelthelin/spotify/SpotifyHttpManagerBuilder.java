package se.michaelthelin.spotify;

import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.core5.http.HttpHost;

public class SpotifyHttpManagerBuilder {
  private HttpHost proxy;
   UsernamePasswordCredentials proxyCredentials;
  private Integer cacheMaxEntries;
  private Integer cacheMaxObjectSize;
  private Integer connectionRequestTimeout;
  private Integer connectTimeout;
  private Integer socketTimeout;
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
  public SpotifyHttpManagerBuilder setProxy(HttpHost proxy) {
    this.proxy = proxy;
    return this;
  }
  public SpotifyHttpManagerBuilder setProxyCredentials(UsernamePasswordCredentials proxyCredentials) {
    this.proxyCredentials = proxyCredentials;
    return this;
  }

  public SpotifyHttpManagerBuilder setCacheMaxEntries(Integer cacheMaxEntries) {
    this.cacheMaxEntries = cacheMaxEntries;
    return this;
  }

  public SpotifyHttpManagerBuilder setCacheMaxObjectSize(Integer cacheMaxObjectSize) {
    this.cacheMaxObjectSize = cacheMaxObjectSize;
    return this;
  }

  public SpotifyHttpManagerBuilder setConnectionRequestTimeout(Integer connectionRequestTimeout) {
    this.connectionRequestTimeout = connectionRequestTimeout;
    return this;
  }

  public SpotifyHttpManagerBuilder setConnectTimeout(Integer connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

  public SpotifyHttpManagerBuilder setSocketTimeout(Integer socketTimeout) {
    this.socketTimeout = socketTimeout;
    return this;
  }

  public SpotifyHttpManager build() {
    return new SpotifyHttpManager(this);
  }
}
