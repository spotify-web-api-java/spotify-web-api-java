import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ApplicationAuthenticationRequest;
import com.wrapper.spotify.models.ApplicationAuthenticationToken;

import static junit.framework.TestCase.fail;

/**
 * This example shows how to get refresh an access token asynchronously. There's a
 * synchronous version of the method available as well.
 *
 * The authorization flow used is documented in detail at
 * https://developer.spotify.com/spotify-web-api/authorization-guide/#client-credentials-flow
 * in the "Client Credentials" section.
 */
public class ApplicationAuthentication {

  public static void main(String[] strings) {

    final String clientId = "<insert client id>";
    final String clientSecret = "<insert client secret>";

    final Api api = Api.DEFAULT_API;

    /* Create a request object. */
    final ApplicationAuthenticationRequest request = api.applicationAuthentication()
            .withClientId(clientId)
            .withClientSecret(clientSecret)
            .build();

    /* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
    final SettableFuture<ApplicationAuthenticationToken> responseFuture = request.getAsync();

    /* Add callbacks to handle success and failure */
    Futures.addCallback(responseFuture, new FutureCallback<ApplicationAuthenticationToken>() {
      @Override
      public void onSuccess(ApplicationAuthenticationToken tokenResponse) {
        /* The tokens were retrieved successfully! */
        System.out.println("Successfully retrieved an access token! " + tokenResponse.getAccessToken());
        System.out.println("The access token expires in " + tokenResponse.getExpiresIn() + " seconds");

        /* Please note that this flow does not return a refresh token.
         * That's only for the Authorization code flow */
      }

      @Override
      public void onFailure(Throwable throwable) {
        /* An error occurred while getting the access token. This is probably caused by the client id or
         * client secret is invalid. */
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

  }
}
