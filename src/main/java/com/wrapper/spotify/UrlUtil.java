package com.wrapper.spotify;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import com.wrapper.spotify.UtilProtos.Url.Scheme;

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
      final URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), url.getPath());
      return uri.toString();
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
