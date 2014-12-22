package com.wrapper.spotify.models;

public class FeaturedPlaylists {

    private String message;
    private Page<Playlist> playlists;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Page<Playlist> playlists) {
        this.playlists = playlists;
    }
}
