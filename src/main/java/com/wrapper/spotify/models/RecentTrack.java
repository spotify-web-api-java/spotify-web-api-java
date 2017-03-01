package com.wrapper.spotify.models;

import java.util.Date;

public class RecentTrack {

	private SimpleTrack track;
	private Date playedAt;
	private Context context;
	
	public SimpleTrack getTrack() {
		return track;
	}
	
	public void setTrack(SimpleTrack track) {
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
