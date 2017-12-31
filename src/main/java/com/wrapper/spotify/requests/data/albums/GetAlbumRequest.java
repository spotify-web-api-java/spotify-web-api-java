package com.wrapper.spotify.requests.data.albums;

import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Get Spotify catalog information for a single album.
 */
public class GetAlbumRequest extends AbstractDataRequest {

  /**
   * The private {@link GetAlbumRequest} constructor.
   *
   * @param builder A {@link GetAlbumRequest} builder.
   */
  private GetAlbumRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get an {@link Album} synchronously.
   *
   * @return An {@link Album}.
   * @throws IOException                  In case of networking issues.
   * @throws NoContentException           The request has succeeded but returns no message body.
   * @throws BadRequestException          The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException          The server was acting as a gateway or proxy and received an invalid response
   *                                      from the upstream server.
   * @throws ForbiddenException           The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException     Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all
   *                                      ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException            The requested resource could not be found. This error can be due to a
   *                                      temporary or permanent condition.
   * @throws UnauthorizedException        The request requires user authorization or, if the request included
   *                                      authorization credentials, authorization has been refused for those
   *                                      credentials.
   * @throws ServiceUnavailableException  The server is currently unable to handle the request due to a temporary
   *                                      condition which will be alleviated after some delay. You can choose to resend
   *                                      the request again.
   */
  public Album get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Album.JsonUtil().createModelObject(getJson());
  }

  /**
   * Get an {@link Album} asynchronously.
   *
   * @return A {@link SettableFuture} for an {@link Album}.
   * @throws IOException                  In case of networking issues.
   * @throws NoContentException           The request has succeeded but returns no message body.
   * @throws BadRequestException          The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException          The server was acting as a gateway or proxy and received an invalid response
   *                                      from the upstream server.
   * @throws ForbiddenException           The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException     Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all
   *                                      ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException            The requested resource could not be found. This error can be due to a
   *                                      temporary or permanent condition.
   * @throws UnauthorizedException        The request requires user authorization or, if the request included
   *                                      authorization credentials, authorization has been refused for those
   *                                      credentials.
   * @throws ServiceUnavailableException  The server is currently unable to handle the request due to a temporary
   *                                      condition which will be alleviated after some delay. You can choose to resend
   *                                      the request again.
   */
  public SettableFuture<Album> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Album.JsonUtil().createModelObject(getJson()));
  }

  /**
   * A builder class for a {@link GetAlbumRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The id path parameter setter.
     *
     * @param id The <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify ID</a> for
     *           the album.
     * @return A {@link GetAlbumRequest.Builder}.
     */
    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    /**
     * The market query parameter setter.
     *
     * @param market Optional. An <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2 country
     *               code</a>. Provide this parameter if you want to apply
     *               <a href="https://developer.spotify.com/web-api/track-relinking-guide/">Track Relinking</a>.
     * @return A {@link GetAlbumRequest.Builder}.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetAlbumRequest}.
     */
    @Override
    public GetAlbumRequest build() {
      setPath("/v1/albums/{id}");
      return new GetAlbumRequest(this);
    }
  }
}
