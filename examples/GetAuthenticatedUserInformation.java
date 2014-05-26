import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.TokenResponse;
import com.wrapper.spotify.models.User;

/**
 * This example shows how to get information about the user that is 'connected' to the
 * access token. The methods used (api.getTokens and api.getCurrentUser) are synchronous, but are
 * available asynchronously as well.
 *
 * The authorization flow used is documented in detail at
 * https://developer.spotify.com/spotify-web-api/authorization-guide/#authorization_code_flow
 *
 * Details about requesting the current user's information is documented at
 * https://developer.spotify.com/spotify-web-api/get-users-profile/ in the
 * "Authorization Code" section.
 */
public class CurrentUser {

  public static void main(String[] args) {

    /* Application details necessary to get an access token */
    final String clientId = "<insert client id>";
    final String clientSecret = "<insert client secret";
    final String code = "<insert code>";
    final String redirectUri = "<insert redirect URI>";

    /* Create a default API instance that will be used to make requests to Spotify */
    final Api api = Api.DEFAULT_API;

    try {

      /* Retrieve an access token */
      final TokenResponse tokenResponse = api.getTokens()
              .withClientId(clientId)
              .withClientSecret(clientSecret)
              .withCode(code)
              .withRedirectUri(redirectUri)
              .build()
              .get();

      /* The token response contains a refresh token, an accesstoken, and some other things.
       * We only need the access token to retrieve the user's information.
       */
      final String accessToken = tokenResponse.getAccessToken();

      /* Retrieve information about the user.
      * The amount of information that is set on the User object depends on the scopes that
      * the user has allowed the application to read.
      * Read about which scopes that are available on
      * https://developer.spotify.com/spotify-web-api/get-users-profile/
      */
      final User currentUser = api.getCurrentUser().accessToken(accessToken).build().get();

      /* Use the information about the user */
      System.out.println("URI to currently logged in user is: " + currentUser.getUri());

    } catch (Exception e) {
      System.out.println("Something went wrong.");
    }

  }
}
