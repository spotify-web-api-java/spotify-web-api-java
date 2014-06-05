import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * This example shows how to get refresh an access token asynchronously. There's a
 * synchronous version of the method available as well.
 *
 * The authorization flow used is documented in detail at
 * https://developer.spotify.com/spotify-web-api/authorization-guide/#authorization_code_flow
 * in the "Authorization Code" section.
 */
public class RefreshTokenAsync {

  public static void main(String[] args) {

    /* Application details necessary to get an access token */
    final String clientId = "<insert client id>";
    final String clientSecret = "<insert client secret>";
    final String code = "<insert code>";
    final String redirectUri = "<insert redirect URI>";

    /* Create a default API instance that will be used to make requests to Spotify */
    final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectUri)
            .build();

    /* Make a token request. Asynchronous requests are made with the .getAsync method and synchronous requests
     * are made with the .get method. This holds for all type of requests. */
    final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

    /* Add callbacks to handle success and failure */
    Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() {
      @Override
      public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) {
        /* The tokens were retrieved successfully! */
        System.out.println("Successfully retrieved an access token! " + authorizationCodeCredentials.getAccessToken());
        System.out.println("The access token expires in " + authorizationCodeCredentials.getExpiresIn() + " seconds");
        System.out.println("Luckily, I can refresh it using this refresh token! " + authorizationCodeCredentials.getRefreshToken());
      }

      @Override
      public void onFailure(Throwable throwable) {
        /* Let's say that the client id is invalid, or the code has been used more than once,
         * the request will fail. Why it fails is written in the throwable's message. */
        fail(throwable.getMessage());
      }

    });

  }

}
