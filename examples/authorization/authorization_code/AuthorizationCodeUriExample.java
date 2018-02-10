package authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AuthorizationCodeUriExample {
  private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
  private static final String clientSecret = "zudknyqbh3wunbhcvg9uyvo7uwzeu6nne";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientSecret(clientSecret)
          .setRedirectUri(redirectUri)
          .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
          .state("x4xkmn9pu3j6ukrs8n")
          .scope("user-read-birthdate,user-read-email")
          .show_dialog(true)
          .build();

  public static void authorizationCodeUri_Sync() {
    final URI uri = authorizationCodeUriRequest.execute();

    System.out.println("URI: " + uri.toString());
  }

  public static void authorizationCodeUri_Async() {
    try {
      final Future<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

      // ...

      final URI uri = uriFuture.get();

      System.out.println("URI: " + uri.toString());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}