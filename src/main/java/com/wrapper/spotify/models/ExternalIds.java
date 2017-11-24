package com.wrapper.spotify.models;

import java.util.HashMap;
import java.util.Map;

public class ExternalIds {

  private final Map<String, String> externalIds = new HashMap<>();

  public Map<String, String> getExternalIds() {
    return externalIds;
  }

  public String get(String key) {
    return externalIds.get(key);
  }

}
