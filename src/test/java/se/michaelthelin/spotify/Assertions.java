package se.michaelthelin.spotify;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.NameValuePair;
import se.michaelthelin.spotify.requests.IRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class Assertions {

  public static <RT, T> void assertHasHeader(IRequest<RT> request, String name, T value) {
    List<Header> headers = request.getHeaders();

    for (Header header : headers) {
      if (header.getName().equals(name) && header.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request \"%s\" does not contain form parameter \"%s\" with value \"%s\"", request.getClass().getSimpleName(), name, value));
  }

  public static <RT, T> void assertHasBodyParameter(IRequest<RT> request, String name, T value) {
    List<NameValuePair> bodyParameters = request.getBodyParameters();

    for (NameValuePair bodyParameter : bodyParameters) {
      if (bodyParameter.getName().equals(name) && bodyParameter.getValue().equals(String.valueOf(value))) {
        return;
      }
    }

    fail(String.format("Request \"%s\" does not contain form parameter \"%s\" with value \"%s\"", request.getClass().getSimpleName(), name, value));
  }
}
