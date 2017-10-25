package com.wrapper.spotify.models;

import java.util.Date;

/**
 * @see <a href=https://developer.spotify.com/web-api/get-the-users-currently-playing-track/>Spotify docs</a>
 */
public class CurrentlyPlayingTrack {

    private Track item;
    private Context context;
    private Long timestamp;
    private Long progress_ms;
    private Boolean is_playing;


    public Track getItem() {
        return item;
    }

    public void setItem(Track item) {
        this.item = item;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getProgress_ms() {
        return progress_ms;
    }

    public void setProgress_ms(Long progress_ms) {
        this.progress_ms = progress_ms;
    }

    public Boolean getIs_playing() {
        return is_playing;
    }

    public void setIs_playing(Boolean is_playing) {
        this.is_playing = is_playing;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CurrentlyPlayingTrack))
            return false;

        CurrentlyPlayingTrack other = (CurrentlyPlayingTrack) obj;
        if (this.getItem() == null) {
            if (other.getItem() != null)
                return false;
        } else if (!this.getItem().getUri().equals(other.getItem().getUri()))
            return false;
        return true;
    }


}
