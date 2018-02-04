package authorization.client_credentials;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.util.concurrent.Future;

public class ClientCredentialsExample {
  private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
  private static final String clientSecret = "zudknyqbh3wunbhcvg9uyvo7uwzeu6nne";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientSecret(clientSecret)
          .build();
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
          .build();

  public static void clientCredentials_Sync() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void clientCredentials_Async() {
    try {
      final Future<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

      // ...

      final ClientCredentials clientCredentials = clientCredentialsFuture.get();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}