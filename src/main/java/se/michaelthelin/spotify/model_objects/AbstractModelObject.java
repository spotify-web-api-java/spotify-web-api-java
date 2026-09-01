package se.michaelthelin.spotify.model_objects;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    public JsonUtil() {
      super();
    }

    /**
     * Deserializes a model object reflectively.
     * <p>
     * Every model object whose JSON mirrors its fields inherits this. The three whose shape does
     * not ({@code Disallows} and the two external-identifier maps) override it, and
     * {@code ModelObjectGson} routes nested occurrences of those back through the override.
     *
     * @param jsonObject The JSON to read, which may be {@code null} or JSON null.
     * @return The model object, or {@code null}.
     */
    public T createModelObject(final JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return ModelObjectGson.GSON.fromJson(jsonObject, modelType());
    }

    /**
     * The model type this JsonUtil produces, taken from the type argument it was declared with.
     *
     * @return The type passed to {@link Gson#fromJson(JsonElement, Type)}.
     */
    protected Type modelType() {
      return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
    public Paging<T> createModelObjectPaging(final JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return ModelObjectGson.GSON.fromJson(
        jsonObject, TypeToken.getParameterized(Paging.class, modelType()).getType());
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
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return ModelObjectGson.GSON.fromJson(
        jsonObject, TypeToken.getParameterized(PagingCursorbased.class, modelType()).getType());
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
