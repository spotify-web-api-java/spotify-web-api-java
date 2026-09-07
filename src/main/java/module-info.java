/**
 * The Spotify Web API client.
 * <p>
 * Everything a caller touches is exported. The model packages are additionally opened to Gson, which
 * populates their private fields reflectively when a response is deserialized.
 */
module se.michaelthelin.spotify {
  // types from these appear in this library's own public signatures, so callers need them too
  requires transitive com.google.gson;
  requires transitive org.apache.httpcomponents.client5.httpclient5;
  requires transitive org.apache.httpcomponents.core5.httpcore5;

  requires org.apache.httpcomponents.client5.httpclient5.cache;
  requires java.logging;

  exports se.michaelthelin.spotify;
  exports se.michaelthelin.spotify.enums;
  exports se.michaelthelin.spotify.exceptions;
  exports se.michaelthelin.spotify.exceptions.detailed;
  exports se.michaelthelin.spotify.model_objects;
  exports se.michaelthelin.spotify.model_objects.credentials;
  exports se.michaelthelin.spotify.model_objects.credentials.error;
  exports se.michaelthelin.spotify.model_objects.interfaces;
  exports se.michaelthelin.spotify.model_objects.miscellaneous;
  exports se.michaelthelin.spotify.model_objects.special;
  exports se.michaelthelin.spotify.model_objects.specification;
  exports se.michaelthelin.spotify.model_objects.utils;
  exports se.michaelthelin.spotify.requests;
  exports se.michaelthelin.spotify.requests.authorization;
  exports se.michaelthelin.spotify.requests.authorization.authorization_code;
  exports se.michaelthelin.spotify.requests.authorization.authorization_code.pkce;
  exports se.michaelthelin.spotify.requests.authorization.client_credentials;
  exports se.michaelthelin.spotify.requests.data;
  exports se.michaelthelin.spotify.requests.data.albums;
  exports se.michaelthelin.spotify.requests.data.artists;
  exports se.michaelthelin.spotify.requests.data.audiobooks;
  exports se.michaelthelin.spotify.requests.data.categories;
  exports se.michaelthelin.spotify.requests.data.chapters;
  exports se.michaelthelin.spotify.requests.data.episodes;
  exports se.michaelthelin.spotify.requests.data.genres;
  exports se.michaelthelin.spotify.requests.data.library;
  exports se.michaelthelin.spotify.requests.data.markets;
  exports se.michaelthelin.spotify.requests.data.player;
  exports se.michaelthelin.spotify.requests.data.playlists;
  exports se.michaelthelin.spotify.requests.data.search;
  exports se.michaelthelin.spotify.requests.data.search.interfaces;
  exports se.michaelthelin.spotify.requests.data.search.simplified;
  exports se.michaelthelin.spotify.requests.data.search.simplified.special;
  exports se.michaelthelin.spotify.requests.data.shows;
  exports se.michaelthelin.spotify.requests.data.tracks;
  exports se.michaelthelin.spotify.requests.data.users;
  exports se.michaelthelin.spotify.requests.data.users.interfaces;
  exports se.michaelthelin.spotify.requests.data.users.simplified;

  opens se.michaelthelin.spotify.model_objects.credentials to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.credentials.error to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.miscellaneous to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.special to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.specification to com.google.gson;
}
