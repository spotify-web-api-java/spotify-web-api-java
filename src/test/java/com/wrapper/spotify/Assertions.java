package com.wrapper.spotify;

import com.wrapper.spotify.requests.IRequest;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.fail;

public class Assertions {

  public static void assertHasQueryParameter(IRequest request, String name) {
    String[] queryParameters = request.getUri().getQuery().split("&");

    for (String queryParameter : queryParameters) {
      String[] queryParameterParts = queryParameter.split("=");

      if (queryParameterParts[0].equals(name)) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s", request, name));
  }

  public static <T> void assertHasQueryParameter(IRequest request, String name, T value) {
    String[] queryParameters = request.getUri().getQuery().split("&");

    for (String queryParameter : queryParameters) {
      String[] queryParameterParts = queryParameter.split("=");

      if (queryParameterParts[0].equals(name) && queryParameterParts[1].equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s with value %s", request, name, String.valueOf(value)));
  }

  public static void assertHasHeader(IRequest request, String name) {
    Assert.assertTrue("Request does not contain form parameter" + name, request.getHeaders().containsKey(name));
  }

  public static <T> void assertHasHeader(IRequest request, String name, T value) {
    Assert.assertTrue("Request does not contain parameter " + name + " with value " + String.valueOf(value),
            request.getHeaders().get(name).equals(String.valueOf(value)));
  }

  public static void assertHasFormParameter(IRequest request, String name) {
    Assert.assertTrue("Request does not contain form parameter" + name, request.getFormParameters().containsKey(name));
  }

  public static <T> void assertHasFormParameter(IRequest request, String name, T value) {
    Assert.assertTrue("Request does not contain form parameter " + name + " with value " + String.valueOf(value),
            request.getFormParameters().get(name).equals(String.valueOf(value)));
  }

  public static void assertHasBodyParameter(IRequest request, String name) {
    Assert.assertTrue("Request does not contain body parameter" + name, request.getBodyParameters().containsKey(name));
  }

  public static <T> void assertHasBodyParameter(IRequest request, String name, T value) {
    Assert.assertTrue("Request does not contain body parameter " + name + " with value " + String.valueOf(value),
            request.getBodyParameters().get(name).equals(String.valueOf(value)));
  }
}
