package se.michaelthelin.spotify.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Shapes builder arguments into the values a request body expects.
 * <p>
 * This package is not exported, so nothing here is public API.
 */
public final class RequestValues {

  private RequestValues() {
  }

  /**
   * Wraps each value into a one-field object, as Spotify expects for e.g. {@code [{"uri": "spotify:track:..."}]}.
   *
   * @param values The field values.
   * @param field  The field name.
   * @return One object per value.
   */
  public static List<Map<String, String>> objects(final List<String> values, final String field) {
    List<Map<String, String>> objects = new ArrayList<>(values.size());
    for (String value : values) {
      objects.add(Collections.singletonMap(field, value));
    }
    return objects;
  }
}
