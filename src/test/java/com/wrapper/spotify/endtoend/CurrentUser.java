package com.wrapper.spotify.endtoend;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.TokenResponse;
import com.wrapper.spotify.models.User;

import static junit.framework.TestCase.fail;

public class CurrentUser {

  public static void main(String[] args) {
    final String clientId = "fcecfc79122e4cd299473677a17cbd4d";
    final String clientSecret = "0d3adc510bb94bcf8129301c46058dd9";
    final String code = "AQAzkh8SH6f7J3D76SkhNSKhaM5QKyLRL-p4OOkup1px9DIOYcg8fO9DI7nZNZhAvBg57dYLj8tvEU1r3wlocMZSDNjpeimPE5AoAFSskqdcpnoKMq14h17W2PfXRkk-0eI3eTdSMWa2gn4CBFxM3p5XrAZ2EKwg-e6a_gHuWtA9XR634nmsDigiRbhbPYxcin2ioFWceMaMXAi0MC6_dEj_MEDqBJNcnpGA5Msy";
    final String redirectUri = "http://www.wrapper.com/test-callback";

    final Api api = Api.builder().build();

    try {
      final TokenResponse tokenResponse = api.getTokens(clientId, clientSecret, code, redirectUri).build().get();

      final User currentUser = api.getCurrentUser().accessToken(tokenResponse.getAccessToken()).build().get();

      System.out.println("URI to currently logged in user is: " + currentUser.getUri());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
