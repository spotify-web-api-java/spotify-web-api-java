package com.wrapper.spotify.methods;

import java.io.IOException;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimplePlaylist;

import net.sf.json.JSONObject;

public class PlaylistSearchRequest extends AbstractRequest {

	protected PlaylistSearchRequest(Builder builder) {
		super(builder);
	}

	public SettableFuture<Page<SimplePlaylist>> getAsync() {
		SettableFuture<Page<SimplePlaylist>> searchResultFuture = SettableFuture.create();

		try {
			final String jsonString = getJson();
			final JSONObject jsonObject = JSONObject.fromObject(jsonString);

			searchResultFuture.set(JsonUtil.createSimplePlaylistPage(jsonObject.getJSONObject("playlists")));
		} catch (Exception e) {
			searchResultFuture.setException(e);
		}

		return searchResultFuture;
	}

	public Page<SimplePlaylist> get() throws IOException, WebApiException {
		final String jsonString = getJson();
		final JSONObject jsonObject = JSONObject.fromObject(jsonString);

		return JsonUtil.createSimplePlaylistPage(JSONObject.fromObject(jsonObject).getJSONObject("playlists"));
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractRequest.Builder<Builder> {

		public Builder query(String query) {
			assert(query != null);
			path("/v1/search");
			parameter("type", "playlist");
			return parameter("q", query);
		}

		public Builder market(String market) {
			assert(market != null);
			return parameter("market", market);
		}

		public Builder limit(int limit) {
			assert(limit > 0);
			return parameter("limit", String.valueOf(limit));
		}

		public Builder offset(int offset) {
			assert(offset >= 0);
			return parameter("offset", String.valueOf(offset));
		}

		public PlaylistSearchRequest build() {
			return new PlaylistSearchRequest(this);
		}

	}
}
