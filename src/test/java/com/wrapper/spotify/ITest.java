package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ITest<T> {

  SpotifyApi SPOTIFY_API = new SpotifyApi.Builder()
          .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
          .setClientId("zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g")
          .setClientSecret("zudknyqbh3wunbhcvg9uyvo7uwzeu6nne")
          .setRedirectUri(SpotifyHttpManager.makeUri("https://example.com/spotify-redirect"))
          .setRefreshToken("b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn")
          .build();

  void shouldSucceed_sync() throws IOException, SpotifyWebApiException;

  void shouldSucceed_async() throws ExecutionException, InterruptedException;

  void shouldSucceed(final T type);
}
