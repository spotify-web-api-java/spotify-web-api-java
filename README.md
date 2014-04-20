Spotify Web API Java [![Build Status](https://magnum.travis-ci.com/thelinmichael/spotify-web-api-java.svg?token=8zHBtRMhPg5xwzh4iTuo&branch=master)](https://magnum.travis-ci.com/thelinmichael/spotify-web-api-java)
==================
This is a Java wrapper for the [Spotify Web API](https://developer.spotify.com/spotify-web-api/). It includes helper functions for looking up as well as searching for albums, artists, and tracks. Functionality to make requests for playlists and user's information are still to be implemented.

## Usage

```java
// Create an API instance. The default instance connects to https://api.spotify.com/.
Api api = Api.DEFAULT_API; 

// Create a request object for the type of request you want to make
AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

// Send the request
Album album = request.getAlbum();

// Handle the response
List<String> genres = album.getGenres(); 
for (String genre : genres) {
  System.out.println(genre);
}
```
