package com.wrapper.spotify;

import static org.junit.Assert.fail;

public class Assertions {

  public static void assertHasParameter(UtilProtos.Url url, String name, String value) {
    UtilProtos.Url.Parameter expected = UtilProtos.Url.Parameter.newBuilder().setName(name).setValue(value).build();
    for (UtilProtos.Url.Parameter actual : url.getParametersList()) {
      if (actual.equals(expected)) {
        return;
      }
    }
    fail(String.format("Actual URL %s does not contain setParameters %s", url, expected));
  }

  public static void assertNoParameter(UtilProtos.Url url, String name) {
    for (UtilProtos.Url.Parameter actual : url.getParametersList()) {
      if (actual.getName().equals(name)) {
        fail(String.format("Actual URL %s contains setParameters %s", url, name));
      }
    }
  }

  public static void assertHasBodyParameter(UtilProtos.Url url, String name, String value) {
    UtilProtos.Url.Parameter expected = UtilProtos.Url.Parameter.newBuilder().setName(name).setValue(value).build();
    for (UtilProtos.Url.Parameter actual : url.getBodyParametersList()) {
      if (actual.equals(expected)) {
        return;
      }
    }
    fail(String.format("Actual URL %s does not contain setBodyParameters setParameters %s", url, expected));
  }


  public static void assertHasHeader(UtilProtos.Url url, String name, String value) {
    UtilProtos.Url.Parameter expected = UtilProtos.Url.Parameter.newBuilder().setName(name).setValue(value).build();
    for (UtilProtos.Url.Parameter actual : url.getHeaderParametersList()) {
      if (actual.equals(expected)) {
        return;
      }
    }
    fail(String.format("Actual URL %s does not contain setHeaderParameters %s", url, expected));
  }

  public static void assertHasJsonBody(UtilProtos.Url url, String jsonBody) {
    if (url.hasJsonBody() && url.getJsonBody().equals(jsonBody)) {
      return;
    }
    fail(String.format("Actual URL %s does not contain setBodyParameters %s", url, jsonBody));
  }
}
