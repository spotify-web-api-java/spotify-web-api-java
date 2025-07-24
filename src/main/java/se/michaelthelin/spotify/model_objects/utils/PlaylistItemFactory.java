package se.michaelthelin.spotify.model_objects.utils;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject.JsonUtil;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * Factory class for creating playlist item objects from JSON data.
 * This utility handles the creation of different types of playlist items
 * such as tracks and episodes.
 */
public class PlaylistItemFactory {
  private static final JsonUtil<?> jsonUtil = new JsonUtil<>() {
    @Override
    public Object createModelObject(JsonObject jsonObject) {
      return null;
    }
  };

  /**
   * Default constructor.
   */
  public PlaylistItemFactory() {
    super();
  }

  /**
   * Creates a playlist item from a JSON object.
   * Determines the type of item (track or episode) based on the "type" field
   * and creates the appropriate model object.
   *
   * @param trackObj the JSON object containing playlist item data
   * @return the created playlist item, or null if the type is not recognized
   */
  public static IPlaylistItem createPlaylistItem(JsonObject trackObj) {
    IPlaylistItem item = null;
    if (jsonUtil.hasAndNotNull(trackObj, "type")) {
      String type = trackObj.get("type").getAsString().toLowerCase();

      if (type.equals("track")) {
        item = new Track.JsonUtil().createModelObject(trackObj);
      } else if (type.equals("episode")) {
        item = new Episode.JsonUtil().createModelObject(trackObj);
      }
    }
    return item;
  }
}
