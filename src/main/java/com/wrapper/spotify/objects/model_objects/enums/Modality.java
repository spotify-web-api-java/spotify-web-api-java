package com.wrapper.spotify.model_objects;

public enum Modality {

  MINOR(0),
  MAJOR(1);

  public final int mode;

  Modality(int mode) {
    this.mode = mode;
  }

  public int getType() {
    return this.mode;
  }

}
