package se.michaelthelin.spotify.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

import java.lang.reflect.Array;

/**
 * Reads a response body into model objects, which is what every request's {@code execute()} does.
 * <p>
 * This replaces going through a model's {@code JsonUtil}, which only ever delegated to
 * {@link ModelObjectGson#GSON}. The parsing steps are the ones {@code AbstractModelObject.JsonUtil}
 * takes, so a malformed or empty body fails exactly as it did there.
 * <p>
 * This package is not exported, so nothing here is public API.
 */
public final class ModelObjectJson {

  private ModelObjectJson() {
  }

  /**
   * Reads a JSON object.
   *
   * @param json The response body, which may be {@code null}.
   * @param type The model type.
   * @param <T>  The model type.
   * @return The model object, or {@code null} for a {@code null} body.
   */
  public static <T> T object(final String json, final Class<T> type) {
    if (json == null) {
      return null;
    }

    return object(JsonParser.parseString(json).getAsJsonObject(), type);
  }

  /**
   * Reads a JSON object.
   *
   * @param jsonObject The object, which may be {@code null} or JSON null.
   * @param type       The model type.
   * @param <T>        The model type.
   * @return The model object, or {@code null}.
   */
  public static <T> T object(final JsonObject jsonObject, final Class<T> type) {
    if (jsonObject == null || jsonObject.isJsonNull()) {
      return null;
    }

    return ModelObjectGson.GSON.fromJson(jsonObject, type);
  }

  /**
   * Reads a top-level JSON array.
   *
   * @param json The response body.
   * @param type The element type.
   * @param <T>  The element type.
   * @return The model objects, with {@code null} for each JSON null.
   */
  public static <T> T[] array(final String json, final Class<T> type) {
    return array(JsonParser.parseString(json).getAsJsonArray(), type);
  }

  /**
   * Reads a JSON array that is a member of the top-level object.
   *
   * @param json The response body.
   * @param key  The member holding the array.
   * @param type The element type.
   * @param <T>  The element type.
   * @return The model objects, with {@code null} for each JSON null.
   */
  public static <T> T[] array(final String json, final String key, final Class<T> type) {
    return array(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonArray(), type);
  }

  private static <T> T[] array(final JsonArray jsonArray, final Class<T> type) {
    @SuppressWarnings("unchecked")
    T[] array = (T[]) Array.newInstance(type, jsonArray.size());

    for (int i = 0; i < jsonArray.size(); i++) {
      JsonElement jsonElement = jsonArray.get(i);
      array[i] = jsonElement instanceof JsonNull ? null : object(jsonElement.getAsJsonObject(), type);
    }

    return array;
  }

  /**
   * Reads a top-level paging object.
   *
   * @param json The response body.
   * @param type The item type.
   * @param <T>  The item type.
   * @return The paging object.
   */
  public static <T> Paging<T> paging(final String json, final Class<T> type) {
    return paging(JsonParser.parseString(json).getAsJsonObject(), type);
  }

  /**
   * Reads a paging object that is a member of the top-level object.
   *
   * @param json The response body.
   * @param key  The member holding the paging object.
   * @param type The item type.
   * @param <T>  The item type.
   * @return The paging object.
   */
  public static <T> Paging<T> paging(final String json, final String key, final Class<T> type) {
    return paging(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonObject(), type);
  }

  private static <T> Paging<T> paging(final JsonObject jsonObject, final Class<T> type) {
    if (jsonObject == null || jsonObject.isJsonNull()) {
      return null;
    }

    return ModelObjectGson.GSON.fromJson(
      jsonObject, TypeToken.getParameterized(Paging.class, type).getType());
  }

  /**
   * Reads a top-level cursor-based paging object.
   *
   * @param json The response body.
   * @param type The item type.
   * @param <T>  The item type.
   * @return The cursor-based paging object.
   */
  public static <T> PagingCursorbased<T> pagingCursorbased(final String json, final Class<T> type) {
    return pagingCursorbased(JsonParser.parseString(json).getAsJsonObject(), type);
  }

  /**
   * Reads a cursor-based paging object that is a member of the top-level object.
   *
   * @param json The response body.
   * @param key  The member holding the paging object.
   * @param type The item type.
   * @param <T>  The item type.
   * @return The cursor-based paging object.
   */
  public static <T> PagingCursorbased<T> pagingCursorbased(final String json, final String key, final Class<T> type) {
    return pagingCursorbased(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonObject(), type);
  }

  private static <T> PagingCursorbased<T> pagingCursorbased(final JsonObject jsonObject, final Class<T> type) {
    if (jsonObject == null || jsonObject.isJsonNull()) {
      return null;
    }

    return ModelObjectGson.GSON.fromJson(
      jsonObject, TypeToken.getParameterized(PagingCursorbased.class, type).getType());
  }
}
