package com.wrapper.spotify.objects;

import com.google.common.reflect.TypeToken;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;

public interface IModelObject {
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

  Builder builder();

  interface Builder {
    IModelObject build();
  }

  interface IJsonUtil<T> {
    T createModelObject(JSONObject jsonObject);
  }

  interface IJsonUtilPaging {
    <X> Paging<X> createModelObject(JSONObject jsonObject, TypeToken<X> typeToken);
  }
}
