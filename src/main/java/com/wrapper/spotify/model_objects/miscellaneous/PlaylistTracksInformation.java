package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about Playlist Track Information objects by building instances from this class.
 */
public class PlaylistTracksInformation extends AbstractModelObject {
  private final String href;
  private final Integer total;

  private PlaylistTracksInformation(final Builder builder) {
    super(builder);

    this.href = builder.href;
    this.total = builder.total;
  }

  /**
   * Get the Spotify Web API endpoint URL of the playlist tracks object.
   *
   * @return A Spotify API endpoint URL.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the total amount of tracks in the playlist.
   *
   * @return The total amount of tracks in the playlist.
   */
  public Integer getTotal() {
    return total;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link PlaylistTracksInformation} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private Integer total;

    /**
     * Set href of Spotify Web API endpoint of the playlist tracks information object to be built.
     *
     * @param href Spotify Web API endpoint URL.
     * @return A {@link PlaylistTracksInformation.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the total amount of tracks in the playlist.
     *
     * @param total Total amount of tracks.
     * @return A {@link PlaylistTracksInformation.Builder}.
     */
    public Builder setTotal(Integer total) {
      this.total = total;
      return this;
    }

    @Override
    public PlaylistTracksInformation build() {
      return new PlaylistTracksInformation(this);
    }
  }

  /**
   * JsonUtil class for building {@link PlaylistTracksInformation} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistTracksInformation> {
    public PlaylistTracksInformation createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new PlaylistTracksInformation.Builder()
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setTotal(
                      hasAndNotNull(jsonObject, "total")
                              ? jsonObject.get("total").getAsInt()
                              : null)
              .build();
    }
  }
}
