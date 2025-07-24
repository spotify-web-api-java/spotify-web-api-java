package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible Spotify product types.
 */
public enum ProductType {

  /** Basic desktop Spotify product. */
  BASIC_DESKTOP("basic-desktop"),
  /** Daypass Spotify product. */
  DAYPASS("daypass"),
  /** Free Spotify product. */
  FREE("free"),
  /** Open Spotify product. */
  OPEN("open"),
  /** Premium Spotify product. */
  PREMIUM("premium");

  private static final Map<String, ProductType> map = new HashMap<>();

  static {
    for (ProductType productType : ProductType.values()) {
      map.put(productType.type, productType);
    }
  }

  /** The product type identifier. */
  public final String type;

  ProductType(final String type) {
    this.type = type;
  }

  /**
   * Get the {@link ProductType} associated with the given type string.
   *
   * @param type the product type string
   * @return the corresponding {@link ProductType}, or null if not found
   */
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
