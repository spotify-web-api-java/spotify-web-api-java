package com.wrapper.spotify;

import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;

/**
 * A simple HTTP connection interface.
 */
public interface HttpManager {

  /**
   * Perform an HTTP GET request to the specified URL.
   *
   * @param url the {@link Url} to HTTP GET.
   * @return a String containing the body of the HTTP GET response.
   * @throws IOException In case of networking issues.
   * @throws NoContentException The request has succeeded but returns no message body.
   * @throws BadRequestException The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException The server was acting as a gateway or proxy and received an invalid response from the upstream server.
   * @throws ForbiddenException The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException The requested resource could not be found. This error can be due to a temporary or permanent condition.
   * @throws UnauthorizedException The request requires user authentication or, if the request included authorization credentials, authorization has been refused for those credentials.
   * @throws ServiceUnavailableException The server is currently unable to handle the request due to a temporary condition which will be alleviated after some delay. You can choose to resend the request again.
   */
  String get(Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException;

  /**
   * Perform an HTTP POST request to the specified URL.
   *
   * @param url the {@link Url} to HTTP POST.
   * @return a String containing the body of the HTTP POST response.
   * @throws IOException In case of networking issues.
   * @throws NoContentException The request has succeeded but returns no message body.
   * @throws BadRequestException The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException The server was acting as a gateway or proxy and received an invalid response from the upstream server.
   * @throws ForbiddenException The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException The requested resource could not be found. This error can be due to a temporary or permanent condition.
   * @throws UnauthorizedException The request requires user authentication or, if the request included authorization credentials, authorization has been refused for those credentials.
   * @throws ServiceUnavailableException The server is currently unable to handle the request due to a temporary condition which will be alleviated after some delay. You can choose to resend the request again.
   */
  String post(Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException;

  /**
   * Perform an HTTP PUT request to the specified URL.
   *
   * @param url the {@link Url} to HTTP PUT.
   * @return a String containing the body of the HTTP PUTresponse.
   * @throws IOException In case of networking issues.
   * @throws NoContentException The request has succeeded but returns no message body.
   * @throws BadRequestException The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException The server was acting as a gateway or proxy and received an invalid response from the upstream server.
   * @throws ForbiddenException The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException The requested resource could not be found. This error can be due to a temporary or permanent condition.
   * @throws UnauthorizedException The request requires user authentication or, if the request included authorization credentials, authorization has been refused for those credentials.
   * @throws ServiceUnavailableException The server is currently unable to handle the request due to a temporary condition which will be alleviated after some delay. You can choose to resend the request again.
   */
  String put(Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException;

  /**
   * Perform an HTTP DELETE request to the specified URL.
   *
   * @param url the {@link Url} to HTTP DELETE.
   * @return a String containing the body of the HTTP DELETE response.
   * @throws IOException In case of networking issues.
   * @throws NoContentException The request has succeeded but returns no message body.
   * @throws BadRequestException The request could not be understood by the server due to malformed syntax.
   * @throws BadGatewayException The server was acting as a gateway or proxy and received an invalid response from the upstream server.
   * @throws ForbiddenException The server understood the request, but is refusing to fulfill it.
   * @throws TooManyRequestsException Rate limiting has been applied.
   * @throws InternalServerErrorException You should never receive this error because our clever coders catch them all ... but if you are unlucky enough to get one, please report it to us.
   * @throws NotFoundException The requested resource could not be found. This error can be due to a temporary or permanent condition.
   * @throws UnauthorizedException The request requires user authentication or, if the request included authorization credentials, authorization has been refused for those credentials.
   * @throws ServiceUnavailableException The server is currently unable to handle the request due to a temporary condition which will be alleviated after some delay. You can choose to resend the request again.
   */
  String delete(Url url) throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException;

}
