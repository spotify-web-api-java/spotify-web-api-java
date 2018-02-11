package com.wrapper.spotify.enums;

import com.wrapper.spotify.model_objects.specification.Copyright;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible {@link Copyright} types.
 */
public enum CopyrightType {

  C("C"),
  P("P");

  private static Map<String, CopyrightType> map = new HashMap<>();

  static {
    for (CopyrightType copyrightType : CopyrightType.values()) {
      map.put(copyrightType.type, copyrightType);
    }
  }

  public final String type;

  CopyrightType(final String type) {
    this.type = type;
  }

  public static CopyrightType keyOf(String type) {
    return map.get(type);
  }

  /**
   * Get the {@link Copyright} type as a string.
   *
   * @return {@link Copyright} type as string.
   */
  public String getType() {
    return type;
  }

}
