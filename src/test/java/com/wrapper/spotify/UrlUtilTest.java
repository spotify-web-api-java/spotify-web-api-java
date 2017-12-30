package com.wrapper.spotify;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class UrlUtilTest {
  @Test
  public void shouldCreateProperUri() throws Exception {
    String user = "ta_!#¤%=?+☃3";
    String userUri = UriUtil.escapeUsername(user);
    URI uri = new URI("ta_%21%23%C2%A4%25%3D%3F%2B%E2%98%833");
    assertEquals(userUri, uri.toString());
  }
}
