package com.wrapper.spotify;

import com.wrapper.spotify.requests.IRequest;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;

import static org.junit.Assert.fail;

public class Assertions {

  public static <T> void assertHasHeader(IRequest request, String name, T value) {
    List<Header> headers = request.getHeaders();

    for (Header header : headers) {
      if (header.getName().equals(name) && header.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request \"%s\" does not contain form parameter \"%s\" with value \"%s\"", request.getClass().getSimpleName(), name, String.valueOf(value)));
  }

  public static <T> void assertHasBodyParameter(IRequest request, String name, T value) {
    List<NameValuePair> bodyParameters = request.getBodyParameters();

    for (NameValuePair bodyParameter : bodyParameters) {
      if (bodyParameter.getName().equals(name) && bodyParameter.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request \"%s\" does not contain form parameter \"%s\" with value \"%s\"", request.getClass().getSimpleName(), name, String.valueOf(value)));
  }
}
