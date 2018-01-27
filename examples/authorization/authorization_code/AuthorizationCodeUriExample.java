package authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

public class AuthorizationCodeUriExample {
  private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
  private static final String clientSecret = "zudknyqbh3wunbhcvg9uyvo7uwzeu6nne";
  private static URI redirectUri = null;

  static {
    try {
      redirectUri = new URIBuilder().setScheme("https").setHost("example.com").setPath("spotify-redirect").build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientId(clientSecret)
          .setRedirectUri(redirectUri)
          .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
          .state("x4xkmn9pu3j6ukrs8n")
          .scope("user-read-birthdate")
          .show_dialog(true)
          .build();

  public static void authorizationCodeUri_Sync() {
    try {
      final URI uri = authorizationCodeUriRequest.execute();

      System.out.println("URI: " + uri.toString());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void authorizationCodeUri_Async() {
    try {
      final Future<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

      // ...

      final URI uri = uriFuture.get();

      System.out.println("URI: " + uri.toString());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}