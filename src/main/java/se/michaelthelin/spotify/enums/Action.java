package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible {@link Action} types.
 *
 * @see <a href="https://developer.spotify.com/web-api/object-model/#disallows-object">Disallows object</a>
 */
public enum Action {

  /** Represents interrupting playback action. */
  INTERRUPTING_PLAYBACK("interrupting_playback"),
  /** Represents pausing action. */
  PAUSING("pausing"),
  /** Represents resuming action. */
  RESUMING("resuming"),
  /** Represents seeking action. */
  SEEKING("seeking"),
  /** Represents skipping to next track action. */
  SKIPPING_NEXT("skipping_next"),
  /** Represents skipping to previous track action. */
  SKIPPING_PREV("skipping_prev"),
  /** Represents toggling repeat context action. */
  TOGGLING_REPEAT_CONTEXT("toggling_repeat_context"),
  /** Represents toggling shuffle action. */
  TOGGLING_SHUFFLE("toggling_shuffle"),
  /** Represents toggling repeat track action. */
  TOGGLING_REPEAT_TRACK("toggling_repeat_track"),
  /** Represents transferring playback action. */
  TRANSFERRING_PLAYBACK("transferring_playback");

  private static final Map<String, Action> map = new HashMap<>();

  static {
    for (Action action : Action.values()) {
      map.put(action.key, action);
    }
  }

  /** The action key string. */
  public final String key;

  Action(final String key) {
    this.key = key;
  }

  /**
   * Get an Action by its key.
   *
   * @param key The action key.
   * @return The corresponding Action or null if not found.
   */
  public static Action keyOf(String key) {
    return map.get(key);
  }

  /**
   * Get the {@link Action} key as a string.
   *
   * @return {@link Action} key as string.
   */
  public String getKey() {
    return key;
  }
}
