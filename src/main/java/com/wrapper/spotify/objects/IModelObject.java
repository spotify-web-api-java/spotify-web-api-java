package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

public interface IModelObject {

  Builder builder();

  interface Builder {
    AbstractModelObject build();
  }

  interface IJsonUtil {
    <T> T createModelObject(JSONObject jsonObject);
  }

}
