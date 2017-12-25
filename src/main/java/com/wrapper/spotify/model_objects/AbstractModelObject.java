package com.wrapper.spotify.objects.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.TimeZone;

public abstract class AbstractModelObject implements IModelObject {
  protected AbstractModelObject(Builder builder) {
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  public static abstract class Builder implements IModelObject.Builder {
  }

  public static abstract class JsonUtil<T> implements IModelObject.IJsonUtil<T> {
    public T createModelObject(String json) {
      return createModelObject(new JsonParser().parse(json).getAsJsonObject());
    }

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

    public T[] createModelObjectArray(String json) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonArray());
    }

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

    public Paging<T> createModelObjectPaging(String json) {
      return createModelObjectPaging(new JsonParser().parse(json).getAsJsonObject());
    }
  }
}
