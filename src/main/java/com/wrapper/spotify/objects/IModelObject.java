package com.wrapper.spotify.objects;

public interface IModelObject {

  Builder builder();

  interface Builder {
    AbstractModelObject build();
  }

}
