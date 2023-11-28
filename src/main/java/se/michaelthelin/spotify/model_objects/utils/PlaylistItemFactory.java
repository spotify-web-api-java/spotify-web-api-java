package se.michaelthelin.spotify.model_objects.utils;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class PlaylistItemFactory {
  public static IPlaylistItem createPlaylistItem(JsonObject trackObj) {
    IPlaylistItem item = null;
    if (trackObj.has("type") && !trackObj.get("type").isJsonNull()) {
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
