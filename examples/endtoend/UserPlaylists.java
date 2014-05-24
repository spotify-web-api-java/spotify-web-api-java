package com.wrapper.spotify.endtoend;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimplePlaylist;
import com.wrapper.spotify.models.TokenResponse;

import static junit.framework.TestCase.fail;

public class UserPlaylists {

  public static void main(String[] args) {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String code = "";
    final String redirectUri = "http://www.wrapper.com/test-callback";

    final Api api = Api.builder().build();

    try {
      final TokenResponse tokenResponse = api.getTokens(clientId, clientSecret, code, redirectUri).build().get();

      final Page<SimplePlaylist> michaelsPlaylistsPage = api.getPlaylistsForUser("thelinmichael")
              .accessToken(tokenResponse.getAccessToken())
              .build()
              .get();
      System.out.println("Michael has " + michaelsPlaylistsPage.getTotal() + " playlists");

      final Page<SimplePlaylist> faruksPlaylistsPage = api.getPlaylistsForUser("faruksahin").
              accessToken(tokenResponse.getAccessToken())
              .build()
              .get();
      System.out.println("Faruk has " + faruksPlaylistsPage.getTotal() + " playlists");
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }
}
