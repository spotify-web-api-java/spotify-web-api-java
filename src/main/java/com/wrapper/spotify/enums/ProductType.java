package com.wrapper.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible Spotify product types.
 */
public enum ProductType {

  BASIC_DESKTOP("basic-desktop"),
  DAYPASS("daypass"),
  FREE("free"),
  OPEN("open"),
  PREMIUM("premium");

  private static Map<String, ProductType> map = new HashMap<>();

  static {
    for (ProductType productType : ProductType.values()) {
      map.put(productType.type, productType);
    }
  }

  public final String type;

  ProductType(final String type) {
    this.type = type;
  }

  public static ProductType keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the Spotify product type as a string.
   *
   * @return The Spotify product type as a string.
   */
  public String getType() {
    return type;
  }

}
