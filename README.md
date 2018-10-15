| Android Developers |
| ------------------ |
| You **cannot** use this library for Android app development. Have a look at [kaaes/spotify-web-api-android](https://www.github.com/kaaes/spotify-web-api-android) and [Spotify's Android SDK](https://developer.spotify.com/technologies/spotify-android-sdk/) and [see why](https://github.com/thelinmichael/spotify-web-api-java/issues/120#issuecomment-363266591). |

Spotify Web API Java [![Build Status](https://travis-ci.org/thelinmichael/spotify-web-api-java.svg?branch=master)](https://travis-ci.org/thelinmichael/spotify-web-api-java)
====================

This is a Java wrapper/client for the [Spotify Web API](https://developer.spotify.com/web-api/).

## Table of Contents
1. **[Installation](#Installation)**
    1. **[Jitpack](#Jitpack)**
2. **[Documentation](#Documentation)**
3. **[General Usage](#General-Usage)**
    1. **[Authorization](#Authorization)**
4. **[Examples](#Examples)**
5. **[Contributions](#Contributions)**
    1. **[Code Overview](#Code-Overview)**

<a name="Installation"></a>

## Installation

The artifact is available through
[Maven Central](https://mvnrepository.com/artifact/se.michaelthelin.spotify/spotify-web-api-java) via
[Sonatype](https://oss.sonatype.org/#nexus-search;quick~spotify-web-api-java).
Or to use a snapshot of the latest commit you can use [jitpack.io](https://jitpack.io/) as described [further down below](#Jitpack).

### Maven

Latest official release:
```XML
<dependency>
  <groupId>se.michaelthelin.spotify</groupId>
  <artifactId>spotify-web-api-java</artifactId>
  <version>2.1.0</version>
</dependency>
```

Latest snapshot:
```XML
<dependency>
  <groupId>com.github.thelinmichael</groupId>
  <artifactId>spotify-web-api-java</artifactId>
  <version>master-SNAPSHOT</version>
</dependency>
```

### Gradle

Latest official release:
```Gradle
compile 'se.michaelthelin.spotify:spotify-web-api-java:2.1.0'
```

Latest snapshot:
```Gradle
compile 'com.github.thelinmichael:spotify-web-api-java:master-SNAPSHOT'
```

---

<a name="Jitpack"></a>

### Jitpack

In order to use Jitpack you need to add their repository to your `pom.xml`:

#### Maven
```XML
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Gradle
```Gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

<a name="Documentation"></a>

## Documentation
See this project's **[Javadoc](https://thelinmichael.github.io/spotify-web-api-java/)**.

*A huge thanks to [c-schuhmann](https://github.com/c-schuhmann) for his amazing work on the documentation!*

<a name="General-Usage"></a>

## General Usage
```Java
// For all requests an access token is needed
SpotifyApi spotifyApi = new SpotifyApi.Builder()
        .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
        .build();

// Create a request object with the optional parameter "market"
final GetSomethingRequest getSomethingRequest = spotifyApi.getSomething("qKRpDADUKrFeKhFHDMdfcu")
        .market(CountryCode.SE)
        .build();

void getSomething_Sync() {
  try {
    // Execute the request synchronous
    final Something something = getSomethingRequest.execute();

    // Print something's name
    System.out.println("Name: " + something.getName());
  } catch (Exception e) {
    System.out.println("Something went wrong!\n" + e.getMessage());
  }
}

void getSomething_Async() {
  try {
    // Execute the request asynchronous
    final Future<Something> somethingFuture = getSomethingRequest.executeAsync();

    // Do other things...

    // Wait for the request to complete
    final Something something = somethingFuture.get();

    // Print something's name
    System.out.println("Name: " + something.getName());
  } catch (Exception e) {
    System.out.println("Something went wrong!\n" + e.getMessage());
  }
}
```

<a name="Authorization"></a>

### Authorization
For authorization requests the API object requires at least to have
[your application](https://developer.spotify.com/my-applications)'s client ID and client secret set as its properties.
When using the authorization code flow, the application's redirect URI is required too. Those properties will then be
automatically used by functions that depend on them.

```Java
SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId("<your_client_id>")
  .setClientSecret("<your_client_secret>")
  .setRedirectUri("<your_redirect_uri>")
  .build();
```

There are two ways to retrieving an access token:

#### [Client Credentials Flow](http://tools.ietf.org/html/rfc6749#section-4.4)
Use the client credentials flow when the requests don't require permission from a specific user. This flow doesn't
return a refresh token and is useful for simple requests, like fetching albums or searching for tracks.

Example: [ClientCredentialsExample.java](examples/authorization/client_credentials/ClientCredentialsExample.java)

#### [Authorization Code Flow](http://tools.ietf.org/html/rfc6749#section-4.1)
Using the authorization code flow to retrieve an access token is necessary if the requests are bound to a specific user.
Using this flow returns a refresh token, which can be used to renew the access token before it expires. This is how it
works:

1. The authorization code flow requires a code, which is part of the `redirectUri`'s query parameters when the user has
opened a custom URL in a browser and authorized the application.

   Example: [AuthorizationCodeUriExample.java](examples/authorization/authorization_code/AuthorizationCodeUriExample.java)

2. When the code has been retrieved, it can be used in another request to get an access token as well as a refresh token.

   Example: [AuthorizationCodeExample.java](examples/authorization/authorization_code/AuthorizationCodeExample.java)

3. Now, the refresh token in turn can be used in a loop to retrieve new access and refresh tokens.

   Example: [AuthorizationCodeRefreshExample.java](examples/authorization/authorization_code/AuthorizationCodeRefreshExample.java)

---

When you've fetched an access and refresh token, you have to add them to your API properties for automatic usage in
requests. The implementer has to handle the access token's expiration.

```Java
spotifyApi
  .setAccessToken("<your_access_token>")
  .setRefreshToken("<your_refresh_token>")
  .build();
```

<a name="Examples"></a>

## Examples
- **Albums**
  - [Get an Album](examples/data/albums/GetAlbumExample.java)
  - [Get an Album's Tracks](examples/data/albums/GetAlbumsTracksExample.java)
  - [Get several Albums](examples/data/albums/GetSeveralAlbumsExample.java)


- **Artists**
  - [Get an Artist](examples/data/artists/GetArtistExample.java)
  - [Get an Artist's Albums](examples/data/artists/GetArtistsAlbumsExample.java)
  - [Get an Artist's Top Tracks](examples/data/artists/GetArtistsTopTracksExample.java)
  - [Get an Artist's Related Artists](examples/data/artists/GetArtistsRelatedArtistsExample.java)
  - [Get Several Artists](examples/data/artists/GetSeveralArtistsExample.java)


- **Browse**
  - Miscellaneous
    - [Get Available Genre Seeds](examples/data/browse/miscellaneous/GetAvailableGenreSeedsExample.java)
  - [Get a Category](examples/data/browse/GetCategoryExample.java)
  - [Get a Category's Playlists](examples/data/browse/GetCategorysPlaylistsExample.java)
  - [Get a List of Categories](examples/data/browse/GetListOfCategoriesExample.java)
  - [Get a List of Featured Playlists](examples/data/browse/GetListOfFeaturedPlaylistsExample.java)
  - [Get a List of New Releases](examples/data/browse/GetListOfNewReleasesExample.java)
  - [Get Recommendations](examples/data/browse/GetRecommendationsExample.java)


- **Follow**
  - [Check if Current User Follows Artists or Users](examples/data/follow/CheckCurrentUserFollowsArtistsOrUsersExample.java)
  - [Check if Users Follow a Playlist](examples/data/follow/CheckUsersFollowPlaylistExample.java)
  - [Follow Artists or Users](examples/data/follow/FollowArtistsOrUsersExample.java)
  - [Follow a Playlist](examples/data/follow/FollowPlaylistExample.java)
  - [Get User's Followed Artists](examples/data/follow/GetUsersFollowedArtistsExample.java)
  - [Unfollow Artists or Users](examples/data/follow/UnfollowArtistsOrUsersExample.java)
  - [Unfollow a Playlist](examples/data/follow/UnfollowPlaylistExample.java)


- **Library**
  - [Check User's Saved Albums](examples/data/library/CheckUsersSavedAlbumsExample.java)
  - [Check User's Saved Tracks](examples/data/library/CheckUsersSavedTracksExample.java)
  - [Get Current User's Saved Albums](examples/data/library/GetCurrentUsersSavedAlbumsExample.java)
  - [Get User's Saved Tracks](examples/data/library/GetUsersSavedTracksExample.java)
  - [Remove Albums for Current User](examples/data/library/RemoveAlbumsForCurrentUserExample.java)
  - [Remove User's Saved Tracks](examples/data/library/RemoveUsersSavedTracksExample.java)
  - [Save Albums for Current User](examples/data/library/SaveAlbumsForCurrentUserExample.java)
  - [Save Tracks for User](examples/data/library/SaveTracksForUserExample.java)


- **Personalization**
  - Simplified
    - [Get a User's Top Artists](examples/data/personalization/simplified/GetUsersTopArtistsExample.java)
    - [Get a User's Top Tracks](examples/data/personalization/simplified/GetUsersTopTracksExample.java)
  - [Get a User's Top Artists and Tracks](examples/data/personalization/GetUsersTopArtistsAndTracksExample.java)


- **Player**
  - [Get a User's Available Devices](examples/data/player/GetUsersAvailableDevicesExample.java)
  - [Get Information About The User's Current Playback](examples/data/player/GetInformationAboutUsersCurrentPlaybackExample.java)
  - [Get Current User's Recently Played Tracks](examples/data/player/GetCurrentUsersRecentlyPlayedTracksExample.java)
  - [Get the User's Currently Playing Track](examples/data/player/GetUsersCurrentlyPlayingTrackExample.java)
  - [Pause a User's Playback](examples/data/player/PauseUsersPlaybackExample.java)
  - [Seek To Position In Currently Playing Track](examples/data/player/SeekToPositionInCurrentlyPlayingTrackExample.java)
  - [Set Repeat Mode On User's Playback](examples/data/player/SetRepeatModeOnUsersPlaybackExample.java)
  - [Set Volume For User's Playback](examples/data/player/SetVolumeForUsersPlaybackExample.java)
  - [Skip User's Playback To Next Track](examples/data/player/SkipUsersPlaybackToNextTrackExample.java)
  - [Skip User's Playback To Previous Track](examples/data/player/SkipUsersPlaybackToPreviousTrackExample.java)
  - [Start/Resume a User's Playback](examples/data/player/StartResumeUsersPlaybackExample.java)
  - [Toggle Shuffle For User's Playback](examples/data/player/ToggleShuffleForUsersPlaybackExample.java)
  - [Transfer a User's Playback](examples/data/player/TransferUsersPlaybackExample.java)


- **Playlists**
  - [Add Tracks to a Playlist](examples/data/playlists/AddTracksToPlaylistExample.java)
  - [Change a Playlist's Details](examples/data/playlists/ChangePlaylistsDetailsExample.java)
  - [Create a Playlist](examples/data/playlists/CreatePlaylistExample.java)
  - [Get a List of Current User's Playlists](examples/data/playlists/GetListOfCurrentUsersPlaylistsExample.java)
  - [Get a List of a User's Playlists](examples/data/playlists/GetListOfUsersPlaylistsExample.java)
  - [Get a Playlist](examples/data/playlists/GetPlaylistExample.java)
  - [Get a Playlist Cover Image](examples/data/playlists/GetPlaylistCoverImageExample.java)
  - [Get a Playlist's Tracks](examples/data/playlists/GetPlaylistsTracksExample.java)
  - [Remove Tracks from a Playlist](examples/data/playlists/RemoveTracksFromPlaylistExample.java)
  - [Reorder a Playlist's Tracks](examples/data/playlists/ReorderPlaylistsTracksExample.java)
  - [Replace a Playlist's Tracks](examples/data/playlists/ReplacePlaylistsTracksExample.java)
  - [Upload a Custom Playlist Cover Image](examples/data/playlists/UploadCustomPlaylistCoverImageExample.java)


- **Search**
  - Simplified
    - [Search Albums](examples/data/search/simplified/SearchAlbumsExample.java)
    - [Search Artists](examples/data/search/simplified/SearchArtistsExample.java)
    - [Search Playlists](examples/data/search/simplified/SearchPlaylistsExample.java)
    - [Search Tracks](examples/data/search/simplified/SearchTracksExample.java)
  - [Search Item](examples/data/search/SearchItemExample.java)


- **Tracks**
  - [Get Audio Analysis for a Track](examples/data/tracks/GetAudioAnalysisForTrackExample.java)
  - [Get Audio Features for a Track](examples/data/tracks/GetAudioFeaturesForTrackExample.java)
  - [Get Audio Features for Several Tracks](examples/data/tracks/GetAudioFeaturesForSeveralTracksExample.java)
  - [Get Several Tracks](examples/data/tracks/GetSeveralTracksExample.java)
  - [Get a Track](examples/data/tracks/GetTrackExample.java)


- **User's Profile**
  - [Get Current User's Profile](examples/data/users_profile/GetCurrentUsersProfileExample.java)
  - [Get a User's Profile](examples/data/users_profile/GetUsersProfileExample.java)

<a name="Contributions"></a>

## Contributions
See [CONTRIBUTING.md](CONTRIBUTING.md).

- Build: `mvn clean install`
- Test: `mvn clean test`

Requirements: Java, Maven.

<a name="Code-Overview"></a>

### Code Overview
This project's main Java package is divided into four sections:
- enumerations
- exceptions
- model objects
- requests.

Those unit-tested parts are connected through various classes that make the API accessible for other Java projects. You
can find details about specific parts or single classes in the sections below.

#### Enumerations
`src/main/java/com.wrapper.spotify/enums/`

Enumerations allow elements to "be of a type" and limit them to a known value set. They are currently not specified in a
unique place, but are rather scrambled across the online reference. Thus, the reference only allows for construction
of enum classes from this sparse information.

#### Exceptions
`src/main/java/com.wrapper.spotify/exceptions/`

Exceptions are thrown when errors occur. They are following RFC-specified
[HTTP status codes](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) and are packed with a more detailed error
description.

#### Model Objects
`src/main/java/com.wrapper.spotify/model_objects/`

The model objects are entities that form the API's responses in arranged formats. They are mostly specified in the
[Web API Object Model](https://developer.spotify.com/web-api/object-model/) and in the
[Web API Authorization Guide](https://developer.spotify.com/web-api/authorization-guide/). Though, unreferenced model
objects exist. This project subdivides those into...

- "miscellaneous" model objects: these are mentioned somewhere in the reference, but not in the model object list
- "special" model objects: these are not mentioned at all, but appear in API answers nonetheless.

Java classes representing those model objects include private instance variables, a private constructor, but public getter
methods as well as an embedded...

1. builder class, including the setter functions and a public build method
2. JSON-util class, implementing the `createModelObject` method.

#### Requests
`src/main/java/com.wrapper.spotify/requests/`

The request classes mirror the strucure of Spotify's Web Api endpoints. They are divided into several categories like
`authorization`, `data/albums` or `data/tracks`. They must extend from `AbstractDataRequest` and contain an
implementation of the request's `execute` method. They have to embed a builder class too, enabling dynamic request
creation.

#### Tests
`src/test/java/com.wrapper.spotify/`

Unit tests ensure that implemented features work. This project's unit tests are implemented with [JUnit](http://junit.org/)
and [mockito](http://site.mockito.org/) for mocking.

##### Fixtures
`src/test/fixtures/`

Fixtures are JSON files that represent the data returned from the API server. We use the examples directly provided by
the [Web API Endpoint Reference](https://developer.spotify.com/web-api/endpoint-reference/) with minor tweaks. Tweaks
are needed because the reference sometimes contains invalid data examples.
