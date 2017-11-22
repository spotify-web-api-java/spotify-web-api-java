package com.wrapper.spotify;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public abstract class UrlUtil {

  private static String getSchemeName(final UtilProtos.Url url) {
    switch (url.getScheme()) {
      case HTTP:
        return "http";
      case HTTPS:
        return "https";
      default:
        throw new IllegalArgumentException();
    }
  }

  public static URI toUri(final UtilProtos.Url url) {
    return toUri(url, true);
  }

  public static URI toUri(final UtilProtos.Url url, final boolean withQueryParameters) {
    final URIBuilder builder = new URIBuilder()
            .setScheme(getSchemeName(url))
            .setHost(url.getHost())
            .setPath(url.getPath())
            .setPort(url.getPort());
    if (withQueryParameters) {
      builder.setParameters(getParametersList(url));
    }
    try {
      return builder.build();
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String toString(final UtilProtos.Url url) {
    return toUri(url, true).toString();
  }

  public static String toString(final UtilProtos.Url url, final boolean withQueryParameters) {
    return toUri(url, withQueryParameters).toString();
  }

  public static Header[] getHeaders(final UtilProtos.Url url) {
    final Header[] headers = new Header[url.getHeaderParametersCount()];
    return url.getHeaderParametersList().toArray(headers);
  }

  public static String usernameToUri(String username){
    String uriString = "";
    try {
       uriString = URLEncoder.encode(username, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return uriString
            .replaceAll("!", "%21")
            .replaceAll("\\?", "%3F")
            .replaceAll("\\+", "%2B")
            .replaceAll("=", "%3D");
  }

  public static List<NameValuePair> getParametersList(UtilProtos.Url url) {
    final List<UtilProtos.Url.Parameter> parameters = url.getParametersList();
    List<NameValuePair> result = new ArrayList<>();
    for (final UtilProtos.Url.Parameter p : parameters) {
      result.add(new NameValuePair() {
        @Override
        public String getName() {
          return p.getName();
        }

        @Override
        public String getValue() {
          return p.getValue();
        }
      });
    }
    return result;
  }
}
