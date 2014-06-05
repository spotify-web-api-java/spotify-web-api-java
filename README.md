Spotify Web API Java [![Build Status](https://travis-ci.org/thelinmichael/spotify-web-api-java.svg?branch=master)](https://travis-ci.org/thelinmichael/spotify-web-api-java)
==================

This is a Java wrapper for the [Spotify Web API](https://developer.spotify.com/spotify-web-api/).

It includes helper functions to do:

- Album, artist, track, user, playlist lookup
- Album lookup for a specific artist
- Top tracks for a specific artist
- Album, artist and track search
- Retrieval of a user's playlists
- Playlist creation
- Adding tracks to a playlist

Some methods require authentication, which can be done using these flows:

- [Client credentials flow](http://tools.ietf.org/html/rfc6749#section-4.4) (Application-only authentication)
- [Authorization code grant](http://tools.ietf.org/html/rfc6749#section-4.1) (Signed by user)

Even though authentication isn't always necessary, it always gives benefits such as an increased rate limit.

## Usage

### Asynchronous, using [Guava's future API](https://code.google.com/p/guava-libraries/wiki/ListenableFutureExplained).

```java
// Create an API instance. The default instance connects to https://api.spotify.com/.
Api api = Api.DEFAULT_API; 

// Create a request object for the type of request you want to make
AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build();

// Retrieve a future for an album
SettableFuture<Album> albumFuture = request.getAsync();

// Create callbacks in case of success or failure
Futures.addCallback(albumFuture, new FutureCallback<Album>() {

  // Print the genres of the album call is successful
  public void onSuccess(Album album) {
    List<String> genres = album.getGenres(); 
    for (String genre : genres) {
      System.out.println(genre);
    }
  }
  // In case of failure
  public void onFailure(Throwable thrown) {
    System.out.println("Could not get albums.");
  }
});
```

### Synchronous 
```java
// Create an API instance. The default instance connects to https://api.spotify.com/.
Api api = Api.DEFAULT_API; 

// Create a request object for the type of request you want to make
AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build();

// Retrieve an album
try {
  Album album = request.get();
  
  // Print the genres of the album
  List<String> genres = album.getGenres(); 
  for (String genre : genres) {
    System.out.println(genre);
  };
  
} catch (Exception e) {
  System.out.println("Could not get albums.");
}
```

## Authorization
Client ID, client secret and redirect URI are set on the API object when it's created, and will be used by functions that need them, e.g. when retrieving or refreshing an access token. You can retrieve these credentials by creating an application [here](https://developer.spotify.com/my-applications).

```java
Api api = Api.builder()
  .clientId("<your_client_id>")
  .clientSecret("<your_client_secret>")
  .redirectURI("<your_redirect_uri>")
  .build();
```

Access token and refresh token can also be set using the Api builder.

```java
Api api = Api.builder()
  .accessToken("<your_access_token>")
  .refreshToken("<your_refresh_token>")
  .build();
```

There are two ways of retrieving access tokens. The first one is the Client Credentials flow, which is useful when a specific user's permission isn't needed, for example when simply looking up albums or searching for tracks. The second one is the Authorization Code Grant, which requires a user to sign in and authorize the application.

#### Client Credentials flow

Use the Client Credentials flow when the requests doesn't require permission from a specific user. This flow doesn't return an refresh token. However, there are still benefits with using an access token when making requests as it gives the application a higher rate limit.

The example below implements the asynchronous version of the Client Credentials flow. 

```java
final String clientId = "<insert client id>";
final String clientSecret = "<insert client secret>";

final Api api = Api.builder()
  .clientId(clientId)
  .clientSecret(clientSecret)
  .build();

/* Create a request object. */
final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

/* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

/* Add callbacks to handle success and failure */
Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
  @Override
  public void onSuccess(ClientCredentials clientCredentials) {
    /* The tokens were retrieved successfully! */
    System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
    System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");
    
    /* Set access token on the Api object so that it's used going forward */
    api.setAccessToken(clientCredentials.getAccessToken());
    
    /* Please note that this flow does not return a refresh token.
   * That's only for the Authorization code flow */
  }

  @Override
  public void onFailure(Throwable throwable) {
    /* An error occurred while getting the access token. This is probably caused by the client id or
     * client secret is invalid. */
  }
});
```

When the access token has been set on the Api object, it will be used automatically when making requests. This wrapper leaves it to the implementer to handle expiration time. If a token expires, the access token on the Api object either needs to be set again, or set to null so that it's not used when making requests.

#### Authorization Code Grant

Using the Authorization Code Grant to retrieve an access token is necessary if the requests are bound to a specific user, e.g. the retrieving information about the current user (.getMe()). Using this flow returns a refresh token, which can be used to renew the access token before it expires.

##### Retrieving an Authorization code

The Authorization Code Grant flow requires a code, which is returned to the redirectURI when the user has authorized the application. This wrapper provides a helper function to create the URL which the user needs to go to in order to authorize the application.

```java
final String clientId = "<your_client_id>";
final String clientSecret = "<your_client_secret>";
final String redirectURI = "<your_redirect_uri>";

final Api api = Api.builder()
  .clientId(clientId)
  .clientSecret(clientSecret)
  .redirectURI(redirectURI)
  .build();

/* Set the necessary scopes that the applicaiton will need from the user */
final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");

/* Set a state. This is used to prevent cross site request forgeries. */
final String state = "someExpectedStateString";

String authorizeURL = api.createAuthorizeURL(scopes, state);

/* Continue by sending the user to the authorizeURL, which will look something like
   https://accounts.spotify.com:443/authorize?client_id=5fe01282e44241328a84e7c5cc169165&response_type=code&redirect_uri=https://example.com/callback&scope=user-read-private%20user-read-email&state=some-state-of-my-choice
 */
```

##### Retrieving the access token

When the code has been retrieved from the user, it can be used to get an access token as well as a refresh token.

```java
/* Application details necessary to get an access token */
final String code = "<insert code>";

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
    System.out.println("Luckily, I can refresh it using this refresh token! " +     authorizationCodeCredentials.getRefreshToken());
  
    /* Set the access token and refresh token so that they are used whenever needed */
    api.setAccessToken(authorizationCodeCredentials.getAccessToken());
    api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
  }

  @Override
  public void onFailure(Throwable throwable) {
    /* Let's say that the client id is invalid, or the code has been used more than once,
     * the request will fail. Why it fails is written in the throwable's message. */

  }
});
```

## More examples

This section includes example requests for every helper function. Please note that the requests in the examples doesn't necessarily use all possible options when making the request.

The examples below use the synchronous version of the request (.get). In order to make asynchronous request, simply use the request's .getAsync() method instead.

##### [Searching for tracks](https://developer.spotify.com/spotify-web-api/search-item/)

```java
final TrackSearchRequest request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();
 
try {
   final Page<Track> trackSearchResult = request.get();
   System.out.println("I got " + trackSearchResult.getTotal() + " results!");
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

More examples coming.
