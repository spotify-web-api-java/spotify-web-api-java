# Migration Guide

## 9.4.0 → 10.0.0

Version 10 aligns the library with Spotify's OpenAPI specification.
Method names, request class names and packages now follow the specification's operation IDs and tags instead of the names this library grew historically.

No endpoint lost its counterpart.
9.4.0 exposed 94 request builders and 10.0.0 exposes 114, every one of the 94 among them.
Of the 94, 50 keep both their name and their endpoint, 40 were renamed, and 4 keep their name while something about them changed.
One further request class was renamed under a method that did not change, so the rename table below has 41 rows.

Work through the tables below to fix the build, but read the next section first.
It collects the handful of changes where a clean compile is not enough, or where the obvious replacement is the wrong one.

### Start here

**`addItemsToPlaylist` now posts to a different endpoint.**
This is the only change that is completely silent.
The signature is unchanged, so existing code keeps compiling, but the request now targets `POST /playlists/{playlist_id}/items` instead of `POST /playlists/{playlist_id}/tracks`.
If you need the old path, `addItemsToPlaylistDeprecated` still calls it.

**`createPlaylist` lost its `user_id` parameter.**
In 9.4.0 it was `createPlaylist(String user_id, String name)` calling `POST /users/{user_id}/playlists`.
In 10.0.0 it is `createPlaylist(String name)` calling `POST /me/playlists`, which always creates the playlist for the authenticated user.
The old form lives on as `createPlaylistForUser(String user_id, String name)`, which is deprecated.

```java
// v9
spotifyApi.createPlaylist(userId, "My playlist").build().execute();

// v10
spotifyApi.createPlaylist("My playlist").build().execute();
```

**`followPlaylist` and `unfollowPlaylist` lost their owner overloads.**
Spotify dropped the `{owner_id}` path segment, so `followPlaylist(String owner_id, String playlist_id, boolean public_)` and `unfollowPlaylist(String owner_id, String playlist_id)` are gone.
The remaining overloads take the playlist ID alone, and they moved from `requests.data.follow.legacy` to `requests.data.users`.

**Playlist item accessors were renamed.**
`Playlist.getTracks()` and `PlaylistSimplified.getTracks()` are now `getItems()`, and `PlaylistTrack.getTrack()` is now `getItem()`.
The return types are unchanged, so these are a straight substitution once you know the new name.

### Renamed methods and request classes

Update the call and the import together.
⚠️ marks a replacement that is itself deprecated because Spotify deprecated the underlying endpoint.
It still works; see [Deprecations](#deprecations).

| v9 method | 10.0.0 method | v9 request class | 10.0.0 request class |
|---|---|---|---|
| `addItemToUsersPlaybackQueue` | `addItemToPlaybackQueue` | `AddItemToUsersPlaybackQueueRequest` | `AddItemToPlaybackQueueRequest` |
| `changePlaylistsDetails` | `changePlaylistDetails` | `ChangePlaylistsDetailsRequest` | `ChangePlaylistDetailsRequest` |
| `checkUsersFollowPlaylist` | `checkIfUserFollowsPlaylist` ⚠️ | `CheckUsersFollowPlaylistRequest` | `CheckIfUserFollowsPlaylistRequest` |
| `getAlbumsTracks` | `getAlbumTracks` | `GetAlbumsTracksRequest` | `GetAlbumTracksRequest` |
| `getAudioAnalysisForTrack` | `getTracksAudioAnalysis` ⚠️ | `GetAudioAnalysisForTrackRequest` | `GetTracksAudioAnalysisRequest` |
| `getAudioFeaturesForSeveralTracks` | `getSeveralTracksAudioFeatures` ⚠️ | `GetAudioFeaturesForSeveralTracksRequest` | `GetSeveralTracksAudioFeaturesRequest` |
| `getAudioFeaturesForTrack` | `getTracksAudioFeatures` ⚠️ | `GetAudioFeaturesForTrackRequest` | `GetTracksAudioFeaturesRequest` |
| `getAvailableGenreSeeds` | `getRecommendationGenres` ⚠️ | `GetAvailableGenreSeedsRequest` | `GetRecommendationGenresRequest` |
| `getCategory` | `getSingleBrowseCategory` ⚠️ | `GetCategoryRequest` | `GetSingleBrowseCategoryRequest` |
| `getCategorysPlaylists` | `getCategoryPlaylists` ⚠️ | `GetCategorysPlaylistsRequest` | `GetCategoryPlaylistsRequest` |
| `getCurrentUsersRecentlyPlayedTracks` | `getRecentlyPlayedTracks` | `GetCurrentUsersRecentlyPlayedTracksRequest` | `GetRecentlyPlayedTracksRequest` |
| `getCurrentUsersSavedAlbums` | `getUsersSavedAlbums` | `GetCurrentUsersSavedAlbumsRequest` | `GetUsersSavedAlbumsRequest` |
| `getInformationAboutUsersCurrentPlayback` | `getPlaybackState` | `GetInformationAboutUsersCurrentPlaybackRequest` | `GetPlaybackStateRequest` |
| `getListOfCategories` | `getSeveralBrowseCategories` ⚠️ | `GetListOfCategoriesRequest` | `GetSeveralBrowseCategoriesRequest` |
| `getListOfCurrentUsersPlaylists` | `getCurrentUsersPlaylists` | `GetListOfCurrentUsersPlaylistsRequest` | `GetCurrentUsersPlaylistsRequest` |
| `getListOfFeaturedPlaylists` | `getFeaturedPlaylists` ⚠️ | `GetListOfFeaturedPlaylistsRequest` | `GetFeaturedPlaylistsRequest` |
| `getListOfNewReleases` | `getNewReleases` ⚠️ | `GetListOfNewReleasesRequest` | `GetNewReleasesRequest` |
| `getListOfUsersPlaylists` | `getUsersPlaylists` ⚠️ | `GetListOfUsersPlaylistsRequest` | `GetUsersPlaylistsRequest` |
| `getPlaylistsItems` | `getPlaylistItemsDeprecated` ⚠️ | `GetPlaylistsItemsRequest` | `GetPlaylistItemsDeprecatedRequest` |
| `getShowEpisodes` | `getShowEpisodes` | `GetShowsEpisodesRequest` | `GetShowEpisodesRequest` |
| `getTheUsersQueue` | `getUsersQueue` | `GetTheUsersQueueRequest` | `GetUsersQueueRequest` |
| `getUsersAvailableDevices` | `getAvailableDevices` | `GetUsersAvailableDevicesRequest` | `GetAvailableDevicesRequest` |
| `getUsersCurrentlyPlayingTrack` | `getCurrentlyPlayingTrack` | `GetUsersCurrentlyPlayingTrackRequest` | `GetCurrentlyPlayingTrackRequest` |
| `getUsersFollowedArtists` | `getFollowedArtists` | `GetUsersFollowedArtistsRequest` | `GetFollowedArtistsRequest` |
| `pauseUsersPlayback` | `pausePlayback` | `PauseUsersPlaybackRequest` | `PausePlaybackRequest` |
| `removeItemsFromPlaylist` | `removePlaylistItemsDeprecated` ⚠️ | `RemoveItemsFromPlaylistRequest` | `RemovePlaylistItemsDeprecatedRequest` |
| `removeUsersSavedEpisodes` | `removeEpisodesForCurrentUser` ⚠️ | `RemoveUsersSavedEpisodesRequest` | `RemoveEpisodesForCurrentUserRequest` |
| `removeUsersSavedShows` | `removeShowsForCurrentUser` ⚠️ | `RemoveUsersSavedShowsRequest` | `RemoveShowsForCurrentUserRequest` |
| `reorderPlaylistsItems` | `updatePlaylistsItemsReorderDeprecated` ⚠️ | `ReorderPlaylistsItemsRequest` | `UpdatePlaylistsItemsReorderDeprecatedRequest` |
| `replacePlaylistsItems` | `updatePlaylistsItemsReplaceDeprecated` ⚠️ | `ReplacePlaylistsItemsRequest` | `UpdatePlaylistsItemsReplaceDeprecatedRequest` |
| `saveTracksForUser` | `saveTracksForCurrentUser` ⚠️ | `SaveTracksForUserRequest` | `SaveTracksForCurrentUserRequest` |
| `searchItem` | `searchForItem` | `SearchItemRequest` | `SearchForItemRequest` |
| `seekToPositionInCurrentlyPlayingTrack` | `seekToPosition` | `SeekToPositionInCurrentlyPlayingTrackRequest` | `SeekToPositionRequest` |
| `setRepeatModeOnUsersPlayback` | `setRepeatMode` | `SetRepeatModeOnUsersPlaybackRequest` | `SetRepeatModeRequest` |
| `setVolumeForUsersPlayback` | `setPlaybackVolume` | `SetVolumeForUsersPlaybackRequest` | `SetPlaybackVolumeRequest` |
| `skipUsersPlaybackToNextTrack` | `skipToNext` | `SkipUsersPlaybackToNextTrackRequest` | `SkipToNextRequest` |
| `skipUsersPlaybackToPreviousTrack` | `skipToPrevious` | `SkipUsersPlaybackToPreviousTrackRequest` | `SkipToPreviousRequest` |
| `startResumeUsersPlayback` | `startResumePlayback` | `StartResumeUsersPlaybackRequest` | `StartResumePlaybackRequest` |
| `toggleShuffleForUsersPlayback` | `togglePlaybackShuffle` | `ToggleShuffleForUsersPlaybackRequest` | `TogglePlaybackShuffleRequest` |
| `transferUsersPlayback` | `transferPlayback` | `TransferUsersPlaybackRequest` | `TransferPlaybackRequest` |
| `uploadCustomPlaylistCoverImage` | `addCustomPlaylistCoverImage` | `UploadCustomPlaylistCoverImageRequest` | `AddCustomPlaylistCoverImageRequest` |

### Moved packages

These classes kept their name and moved to another package, so only the import changes.

| Class | v9 package | 10.0.0 package |
|---|---|---|
| `CheckCurrentUserFollowsArtistsOrUsersRequest` | `requests.data.follow` | `requests.data.users` |
| `CheckUsersSavedAlbumsRequest` | `requests.data.library` | `requests.data.albums` |
| `CheckUsersSavedEpisodesRequest` | `requests.data.library` | `requests.data.episodes` |
| `CheckUsersSavedShowsRequest` | `requests.data.library` | `requests.data.shows` |
| `CheckUsersSavedTracksRequest` | `requests.data.library` | `requests.data.tracks` |
| `FollowArtistsOrUsersRequest` | `requests.data.follow` | `requests.data.users` |
| `FollowPlaylistRequest` | `requests.data.follow.legacy` | `requests.data.users` |
| `GetCurrentUsersProfileRequest` | `requests.data.users_profile` | `requests.data.users` |
| `GetRecommendationsRequest` | `requests.data.browse` | `requests.data.tracks` |
| `GetUsersProfileRequest` | `requests.data.users_profile` | `requests.data.users` |
| `GetUsersSavedEpisodesRequest` | `requests.data.library` | `requests.data.episodes` |
| `GetUsersSavedShowsRequest` | `requests.data.library` | `requests.data.shows` |
| `GetUsersSavedTracksRequest` | `requests.data.library` | `requests.data.tracks` |
| `GetUsersTopArtistsAndTracksRequest` | `requests.data.personalization` | `requests.data.users` |
| `GetUsersTopArtistsRequest` | `requests.data.personalization.simplified` | `requests.data.users.simplified` |
| `GetUsersTopTracksRequest` | `requests.data.personalization.simplified` | `requests.data.users.simplified` |
| `IArtistTrackModelObject` | `requests.data.personalization.interfaces` | `requests.data.users.interfaces` |
| `RemoveAlbumsForCurrentUserRequest` | `requests.data.library` | `requests.data.albums` |
| `RemoveUsersSavedTracksRequest` | `requests.data.library` | `requests.data.tracks` |
| `SaveAlbumsForCurrentUserRequest` | `requests.data.library` | `requests.data.albums` |
| `SaveEpisodesForCurrentUserRequest` | `requests.data.library` | `requests.data.episodes` |
| `SaveShowsForCurrentUserRequest` | `requests.data.library` | `requests.data.shows` |
| `SavedEpisode` | `model_objects.miscellaneous` | `model_objects.specification` |
| `UnfollowArtistsOrUsersRequest` | `requests.data.follow` | `requests.data.users` |
| `UnfollowPlaylistRequest` | `requests.data.follow.legacy` | `requests.data.users` |

### Model object changes

#### Renamed getters

| Class | v9 getter | 10.0.0 getter |
|---|---|---|
| `Playlist`, `PlaylistSimplified` | `getTracks()` | `getItems()` |
| `Playlist`, `PlaylistSimplified` | `getIsPublicAccess()` | `getPublic()` |
| `PlaylistTrack` | `getTrack()` | `getItem()` |
| `Track`, `TrackSimplified` | `getIsExplicit()` | `getExplicit()` |
| `Episode`, `EpisodeSimplified` | `getPlayable()` | `getIsPlayable()` |

The getter names now follow the JSON field names in the specification.
`Chapter` and `ChapterSimplified` are new in 10.0.0 and expose `getPlayable()`, because their field is `playable` rather than `is_playable`.

#### Removed getters

The specification marks these fields deprecated, so the model objects dropped them.
There is no replacement.

| Class | v9 getters with no replacement |
|---|---|
| `Album` | `getAvailableMarkets()`, `getPopularity()` |
| `AlbumSimplified` | `getAlbumGroup()`, `getAvailableMarkets()` |
| `Show`, `ShowSimplified` | `getAvailableMarkets()`, `getPublisher()` |
| `Track` | `getAvailableMarkets()`, `getLinkedFrom()`, `getPopularity()` |
| `TrackSimplified` | `getAvailableMarkets()`, `getLinkedFrom()` |

#### Removed classes

Nothing in the library referred to these any more, in most cases because 10.0.0 dropped the getter that used them.

| v9 type | Why it went |
|---|---|
| `AlbumGroup` | backed `AlbumSimplified.getAlbumGroup()`, which the specification deprecated |
| `ProductType` | backed the user product getter, which the specification deprecated |
| `PlaylistTrackPosition` | never referenced by any request or model object |
| `Paging.JsonUtil`, `PagingCursorbased.JsonUtil` | resolved their type argument to a parameterized type rather than a class, so instantiating either always threw |

A `Paging` object is built through the JsonUtil of the type it contains, which is how every request already built one.
The `IModelObject` array helper that took an explicit element class is gone as well, having had no callers.

#### New getters and model objects

`Album` and `AlbumSimplified` gained `getTotalTracks()`, `AlbumSimplified` also `getIsPlayable()`, `Episode` and `EpisodeSimplified` `getHtmlDescription()`, `User` `getAccountId()` and `getExplicitContent()`, and `SearchResult` `getAudiobooks()`.

Audiobooks and chapters are new: `Audiobook`, `AudiobookSimplified`, `Chapter`, `ChapterSimplified`, `ChapterRestriction`, `Author` and `Narrator`.
`ExplicitContentSettings` backs the new `User.getExplicitContent()`.

Three getters that always returned `null` in 9.4.0 now return a value, because the parser was reading a key the API does not send.
`AudioAnalysisTrack` fixes `getWindowSeconds()` and `getEndOfFadeIn()`, and `AudioAnalysisSection` fixes `getMode()`.
Code that null-checked them will now take the other branch.

Seven interfaces were extracted so that full and simplified objects can be handled through one type: `IAlbum`, `IArtist`, `IEpisode`, `IPlaylist`, `IShow`, `ITrack` and `IHasTotal`.

### New in 10.0.0

Audiobooks and chapters:

| Method | Endpoint |
|---|---|
| `getAudiobook` | `GET /audiobooks/{id}` |
| `getSeveralAudiobooks` ⚠️ | `GET /audiobooks` |
| `getAudiobookChapters` | `GET /audiobooks/{id}/chapters` |
| `getUsersSavedAudiobooks` | `GET /me/audiobooks` |
| `saveAudiobooksForCurrentUser` ⚠️ | `PUT /me/audiobooks` |
| `removeAudiobooksForCurrentUser` ⚠️ | `DELETE /me/audiobooks` |
| `checkUsersSavedAudiobooks` ⚠️ | `GET /me/audiobooks/contains` |
| `getChapter` | `GET /chapters/{id}` |
| `getSeveralChapters` ⚠️ | `GET /chapters` |
| `searchAudiobooks` | `GET /search` |

The unified library API, which works on Spotify URIs of any type instead of one method per content type:

| Method | Endpoint |
|---|---|
| `saveItemsToLibrary` | `PUT /me/library` |
| `removeItemsFromLibrary` | `DELETE /me/library` |
| `checkUsersSavedItems` | `GET /me/library/contains` |

```java
// v10
spotifyApi.saveItemsToLibrary("spotify:album:id1", "spotify:track:id2").build().execute();
spotifyApi.checkUsersSavedItems("spotify:album:id1", "spotify:track:id2").build().execute();
```

The per-type methods (`saveAlbumsForCurrentUser`, `saveTracksForCurrentUser` and the rest) still work and are deprecated in favour of these.

Playlist items on the `/items` path, replacing the `/tracks` path:

| Method | Endpoint |
|---|---|
| `getPlaylistItems` | `GET /playlists/{playlist_id}/items` |
| `removePlaylistItems` | `DELETE /playlists/{playlist_id}/items` |
| `updatePlaylistsItemsReorder` | `PUT /playlists/{playlist_id}/items` |
| `updatePlaylistsItemsReplace` | `PUT /playlists/{playlist_id}/items` |

And `getAvailableMarkets` ⚠️ for `GET /markets`.

### Deserialization

Model objects are deserialized reflectively by Gson.
The Jackson annotations every model carried are gone, along with the Jackson dependency itself, because nothing in the library ever constructed an ObjectMapper.

This only affects you if you fed these model classes into your own ObjectMapper.
The `@JsonDeserialize` annotations gave you builder-based deserialization for free, and you now have to configure that yourself.
Nothing changes for code that goes through the library.

### Deprecations

49 of the 114 request builders carry `@Deprecated`, up from 1 in 9.4.0.
That number is large because it now tracks Spotify's specification: a builder is deprecated when the endpoint behind it is marked deprecated there, not because this library plans to remove it.

None of them are scheduled for removal, and they keep working for clients whose credentials still reach the endpoint.
Each one names its replacement in the `@deprecated` Javadoc tag, and the [example index](README.md#Examples) marks them with ⚠️.
