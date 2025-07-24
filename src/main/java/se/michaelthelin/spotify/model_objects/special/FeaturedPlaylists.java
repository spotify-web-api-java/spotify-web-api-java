package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-featured-playlists">
 * Featured Playlist objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = FeaturedPlaylists.Builder.class)
public class FeaturedPlaylists extends AbstractModelObject {
  /** A message describing the featured playlists. */
  private final String message;
  /** A paging object containing the featured playlists. */
  private final Paging<PlaylistSimplified> playlists;

  private FeaturedPlaylists(final Builder builder) {
    super(builder);

    this.message = builder.message;
    this.playlists = builder.playlists;
  }

  /**
   * Get the message which is displayed on the front page of the "browse" tab in the Spotify client. <br>
   * The message usually refers to the featured playlists.
   *
   * @return A "welcoming" message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Get the page of featured playlists.
   *
   * @return Featured playlists page.
   */
  public Paging<PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  @Override
  public String toString() {
    return "FeaturedPlaylists(message=" + message + ", playlists=" + playlists + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link FeaturedPlaylists} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String message;
    private Paging<PlaylistSimplified> playlists;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Set the message, which normally would be displayed on the front page of the "browse" tab.
     *
     * @param message Message to be set.
     * @return A {@link FeaturedPlaylists.Builder}.
     */
    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * Set a page of playlists contained in the featured playlists object to be built.
     *
     * @param playlists A page of simplified playlists.
     * @return A {@link FeaturedPlaylists.Builder}.
     */
    public Builder setPlaylists(Paging<PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    @Override
    public FeaturedPlaylists build() {
      return new FeaturedPlaylists(this);
    }
  }

  /**
   * JsonUtil class for building {@link FeaturedPlaylists} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<FeaturedPlaylists> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public FeaturedPlaylists createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new FeaturedPlaylists.Builder()
        .setMessage(
          hasAndNotNull(jsonObject, "message")
            ? jsonObject.get("message").getAsString()
            : null)
        .setPlaylists(
          hasAndNotNull(jsonObject, "playlists")
            ? new PlaylistSimplified.JsonUtil().createModelObjectPaging(
            jsonObject.getAsJsonObject("playlists"))
            : null)
        .build();
    }
  }
}
