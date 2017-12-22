package com.wrapper.spotify.models;

import java.util.List;

public class Context {
    private ExternalUrls externalUrls;
    private String href;
    private SpotifyEntityType type;
    private String uri;


    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public SpotifyEntityType getType() {
        return type;
    }

    public void setType(SpotifyEntityType type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

