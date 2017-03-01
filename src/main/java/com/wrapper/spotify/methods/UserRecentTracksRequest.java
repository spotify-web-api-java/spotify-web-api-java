package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.RecentTrack;

import java.io.IOException;
import java.util.List;

public class UserRecentTracksRequest extends AbstractRequest {

	public UserRecentTracksRequest(Builder builder) {
		super(builder);
	}

	public SettableFuture<List<RecentTrack>> getAsync() {
		SettableFuture<List<RecentTrack>> tracksFuture = SettableFuture.create();

		try {
			final String jsonString = getJson();
			final JSONObject jsonObject = JSONObject.fromObject(jsonString);
			
			tracksFuture.set(JsonUtil.createRecentTracks(jsonObject));
		} catch (Exception e) {
			tracksFuture.setException(e);
		}

		return tracksFuture;
	}

	public List<RecentTrack> get() throws IOException, WebApiException {
		final String jsonString = getJson();
		final JSONObject jsonObject = JSONObject.fromObject(jsonString);

		return JsonUtil.createRecentTracks(jsonObject);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractRequest.Builder<Builder> {

		public Builder limit(int limit) {
			assert (limit > 0);
			return parameter("limit", String.valueOf(limit));
		}

		public Builder after(long after) {
			assert (after >= 0);
			return parameter("after", String.valueOf(after));
		}

		public Builder before(long before) {
			assert (before >= 0);
			return parameter("before", String.valueOf(before));
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

		public UserRecentTracksRequest build() {
			path("/v1/me/player/recently-played");
			return new UserRecentTracksRequest(this);
		}
	}
}
