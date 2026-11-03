package se.michaelthelin.spotify.model_objects;

import se.michaelthelin.spotify.internal.ModelObjectJson;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

/**
 * Reads model objects from JSON in the shape the Spotify Web API sends them.
 * <p>
 * Requests already return model objects, so this is only needed for JSON that reached the caller some
 * other way, for example a response that was cached or stored. It reads exactly as the requests do,
 * including the wire formats of enums and dates and the polymorphic playlist items.
 * <pre>{@code
 * Track track = ModelObjects.fromJson(json, Track.class);
 * Paging<SavedTrack> savedTracks = ModelObjects.pagingFromJson(json, SavedTrack.class);
 * }</pre>
 * Malformed JSON, or JSON of a different shape than requested (e.g. an array where an object is
 * expected), throws an unchecked exception.
 */
public final class ModelObjects {

  private ModelObjects() {
  }

  /**
   * Reads a model object from a JSON object.
   *
   * @param json The JSON object, or {@code null}.
   * @param type The model object class, for example {@code Track.class}.
   * @param <T>  The model object type.
   * @return The model object, or {@code null} if {@code json} is {@code null}.
   */
  public static <T extends IModelObject> T fromJson(final String json, final Class<T> type) {
    return ModelObjectJson.object(json, type);
  }

  /**
   * Reads model objects from a JSON array of objects.
   *
   * @param json The JSON array.
   * @param type The model object class of the elements, for example {@code Artist.class}.
   * @param <T>  The model object type.
   * @return The model objects, with {@code null} for each JSON {@code null} element.
   */
  public static <T extends IModelObject> T[] arrayFromJson(final String json, final Class<T> type) {
    return ModelObjectJson.array(json, type);
  }

  /**
   * Reads a {@link Paging} object, such as the response of a request that lists items page by page.
   *
   * @param json The JSON paging object.
   * @param type The model object class of the items, for example {@code SavedTrack.class}.
   * @param <T>  The model object type of the items.
   * @return The paging object.
   */
  public static <T extends IModelObject> Paging<T> pagingFromJson(final String json, final Class<T> type) {
    return ModelObjectJson.paging(json, type);
  }

  /**
   * Reads a {@link PagingCursorbased} object, such as the response of a request that pages by cursor.
   *
   * @param json The JSON cursor-based paging object.
   * @param type The model object class of the items, for example {@code PlayHistory.class}.
   * @param <T>  The model object type of the items.
   * @return The cursor-based paging object.
   */
  public static <T extends IModelObject> PagingCursorbased<T> pagingCursorbasedFromJson(final String json, final Class<T> type) {
    return ModelObjectJson.pagingCursorbased(json, type);
  }
}
