package com.wrapper.spotify.objects;

import com.google.common.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public abstract class AbstractModelObject implements IModelObject {
  protected AbstractModelObject(Builder builder) {
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  public static abstract class Builder implements IModelObject.Builder {
  }

  public static abstract class JsonUtil<T> implements IModelObject.IJsonUtil<T> {
    public T createModelObject(String json) {
      return createModelObject(JSONObject.fromObject(json));
    }

    public List<T> createModelObjectList(JSONArray jsonArray) {
      List<T> list = new ArrayList<>();

      for (int i = 0; i < jsonArray.size(); i++) {
        list.add(this.createModelObject(jsonArray.getJSONObject(i)));
      }

      return list;
    }

    public List<T> createModelObjectList(String json) {
      return createModelObjectList(JSONArray.fromObject(json));
    }

    public Paging<T> createModelObjectPaging(JSONObject jsonObject) {
      return new Paging.Builder<T>()
              .setHref(jsonObject.getString("href"))
              .setItems(createModelObjectList(jsonObject.getJSONArray("items")))
              .setLimit(jsonObject.getInt("limit"))
              .setNext(jsonObject.getString("next"))
              .setOffset(jsonObject.getInt("offset"))
              .setPrevious(jsonObject.getString("previous"))
              .setTotal(jsonObject.getInt("total"))
              .build();
    }

    public Paging<T> createModelObjectPaging(String json) {
      return createModelObjectPaging(JSONObject.fromObject(json));
    }
  }

  public static abstract class JsonUtilPaging implements IModelObject.IJsonUtilPaging {
    public <T> List<Paging<T>> createModelObjectList(JSONArray jsonArray, TypeToken<T> typeToken) {
      List<Paging<T>> list = new ArrayList<>();

      for (int i = 0; i < jsonArray.size(); i++) {
        list.add(this.createModelObject(jsonArray.getJSONObject(i), typeToken));
      }

      return list;
    }
  }
}
