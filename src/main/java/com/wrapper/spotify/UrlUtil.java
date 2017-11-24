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

  public static String escapeUsername(String username) {
    String escapedUsername = "";
    try {
      escapedUsername = URLEncoder.encode(username, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return escapedUsername
            .replaceAll("!", "%21")
            .replaceAll("\\?", "%3F")
            .replaceAll("\\+", "%2B")
            .replaceAll("=", "%3D");
  }

  public static Header[] getHeaders(final UtilProtos.Url url) {
    final Header[] headers = new Header[url.getHeaderParametersCount()];
    return url.getHeaderParametersList().toArray(headers);
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

  public static String urlToString(final UtilProtos.Url url) {
    return urlToUri(url, true).toString();
  }

  public static String urlToString(final UtilProtos.Url url, final boolean withQueryParameters) {
    return urlToUri(url, withQueryParameters).toString();
  }

  public static URI urlToUri(final UtilProtos.Url url) {
    return urlToUri(url, true);
  }

  public static URI urlToUri(final UtilProtos.Url url, final boolean withQueryParameters) {
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
}
