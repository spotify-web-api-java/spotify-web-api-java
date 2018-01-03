package com.wrapper.spotify.enums;

/**
 * An enumeration with the two modality types.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Mode_(music)">Wikipedia: Mode (Music)</a>
 */
public enum Modality {

  MINOR(0),
  MAJOR(1);

  public final int mode;

  Modality(int mode) {
    this.mode = mode;
  }

    /**
     * Get the {@link Modality} type as a string.
     *
     * @return {@link Modality} type as a string.
     */
  public int getType() {
    return this.mode;
  }

}
