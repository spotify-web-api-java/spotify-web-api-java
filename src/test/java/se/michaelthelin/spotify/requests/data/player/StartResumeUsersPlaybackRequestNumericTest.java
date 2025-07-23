package se.michaelthelin.spotify.requests.data.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.SpotifyApi;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for ensuring numeric and structured parameters are preserved with correct types in JSON request bodies
 */
public class StartResumeUsersPlaybackRequestNumericTest {

    @Test
    public void shouldPreserveNumericParametersAsNumbers() {
        // Create a request with numeric position_ms parameter
        StartResumeUsersPlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumeUsersPlayback()
            .position_ms(10000)
            .build();

        // Generate the JSON body
        String json = request.bodyParametersToJson(request.getBodyParameters());
        
        // The JSON should contain unquoted number, not a string
        assertTrue(json.contains("\"position_ms\":10000"), 
            "position_ms should be an unquoted number. Actual JSON: " + json);
        
        // Should NOT contain quoted string
        assertTrue(!json.contains("\"position_ms\":\"10000\""), 
            "position_ms should not be a quoted string. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveBooleanParametersAsBooleans() {
        // Create a request with JSON array uris
        JsonArray uris = new JsonArray();
        uris.add("spotify:track:test1");
        uris.add("spotify:track:test2");
        
        StartResumeUsersPlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumeUsersPlayback()
            .uris(uris)
            .build();

        // Generate the JSON body
        String json = request.bodyParametersToJson(request.getBodyParameters());
        
        // The JSON should contain a proper JSON array
        assertTrue(json.contains("\"uris\":["), 
            "uris should be a JSON array. Actual JSON: " + json);
        assertTrue(json.contains("spotify:track:test1"), 
            "uris should contain the track URIs. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveJsonObjectParameters() {
        // Create a request with JSON object offset
        JsonObject offset = new JsonObject();
        offset.addProperty("position", 5);
        
        StartResumeUsersPlaybackRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .startResumeUsersPlayback()
            .offset(offset)
            .build();

        // Generate the JSON body
        String json = request.bodyParametersToJson(request.getBodyParameters());
        
        // The JSON should contain a proper JSON object
        assertTrue(json.contains("\"offset\":{"), 
            "offset should be a JSON object. Actual JSON: " + json);
        assertTrue(json.contains("\"position\":5"), 
            "offset should contain the position property. Actual JSON: " + json);
    }
}