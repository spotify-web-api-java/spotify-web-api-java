package com.wrapper.spotify.model_objects;

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
  }

//  interface IJsonUtilPaging {
//    <X> Paging<X> createModelObject(JsonObject jsonObject, TypeToken<X> typeToken);
//  }
}
