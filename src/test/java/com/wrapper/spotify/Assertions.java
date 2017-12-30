package com.wrapper.spotify;

import com.wrapper.spotify.requests.IRequest;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

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
    List<Header> headers = request.getHeaders();

    for (Header header : headers) {
      if (header.getName().equals(name)) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s", request, name));
  }

  public static <T> void assertHasHeader(IRequest request, String name, T value) {
    List<Header> headers = request.getHeaders();

    for (Header header : headers) {
      if (header.getName().equals(name) && header.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s with value %s", request, name, String.valueOf(value)));
  }

  public static void assertHasFormParameter(IRequest request, String name) {
    List<NameValuePair> formParameters = request.getFormParameters();

    for (NameValuePair formParameter : formParameters) {
      if (formParameter.getName().equals(name)) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s", request, name));
  }

  public static <T> void assertHasFormParameter(IRequest request, String name, T value) {
    List<NameValuePair> formParameters = request.getFormParameters();

    for (NameValuePair formParameter : formParameters) {
      if (formParameter.getName().equals(name) && formParameter.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s with value %s", request, name, String.valueOf(value)));
  }

  public static void assertHasBodyParameter(IRequest request, String name) {
    List<NameValuePair> bodyParameters = request.getBodyParameters();

    for (NameValuePair bodyParameter : bodyParameters) {
      if (bodyParameter.getName().equals(name)) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s", request, name));
  }

  public static <T> void assertHasBodyParameter(IRequest request, String name, T value) {
    List<NameValuePair> bodyParameters = request.getBodyParameters();

    for (NameValuePair bodyParameter : bodyParameters) {
      if (bodyParameter.getName().equals(name) && bodyParameter.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request %s does not contain form parameter %s with value %s", request, name, String.valueOf(value)));
  }
}
