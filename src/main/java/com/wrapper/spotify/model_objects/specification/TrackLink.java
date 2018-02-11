package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#track-link-object">
 * Track Link objects</a> by building instances from this class. <br>
 * Track Link objects contain information about originally requested tracks, when the given track is not available in
 * your market region.
 *
 * @see <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Spotify: Track Relinking Guide</a>
 */
public class TrackLink extends AbstractModelObject {
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final ModelObjectType type;
  private final String uri;

  private TrackLink(final Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the external URLs of the track.<br>
   * Example: Spotify-URL.
   *
   * @return Known external URLs for this track.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the Spotify Web API endpoint URL of the track.
   *
   * @return A link to the Web API endpoint providing full details of the track.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a> of the
   * track.
   *
   * @return A Spotify track ID.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the model object type, which should be a "track" in this case.
   *
   * @return The object type: "track".
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get the <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URI</a> of the
   * track.
   *
   * @return The Spotify URI for the track.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link TrackLink} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private ModelObjectType type;
    private String uri;

    /**
     * Set external URLs of the track to be built.
     *
     * @param externalUrls Known external URLs for this track.
     * @return A {@link TrackLink.Builder}.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify Web API endpoint of the track to be built.
     *
     * @param href A link to the Web API endpoint providing full details of the track.
     * @return A {@link TrackLink.Builder}.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the Spotify ID of the track to be built.
     *
     * @param id A Spotify track ID.
     * @return A {@link TrackLink.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the type of the model object. In this case "track".
     *
     * @param type The object type: "track".
     * @return A {@link TrackLink.Builder}.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set the Spotify URI of the track to be built.
     *
     * @param uri The Spotify URI for the track.
     * @return A {@link TrackLink.Builder}.
     */
    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public TrackLink build() {
      return new TrackLink(this);
    }
  }

  /**
   * JsonUtil class for building {@link TrackLink} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<TrackLink> {
    public TrackLink createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new TrackLink.Builder()
              .setExternalUrls(
                      hasAndNotNull(jsonObject, "external_urls")
                              ? new ExternalUrl.JsonUtil().createModelObject(
                              jsonObject.getAsJsonObject("external_urls"))
                              : null)
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? ModelObjectType.keyOf(
                              jsonObject.get("type").getAsString().toLowerCase())
                              : null)
              .setUri(
                      hasAndNotNull(jsonObject, "uri")
                              ? jsonObject.get("uri").getAsString()
                              : null)
              .build();
    }
  }
}
