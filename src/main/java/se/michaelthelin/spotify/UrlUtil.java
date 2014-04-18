package se.michaelthelin.spotify;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import se.michaelthelin.spotify.UtilProtos.Url.Scheme;

import java.util.Map;

public abstract class UrlUtil {

  private final static Map<Scheme, String> SCHEME_NAMES =
          new ImmutableMap.Builder<Scheme, String>()
                  .put(Scheme.HTTP, "http")
                  .put(Scheme.HTTPS, "https")
                  .build();

  public static String assemble(UtilProtos.Url url)  {
    try {
      URI uri = new URI(SCHEME_NAMES.get(url.getScheme()), null, url.getHost(), url.getPort(), url.getPath());
      return uri.toString();
    } catch (URIException e) {
      throw new IllegalStateException(e);
    }
  }

}
