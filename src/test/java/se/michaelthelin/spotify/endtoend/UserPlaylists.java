package se.michaelthelin.spotify.endtoend;

import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.models.Page;
import se.michaelthelin.spotify.models.SimplePlaylist;

import static junit.framework.TestCase.fail;

public class UserPlaylists {

  public static void main(String[] args) {
    String clientId = "";
    String clientSecret = "";
    String code = "";
    String redirectUri = "";

    Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .code(code)
            .redirectUri(redirectUri)
            .build();

    try {
      Page<SimplePlaylist> michaelsPlaylistsPage = api.getPlaylistsForUser("thelinmichael").build().get();
      System.out.println("Michael has " + michaelsPlaylistsPage.getTotal() + " playlists.");

      Page<SimplePlaylist> faruksPlaylistsPage = api.getPlaylistsForUser("faruksahin").build().get();
      System.out.println("Faruk has " + faruksPlaylistsPage.getTotal() + " playlists.");
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }
}
