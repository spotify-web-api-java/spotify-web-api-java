package com.wrapper.spotify;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class UriUtil {

  public static String escapeUsername(String username) {
    String escapedUsername = "";
    try {
      escapedUsername = URLEncoder.encode(username, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return escapedUsername
            .replaceAll("!", "%21")
            .replaceAll("\\?", "%3F")
            .replaceAll("\\+", "%2B")
            .replaceAll("=", "%3D");
  }
}
