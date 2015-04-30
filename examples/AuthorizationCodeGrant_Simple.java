import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;
import com.wrapper.spotify.models.User;

public class YouFmToSpotifyPlaylist {

	public static void main(String[] args) {
		/* Application details necessary to get an access token */
	    final String clientId = "<insert client id>";
	    final String clientSecret = "<insert client secret>";
	    String code = "";
	    final String redirectUri = "<insert redirect URI>";

	    /* Create a default API instance that will be used to make requests to Spotify */
	    final Api api = Api.builder()
	            .clientId(clientId)
	            .clientSecret(clientSecret)
	            .redirectURI(redirectUri)
	            .build();

	    try {
	    	String accessToken = "";
	    	String refreshToken = "";
	    	
	    	/* Set the necessary scopes that the application will need from the user */
	    	final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");

	    	/* Set a state. This is used to prevent cross site request forgeries. */
	    	final String state = "someExpectedStateString";
	    	
	    	//Create the AuthorisationURL
	    	String authorizeURL = api.createAuthorizeURL(scopes, state);
	    	
	    	//print AuthorisationURL. Copy and paste it to your browser.
	    	System.out.println("AuthorisationURL: ");
	    	System.out.println(authorizeURL);
	    	//Copy the code parameter back from the browser to the console.
	    	System.out.println("Code: ");
	    	Scanner scanner = new Scanner(System.in);
	    	try {
	    		code = scanner.nextLine();
	    	} finally {
	    		scanner.close();
	    	}
	    	//Double check the code.
	    	System.out.println("AuthorisationCode: ");
	    	System.out.println(code);
	    	
	    	
	    	/* Retrieve an access token */
	    	final AuthorizationCodeCredentials authorizationCodeCredentials = api.authorizationCodeGrant(code).build().get();
		
	    	//Get the Tokens from the authorizationCodeCredentials
	    	accessToken = authorizationCodeCredentials.getAccessToken();
	    	refreshToken = authorizationCodeCredentials.getRefreshToken();
	    	
	    	//Print them so you can use them later again
	    	System.out.println("AccessToken: " + accessToken);
	    	System.out.println("RefreshToken: " + refreshToken);
	    	
	    	//Set Tokens
	    	api.setAccessToken(accessToken);
	    	api.setRefreshToken(refreshToken);
	    	
	    	//Update AccessToken (only if necessary)
	    	accessToken = api.refreshAccessToken().build().get().getAccessToken();
	    	api.setAccessToken(accessToken);
		
	    	/* Retrieve information about the user.
	    	 * The amount of information that is set on the User object depends on the scopes that
	    	 * the user has allowed the application to read.
	    	 * Read about which scopes that are available on
	    	 * https://developer.spotify.com/spotify-web-api/get-users-profile/
	    	 */
	    	final User currentUser = api.getMe().build().get();
	    	
	    	/* Use the information about the user */
	    	System.out.println("URI to currently logged in user is: " + currentUser.getUri());
	    	System.out.println("The currently logged in user comes from: " + currentUser.getCountry());
	    	System.out.println("You can reach this user at: " + currentUser.getEmail());

	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.out.println("Something went wrong. : " + e.getMessage());
	    }
	}

}