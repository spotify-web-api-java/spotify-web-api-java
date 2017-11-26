package com.wrapper.spotify.objects;

import java.util.HashMap;
import java.util.Map;

public class ExternalUrls {

  private final Map<String, String> externalUrls = new HashMap<>();

  public Map<String, String> getExternalUrls() {
    return externalUrls;
  }

  public String get(String key) {
    return externalUrls.get(key);
  }

}
