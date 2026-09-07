package se.michaelthelin.spotify.model_objects.interfaces;

/**
 * Represents an object that has a total count.
 * <p>
 * This interface is implemented by objects that provide a total count of items,
 * such as playlists or other paging objects.
 */
public interface IHasTotal {
  /**
   * Get the total count.
   *
   * @return The total count of items
   */
  Integer getTotal();
}

