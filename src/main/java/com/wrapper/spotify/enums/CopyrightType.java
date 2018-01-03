package com.wrapper.spotify.enums;

import com.wrapper.spotify.model_objects.specification.Copyright;

/**
 * An enumeration of all possible {@link Copyright} types.
 */
public enum CopyrightType {

  C("C"),
  P("P");

  public final String type;

  CopyrightType(final String type) {
    this.type = type;
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
