package com.wrapper.spotify;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import com.wrapper.spotify.UtilProtos.Url.Scheme;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UrlUtil {

  private final static Map<Scheme, String> SCHEME_NAMES =
          new ImmutableMap.Builder<Scheme, String>()
                  .put(Scheme.HTTP, "http")
                  .put(Scheme.HTTPS, "https")
                  .build();

  public static String assemble(UtilProtos.Url url)  {
    try {
      if (!url.getPath().contains("users")) {
        final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), url.getPath());
        return uri.toString();
      } else {
        final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort());
        return uri.toString() + url.getPath();
      }
    } catch (URIException e) {
      throw new IllegalStateException(e);
    }
  }
  
  public static String assembleWithQueryParameters(UtilProtos.Url url) {
    try {
      final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), url.getPath(), getParametersListAsString(url));
      return uri.toString();
    } catch (URIException e) {
      throw new IllegalStateException(e);
    }
  }

  public static String userToUri(String userName){
    String uriString = "";
    try {
       uriString = URIUtil.encodeQuery(userName, "UTF-8");
    } catch (URIException e) {
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
    final StringBuilder stringBuilder = new StringBuilder();
    final List<UtilProtos.Url.Parameter> parameters = url.getParametersList();
    boolean first = true;
    for (UtilProtos.Url.Parameter parameter : parameters) {
      if (!first) {
        stringBuilder.append("&");
      }
      first = false;
      stringBuilder.append(parameter.getName() + "=" + parameter.getValue());
    }
    return stringBuilder.toString();
  }
}
