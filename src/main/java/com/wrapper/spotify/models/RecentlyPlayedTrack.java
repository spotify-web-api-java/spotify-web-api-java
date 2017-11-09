package com.wrapper.spotify.models;

import java.util.Date;

public class RecentlyPlayedTrack {

    private Track track;
    private Date playedAt;
    private Context context;


    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
