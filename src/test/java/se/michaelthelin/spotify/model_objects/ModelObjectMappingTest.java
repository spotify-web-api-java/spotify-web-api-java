package se.michaelthelin.spotify.model_objects;

import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.Modality;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.miscellaneous.AudioAnalysis;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.ExternalId;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Pins the field mappings that reflective deserialization cannot derive from the field name alone.
 * The per-request tests cover the plain snake_case fields; these are the ones that would silently
 * deserialize to {@code null} if a {@code @SerializedName} or a type adapter went missing.
 */
public class ModelObjectMappingTest {

  private static String fixture(String path) throws IOException {
    return TestUtil.readTestData(path);
  }

  @Test
  public void seedsKeepTheirCamelCaseWireNames() throws IOException {
    Recommendations recommendations = new Recommendations.JsonUtil()
      .createModelObject(fixture("requests/data/tracks/GetRecommendationsRequest.json"));

    assertEquals(448, (int) recommendations.getSeeds()[0].getInitialPoolSize());
    assertEquals(120, (int) recommendations.getSeeds()[0].getAfterFilteringSize());
    assertEquals(121, (int) recommendations.getSeeds()[0].getAfterRelinkingSize());
  }

  @Test
  public void seedTypeIsReadCaseInsensitively() throws IOException {
    // the fixture sends "ARTIST", the enum's wire name is "artist"
    Recommendations recommendations = new Recommendations.JsonUtil()
      .createModelObject(fixture("requests/data/tracks/GetRecommendationsRequest.json"));

    assertEquals(ModelObjectType.ARTIST, recommendations.getSeeds()[0].getType());
  }

  @Test
  public void playlistVisibilityComesFromThePublicKey() throws IOException {
    Playlist playlist = new Playlist.JsonUtil()
      .createModelObject(fixture("requests/data/playlists/GetPlaylistRequest.json"));

    // the fixture sends "public": null, so the field is null rather than absent
    assertNull(playlist.getPublic());
    assertNotNull(playlist.getName());
  }

  @Test
  public void audioAnalysisReadsTheFieldsTheSpecDefines() throws IOException {
    AudioAnalysis analysis = new AudioAnalysis.JsonUtil()
      .createModelObject(fixture("requests/data/tracks/GetAudioAnalysisRequest.json"));

    // window_seconds and end_of_fade_in were previously read under misspelled keys and so were
    // always null; the section's mode was guarded on a "type" key that sections do not carry
    assertEquals(0.0f, analysis.getTrack().getWindowSeconds(), 0.0f);
    assertEquals(0.0f, analysis.getTrack().getEndOfFadeIn(), 0.0f);
    assertEquals(Modality.MAJOR, analysis.getSections()[0].getMode());

    // these three really are spelled without an underscore on the wire
    assertNotNull(analysis.getTrack().getEchoprintString());
    assertNotNull(analysis.getTrack().getSynchString());
    assertNotNull(analysis.getTrack().getRhythmString());
  }

  @Test
  public void albumTypeAndNestedObjectsResolve() throws IOException {
    Album album = new Album.JsonUtil()
      .createModelObject(fixture("requests/data/albums/GetAlbumRequest.json"));

    assertEquals(AlbumType.ALBUM, album.getAlbumType());
    assertEquals(ModelObjectType.ALBUM, album.getType());
    assertNotNull(album.getArtists()[0].getName());
    assertNotNull(album.getTracks().getItems()[0].getName());
  }

  /**
   * A map-shaped model object keeps a hand-written parser, because its JSON is the map itself
   * rather than one key per field. Reflection would map the sole field to an "external_urls" or
   * "external_ids" key that is not there and hand back a null map.
   * <p>
   * ExternalUrl is nested in most responses, so {@code ModelObjectGson} also registers an adapter
   * that delegates to the same parser. ExternalId currently has no model object holding it, which
   * is why it is read here on its own.
   */
  @Test
  public void mapShapedObjectsAreReadByTheirOwnParser() throws IOException {
    Album album = new Album.JsonUtil()
      .createModelObject(fixture("requests/data/albums/GetAlbumRequest.json"));

    assertEquals("https://open.spotify.com/album/0sNOF9WDwhWunNAHPD3Baj",
      album.getExternalUrls().get("spotify"));

    ExternalId externalId = new ExternalId.JsonUtil().createModelObject("{\"upc\": \"5099749994324\"}");
    assertEquals("5099749994324", externalId.getExternalIds().get("upc"));
  }
}
