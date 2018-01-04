package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.wrapper.spotify.model_objects.specification.Cursor;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

import java.lang.reflect.Array;
import java.util.TimeZone;

/**
 * This abstract class (and its wrapping classes) is used as a sort of template for
 * other model object classes and includes multiple generic methods.
 */
public abstract class AbstractModelObject implements IModelObject {

  /**
   * This constructor initializes the time zone.
   *
   * @param builder The builder object of the corresponding model object.
   */
  protected AbstractModelObject(final Builder builder) {
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  /**
   * Each model object needs to implement its own builder class.
   */
  public static abstract class Builder implements IModelObject.Builder {
  }

  /**
   * Each model object needs to implement its own JsonUtil class.<br>
   *
   * @param <T> The model object type of the corresponding JsonUtil.
   */
  public static abstract class JsonUtil<T> implements IModelObject.IJsonUtil<T> {

    public boolean hasAndNotNull(final JsonObject jsonObject, final String memberName) {
      if (jsonObject.has(memberName) && !jsonObject.get(memberName).isJsonNull()) {
        return true;
      } else {
        return false;
      }
    }

    public T createModelObject(final String json) {
      return createModelObject(new JsonParser().parse(json).getAsJsonObject());
    }

    /**
     * Create an array of model objects out of a json array object.
     *
     * @param jsonArray A json array object.
     * @return A model object array.
     */
    public T[] createModelObjectArray(final JsonArray jsonArray) {
      @SuppressWarnings("unchecked")
      T[] array = (T[]) Array.newInstance(new TypeToken<T>(getClass()) {
      }.getRawType(), jsonArray.size());

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
     * Create an array of model objects out of a json string.
     *
     * @param json A json string.
     * @return A model object array.
     */
    public T[] createModelObjectArray(final String json) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonArray());
    }

    public T[] createModelObjectArray(final String json, final String key) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonArray());
    }

    /**
     * Create an array of model objects out of a json array object and type token.
     *
     * @param jsonArray A json array object.
     * @param typeToken A type token.
     * @param <X>       The model object type of the array and type token.
     * @return A model object array.
     */
    public <X> X[] createModelObjectArray(final JsonArray jsonArray, TypeToken<X> typeToken) {
      @SuppressWarnings("unchecked")
      X[] array = (X[]) Array.newInstance(typeToken.getRawType(), jsonArray.size());

      for (int i = 0; i < jsonArray.size(); i++) {
        JsonElement jsonElement = jsonArray.get(i);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        array[i] = (X) createModelObject(jsonObject);
      }

      return array;
    }

    /**
     * Create a paging object out of a json array.
     *
     * @param jsonObject A json object.
     * @return A model object paging.
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
     * Create a paging object out of a json string.
     *
     * @param json A json string.
     * @return A model object paging.
     */
    public Paging<T> createModelObjectPaging(final String json) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject());
    }

    public Paging<T> createModelObjectPaging(final String json, final String key) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
    }

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
                      hasAndNotNull(jsonObject, "total")
                              ? jsonObject.get("next").getAsString()
                              : null)
              .setCursors(
                      hasAndNotNull(jsonObject, "total")
                              ? new Cursor.JsonUtil().createModelObjectArray(jsonObject.get("cursors").getAsJsonArray())
                              : null)
              .setTotal(
                      hasAndNotNull(jsonObject, "total")
                              ? jsonObject.get("total").getAsInt()
                              : null)
              .build();
    }

    public PagingCursorbased<T> createModelObjectPagingCursorbased(final String json) {
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject());
    }

    public PagingCursorbased<T> createModelObjectPagingCursorbased(final String json, final String key) {
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
    }
  }
}
