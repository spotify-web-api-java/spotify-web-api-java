package com.wrapper.spotify.model_objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;

/**
 * Interface with methods used in model objects.
 */
public interface IModelObject {

  /**
   * Create a builder for building an instance of a model object. <br>
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

    /**
     * Check whether the supplied JSON object contains data in the given member, which is not {@code null}.
     *
     * @param jsonObject The JSON object.
     * @param memberName The member name.
     * @return Whether the supplied JSON object contains data in the given member.
     */
    boolean hasAndNotNull(final JsonObject jsonObject, final String memberName);

    /**
     * Build a model object with the information given in a json object. <br>
     * The type of the model object and its methods depend on its corresponding implementation.
     *
     * @param jsonObject A json object.
     * @return A model object. The type depends on this methods implementation.
     */
    T createModelObject(final JsonObject jsonObject);

    /**
     * Build a model object with the information given in a json string. <br>
     * The type of the model object and its methods depend on its corresponding implementation.
     *
     * @param json A json object.
     * @return A model object. The type depends on this methods implementation.
     */
    T createModelObject(final String json);

    /**
     * Create an array of model objects out of a json array object.
     *
     * @param jsonArray A {@link JsonArray}.
     * @return A model object array. The type depends on this methods implementation.
     */
    T[] createModelObjectArray(final JsonArray jsonArray);

    /**
     * Create an array of model objects out of a json string.
     *
     * @param json A {@link JsonArray}.
     * @return A model object array. The type depends on this methods implementation.
     */
    T[] createModelObjectArray(final String json);

    /**
     * Create an array of model objects out of a json array, which is contained in a json object.
     *
     * @param json A {@link JsonObject}.
     * @param key  The key of the json array in the json object.
     * @return A model object array. The type depends on this methods implementation.
     */
    T[] createModelObjectArray(final String json, final String key);

    /**
     * Create an array of model objects out of a json array object and a {@link Class} object.
     *
     * @param jsonArray A json array object.
     * @param clazz     The class object.
     * @param <X>       The model object type of the array and class object.
     * @return A model object array.
     */
    <X> X[] createModelObjectArray(final JsonArray jsonArray, final Class<X> clazz);

    /**
     * Create a paging of model objects out of a json object.
     *
     * @param jsonObject A json object.
     * @return A model object paging.
     */
    Paging<T> createModelObjectPaging(final JsonObject jsonObject);

    /**
     * Create a paging of model objects out of a json string.
     *
     * @param json A json string.
     * @return A model object paging.
     */
    Paging<T> createModelObjectPaging(final String json);

    /**
     * Create a paging of model objects out of a json array, which is contained in a json object.
     *
     * @param json A {@link JsonObject}.
     * @param key  The key of the json array in the json object.
     * @return A model object array. The type depends on this methods implementation.
     */
    Paging<T> createModelObjectPaging(final String json, final String key);

    /**
     * Create a cursor-based paging of model objects out of a json object.
     *
     * @param jsonObject A json object.
     * @return A cursor-based model object paging.
     */
    PagingCursorbased<T> createModelObjectPagingCursorbased(final JsonObject jsonObject);

    /**
     * Create a cursor-based paging of model objects out of a json string.
     *
     * @param json A json string.
     * @return A cursor-based model object paging.
     */
    PagingCursorbased<T> createModelObjectPagingCursorbased(final String json);

    /**
     * Create a cursor-based paging of model objects out of a json array, which is contained in a json object.
     *
     * @param json A {@link JsonObject}.
     * @param key  The key of the json array in the json object.
     * @return A cursor-based model object paging.
     */
    PagingCursorbased<T> createModelObjectPagingCursorbased(final String json, final String key);
  }
}
