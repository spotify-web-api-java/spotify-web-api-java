package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#track-link-object">
 * Track Link objects</a> by building instances from this class. <br>
 * Track Link objects contain information about originally requested tracks, when the given track is not available in
 * your market region.
 *
 * @see <a href="https://developer.spotify.com/documentation/web-api/concepts/track-relinking">Spotify: Track Relinking Guide</a>
 */
public class TrackLink extends AbstractModelObject {
  /** External URLs for the track. */
  private final ExternalUrl externalUrls;
  /** The Spotify Web API endpoint URL for the track. */
  private final String href;
  /** The Spotify ID for the track. */
  private final String id;
  /** The object type. */
  private final ModelObjectType type;
  /** The Spotify URI for the track. */
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify ID</a> of the
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
   * Get the <a href="https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids">Spotify URI</a> of the
   * track.
   *
   * @return The Spotify URI for the track.
   */
  public String getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return "TrackLink(externalUrls=" + externalUrls + ", href=" + href + ", id=" + id + ", type=" + type + ", uri="
        + uri + ")";
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

    public Builder() {
      super();
    }

    public Builder setExternalUrls(ExternalUrl externalUrls) {
      this.externalUrls = externalUrls;
      return this;
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setType(ModelObjectType type) {
      this.type = type;
      return this;
    }

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

    public JsonUtil() {
      super();
    }

  }
}
