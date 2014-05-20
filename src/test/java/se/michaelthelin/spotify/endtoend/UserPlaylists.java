package se.michaelthelin.spotify.endtoend;

import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.exceptions.ErrorResponseException;
import se.michaelthelin.spotify.exceptions.NoCredentialsException;
import se.michaelthelin.spotify.exceptions.UnexpectedResponseException;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimplePlaylist;
import se.michaelthelin.spotify.models.TokenResponse;

import java.io.IOException;

import static junit.framework.TestCase.fail;

public class UserPlaylists {

  public static void main(String[] args) throws ErrorResponseException, NoCredentialsException, UnexpectedResponseException, IOException {
    String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    String code = "";
    String redirectUri = "http://www.michaelthelin.se/test-callback";

    Api api = Api.builder().build();

    TokenResponse tokenResponse = api.getTokens()
            .authorizationHeader(clientId, clientSecret)
            .code(code)
            .redirectUri(redirectUri)
            .build()
            .post();

    try {
      Page<SimplePlaylist> michaelsPlaylistsPage = api.getPlaylistsForUser("thelinmichael")
              .accessToken(tokenResponse.getAccessToken())
              .build()
              .get();
      System.out.println("Michael has " + michaelsPlaylistsPage.getTotal() + " playlists");

      Page<SimplePlaylist> faruksPlaylistsPage = api.getPlaylistsForUser("faruksahin").
              accessToken(tokenResponse.getAccessToken())
              .build()
              .get();
      System.out.println("Faruk has " + faruksPlaylistsPage.getTotal() + " playlists");
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }
}
