package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about track links by building instances from this class.<br>
 * Track link objects contain information about originally requested tracks, when the
 * given track is not available in your market region.
 */
public class TrackLink extends AbstractModelObject {
  private final ExternalUrl externalUrls;
  private final String href;
  private final String id;
  private final ModelObjectType type;
  private final String uri;

  private TrackLink(final TrackLink.Builder builder) {
    super(builder);

    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  /**
   * Get the external urls of a track.<br>
   * Example: Spotify-URL.
   *
   * @return The external urls of the track.
   */
  public ExternalUrl getExternalUrls() {
    return externalUrls;
  }

  /**
   * Get the full Spotify API endpoint url of a track.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the Spotify id of a track.
   *
   * @return A Spotify track id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the model object type, which should be a "track" in this case.
   *
   * @return A model object type.
   */
  public ModelObjectType getType() {
    return type;
  }

  /**
   * Get a Spotify track uri.
   *
   * @return A Spotify track uri.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building track link instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private ExternalUrl externalUrls;
    private String href;
    private String id;
    private ModelObjectType type;
    private String uri;

    /**
     * Set external urls of the track to be built.
     *
     * @param externalUrls External urls object.
     * @return A builder object.
     */
    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    /**
     * Set href of Spotify api endpoint of the track to be built.
     *
     * @param href Spotify api endpoint url
     * @return A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set track id of the track to be built.
     *
     * @param id Track id.
     * @return A builder object.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the type of the model object. In this case "track".
     *
     * @param type Type of the model object.
     * @return A builder object.
     */
    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

    /**
     * Set Spotify uri of the track to be built.
     *
     * @param uri The Spotify track uri.
     * @return A builder object.
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
   * JsonUtil class for building track link instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<TrackLink> {
    public TrackLink createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new TrackLink.Builder()
              .setExternalUrls(new ExternalUrl.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
              .setHref(jsonObject.get("href").getAsString())
              .setId(jsonObject.get("id").getAsString())
              .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
              .setUri(jsonObject.get("uri").getAsString())
              .build();
    }
  }
}
