package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.ServerErrorException;
import com.wrapper.spotify.exceptions.WebApiException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * A simple HTTP connection interface.
 */
public interface HttpManager {

  /**
   * Perform an HTTP GET request to the specified URL.
   *
   * @param url the {@link Url} to HTTP GET.
   * @return a String containing the body of the HTTP GET response.
   * @throws WebApiException In case of error replies from the Web API.
   * @throws IOException In case of networking issues.
   */
  String get(Url url) throws WebApiException, IOException;

  /**
   * Perform an HTTP POST request to the specified URL.
   *
   * @param url the {@link Url} to HTTP POST.
   * @return a String containing the body of the HTTP POST response.
   * @throws WebApiException In case of error replies from the Web API.
   * @throws IOException In case of networking issues.
   */
  String post(Url url) throws WebApiException, IOException;

  /**
   * Perform an HTTP DELETE request to the specified URL.
   * @param url the {@link Url} to HTTP DELETE.
   * @return a String containing the body of the HTTP DELETE response.
   */
  String delete(Url url) throws WebApiException, IOException;

  /**
   * Perform an HTTP PUT request to the specified URL.
   *
   * @param url the {@link Url} to HTTP PUT.
   * @return a String containing the body of the HTTP PUTresponse.
   * @throws WebApiException In case of error replies from the Web API.
   * @throws IOException In case of networking issues.
   */
  String put(Url url) throws IOException, WebApiException;

}
