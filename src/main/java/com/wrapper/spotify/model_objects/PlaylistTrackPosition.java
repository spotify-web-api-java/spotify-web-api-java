package com.wrapper.spotify.models;

public class PlaylistTrackPosition
{
    private String uri;
    private int[] positions;

    public PlaylistTrackPosition(String uri, int[] positions)
    {
        this.uri = uri;
        this.positions = positions;
    }

    public PlaylistTrackPosition(String uri)
    {
        this.uri = uri;
    }

    public int[] getPositions()
    {
        return positions;
    }

    public void setPositions(int[] positions)
    {
        this.positions = positions;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
