package se.michaelthelin.spotify.requests.data.playlists;

import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.SpotifyApi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for ensuring numeric strings are preserved as strings in JSON request bodies
 */
public class CreatePlaylistRequestNumericStringTest {

    @Test
    public void shouldPreserveNumericStringsAsStrings() {
        // Create a request with numeric strings for name and description
        CreatePlaylistRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .createPlaylist("2025")
            .description("2025")
            .build();

        // Generate the JSON body
        String json = request.bodyParametersToJson(request.getBodyParameters());

        // The JSON should contain quoted strings, not numbers
        assertTrue(json.contains("\"name\":\"2025\""),
            "Name should be a quoted string, not a number. Actual JSON: " + json);
        assertTrue(json.contains("\"description\":\"2025\""),
            "Description should be a quoted string, not a number. Actual JSON: " + json);

        // Should NOT contain unquoted numbers
        assertFalse(json.contains("\"name\":2025"),
          "Name should not be an unquoted number. Actual JSON: " + json);
        assertFalse(json.contains("\"description\":2025"),
          "Description should not be an unquoted number. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveMixedTypesCorrectly() {
        // Test various edge cases
        CreatePlaylistRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .createPlaylist("123.45")  // decimal number as string
            .description("abc123")  // mixed alphanumeric
            .build();

        String json = request.bodyParametersToJson(request.getBodyParameters());

        // Both should remain as strings
        assertTrue(json.contains("\"name\":\"123.45\""),
            "Decimal number string should remain quoted. Actual JSON: " + json);
        assertTrue(json.contains("\"description\":\"abc123\""),
            "Mixed alphanumeric should remain quoted. Actual JSON: " + json);
    }

    @Test
    public void shouldPreserveEmptyAndNullLikeStrings() {
        CreatePlaylistRequest request = new SpotifyApi.Builder()
            .setAccessToken("test-token")
            .build()
            .createPlaylist("null")
            .description("true")
            .build();

        String json = request.bodyParametersToJson(request.getBodyParameters());

        // Should remain as quoted strings, not become null/boolean literals
        assertTrue(json.contains("\"name\":\"null\""),
            "String 'null' should remain quoted. Actual JSON: " + json);
        assertTrue(json.contains("\"description\":\"true\""),
            "String 'true' should remain quoted. Actual JSON: " + json);
    }
}
