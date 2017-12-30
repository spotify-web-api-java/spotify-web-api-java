package com.wrapper.spotify.model_objects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.enums.ModelObjectType;

public class TrackSimplified extends AbstractModelObject {
  private final ArtistSimplified[] artists;
  private final CountryCode[] availableMarkets;
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
  private final ModelObjectType type;
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

  public ArtistSimplified[] getArtists() {
    return artists;
  }

  public CountryCode[] getAvailableMarkets() {
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

  public ModelObjectType getType() {
    return type;
  }

  public String getUri() {
    return uri;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private ArtistSimplified[] artists;
    private CountryCode[] availableMarkets;
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
    private ModelObjectType type;
    private String uri;

    public Builder setArtists(ArtistSimplified... artists) {
      this.artists = artists;
      return this;
    }

    public Builder setAvailableMarkets(CountryCode... availableMarkets) {
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

    public Builder setType(ModelObjectType type) {
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<TrackSimplified> {
    public TrackSimplified createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      if (jsonObject.has("available_markets")) {
        return new Builder()
                .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
                .setAvailableMarkets(new Gson().fromJson(jsonObject.getAsJsonArray("available_markets"), CountryCode[].class))
                .setDiscNumber(jsonObject.get("disc_number").getAsInt())
                .setDurationMs(jsonObject.get("duration_ms").getAsInt())
                .setExplicit(jsonObject.get("explicit").getAsBoolean())
                .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
                .setHref(jsonObject.get("href").getAsString())
                .setId(jsonObject.get("id").getAsString())
                .setName(jsonObject.get("name").getAsString())
                .setPreviewUrl(jsonObject.get("preview_url").isJsonNull() ? null : jsonObject.get("preview_url").getAsString())
                .setTrackNumber(jsonObject.get("track_number").getAsInt())
                .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                .setUri(jsonObject.get("uri").getAsString())
                .build();
      } else if (jsonObject.has("is_playable")) {
        return new Builder()
                .setArtists(new ArtistSimplified.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("artists")))
                .setDiscNumber(jsonObject.get("disc_number").getAsInt())
                .setDurationMs(jsonObject.get("duration_ms").getAsInt())
                .setExplicit(jsonObject.get("explicit").getAsBoolean())
                .setExternalUrls(new ExternalUrls.JsonUtil().createModelObject(jsonObject.getAsJsonObject("external_urls")))
                .setHref(jsonObject.get("href").getAsString())
                .setId(jsonObject.get("id").getAsString())
                .setIsPlayable(jsonObject.get("is_playable").getAsBoolean())
                .setLinkedFrom(jsonObject.has("linked_from") ? new TrackLink.JsonUtil().createModelObject(jsonObject.get("linked_from").getAsJsonObject()) : null)
                .setName(jsonObject.get("name").getAsString())
                .setPreviewUrl(jsonObject.get("preview_url").isJsonNull() ? null : jsonObject.get("preview_url").getAsString())
                .setTrackNumber(jsonObject.get("track_number").getAsInt())
                .setType(ModelObjectType.valueOf(jsonObject.get("type").getAsString().toUpperCase()))
                .setUri(jsonObject.get("uri").getAsString())
                .build();
      } else {
        return null;
      }
    }
  }
}
