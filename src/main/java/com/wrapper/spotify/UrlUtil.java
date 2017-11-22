package com.wrapper.spotify;

import com.google.common.collect.ImmutableMap;
import com.wrapper.spotify.UtilProtos.Url.Scheme;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public abstract class UrlUtil {

  private final static Map<Scheme, String> SCHEME_NAMES =
          new ImmutableMap.Builder<Scheme, String>()
                  .put(Scheme.HTTP, "http")
                  .put(Scheme.HTTPS, "https")
                  .build();

  public static String assemble(UtilProtos.Url url)  {
    try {
      if (!url.getPath().contains("users")) {
        final URI uri = new URIBuilder()
                .setScheme(SCHEME_NAMES.get(url.getScheme()))
                .setPath(url.getPath())
                .setHost(url.getHost())
                .setPort(url.getPort())
                .build();
        return uri.toString();
      } else {
        final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), null, null, null);
        return uri.toString() + url.getPath();
      }
    } catch (Throwable e) {
      throw new IllegalStateException(e);
    }
  }
  
  public static String assembleWithQueryParameters(UtilProtos.Url url) {
    try {
      final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), url.getPath(), getParametersListAsString(url), null);
      return uri.toString();
    } catch (Throwable e) {
      throw new IllegalStateException(e);
    }
  }

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

  private static String getParametersListAsString(UtilProtos.Url url) {
    final List<UtilProtos.Url.Parameter> parameters = url.getParametersList();
    StringBuilder stringBuilder = new StringBuilder();
    boolean first = true;
    for (UtilProtos.Url.Parameter parameter : parameters) {
      if (!first) {
        stringBuilder.append("&");
      } else {
        first = false;
      }
      stringBuilder.append(parameter.getName());
      stringBuilder.append("=");
      stringBuilder.append(parameter.getValue());
    }
    return stringBuilder.toString();
  }
}
