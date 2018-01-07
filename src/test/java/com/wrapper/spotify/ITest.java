package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ITest<T> {

  SpotifyApi SPOTIFY_API = new SpotifyApi.Builder().setAccessToken("AccessToken")
          .setClientId("ClientId")
          .setClientSecret("ClientSecret")
          .setRedirectUri("RedirectUri")
          .setRefreshToken("RefreshToken")
          .build();

  void shouldSucceed_sync() throws IOException, SpotifyWebApiException;

  void shouldSucceed_async() throws ExecutionException, InterruptedException;

  void shouldSucceed(final T type);
}
