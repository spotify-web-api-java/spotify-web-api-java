Spotify Web API Java [![Build Status](https://travis-ci.org/thelinmichael/spotify-web-api-java.svg?branch=master)](https://travis-ci.org/thelinmichael/spotify-web-api-java)
==================

This is a Java wrapper for the [Spotify Web API](https://developer.spotify.com/spotify-web-api/).

It includes helper functions to do:

- Album, artist, track, user, playlist lookup
- Album lookup for a specific artist
- Top tracks for a specific artist
- Artists related to a given artist
- Album, artist and track search
- Retrieval of a user's playlists
- Playlist creation
- Adding tracks to a playlist

Some methods require authentication, which can be done using these flows:

- [Client credentials flow](http://tools.ietf.org/html/rfc6749#section-4.4) (Application-only authentication)
- [Authorization code grant](http://tools.ietf.org/html/rfc6749#section-4.1) (Signed by user)

Even though authentication isn't always necessary, it always gives benefits such as an increased rate limit.

## Install

As of version 1.4.5 the artifact is available through Maven Central.

```
<dependency>
  <groupId>se.michaelthelin.spotify</groupId>
  <artifactId>spotify-web-api-java</artifactId>
  <version>1.4.5</version>
</dependency>
```

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

This section includes example requests for every helper function. Please note that the requests in the examples doesn't necessarily use all possible options when making the request. Since the requests use the builder pattern, optional parameters can be appended using the builder. Required parameters are parameters on the api method (e.g. searchTracks).

The examples below use the synchronous version of the request (.get). In order to make asynchronous request, simply use the request's .getAsync() method instead.

Please refer to the documentation linked in the headline of each example to read about potential authorization requirements.

#### Searching tracks, artists and albums

##### [Searching for tracks](https://developer.spotify.com/spotify-web-api/search-item/)

```java
final TrackSearchRequest request = api.searchTracks("Mr. Brightside").build();
 
try {
   final Page<Track> trackSearchResult = request.get();
   System.out.println("I got " + trackSearchResult.getTotal() + " results!");
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Searching for artists](https://developer.spotify.com/spotify-web-api/search-item/)

```java
final TrackSearchRequest request = api.searchArtists("tania bowra").limit(10).build();
 
try {
   final Page<Artist> artistSearchResult = request.get();
   final List<Artist> artists = artistSearchResult.getItems();
   
   System.out.println("I've found " + artistSearchResult.getTotal() + " artists!");
   
   for (Artist artist : artists) {
     System.out.println(artist.getName());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Searching for albums](https://developer.spotify.com/spotify-web-api/search-item/)

```java
final AlbumSearchRequest request = api.searchAlbums("black album").offset(0).limit(3).build();
 
try {
   final Page<SimpleAlbum> albumSearchResult = request.get();
   
   System.out.println("Printing results..");
   for (SimpleAlbum album : albumSearchResult.getItems)) {
     System.out.println(album.getName());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

#### Lookup of albums, tracks, artists, playlists and users

##### [Album lookup](https://developer.spotify.com/spotify-web-api/get-album/)

```java
final String albumId = "0sNOF9WDwhWunNAHPD3Baj";
final AlbumRequest request = api.getAlbum(albumId).build();
 
try {
   final Album album = request.get();
   System.out.println("Retrieved album " + album.getName());
   System.out.println("Its popularity is " + album.getPopularity()); 
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Track lookup](https://developer.spotify.com/spotify-web-api/get-track/)

```java
final TrackRequest request = api.getTrack("0eGsygTp906u18L0Oimnem").build();

try {
   final Track track = request.get();
   System.out.println("Retrieved track " + track.getName());
   System.out.println("Its popularity is " + track.getPopularity());
   
   if (track.isExplicit()) {
      System.out.println("This track is explicit!");
   } else {
      System.out.println("It's OK, this track isn't explicit.");
   }
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Artist lookup](https://developer.spotify.com/spotify-web-api/get-artist/)

```java
final Artist request = api.getArtist("0LcJLqbBmaGUft1e9Mm8HV").build();

try {
   final Artist artist = request.get();
   
   System.out.println("This artist's name is " + artist.getName());
   
   // Print URLs to the artist's images
   final List<Image> images = artist.getImages();
   for (Image image : images) {
      System.out.println(image.getUrl());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Playlist lookup](https://developer.spotify.com/spotify-web-api/get-playlist/)

```java
final PlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn").build();

try {
   final Playlist playlist = request.get();
   
   System.out.println("Retrieved playlist " + playlist.getName());
   System.out.println(playlist.getDescription());
   System.out.println("It contains " + playlist.getTracks().getTotal() + " tracks");
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [User lookup](https://developer.spotify.com/spotify-web-api/get-users-profile/)

```java
final UserRequest request = api.getUser("wizzler").build();

try {
   final User user = request.get();

   System.out.println("This user's Spotify URI is " + user.getUri());   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

#### Lookup of of several albums, tracks, and artists

##### [Several albums lookup](https://developer.spotify.com/spotify-web-api/get-several-albums/)

```java
final AlbumsRequest = api.getAlbums("41MnTivkwTO3UUJ8DrqEJJ", "0ntmUPwjfE9iGGM9qHglCm").get();

try {
   final List<Album> albums = request.get();
  
   final Album firstAlbum = albums.get(0);
   
   System.out.println(firstAlbum.getName());
   System.out.println("The artists on this albums are");
   
   for (SimpleArtist artist : firstAlbum.getArtists()) {
      System.out.println(artist.getName());
   }
   
   
   final Album secondAlbum = albums.get(1);
   
   System.out.println(secondAlbum.getName());
   System.out.println("The artists on this albums are");
   
   for (SimpleArtist artist : secondAlbum.getArtists()) {
      System.out.println(artist.getName());
   }

} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Several tracks lookup](https://developer.spotify.com/spotify-web-api/get-several-tracks/)

```java
final TracksRequest request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G").build();

try {
   final List<Track> tracks = request.get();
   
   for (Track track : tracks) {
      System.out.println(track.getName());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Several artists lookup](https://developer.spotify.com/spotify-web-api/get-several-artists/)

```java
final ArtistsRequest request = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin").build();

try {
  
   final List<Artist> artists = request.get();

   for (Artist artist : artists) {
      System.out.println(artist.getName() + " has popularity " + artist.getPopularity());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

#### Other lookup methods


##### [Related artists](https://developer.spotify.com/web-api/get-related-artists/)

```java
final RelatedArtistsRequest request = api.getArtistRelatedArtists("0qeei9KQnptjwb8MgkqEoy").build();

try {

  final List<Artist> artists = request.get();

  if (numbers.isEmpty()) {
    System.out.println("Didn't find any similar artists!");
  } else {
    System.out.println("The related artists are:");
    for (Artist artist : artists) {
      System.out.println(artist.getName());
    }
  }
} catch (Exception e) {
  System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Current user lookup](https://developer.spotify.com/spotify-web-api/get-current-users-profile/)

The attributes that are loaded onto the User object depends on the permissions given by the user to the application.

```java
final CurrentUserRequest request = api.getMe().build();
  
try {
   final User user = request.get();
   
   System.out.println("Display name: " + user.getDisplayName());
   System.out.println("Email: " + user.getEmail());
   
   System.out.println("Images:");
   for (Image image : user.getImages()) {
      System.out.println(image.getUrl());
   }
   
   System.out.println("This account is a " + user.getProduct() + " account");
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Playlist track lookup](https://developer.spotify.com/spotify-web-api/get-playlists-tracks/)

```java
final PlaylistTracksRequest request = api.getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn").build();

try {
   final Page<PlaylistTrack> page = request.get();
   
   final List<PlaylistTrack> playlistTracks = page.getItems();
   
   for (PlaylistTrack playlistTrack : playlistTracks) {
      System.out.println(playlistTrack.getTrack().getName());
   }
   
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Users playlist lookup](https://developer.spotify.com/spotify-web-api/get-list-users-playlists/)

```java
final UserPlaylistsRequest request = api.getPlaylistsForUser("thelinmichael").build();

try {
   final Page<SimplePlaylist> playlistsPage = request.get();
   
   for (SimplePlaylist playlist : playlistsPage.getItems()) {
      System.out.println(playlist.getName());
   }
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

#### Playlist manipulation

##### [Add tracks to a playlist](https://developer.spotify.com/spotify-web-api/add-tracks-to-playlist/)

```java
final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:track:0BG2iE6McPhmAEKIhfqy1X");

// Index starts at 0
final int insertIndex = 3;

final AddTrackToPlaylistRequest request = api.addTracksToPlaylist("thelinmichael", "5ieJqeLJjjI8iJWaxeBLuK", tracksToAdd)
  .position(insertIndex)
  .build();
  
try {
  request.get(); // Empty response
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

##### [Create a playlist](https://developer.spotify.com/spotify-web-api/create-playlist/)

```java
final PlaylistCreationRequest request = api.createPlaylist("thelinmichael","title")
  .publicAccess(true)
  .build();

try {
  final Playlist playlist = request.get();
  
  System.out.println("You just created this playlist!");
  System.out.println("Its title is " + playlist.getName());
} catch (Exception e) {
   System.out.println("Something went wrong!" + e.getMessage());
}
```

#### Change log

##### 1.4.5

- Distribute via Maven Central.
- Use query parameter to add tracks.

##### 1.4.1

- Add available_markets to SimpleAlbum
- Add show dialog parameter to authorization URL

##### 1.4.0

- Enable setting limit and offset when requesting a user's playlists.

##### 1.3

- Bug fixes. (Thanks [danieldisu](https://www.github.com/danieldisu) and [Schweig](https://www.github.com/Schweig))

##### 1.2

- Add [Related artists](https://developer.spotify.com/web-api/get-related-artists/) endpoint
