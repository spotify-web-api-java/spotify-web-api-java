package com.wrapper.spotify.objects;

public abstract class AbstractModelObject implements IModelObject {

  protected AbstractModelObject(Builder<?> builder) {
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements IModelObject.Builder {
  }

}
