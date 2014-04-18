package se.michaelthelin.spotify;

import se.michaelthelin.spotify.UtilProtos.Url;

/**
 * A simple HTTP connection interface.
 */
public interface HttpManager {

  /**
   * Perform an HTTP GET request to the specified URL.
   *
   * @param url the {@link Url} to HTTP GET.
   * @return a String containing the body of the HTTP GET response.
   */
  String get(Url url);

  /**
   * Perform an HTTP POST request to the specified URL.
   *
   * @param url the {@link Url} to HTTP POST.
   * @return a String containing the body of the HTTP POST response.
   */
  String post(Url url);

  /**
   * Perform an HTTP DELETE request to the specified URL.
   *
   * @param url the {@link Url} to HTTP DELEte.
   * @return a String containing the body of the HTTP DELETE response.
   */
  String delete(Url url);

  /**
   * Perform an HTTP PUT request to the specified URL.
   *
   * @param url the {@link Url} to HTTP PUT.
   * @return a String containing the body of the HTTP PUTresponse.
   */
  String put(Url url);
}
