package se.michaelthelin.spotify.model_objects;

import com.google.gson.*;
import se.michaelthelin.spotify.model_objects.specification.Cursor;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

/**
 * This abstract class (and its wrapping classes) is used as a sort of template for other model object classes and
 * includes multiple generic methods.
 */
public abstract class AbstractModelObject implements IModelObject {

  /**
   * This constructor initializes the time zone.
   *
   * @param builder The builder object of the corresponding model object.
   */
  protected AbstractModelObject(final Builder builder) {
    assert (builder != null);
  }

  /**
   * Returns a String representation of this model object in the style:<p>
   * {@code ModelObject(attr1=value1, attr2=value2, ...)}
   */
  @Override
  public abstract String toString(); // abstract enforces overriding of toString() for subclasses

  /**
   * Each model object needs to implement its own builder class.
   */
  public static abstract class Builder implements IModelObject.Builder {
    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }
  }

  /**
   * Each model object needs to implement its own JsonUtil class. <br>
   *
   * @param <T> The model object type of the corresponding JsonUtil.
   */
  public static abstract class JsonUtil<T> implements IModelObject.IJsonUtil<T> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasAndNotNull(final JsonObject jsonObject, final String memberName) {
      return jsonObject.has(memberName) && !jsonObject.get(memberName).isJsonNull();
    }

    /**
     * {@inheritDoc}
     */
    public T createModelObject(final String json) {
      if (json == null) {
        return null;
      } else {
        return createModelObject(JsonParser.parseString(json).getAsJsonObject());
      }
    }

    /**
     * {@inheritDoc}
     */
    public T[] createModelObjectArray(final JsonArray jsonArray) {
      @SuppressWarnings("unchecked")
      T[] array = (T[]) Array.newInstance((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], jsonArray.size());

      for (int i = 0; i < jsonArray.size(); i++) {
        JsonElement jsonElement = jsonArray.get(i);

        if (jsonElement instanceof JsonNull) {
          array[i] = null;
        } else {
          JsonObject jsonObject = jsonElement.getAsJsonObject();
          array[i] = createModelObject(jsonObject);
        }
      }

      return array;
    }

    /**
     * {@inheritDoc}
     */
    public T[] createModelObjectArray(final String json) {
      return createModelObjectArray(JsonParser.parseString(json).getAsJsonArray());
    }

    /**
     * {@inheritDoc}
     */
    public T[] createModelObjectArray(final String json, final String key) {
      return createModelObjectArray(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonArray());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <X> X[] createModelObjectArray(final JsonArray jsonArray, Class<X> clazz) {
      X[] array = (X[]) Array.newInstance(clazz, jsonArray.size());

      for (int i = 0; i < jsonArray.size(); i++) {
        JsonElement jsonElement = jsonArray.get(i);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        array[i] = (X) createModelObject(jsonObject);
      }

      return array;
    }

    /**
     * {@inheritDoc}
     */
    public Paging<T> createModelObjectPaging(final JsonObject jsonObject) {
      return new Paging.Builder<T>()
        .setHref(
          hasAndNotNull(jsonObject, "href")
            ? jsonObject.get("href").getAsString()
            : null)
        .setItems(
          hasAndNotNull(jsonObject, "items")
            ? createModelObjectArray(jsonObject.getAsJsonArray("items"))
            : null)
        .setLimit(
          hasAndNotNull(jsonObject, "limit")
            ? jsonObject.get("limit").getAsInt()
            : null)
        .setNext(
          hasAndNotNull(jsonObject, "next")
            ? jsonObject.get("next").getAsString()
            : null)
        .setOffset(
          hasAndNotNull(jsonObject, "offset")
            ? jsonObject.get("offset").getAsInt()
            : null)
        .setPrevious(
          hasAndNotNull(jsonObject, "previous")
            ? jsonObject.get("previous").getAsString()
            : null)
        .setTotal(
          hasAndNotNull(jsonObject, "total")
            ? jsonObject.get("total").getAsInt()
            : null)
        .build();
    }

    /**
     * {@inheritDoc}
     */
    public Paging<T> createModelObjectPaging(final String json) {
      return createModelObjectPaging(JsonParser.parseString(json).getAsJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    public Paging<T> createModelObjectPaging(final String json, final String key) {
      return createModelObjectPaging(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    public PagingCursorbased<T> createModelObjectPagingCursorbased(final JsonObject jsonObject) {
      return new PagingCursorbased.Builder<T>()
        .setHref(
          hasAndNotNull(jsonObject, "href")
            ? jsonObject.get("href").getAsString()
            : null)
        .setItems(
          hasAndNotNull(jsonObject, "items")
            ? createModelObjectArray(jsonObject.getAsJsonArray("items"))
            : null)
        .setLimit(
          hasAndNotNull(jsonObject, "limit")
            ? jsonObject.get("limit").getAsInt()
            : null)
        .setNext(
          hasAndNotNull(jsonObject, "next")
            ? jsonObject.get("next").getAsString()
            : null)
        .setCursors(
          hasAndNotNull(jsonObject, "cursors")
            ? new Cursor.JsonUtil().createModelObject(jsonObject.getAsJsonObject("cursors"))
            : null)
        .setTotal(
          hasAndNotNull(jsonObject, "total")
            ? jsonObject.get("total").getAsInt()
            : null)
        .build();
    }

    /**
     * {@inheritDoc}
     */
    public PagingCursorbased<T> createModelObjectPagingCursorbased(final String json) {
      return createModelObjectPagingCursorbased(JsonParser.parseString(json).getAsJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    public PagingCursorbased<T> createModelObjectPagingCursorbased(final String json, final String key) {
      return createModelObjectPagingCursorbased(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonObject());
    }
  }
}
