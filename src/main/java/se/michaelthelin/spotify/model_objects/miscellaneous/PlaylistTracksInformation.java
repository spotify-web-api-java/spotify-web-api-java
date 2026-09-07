package se.michaelthelin.spotify.model_objects.miscellaneous;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.IHasTotal;

/**
 * Retrieve information about Playlist Track Information objects by building instances from this class.
 */
public class PlaylistTracksInformation extends AbstractModelObject implements IHasTotal {
  /** A link to the Web API endpoint where full details of the playlist's tracks can be retrieved. */
  private final String href;
  /** Number of tracks in the playlist. */
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
  public String toString() {
    return "PlaylistTracksInformation(href=" + href + ", total=" + total + ")";
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

    public Builder() {
      super();
    }

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

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

    public JsonUtil() {
      super();
    }

  }
}
