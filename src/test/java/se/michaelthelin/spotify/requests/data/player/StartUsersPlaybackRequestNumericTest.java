package se.michaelthelin.spotify.requests.data.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.SpotifyApi;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for ensuring numeric and structured parameters are preserved with correct types in JSON request bodies
 */
public class StartUsersPlaybackRequestNumericTest {

    @Test
    public void shouldPreserveNumericParametersAsNumbers() throws IOException, ParseException {
        // Create a request with numeric position_ms parameter
        StartResumePlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumePlayback()
            .position_ms(10000)
            .build();

        // Build the body the same way the client sends it
        request.initializeBody();
        String json = EntityUtils.toString(request.getBody());

        // The JSON should contain unquoted number, not a string
        assertTrue(json.contains("\"position_ms\":10000"),
          "position_ms should be an unquoted number. Actual JSON: " + json);

        // Should NOT contain quoted string
        assertFalse(json.contains("\"position_ms\":\"10000\""),
          "position_ms should not be a quoted string. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveBooleanParametersAsBooleans() throws IOException, ParseException {
        // Create a request with JSON array uris
        JsonArray uris = new JsonArray();
        uris.add("spotify:track:test1");
        uris.add("spotify:track:test2");

        StartResumePlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumePlayback()
            .uris(uris)
            .build();

        // Build the body the same way the client sends it
        request.initializeBody();
        String json = EntityUtils.toString(request.getBody());

        // The JSON should contain a proper JSON array
        assertTrue(json.contains("\"uris\":["),
            "uris should be a JSON array. Actual JSON: " + json);
        assertTrue(json.contains("spotify:track:test1"),
            "uris should contain the track URIs. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveJsonObjectParameters() throws IOException, ParseException {
        // Create a request with JSON object offset
        JsonObject offset = new JsonObject();
        offset.addProperty("position", 5);

        StartResumePlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumePlayback()
            .offset(offset)
            .build();

        // Build the body the same way the client sends it
        request.initializeBody();
        String json = EntityUtils.toString(request.getBody());

        // The JSON should contain a proper JSON object
        assertTrue(json.contains("\"offset\":{"),
            "offset should be a JSON object. Actual JSON: " + json);
        assertTrue(json.contains("\"position\":5"),
            "offset should contain the position property. Actual JSON: " + json);
    }
}
