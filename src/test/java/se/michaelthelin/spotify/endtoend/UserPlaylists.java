package se.michaelthelin.spotify.endtoend;

import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimplePlaylist;
import se.michaelthelin.spotify.models.TokenResponse;

import static junit.framework.TestCase.fail;

public class UserPlaylists {

  public static void main(String[] args) {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String code = "";
    final String redirectUri = "http://www.michaelthelin.se/test-callback";

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
