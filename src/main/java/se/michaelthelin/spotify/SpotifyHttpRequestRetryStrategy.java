/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package se.michaelthelin.spotify;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.concurrent.CancellableDependency;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.Args;
import org.apache.hc.core5.util.TimeValue;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Default implementation of the {@link HttpRequestRetryStrategy} interface.
 *
 * @since 5.0
 */
@Contract(threading = ThreadingBehavior.STATELESS)
public class SpotifyHttpRequestRetryStrategy implements HttpRequestRetryStrategy {

  /**
   * Maximum number of allowed retries
   */
  private final int maxRetries;

  /**
   * Retry interval between subsequent retries
   */
  private final TimeValue defaultRetryInterval;

  /**
   * Derived {@code IOExceptions} which shall not be retried
   */
  private final Set<Class<? extends IOException>> nonRetriableIOExceptionClasses;

  /**
   * HTTP status codes which shall be retried
   */
  private final Set<Integer> retriableCodes;

  protected SpotifyHttpRequestRetryStrategy(
    final int maxRetries,
    final TimeValue defaultRetryInterval,
    final Collection<Class<? extends IOException>> clazzes,
    final Collection<Integer> codes) {
    Args.notNegative(maxRetries, "maxRetries");
    Args.notNegative(defaultRetryInterval.getDuration(), "defaultRetryInterval");
    this.maxRetries = maxRetries;
    this.defaultRetryInterval = defaultRetryInterval;
    this.nonRetriableIOExceptionClasses = new HashSet<>(clazzes);
    this.retriableCodes = new HashSet<>(codes);
  }

  /**
   * Create the HTTP request retry strategy using the following list of
   * non-retriable I/O exception classes:<br>
   * <ul>
   * <li>InterruptedIOException</li>
   * <li>UnknownHostException</li>
   * <li>ConnectException</li>
   * <li>ConnectionClosedException</li>
   * <li>NoRouteToHostException</li>
   * <li>SSLException</li>
   * </ul>
   * <p>
   * and retriable HTTP status codes:<br>
   * <ul>
   * <li>SC_SERVICE_UNAVAILABLE (503)</li>
   * </ul>
   *
   * @param maxRetries           how many times to retry; 0 means no retries
   * @param defaultRetryInterval the default retry interval between
   *                             subsequent retries if the {@code Retry-After} header is not set
   *                             or invalid.
   */
  public SpotifyHttpRequestRetryStrategy(
    final int maxRetries,
    final TimeValue defaultRetryInterval) {
    this(maxRetries, defaultRetryInterval,
      Arrays.asList(
        InterruptedIOException.class,
        UnknownHostException.class,
        ConnectException.class,
        ConnectionClosedException.class,
        NoRouteToHostException.class,
        SSLException.class),
      List.of(
        HttpStatus.SC_SERVICE_UNAVAILABLE));
  }

  /**
   * Create the HTTP request retry strategy with a max retry count of 1,
   * default retry interval of 1 second, and using the following list of
   * non-retriable I/O exception classes:<br>
   * <ul>
   * <li>InterruptedIOException</li>
   * <li>UnknownHostException</li>
   * <li>ConnectException</li>
   * <li>ConnectionClosedException</li>
   * <li>SSLException</li>
   * </ul>
   * <p>
   * and retriable HTTP status codes:<br>
   * <ul>
   * <li>SC_SERVICE_UNAVAILABLE (503)</li>
   * </ul>
   */
  public SpotifyHttpRequestRetryStrategy() {
    this(1, TimeValue.ofSeconds(1L));
  }

  @Override
  public boolean retryRequest(
    final HttpRequest request,
    final IOException exception,
    final int execCount,
    final HttpContext context) {
    Objects.requireNonNull(request, "request");
    Objects.requireNonNull(exception, "exception");

    if (execCount > this.maxRetries) {
      // Do not retry if over max retries
      return false;
    }
    if (this.nonRetriableIOExceptionClasses.contains(exception.getClass())) {
      return false;
    } else {
      for (final Class<? extends IOException> rejectException : this.nonRetriableIOExceptionClasses) {
        if (rejectException.isInstance(exception)) {
          return false;
        }
      }
    }
    if (request instanceof CancellableDependency && ((CancellableDependency) request).isCancelled()) {
      return false;
    }

    // Retry if the request is considered idempotent
    return handleAsIdempotent(request);
  }

  @Override
  public boolean retryRequest(
    final HttpResponse response,
    final int execCount,
    final HttpContext context) {
    Objects.requireNonNull(response, "response");

    return execCount <= this.maxRetries && retriableCodes.contains(response.getCode());
  }

  @Override
  public TimeValue getRetryInterval(
    final HttpResponse response,
    final int execCount,
    final HttpContext context) {
    Objects.requireNonNull(response, "response");

    return this.defaultRetryInterval;
  }

  protected boolean handleAsIdempotent(final HttpRequest request) {
    return Method.isIdempotent(request.getMethod());
  }

}
