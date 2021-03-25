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
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.player.GetCurrentUsersRecentlyPlayedTracksRequest;


public class AuthoritationCodeCompleteWorkFlow {
	/*
	* Before testing the API you must register your application on the Spotify Developers dashboard. 
	* After registration, copy your client_id and secret_id and paste them into the following constants. 
	* You will also need to have a redirect address, to which the user will be redirected after authorization 
	* to use the app, which must be authorized in the Spotify Developer dashboard in the configuration area. 
	* After giving permissions to our application to use the data we need, we will be redirected to the URI we
	* have configured. In it, there will be a code that will be used to obtain an access and refresh token.
	*/
	
	private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
	private static final String clientSecret = "zudknyqbh3wunbhcvg9uyvo7uwzeu6nne";
	private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

	  
	  /*
	   * Add the properties to our spotifyApi object.
	   */
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setClientSecret(clientSecret)
	    .setRedirectUri(redirectUri)
	    .build();
	  
	  /*
	   * After pasting the client_id and secret_id we can execute the fetching of the code that will later be exchanged for an access token and a refresh token.
	   * The scope field indicates what we can be done with the user's information. This will be shown to them before they authorize our app.
	   */
	  public static void obtainsCode() {
		  final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().scope("user-read-recently-played").build();
		  final URI authorizationCodeCredentials = authorizationCodeUriRequest.execute();
		  System.out.println("The URI you have to use in the browser is the following: "+authorizationCodeCredentials.toString());
	  }
	  
	  
	  /*
	   * After using the code, the server returns an access token and a refresh token that will be used to renew the access token for each request.
	   * 
	   * parameter: code - The code that returns the server to the redirect uri. It appears at http://www.example.com/...code=AQwe3...2dFv
	   * can throws: ParseException (Signals a protocol exception due to failure to parse a message element)
	   * can throws: SpotifyWebApiException (An exception happened, eg. a HTTP status code 4** or 5** has been returned in a request.)
	   * can throws: IOException (Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed orinterrupted I/O operations.)
	   */
	  
	  public static void obtainsTokens(String code) throws ParseException, SpotifyWebApiException, IOException {
		  
		  final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();       
	      final AuthorizationCodeCredentials authorizationCodeCredentials2 = authorizationCodeRequest.execute();
	            
	      spotifyApi.setAccessToken(authorizationCodeCredentials2.getAccessToken());
	      spotifyApi.setRefreshToken(authorizationCodeCredentials2.getRefreshToken());
	      
		  System.out.println("Access token: "+spotifyApi.getAccessToken());
		  System.out.println("Refresh code: "+spotifyApi.getRefreshToken());
	  }
	  
	  
	  /*
	   * With this type of credential retrieval we can see information about the account with which we are logged into Spotify thanks to the obtainsCode() method. 
	   * Among others, we can see the last 5 plays of our profile and all its information.
	   * 
	   * parameter: refreshToken - The refresh token displayed on console after execution of obtainsTokens()
	   * can throws: ParseException (Signals a protocol exception due to failure to parse a message element)
	   * can throws: SpotifyWebApiException (An exception happened, eg. a HTTP status code 4** or 5** has been returned in a request.)
	   * can throws: IOException (Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed orinterrupted I/O operations.)
	   */
	  public static void obtainsRecentTracks(String refreshToken) throws ParseException, SpotifyWebApiException, IOException {
		 
		  spotifyApi.setRefreshToken(refreshToken);
		  
		  AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
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
		
/*It is necessary to execute function by function, in order to copy the code after the first function and the refresh token after the second.*/
		
		obtainsCode();
		
		
//		try {
//		
//			obtainsTokens("AQCuk9rNUYhmL239J8T14BaJfxbR_xFTv3fP_NvsXZwZf4zbEmXnIsn3NBxctbAP2NIHDZHBNcGjpH9G8uGDYwA9WaQelgSyjFpBQeLpAUZcc77wiJj1k24fMGXz0kXR4U-05dADm8UKVpLBvjXMgzbZ79MIS4hSlNLIcw8V8YxmEaFeVS_RgjCpk1uHXlYd9KxQzJbOc11XcBgXYWl1Hlcp8g");
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (SpotifyWebApiException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			
//			obtainsRecentTracks("AQAHdhJHISQFwIMWNYjrziFuqK7i2pMVOR5eKN515yT-0q7GjjmEZJxgOe0AVucp3q5lVFSZ11Fn2cK7y8OPKhUjvnyBTK0jkE1Hmar6jVv9tDA0a5pQTSwxl7qJcPBhoH8");
//		
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (SpotifyWebApiException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		

	}

}
