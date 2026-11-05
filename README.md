| You and me |
|-|
| Spotify has a history of pushing considerable **breaking changes** to their API and documentation **without prior notice**. Additionally, Spotify is known for not responding to developers' inquiries.<br>This can suddenly render development on top of their platform useless, reportedly putting university projects and job applications at risk. Be encouraged to share your feedback with Spotify on a platform of your choice.<br>[[1]](https://developer.spotify.com/blog/2024-11-27-changes-to-the-web-api) [[2]](https://community.spotify.com/t5/Spotify-for-Developers/Revive-the-Object-Model-Specification/td-p/5368580) |

| Android Developers |
| - |
| You **cannot** use this library for Android app development. Have a look at [adamint/spotify-web-api-kotlin](https://www.github.com/adamint/spotify-web-api-kotlin), [kaaes/spotify-web-api-android](https://www.github.com/kaaes/spotify-web-api-android) and [Spotify's Android SDK](https://developer.spotify.com/documentation/android) and [see why](https://github.com/spotify-web-api-java/spotify-web-api-java/issues/120#issuecomment-363266591). |

# Spotify Web API Java
[![CI](https://github.com/spotify-web-api-java/spotify-web-api-java/actions/workflows/ci.yml/badge.svg)](https://github.com/spotify-web-api-java/spotify-web-api-java/actions/workflows/ci.yml) [![codecov](https://codecov.io/gh/spotify-web-api-java/spotify-web-api-java/branch/main/graph/badge.svg)](https://codecov.io/gh/spotify-web-api-java/spotify-web-api-java)

This is a Java wrapper/client for the [Spotify Web API](https://developer.spotify.com/documentation/web-api).

## Table of Contents
1. **[Installation](#Installation)**
    1. **[Jitpack](#Jitpack)**
2. **[Documentation](#Documentation)**
3. **[Migration](#Migration)**
4. **[General Usage](#General-Usage)**
    1. **[Authorization](#Authorization)**
5. **[Examples](#Examples)**
6. **[Support](#Support)**
7. **[Contributions](#Contributions)**

## Installation

The artifact is available through
[Maven Central](https://mvnrepository.com/artifact/se.michaelthelin.spotify/spotify-web-api-java) via
[Sonatype](https://central.sonatype.com/artifact/se.michaelthelin.spotify/spotify-web-api-java).
Or to use a snapshot of the latest commit you can use [jitpack.io](https://jitpack.io/) as described [further down below](#Jitpack).

The library requires Java 9 or later. It is an explicit Java module named `se.michaelthelin.spotify`, so on the module path add `requires se.michaelthelin.spotify;` to your `module-info.java`; on the classpath nothing is needed.

### Maven

Latest official release:
```XML
<dependency>
  <groupId>se.michaelthelin.spotify</groupId>
  <artifactId>spotify-web-api-java</artifactId>
  <version>11.0.0-RC1</version>
</dependency>
```

Latest snapshot:
```XML
<dependency>
  <groupId>com.github.spotify-web-api-java</groupId>
  <artifactId>spotify-web-api-java</artifactId>
  <version>main-SNAPSHOT</version>
</dependency>
```

### Gradle

Latest official release:
```Gradle
implementation 'se.michaelthelin.spotify:spotify-web-api-java:11.0.0-RC1'
```

Latest snapshot:
```Gradle
implementation 'com.github.spotify-web-api-java:spotify-web-api-java:main-SNAPSHOT'
```

---

### Jitpack

In order to use Jitpack you need to add their repository to your build:

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

## Documentation
See this project's **[Javadoc](https://spotify-web-api-java.github.io/spotify-web-api-java/apidocs/)**.

*A huge thanks to [c-schuhmann](https://github.com/c-schuhmann) for his amazing work on the documentation!*

## Migration
Upgrading from an earlier major version? See the **[migration guide](MIGRATION.md)**.

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
    final CompletableFuture<Something> somethingFuture = getSomethingRequest.executeAsync();

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

### Authorization
**Please see [Spotify's authorization guide](https://developer.spotify.com/documentation/web-api/concepts/authorization) too!**

For authorization requests the API object requires at least to have
[your application](https://developer.spotify.com/dashboard)'s client ID and client secret set as its properties.
When using the authorization code flow, the application's redirect URI is required too. Those properties will then be
automatically used by functions that depend on them.

```Java
SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId("<your_client_id>")
  .setClientSecret("<your_client_secret>")
  .setRedirectUri("<your_redirect_uri>")
  .build();
```

There are three ways to retrieving an access token:

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
spotifyApi.setAccessToken("<your_access_token>");
spotifyApi.setRefreshToken("<your_refresh_token>");
```

#### [Authorization Code Flow with Proof Key for Code Exchange (PKCE)](https://tools.ietf.org/html/rfc7636)
The authorization code flow with PKCE is quite like the Authorization Code Flow except that no client secret is necessary
(therefore, it is a good option for mobile and desktop applications). Instead, your application should generate a
**code verifier** and a **code challenge** before each authentication request.


The code verifier is a cryptographically random string between 43 and 128 characters in length. It can contain letters, digits, underscores, periods, hyphens, or tildes.
To generate the code challenge, your app should hash the code verifier using the SHA256 algorithm. Then, base64url encode the hash that you generated.


This flow provides your app with an access token which can be refreshed, too.
The steps are similar as above:

1. The authorization code flow with PKCE requires a code, which is part of the `redirectUri`'s query parameters when the user has
   opened a custom URL in a browser and authorized the application. The **code challenge** is supplied to this request as a query parameter.

   Example: [AuthorizationCodePKCEUriExample.java](examples/authorization/authorization_code/pkce/AuthorizationCodePKCEUriExample.java)

2. When the code has been retrieved, it can be used in another request to get an access token as well as a refresh token.
   The **code verifier** is supplied to this request a query parameter.

   Example: [AuthorizationCodePKCEExample.java](examples/authorization/authorization_code/pkce/AuthorizationCodePKCEExample.java)

3. Now, the refresh token in turn can be used in a loop to retrieve new access and refresh tokens.

   Example: [AuthorizationCodePKCERefreshExample.java](examples/authorization/authorization_code/pkce/AuthorizationCodePKCERefreshExample.java)

---

When you have fetched an access and refresh token, you have to add them to your API properties for automatic usage in
requests. The implementer must handle the access token's expiration. The refresh token can be exchanged for an
access token only once, after which it becomes invalid.



## Examples

> Endpoints that Spotify deprecated are marked with ⚠️.
> They keep working for clients whose credentials still reach them and none of them are scheduled for removal.
> See the `@deprecated` Javadoc tag on the corresponding request class for the recommended alternative.

- **Albums**
  - [Get an Album](examples/data/albums/GetAlbumExample.java)
  - [Get an Album's Tracks](examples/data/albums/GetAlbumTracksExample.java)
  - ⚠️ [Get Several Albums](examples/data/albums/GetSeveralAlbumsExample.java)
  - ⚠️ [Get New Releases](examples/data/albums/GetNewReleasesExample.java)
  - ⚠️ [Check User's Saved Albums](examples/data/albums/CheckUsersSavedAlbumsExample.java)
  - [Get Current User's Saved Albums](examples/data/albums/GetUsersSavedAlbumsExample.java)
  - ⚠️ [Save Albums for Current User](examples/data/albums/SaveAlbumsForCurrentUserExample.java)
  - ⚠️ [Remove Albums for Current User](examples/data/albums/RemoveAlbumsForCurrentUserExample.java)


- **Artists**
  - [Get an Artist](examples/data/artists/GetArtistExample.java)
  - [Get an Artist's Albums](examples/data/artists/GetArtistsAlbumsExample.java)
  - ⚠️ [Get an Artist's Related Artists](examples/data/artists/GetArtistsRelatedArtistsExample.java)
  - ⚠️ [Get an Artist's Top Tracks](examples/data/artists/GetArtistsTopTracksExample.java)
  - ⚠️ [Get Several Artists](examples/data/artists/GetSeveralArtistsExample.java)


- **Audiobooks**
  - [Get an Audiobook](examples/data/audiobooks/GetAudiobookExample.java)
  - [Get an Audiobook's Chapters](examples/data/audiobooks/GetAudiobookChaptersExample.java)
  - ⚠️ [Get Several Audiobooks](examples/data/audiobooks/GetSeveralAudiobooksExample.java)
  - [Get User's Saved Audiobooks](examples/data/audiobooks/GetUsersSavedAudiobooksExample.java)
  - ⚠️ [Check User's Saved Audiobooks](examples/data/audiobooks/CheckUsersSavedAudiobooksExample.java)
  - ⚠️ [Save Audiobooks for Current User](examples/data/audiobooks/SaveAudiobooksForCurrentUserExample.java)
  - ⚠️ [Remove Audiobooks for Current User](examples/data/audiobooks/RemoveAudiobooksForCurrentUserExample.java)


- **Browsing & Discovery**
  - ⚠️ [Get Available Genre Seeds](examples/data/genres/GetRecommendationGenresExample.java)
  - ⚠️ [Get Categories](examples/data/categories/GetSeveralBrowseCategoriesExample.java)
  - ⚠️ [Get a Category](examples/data/categories/GetSingleBrowseCategoryExample.java)
  - ⚠️ [Get a Category's Playlists](examples/data/playlists/GetCategoryPlaylistsExample.java)
  - ⚠️ [Get Featured Playlists](examples/data/playlists/GetFeaturedPlaylistsExample.java)
  - ⚠️ [Get Available Markets](examples/data/markets/GetAvailableMarketsExample.java)


- **Chapters**
  - [Get a Chapter](examples/data/chapters/GetChapterExample.java)
  - ⚠️ [Get Several Chapters](examples/data/chapters/GetSeveralChaptersExample.java)


- **Episodes**
  - [Get an Episode](examples/data/episodes/GetEpisodeExample.java)
  - ⚠️ [Get Several Episodes](examples/data/episodes/GetSeveralEpisodesExample.java)
  - ⚠️ [Check User's Saved Episodes](examples/data/episodes/CheckUsersSavedEpisodesExample.java)
  - [Get User's Saved Episodes](examples/data/episodes/GetUsersSavedEpisodesExample.java)
  - ⚠️ [Save Episodes for Current User](examples/data/episodes/SaveEpisodesForCurrentUserExample.java)
  - ⚠️ [Remove Episodes for Current User](examples/data/episodes/RemoveEpisodesForCurrentUserExample.java)


- **Library (Unified Save/Remove/Check API)**
  - [Save Items to Library](examples/data/library/SaveItemsToLibraryExample.java)
  - [Remove Items from Library](examples/data/library/RemoveItemsFromLibraryExample.java)
  - [Check User's Saved Items](examples/data/library/CheckUsersSavedItemsExample.java)


- **Personalization**
  - [Get a User's Top Artists and Tracks](examples/data/users/GetUsersTopArtistsAndTracksExample.java)
  - Simplified
    - [Get a User's Top Artists](examples/data/users/simplified/GetUsersTopArtistsExample.java)
    - [Get a User's Top Tracks](examples/data/users/simplified/GetUsersTopTracksExample.java)


- **Player**
  - [Get Available Devices](examples/data/player/GetAvailableDevicesExample.java)
  - [Get Current Playback State](examples/data/player/GetPlaybackStateExample.java)
  - [Get Currently Playing Track](examples/data/player/GetCurrentlyPlayingTrackExample.java)
  - [Get Recently Played Tracks](examples/data/player/GetRecentlyPlayedTracksExample.java)
  - [Get User's Queue](examples/data/player/GetUsersQueueExample.java)
  - [Start/Resume Playback](examples/data/player/StartResumePlaybackExample.java)
  - [Pause Playback](examples/data/player/PausePlaybackExample.java)
  - [Skip to Next Track](examples/data/player/SkipToNextExample.java)
  - [Skip to Previous Track](examples/data/player/SkipToPreviousExample.java)
  - [Seek to Position](examples/data/player/SeekToPositionExample.java)
  - [Set Repeat Mode](examples/data/player/SetRepeatModeExample.java)
  - [Toggle Shuffle](examples/data/player/TogglePlaybackShuffleExample.java)
  - [Set Volume](examples/data/player/SetPlaybackVolumeExample.java)
  - [Transfer Playback](examples/data/player/TransferPlaybackExample.java)
  - [Add Item to Queue](examples/data/player/AddItemToPlaybackQueueExample.java)


- **Playlists**
  - [Get a Playlist](examples/data/playlists/GetPlaylistExample.java)
  - [Get Current User's Playlists](examples/data/playlists/GetCurrentUsersPlaylistsExample.java)
  - ⚠️ [Get a User's Playlists](examples/data/playlists/GetUsersPlaylistsExample.java)
  - [Create a Playlist](examples/data/playlists/CreatePlaylistExample.java)
  - ⚠️ [Create a Playlist for a User](examples/data/playlists/CreatePlaylistForUserExample.java)
  - [Get a Playlist's Items](examples/data/playlists/GetPlaylistItemsExample.java)
  - [Get a Playlist Cover Image](examples/data/playlists/GetPlaylistCoverImageExample.java)
  - [Add Items to a Playlist](examples/data/playlists/AddItemsToPlaylistExample.java)
  - [Remove Items from a Playlist](examples/data/playlists/RemovePlaylistItemsExample.java)
  - [Update Playlist Details](examples/data/playlists/ChangePlaylistDetailsExample.java)
  - [Reorder Playlist Items](examples/data/playlists/UpdatePlaylistsItemsReorderExample.java)
  - [Replace Playlist Items](examples/data/playlists/UpdatePlaylistsItemsReplaceExample.java)
  - [Upload Playlist Cover Image](examples/data/playlists/AddCustomPlaylistCoverImageExample.java)
  - Legacy `/tracks` path
    - ⚠️ [Get a Playlist's Items](examples/data/playlists/GetPlaylistItemsDeprecatedExample.java)
    - ⚠️ [Add Items to a Playlist](examples/data/playlists/AddItemsToPlaylistDeprecatedExample.java)
    - ⚠️ [Remove Items from a Playlist](examples/data/playlists/RemovePlaylistItemsDeprecatedExample.java)
    - ⚠️ [Reorder Playlist Items](examples/data/playlists/UpdatePlaylistsItemsReorderDeprecatedExample.java)
    - ⚠️ [Replace Playlist Items](examples/data/playlists/UpdatePlaylistsItemsReplaceDeprecatedExample.java)


- **Search**
  - [Search for Item](examples/data/search/SearchForItemExample.java)
  - Simplified (Direct Type Search)
    - [Search Albums](examples/data/search/simplified/SearchAlbumsExample.java)
    - [Search Artists](examples/data/search/simplified/SearchArtistsExample.java)
    - [Search Audiobooks](examples/data/search/simplified/SearchAudiobooksExample.java)
    - [Search Tracks](examples/data/search/simplified/SearchTracksExample.java)
    - [Search Shows](examples/data/search/simplified/SearchShowsExample.java)
    - [Search Episodes](examples/data/search/simplified/SearchEpisodesExample.java)
    - [Search Playlists](examples/data/search/simplified/SearchPlaylistsExample.java)
    - [Search Albums (Extended Model)](examples/data/search/simplified/special/SearchAlbumsSpecialExample.java)


- **Shows**
  - [Get a Show](examples/data/shows/GetShowExample.java)
  - [Get a Show's Episodes](examples/data/shows/GetShowEpisodesExample.java)
  - ⚠️ [Get Several Shows](examples/data/shows/GetSeveralShowsExample.java)
  - ⚠️ [Check User's Saved Shows](examples/data/shows/CheckUsersSavedShowsExample.java)
  - [Get User's Saved Shows](examples/data/shows/GetUsersSavedShowsExample.java)
  - ⚠️ [Save Shows for Current User](examples/data/shows/SaveShowsForCurrentUserExample.java)
  - ⚠️ [Remove Shows for Current User](examples/data/shows/RemoveShowsForCurrentUserExample.java)


- **Tracks**
  - [Get a Track](examples/data/tracks/GetTrackExample.java)
  - ⚠️ [Get Several Tracks](examples/data/tracks/GetSeveralTracksExample.java)
  - ⚠️ [Check User's Saved Tracks](examples/data/tracks/CheckUsersSavedTracksExample.java)
  - [Get User's Saved Tracks](examples/data/tracks/GetUsersSavedTracksExample.java)
  - ⚠️ [Save Tracks for Current User](examples/data/tracks/SaveTracksForCurrentUserExample.java)
  - ⚠️ [Remove User's Saved Tracks](examples/data/tracks/RemoveUsersSavedTracksExample.java)
  - ⚠️ [Get Audio Features for a Track](examples/data/tracks/GetTracksAudioFeaturesExample.java)
  - ⚠️ [Get Audio Features for Several Tracks](examples/data/tracks/GetSeveralTracksAudioFeaturesExample.java)
  - ⚠️ [Get Audio Analysis for a Track](examples/data/tracks/GetTracksAudioAnalysisExample.java)
  - ⚠️ [Get Recommendations](examples/data/tracks/GetRecommendationsExample.java)


- **User's Profile & Following**
  - [Get Current User's Profile](examples/data/users/GetCurrentUsersProfileExample.java)
  - ⚠️ [Get a User's Profile](examples/data/users/GetUsersProfileExample.java)
  - [Get User's Followed Artists](examples/data/users/GetFollowedArtistsExample.java)
  - ⚠️ [Follow Artists or Users](examples/data/users/FollowArtistsOrUsersExample.java)
  - ⚠️ [Unfollow Artists or Users](examples/data/users/UnfollowArtistsOrUsersExample.java)
  - ⚠️ [Check if User Follows Artists or Users](examples/data/users/CheckCurrentUserFollowsArtistsOrUsersExample.java)
  - ⚠️ [Follow a Playlist](examples/data/users/FollowPlaylistExample.java)
  - ⚠️ [Unfollow a Playlist](examples/data/users/UnfollowPlaylistExample.java)
  - ⚠️ [Check if Users Follow a Playlist](examples/data/users/CheckIfUserFollowsPlaylistExample.java)

## Support
Please ask questions about using the library in help networks like
[StackOverflow](https://stackoverflow.com/questions/tagged/spotify-web-api-java) with the tag `spotify-web-api-java`,
where they get quick and helpful answers.

Issues are for bug reports and for questions about **this** project's source code.

## Contributions
Contributions are very welcome. [CONTRIBUTING.md](CONTRIBUTING.md) describes how the code is organized, how to build
and test it, and what a pull request needs to be accepted.
