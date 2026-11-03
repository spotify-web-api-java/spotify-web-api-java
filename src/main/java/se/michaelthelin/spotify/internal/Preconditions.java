package se.michaelthelin.spotify.internal;

import java.util.Collection;

/**
 * Argument checks shared by the request builders.
 * <p>
 * These are Java assertions, so like the inline {@code assert} statements they replace they only run
 * when assertions are enabled (for example in this library's tests) and cost nothing otherwise.
 * <p>
 * This package is not exported, so nothing here is public API.
 */
public final class Preconditions {

  private Preconditions() {
  }

  /**
   * Asserts that a string is neither {@code null} nor empty.
   *
   * @param value The value to check.
   */
  public static void assertNotEmpty(final String value) {
    assert (value != null);
    assert (!value.isEmpty());
  }

  /**
   * Asserts that a collection is neither {@code null} nor empty.
   *
   * @param value The value to check.
   */
  public static void assertNotEmpty(final Collection<?> value) {
    assert (value != null);
    assert (!value.isEmpty());
  }
}
