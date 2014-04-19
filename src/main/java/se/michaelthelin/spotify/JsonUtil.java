package se.michaelthelin.spotify;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import se.michaelthelin.spotify.SpotifyProtos.Artist;
import se.michaelthelin.spotify.SpotifyProtos.Image;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

  public static Image newImage(String json) {
    return newImage(JSONObject.fromObject(json));
  }

  public static Image newImage(JSONObject jsonObject) {
    Image.Builder builder = Image.newBuilder();
    builder.setHeight(jsonObject.getInt("height"));
    builder.setImageUrl(jsonObject.getString("image_url"));
    builder.setWidth(jsonObject.getInt("width"));
    return builder.build();
  }

  public static List<Artist> newArtistList(String json) {
    return newArtistList(JSONObject.fromObject(json));
  }

  public static List<Artist> newArtistList(JSONObject jsonObject) {
    List<Artist> returnedArtists = new ArrayList<Artist>();
    JSONArray artistsObject = jsonObject.getJSONArray("artists");
    for (int i = 0; i < artistsObject.size(); i++) {
      returnedArtists.add(newArtist(artistsObject.getJSONObject(i)));
    }
    return returnedArtists;
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

    JSONObject smallImageJsonObject = imagesJsonObject.getJSONObject("SMALL");
    Image smallImage = newImage(smallImageJsonObject);
    artistImagesBuilder.setSMALL(smallImage);

    JSONObject mediumImageJsonObject = imagesJsonObject.getJSONObject("MEDIUM");
    Image mediumImage = newImage(mediumImageJsonObject);
    artistImagesBuilder.setMEDIUM(mediumImage);

    JSONObject largeImageJsonObject = imagesJsonObject.getJSONObject("LARGE");
    Image largeImage = newImage(largeImageJsonObject);
    artistImagesBuilder.setLARGE(largeImage);

    artistBuilder.setImages(artistImagesBuilder.build());

    artistBuilder.setLink(jsonObject.getString("link"));
    artistBuilder.setName(jsonObject.getString("name"));
    artistBuilder.setPopularity(jsonObject.getInt("popularity"));
    artistBuilder.setSpotifyUri(jsonObject.getString("spotify_uri"));
    artistBuilder.setType(jsonObject.getString("type"));

    return artistBuilder.build();
  }
}
