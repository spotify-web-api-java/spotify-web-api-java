package se.michaelthelin.spotify.model_objects.utils;

import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject.JsonUtil;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class PlaylistItemFactory {
  private static final JsonUtil<?> jsonUtil = new JsonUtil<>() {
    @Override
    public Object createModelObject(JsonObject jsonObject) {
      return null;
    }
  };

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
