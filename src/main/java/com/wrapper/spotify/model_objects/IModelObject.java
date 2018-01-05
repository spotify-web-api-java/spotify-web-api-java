package com.wrapper.spotify.model_objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

import java.text.SimpleDateFormat;

/**
 * Interface with methods used in model objects.
 */
public interface IModelObject {
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  /**
   * Create a builder for building an instance of a model object.<br>
   * The type of the builder and its methods depend on its corresponding implementation.
   *
   * @return A builder object.
   */
  Builder builder();

  /**
   * Interface with methods used in builder classes of model objects.
   */
  interface Builder {

    /**
     * Build a model object with the information set in the builder object. <br>
     * The type of the model object and its methods depend on its corresponding implementation.
     *
     * @return A model object.
     */
    IModelObject build();
  }

  /**
   * Interface with methods used in JsonUtil classes of model objects.
   *
   * @param <T> Type of the corresponding model object.
   */
  interface IJsonUtil<T> {

    boolean hasAndNotNull(final JsonObject jsonObject, final String memberName);

    /**
     * Build a model object with the information given in a json object. <br>
     * The type of the model object and its methods depend on its corresponding implementation.
     *
     * @param jsonObject A json object.
     * @return A model object. The type depends on this methods implementation.
     */
    T createModelObject(final JsonObject jsonObject);

    T createModelObject(final String json);

    T[] createModelObjectArray(final JsonArray jsonArray);

    T[] createModelObjectArray(final String json);

    T[] createModelObjectArray(final String json, final String key);

    <X> X[] createModelObjectArray(final JsonArray jsonArray, final Class<X> clazz);

    Paging<T> createModelObjectPaging(final JsonObject jsonObject);

    Paging<T> createModelObjectPaging(final String json);

    Paging<T> createModelObjectPaging(final String json, final String key);

    PagingCursorbased<T> createModelObjectPagingCursorbased(final JsonObject jsonObject);

    PagingCursorbased<T> createModelObjectPagingCursorbased(final String json);

    PagingCursorbased<T> createModelObjectPagingCursorbased(final String json, final String key);
  }
}
