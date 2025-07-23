package se.michaelthelin.spotify.enums;

import se.michaelthelin.spotify.model_objects.specification.Copyright;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible {@link Copyright} types.
 */
public enum CopyrightType {

  /** The copyright for the composition (recording). */
  C("c"),
  /** The copyright for the sound recording (phonogram). */
  P("p");

  private static final Map<String, CopyrightType> map = new HashMap<>();

  static {
    for (CopyrightType copyrightType : CopyrightType.values()) {
      map.put(copyrightType.type, copyrightType);
    }
  }

  /** The copyright type identifier. */
  public final String type;

  CopyrightType(final String type) {
    this.type = type;
  }

  /**
   * Get the {@link CopyrightType} associated with the given type string.
   *
   * @param type the copyright type string
   * @return the corresponding {@link CopyrightType}, or null if not found
   */
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
