package com.wrapper.spotify.objects;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public class TrackSimplified extends AbstractModelObject {
  private final List<ArtistSimplified> artists;
  private final List<CountryCode> availableMarkets;
  private final int discNumber;
  private final int durationMs;
  private final boolean explicit;
  private final ExternalUrls externalUrls;
  private final String href;
  private final String id;
  private final boolean isPlayable;
  private final TrackLink linkedFrom;
  private final String name;
  private final String previewUrl;
  private final int trackNumber;
  private final ObjectType type;
  private final String uri;

  private TrackSimplified(final TrackSimplified.Builder builder) {
    super(builder);

    this.artists = builder.artists;
    this.availableMarkets = builder.availableMarkets;
    this.discNumber = builder.discNumber;
    this.durationMs = builder.durationMs;
    this.explicit = builder.explicit;
    this.externalUrls = builder.externalUrls;
    this.href = builder.href;
    this.id = builder.id;
    this.isPlayable = builder.isPlayable;
    this.linkedFrom = builder.linkedFrom;
    this.name = builder.name;
    this.previewUrl = builder.previewUrl;
    this.trackNumber = builder.trackNumber;
    this.type = builder.type;
    this.uri = builder.uri;
  }

  public List<ArtistSimplified> getArtists() {
    return artists;
  }

  public List<CountryCode> getAvailableMarkets() {
    return availableMarkets;
  }

  public int getDiscNumber() {
    return discNumber;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public boolean getIsExplicit() {
    return explicit;
  }

  public ExternalUrls getExternalUrls() {
    return externalUrls;
  }

  public String getHref() {
    return href;
  }

  public String getId() {
    return id;
  }

  public boolean getIsPlayable() {
    return isPlayable;
  }

  public TrackLink getLinkedFrom() {
    return linkedFrom;
  }

  public String getName() {
    return name;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public int getTrackNumber() {
    return trackNumber;
  }

  public ObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<TrackSimplified.Builder> {
    private List<ArtistSimplified> artists;
    private List<CountryCode> availableMarkets;
    private int discNumber;
    private int durationMs;
    private boolean explicit;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private String name;
    private String previewUrl;
    private int trackNumber;
    private ObjectType type;
    private String uri;

    public Builder setArtists(List<ArtistSimplified> artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(List<CountryCode> availableMarkets) {
      this.availableMarkets = availableMarkets;
      return this;
    }

    public Builder setDiscNumber(int discNumber) {
      this.discNumber = discNumber;
      return this;
    }

    public Builder setDurationMs(int durationMs) {
      this.durationMs = durationMs;
      return this;
    }

    public Builder setExplicit(boolean explicit) {
      this.explicit = explicit;
      return this;
    }

    public Builder setExternalUrls(ExternalUrls externalUrls) {
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

    public Builder setIsPlayable(boolean isPlayable) {
      this.isPlayable = isPlayable;
      return this;
    }

    public Builder setLinkedFrom(TrackLink linkedFrom) {
      this.linkedFrom = linkedFrom;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setPreviewUrl(String previewUrl) {
      this.previewUrl = previewUrl;
      return this;
    }

    public Builder setTrackNumber(int trackNumber) {
      this.trackNumber = trackNumber;
      return this;
    }

    public Builder setType(ObjectType type) {
      this.type = type;
      return this;
    }

    public Builder setUri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public TrackSimplified build() {
      return new TrackSimplified(this);
    }
  }
}
