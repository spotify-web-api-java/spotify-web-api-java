package se.michaelthelin.spotify.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.hc.core5.http.*;
import se.michaelthelin.spotify.IHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface representing a request to the Spotify Web API.
 *
 * @param <T> the type of object this request will return
 */
public interface IRequest<T> {

  /**
   * Get the HTTP manager for this request.
   *
   * @return The HTTP manager.
   */
  IHttpManager getHttpManager();

  /**
   * Get the URI for this request.
   *
   * @return The request URI.
   */
  URI getUri();

  /**
   * Get the headers for this request.
   *
   * @return List of headers.
   */
  List<Header> getHeaders();

  /**
   * Get the content type for this request.
   *
   * @return The content type.
   */
  ContentType getContentType();

  /**
   * Get the request body.
   *
   * @return The HTTP entity body.
   */
  HttpEntity getBody();

  /**
   * Get the body parameters for this request.
   *
   * @return List of body parameters.
   */
  List<NameValuePair> getBodyParameters();

  /**
   * Execute the request synchronously.
   *
   * @return The response.
   * @throws IOException              If an I/O error occurs.
   * @throws SpotifyWebApiException   If a Spotify Web API error occurs.
   * @throws ParseException           If a parsing error occurs.
   */
  T execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  /**
   * Execute the request asynchronously.
   *
   * @return A CompletableFuture for the response.
   */
  CompletableFuture<T> executeAsync();

  /**
   * Get JSON response as string.
   *
   * @return JSON response string.
   * @throws IOException              If an I/O error occurs.
   * @throws SpotifyWebApiException   If a Spotify Web API error occurs.
   * @throws ParseException           If a parsing error occurs.
   */
  String getJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  /**
   * Execute POST request and get JSON response.
   *
   * @return JSON response string.
   * @throws IOException              If an I/O error occurs.
   * @throws SpotifyWebApiException   If a Spotify Web API error occurs.
   * @throws ParseException           If a parsing error occurs.
   */
  String postJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  /**
   * Execute PUT request and get JSON response.
   *
   * @return JSON response string.
   * @throws IOException              If an I/O error occurs.
   * @throws SpotifyWebApiException   If a Spotify Web API error occurs.
   * @throws ParseException           If a parsing error occurs.
   */
  String putJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  /**
   * Execute DELETE request and get JSON response.
   *
   * @return JSON response string.
   * @throws IOException              If an I/O error occurs.
   * @throws SpotifyWebApiException   If a Spotify Web API error occurs.
   * @throws ParseException           If a parsing error occurs.
   */
  String deleteJson() throws
    IOException,
    SpotifyWebApiException,
    ParseException;

  /**
   * Interface for building requests to the Spotify Web API.
   *
   * @param <T> the type of object the built request will return
   * @param <BT> the specific builder type extending this interface
   */
  @JsonPOJOBuilder(withPrefix = "set")
  interface Builder<T, BT extends Builder<T, ?>> {

    /**
     * Set the HTTP manager for the request.
     *
     * @param httpManager The HTTP manager.
     * @return This builder instance.
     */
    BT setHttpManager(final IHttpManager httpManager);

    /**
     * Set the scheme for the request.
     *
     * @param scheme The scheme.
     * @return This builder instance.
     */
    BT setScheme(final String scheme);

    /**
     * Set the host for the request.
     *
     * @param host The host.
     * @return This builder instance.
     */
    BT setHost(final String host);

    /**
     * Set the port for the request.
     *
     * @param port The port.
     * @return This builder instance.
     */
    BT setPort(final Integer port);

    /**
     * Set the path for the request.
     *
     * @param path The path.
     * @return This builder instance.
     */
    BT setPath(final String path);

    /**
     * Set a path parameter for the request.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return This builder instance.
     */
    BT setPathParameter(final String name, final String value);

    /**
     * Set default values for the request.
     *
     * @param httpManager The HTTP manager.
     * @param scheme      The scheme.
     * @param host        The host.
     * @param port        The port.
     * @return This builder instance.
     */
    BT setDefaults(final IHttpManager httpManager,
                   final String scheme,
                   final String host,
                   final Integer port);

    /**
     * Set a query parameter for the request.
     *
     * @param <ST>  The parameter value type.
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return This builder instance.
     */
    <ST> BT setQueryParameter(final String name, final ST value);

    /**
     * Set a header for the request.
     *
     * @param <ST>  The header value type.
     * @param name  The header name.
     * @param value The header value.
     * @return This builder instance.
     */
    <ST> BT setHeader(final String name, final ST value);

    /**
     * Set the content type for the request.
     *
     * @param contentType The content type.
     * @return This builder instance.
     */
    BT setContentType(final ContentType contentType);

    /**
     * Set the body for the request.
     *
     * @param httpEntity The HTTP entity body.
     * @return This builder instance.
     */
    BT setBody(final HttpEntity httpEntity);

    /**
     * Set a body parameter for the request.
     *
     * @param <ST>  The parameter value type.
     * @param name  The parameter name.
     * @param value The parameter value.
     * @return This builder instance.
     */
    <ST> BT setBodyParameter(final String name, final ST value);

    /**
     * Build the request.
     *
     * @return The constructed request.
     */
    IRequest<T> build();
  }
}
