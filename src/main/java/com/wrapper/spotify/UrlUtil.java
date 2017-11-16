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
        final URIBuilder uriBuilder = new URIBuilder();
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

  public static String userToUri(String userName){
    String uriString = "";
    try {
       uriString = URLEncoder.encode(userName, "UTF-8");
    } catch (Throwable e) {
       e.printStackTrace();
    }
    uriString = uriString.replaceAll("!", toHex("!"));
    uriString = uriString.replaceAll("\\?", toHex("?"));
    uriString = uriString.replaceAll("\\+", toHex("+"));
    uriString = uriString.replaceAll("=", toHex("="));
    return uriString;
  }

  private static String toHex(String s) {
    StringBuffer buf;
    try {
       buf = new StringBuffer(s.getBytes("UTF-8").length);
       for (byte x : s.getBytes("UTF-8")) {
         buf.append("%");
         buf.append(Integer.toHexString(x & 0xFF));
      }
    } catch (UnsupportedEncodingException e) {
       throw new IllegalStateException(e);
    }
    return buf.toString().toUpperCase();
  }

  private static String getParametersListAsString(UtilProtos.Url url) {
    String out = "";
    final List<UtilProtos.Url.Parameter> parameters = url.getParametersList();
    boolean first = true;
    for (UtilProtos.Url.Parameter parameter : parameters) {
      if (!first) {
        out += "&";
      } else {
        first = false;
      }
      out += parameter.getName() + "=" + parameter.getValue();
    }
    return out;
  }
}
