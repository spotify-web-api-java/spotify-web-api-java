package se.michaelthelin.spotify;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.SpotifyProtos.Artist;
import se.michaelthelin.spotify.SpotifyProtos.Image;

import java.util.List;

public class JsonUtil {

  public static List<Artist> newArtistList(String json) {
    throw new RuntimeException("Not implemented");
  }

  public static Artist newArtist(String json) {
    return newArtist(JSONObject.fromObject(json));
  }

  public static Artist newArtist(JSONObject jsonObject) {
    if (jsonObject == null || jsonObject.isNullObject()) {
      return null;
    }
    Artist.Builder artistBuilder = Artist.newBuilder();

    artistBuilder.setApiLink(jsonObject.getString("api_link"));

    JSONArray apiLinkArray = jsonObject.getJSONArray("genres");
    for (int i = 0; i < apiLinkArray.size(); i++) {
      artistBuilder.addGenres(apiLinkArray.getString(i));
    }

    artistBuilder.setId(jsonObject.getString("id"));

    Artist.Images.Builder artistImagesBuilder = Artist.Images.newBuilder();
    JSONObject imagesJsonObject = jsonObject.getJSONObject("images");

    Image.Builder smallImageBuilder = Image.newBuilder();
    JSONObject smallImageJsonObject = imagesJsonObject.getJSONObject("SMALL");
    smallImageBuilder.setHeight(smallImageJsonObject.getInt("height"));
    smallImageBuilder.setImageUrl(smallImageJsonObject.getString("image_url"));
    smallImageBuilder.setWidth(smallImageJsonObject.getInt("width"));
    artistImagesBuilder.setSMALL(smallImageBuilder.build());

    Image.Builder mediumImageBuilder = Image.newBuilder();
    JSONObject mediumImageJsonObject = imagesJsonObject.getJSONObject("MEDIUM");
    mediumImageBuilder.setHeight(mediumImageJsonObject.getInt("height"));
    mediumImageBuilder.setImageUrl(mediumImageJsonObject.getString("image_url"));
    mediumImageBuilder.setWidth(mediumImageJsonObject.getInt("width"));
    artistImagesBuilder.setMEDIUM(mediumImageBuilder.build());

    Image.Builder largeImageBuilder = Image.newBuilder();
    JSONObject largeImageJsonObject = imagesJsonObject.getJSONObject("LARGE");
    largeImageBuilder.setHeight(largeImageJsonObject.getInt("height"));
    largeImageBuilder.setImageUrl(largeImageJsonObject.getString("image_url"));
    largeImageBuilder.setWidth(largeImageJsonObject.getInt("width"));
    artistImagesBuilder.setLARGE(largeImageBuilder.build());

    artistBuilder.setImages(artistImagesBuilder.build());

    artistBuilder.setLink(jsonObject.getString("link"));
    artistBuilder.setName(jsonObject.getString("name"));
    artistBuilder.setPopularity(jsonObject.getInt("popularity"));
    artistBuilder.setSpotifyUri(jsonObject.getString("spotify_uri"));
    artistBuilder.setType(jsonObject.getString("type"));

    return artistBuilder.build();
  }
}
