package com.wrapper.spotify.objects;

public abstract class AbstractObject implements Object {

  protected AbstractObject(Builder<?> builder) {
  }

  public static abstract class Builder<BuilderType extends Builder<?>> implements Object.Builder {
  }

}
