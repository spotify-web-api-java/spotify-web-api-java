package com.wrapper.spotify.objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
      return new Gson().fromJson(jsonArray, new TypeToken<T>() {
      }.getType());
    }

    public T[] createModelObjectArray(String json) {
      return createModelObjectArray(new JsonParser().parse(json).getAsJsonArray());
    }

    public <X> X[] createModelObjectArray(JsonArray jsonArray, TypeToken<X> typeToken) {
      return new Gson().fromJson(jsonArray, new TypeToken<X>() {
      }.getType());
    }

    public Paging<T> createModelObjectPaging(JsonObject jsonObject) {
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
