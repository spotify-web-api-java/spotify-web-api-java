package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;

public interface IModelObject {
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  Builder builder();

  interface Builder {
    IModelObject build();
  }

  interface IJsonUtil<T> {
    T createModelObject(JsonObject jsonObject);

    T[] createModelObjectArray(JsonArray jsonArray);

    T[] createModelObjectArray(String json);

    T[] createModelObjectArray(String json, String key);

    <X> X[] createModelObjectArray(JsonArray jsonArray, TypeToken<X> typeToken);

    Paging<T> createModelObjectPaging(JsonObject jsonObject);

    Paging<T> createModelObjectPaging(String json);

    Paging<T> createModelObjectPaging(String json, String key);

    PagingCursorbased<T> createModelObjectPagingCursorbased(JsonObject jsonObject);

    PagingCursorbased<T> createModelObjectPagingCursorbased(String json);

    PagingCursorbased<T> createModelObjectPagingCursorbased(String json, String key);
  }

//  interface IJsonUtilPaging {
//    <X> Paging<X> createModelObject(JsonObject jsonObject, TypeToken<X> typeToken);
//  }
}
