package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.wrapper.spotify.model_objects.specification.Cursor;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
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
  protected AbstractModelObject(Builder builder) {
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
    public T createModelObject(String json) {
      return createModelObject(new JsonParser().parse(json).getAsJsonObject());
    }

    /**
     * Create an array of model objects out of a json array object.
     *
     * @param jsonArray A json array object.
     * @return          A model object array.
     */
    public T[] createModelObjectArray(JsonArray jsonArray) {
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
     * @param json  A json string.
     * @return      A model object array.
     */
    public T[] createModelObjectArray(String json) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonArray());
    }

    public T[] createModelObjectArray(String json, String key) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonArray());
    }

    /**
     * Create an array of model objects out of a json array object and type token.
     *
     * @param jsonArray A json array object.
     * @param typeToken A type token.
     * @param <X>       The model object type of the array and type token.
     * @return          A model object array.
     */
    public <X> X[] createModelObjectArray(JsonArray jsonArray, TypeToken<X> typeToken) {
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
     * @param jsonObject  A json object.
     * @return            A model object paging.
     */
    public Paging<T> createModelObjectPaging(JsonObject jsonObject) {
      Type type = new TypeToken<T>(getClass()) {
      }.getType();

      return new Paging.Builder<T>()
              .setHref(jsonObject.get("href").getAsString())
              .setItems(createModelObjectArray(jsonObject.getAsJsonArray("items")))
              .setLimit(jsonObject.get("limit").getAsInt())
              .setNext((jsonObject.get("next") instanceof JsonNull) ? null : jsonObject.get("next").getAsString())
              .setOffset(jsonObject.get("offset").getAsInt())
              .setPrevious((jsonObject.get("previous") instanceof JsonNull) ? null : jsonObject.get("previous").getAsString())
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }

    /**
     * Create a paging object out of a json string.
     *
     * @param json  A json string.
     * @return      A model object paging.
     */
    public Paging<T> createModelObjectPaging(String json) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject());
    }

    public Paging<T> createModelObjectPaging(String json, String key) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
    }

    public PagingCursorbased<T> createModelObjectPagingCursorbased(JsonObject jsonObject) {
      Type type = new TypeToken<T>(getClass()) {
      }.getType();

      return new PagingCursorbased.Builder<T>()
              .setHref(jsonObject.get("href").getAsString())
              .setItems(createModelObjectArray(jsonObject.getAsJsonArray("items")))
              .setLimit(jsonObject.get("limit").getAsInt())
              .setNext((jsonObject.get("next") instanceof JsonNull) ? null : jsonObject.get("next").getAsString())
              .setCursors(new Cursor.JsonUtil().createModelObjectArray(jsonObject.get("cursors").getAsJsonArray()))
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }

    public PagingCursorbased<T> createModelObjectPagingCursorbased(String json) {
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject());
    }

    public PagingCursorbased<T> createModelObjectPagingCursorbased(String json, String key) {
      return createModelObjectPagingCursorbased(new JsonParser().parse(json).getAsJsonObject().get(key).getAsJsonObject());
    }
  }
}
