package se.michaelthelin.spotify.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all possible {@link Action} types.
 *
 * @see <a href="https://developer.spotify.com/web-api/object-model/#disallows-object">Disallows object</a>
 */
public enum Action {

  INTERRUPTING_PLAYBACK("interrupting_playback"),
  PAUSING("pausing"),
  RESUMING("resuming"),
  SEEKING("seeking"),
  SKIPPING_NEXT("skipping_next"),
  SKIPPING_PREV("skipping_prev"),
  TOGGLING_REPEAT_CONTEXT("toggling_repeat_context"),
  TOGGLING_SHUFFLE("toggling_shuffle"),
  TOGGLING_REPEAT_TRACK("toggling_repeat_track"),
  TRANSFERRING_PLAYBACK("transferring_playback");

  private static final Map<String, Action> map = new HashMap<>();

  static {
    for (Action action : Action.values()) {
      map.put(action.key, action);
    }
  }

  public final String key;

  Action(final String key) {
    this.key = key;
  }

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
