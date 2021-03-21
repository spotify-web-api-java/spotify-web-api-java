package complete_workflow;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.PagingCursorbased;
import com.wrapper.spotify.model_objects.specification.PlayHistory;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERequest;
import com.wrapper.spotify.requests.data.player.GetCurrentUsersRecentlyPlayedTracksRequest;

public class AuthoritationCodePKCECompleteWorkFlow {
	
	/*
	 *This method is the most appropriate for desktop applications, as we will not use the clientSecret of our app, only the clientId, which we will get from Spotify Developer DashBoard 
	 *after registering our app. Instead we will use two codes, the codeVerifier and the codeChallenge.  Instead, your application should generate a code verifier and a code challenge 
	 *before each authentication request.The code verifier is a cryptographically random string between 43 and 128 characters in length. It can contain letters, digits, underscores, 
	 *periods, hyphens, or tildes. To generate the code challenge, your app should hash the code verifier using the SHA256 algorithm. Then, base64url encode the hash that you generated. 
	 *We will also need the redirection URI, to which the user will be redirected after authorising his account to obtain the code that is exchanged for the access and refresh tokens.
	 *This URI must be registered in our app in Spotify Developer DashBoards.
	 * 
	 */

	  private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
	  private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");
	  private static final String codeChallenge = "w6iZIj99vHGtEx_NVl9u3sthTN646vvkiP8OMCGfPmo";
	  private static final String codeVerifier = "NlJx4kD4opk4HY7zBM6WfUHxX7HoF8A2TUhOIPGA74w";
	  
	  /*
	   * Add the properties to our spotifyApi object
	   */
	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setRedirectUri(redirectUri)
	    .build();

	  /**
	   * <p>We make the request to obtain the authorisation URI that will allow us, after the redirection, to obtain the code we need to 
	   * obtain the access and refresh token. The URI shown must be copied in our browser. The scope field indicates what we will be able 
	   * to do with the user's information. This will be shown to them before they authorise our app.</p>
	   */
	  public static void obtainsCode() {
		 final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodePKCEUri(codeChallenge)
			        .scope("user-read-recently-played")
				    .build();
	    final URI uri = authorizationCodeUriRequest.execute();
	    System.out.println("The URI you have to use in the browser is the following: " + uri.toString());
	  }
	
	  
	  /**
	   * <p>After obtaining the code, we add it to spotifyApi along with the codeVerifier and execute a token fetch request that will return the access and refresh token. 
	   * The refresh token will be used to obtain an access token in each new request.</p>
	   * 
	   * @param code - The code that returns the server to the redirect uri. It appears at http://www.example.com/...code=AQwe3...2dFv
	   */
	  public static void obtainsTokens(String code) {
		  final AuthorizationCodePKCERequest authorizationCodePKCERequest = spotifyApi.authorizationCodePKCE(code, codeVerifier)
                  .build();	  
		try {
			AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodePKCERequest.execute();
			spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
		    spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
		    
		    System.out.println("Access token: "+spotifyApi.getAccessToken());
			System.out.println("Refresh code: "+spotifyApi.getRefreshToken());
		    
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SpotifyWebApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	  
	  }
	  
	  
	  /**
	   * <p>With this type of credential retrieval we can see information about the profile with which we are logged into Spotify thanks to the obtainsCode() method. 
	   * Among others, we can see the last 5 plays of our profile and all its information.</p>
	   * 
	   * @param refreshToken - The refresh token displayed on console after execution of obtainsTokens()
	   * @throws ParseException
	   * @throws SpotifyWebApiException
	   * @throws IOException
	   */
	  public static void obtainsRecentTracks(String refreshToken) throws ParseException, SpotifyWebApiException, IOException {
		 
		  spotifyApi.setRefreshToken(refreshToken);
		  
		  AuthorizationCodePKCERefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodePKCERefresh()
	    		    .build();
	      AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

	      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

	     GetCurrentUsersRecentlyPlayedTracksRequest agb=spotifyApi.getCurrentUsersRecentlyPlayedTracks().build();
	     PagingCursorbased<PlayHistory> age=agb.execute();
	     
	     for (Integer i=0;i<5;i++) {
	    	 System.out.println(String.format("The track number %s is: %s", i.toString() ,age.getItems()[i].toString()));
	     }
	  }
	     
	  
	  
	public static void main(String[] args) {
		//obtainsCode();
		
		
		//obtainsTokens("AQDSsVx3udX39eztbNdTEqcvnX1R3fwfpbrWeI6hnMLaIzpnfmoo0zfkO4yRLgsR0UVDDe9xgVFHBXqCIpnjvbA-TxBHDMjpuG30gjnSE9vADrm9vEXsJayJXEyWCIy2U2tBOB-iGfhA4pHAsTdzCSaHGfNPH-9ziOxi7j5pG5lDsndwVrOGuFd0_BgDeFRLIFuYw6FGWj9W5f1E2T77bD_uuRjwN_9xiIDDG9gBJXpPo-U40e-lY1STXZuwtiP3UTXIDBCqv883icmc_7E1NjrzUc5wUA");
		
		
		try {
			
			obtainsRecentTracks("AQBy6boUOM5DpfH-Rh9fprJb0uL0WlowOM5XCX4_zSGp5vuQ51Vz54M7ykV5im8Q-TdDMvDT0ctP189cBF5SnDZxNd915ZR6Def79ycw5nbT9HB3bVXxFn73XGe77IW-LT0");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SpotifyWebApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
