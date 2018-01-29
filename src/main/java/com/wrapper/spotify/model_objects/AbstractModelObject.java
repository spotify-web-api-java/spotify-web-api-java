package com.wrapper.spotify.model_objects;

import com.google.gson.*;
import com.wrapper.spotify.model_objects.specification.Cursor;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

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
   * Each model object needs to implement its own builder class.
   */
  public static abstract class Builder implements IModelObject.Builder {
  }

  /**
   * Each model object needs to implement its own JsonUtil class. <br>
   *
   * @param <T> The model object type of the corresponding JsonUtil.
   */
  public static abstract class JsonUtil<T> implements IModelObject.IJsonUtil<T> {

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
        return createModelObject(new JsonParser().parse(json).getAsJsonObject());
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
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonArray());
    }

    /**
     * {@inheritDoc}
     */
    public T[] createModelObjectArray(final String json, final String key) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonArray());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <X> X[] createModelObjectArray(final JsonArray jsonArray, Class<X> clazz) {
      @SuppressWarnings("unchecked")
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
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    public Paging<T> createModelObjectPaging(final String json, final String key) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
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
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    public PagingCursorbased<T> createModelObjectPagingCursorbased(final String json, final String key) {
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
    }
  }
}
