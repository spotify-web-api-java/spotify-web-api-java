Spotify Web API Java [![Build Status](https://travis-ci.org/thelinmichael/spotify-web-api-java.svg?branch=master)](https://travis-ci.org/thelinmichael/spotify-web-api-java)
==================

This is a Java wrapper for the [Spotify Web API](https://developer.spotify.com/spotify-web-api/). It includes helper functions for looking up as well as searching for albums, artists, and tracks. Functionality to make requests for playlists and user's information are still to be implemented.

- [x] Album, artist and track lookup
- [x] Album lookup for specific artist
- [x] Top tracks for specific artist
- [x] Album, artist and track search
- [x] Playlists for specific user
- [ ] Playlist lookup
- [ ] Create a playlist
- [ ] Add tracks to a playlist
- [ ] Remove tracks from a playlist
- [ ] Authenticated user lookup
- [x] User lookup


## Usage

#### Asynchronous, using [Guava's future API](https://code.google.com/p/guava-libraries/wiki/ListenableFutureExplained).

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

#### Synchronous 
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
