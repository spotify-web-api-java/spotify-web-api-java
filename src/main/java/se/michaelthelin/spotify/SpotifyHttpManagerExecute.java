package se.michaelthelin.spotify;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.hc.client5.http.cache.CacheResponseStatus;
import org.apache.hc.client5.http.cache.HttpCacheContext;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.*;

import java.io.IOException;
import java.util.logging.Level;

public class SpotifyHttpManagerExecute {
  public CloseableHttpResponse execute(CloseableHttpClient httpClient, ClassicHttpRequest method) throws
    IOException {
    HttpCacheContext context = HttpCacheContext.create();
    CloseableHttpResponse response = httpClient.execute(method, context);

    try {
      CacheResponseStatus responseStatus = context.getCacheResponseStatus();
      extractSwitchLogError(responseStatus);
    } catch (Exception e) {
      SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
    }
    return response;
  }

  private void extractSwitchLogError(CacheResponseStatus responseStatus) {
    if (responseStatus != null) {
      switch (responseStatus) {
        case CACHE_HIT:
          SpotifyApi.LOGGER.log(
            Level.CONFIG,
            "A response was generated from the cache with no requests sent upstream");
          break;
        case CACHE_MODULE_RESPONSE:
          SpotifyApi.LOGGER.log(
            Level.CONFIG,
            "The response was generated directly by the caching module");
          break;
        case CACHE_MISS:
          SpotifyApi.LOGGER.log(
            Level.CONFIG,
            "The response came from an upstream server");
          break;
        case VALIDATED:
          SpotifyApi.LOGGER.log(
            Level.CONFIG,
            "The response was generated from the cache after validating the entry with the origin server");
          break;
        case FAILURE:
          SpotifyApi.LOGGER.log(
            Level.CONFIG,
            "The response came from an upstream server after a cache failure");
          break;
      }
    }
  }

  private void extractErrorMessage(JsonObject jsonObject, String errorMessage) {
    if (jsonObject.has("error_description")) {
      errorMessage =  jsonObject.get("error_description").getAsString();
    }
    if (jsonObject.get("error").isJsonObject() && jsonObject.getAsJsonObject("error").has("message")) {
      errorMessage =  jsonObject.getAsJsonObject("error").get("message").getAsString();
    }
  }

  private void extractVerifyResponseBody(String responseBody, String errorMessage) {
    if (responseBody != null && !responseBody.isEmpty()) {
      try {
        final JsonElement jsonElement = JsonParser.parseString(responseBody);
        if (jsonElement.isJsonObject()) {
          extractErrorMessage(jsonElement.getAsJsonObject(), errorMessage);
        }
      } catch (JsonSyntaxException ignored) { }
    }
  }

  public String getResponseBody(CloseableHttpResponse httpResponse) throws
    IOException,
    SpotifyWebApiException,
    ParseException {

    final String responseBody = httpResponse.getEntity() != null
      ? EntityUtils.toString(httpResponse.getEntity(), "UTF-8")
      : null;

    SpotifyApi.LOGGER.log(
      Level.FINE,
      "The http response has body " + responseBody);

    String errorMessage = httpResponse.getReasonPhrase();

    extractVerifyResponseBody(responseBody, errorMessage);

    SpotifyApi.LOGGER.log(
      Level.FINE,
      "The http response has status code " + httpResponse.getCode());

    switch (httpResponse.getCode()) {
      case HttpStatus.SC_BAD_REQUEST:
        throw new BadRequestException(errorMessage);
      case HttpStatus.SC_UNAUTHORIZED:
        throw new UnauthorizedException(errorMessage);
      case HttpStatus.SC_FORBIDDEN:
        throw new ForbiddenException(errorMessage);
      case HttpStatus.SC_NOT_FOUND:
        throw new NotFoundException(errorMessage);
      case 429: // TOO_MANY_REQUESTS (additional status code, RFC 6585)
        // Sets "Retry-After" header as described at https://beta.developer.spotify.com/documentation/web-api/#rate-limiting
        Header header = httpResponse.getFirstHeader("Retry-After");
        extractIsHeaderNull(header, errorMessage);
      case HttpStatus.SC_INTERNAL_SERVER_ERROR:
        throw new InternalServerErrorException(errorMessage);
      case HttpStatus.SC_BAD_GATEWAY:
        throw new BadGatewayException(errorMessage);
      case HttpStatus.SC_SERVICE_UNAVAILABLE:
        throw new ServiceUnavailableException(errorMessage);
      default:
        return responseBody;
    }
  }

  private void extractIsHeaderNull(Header header, String errorMessage) throws TooManyRequestsException {
    if (header != null) {
      throw new TooManyRequestsException(errorMessage, Integer.parseInt(header.getValue()));
    } else {
      throw new TooManyRequestsException(errorMessage);
    }
  }
}
