package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetRecommendationsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
          .limit(10)
          .market(CountryCode.SE)
          .max_popularity(50)
          .min_popularity(10)
          .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
          .seed_genres("electro")
          .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
          .target_popularity(20)
          .build();

  public static void getRecommendations_Sync() {
    try {
      final Recommendations recommendations = getRecommendationsRequest.execute();

      System.out.println("Length: " + recommendations.getTracks().length);
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getRecommendations_Async() {
    try {
      final Future<Recommendations> recommendationsFuture = getRecommendationsRequest.executeAsync();

      // ...

      final Recommendations recommendations = recommendationsFuture.get();

      System.out.println("Length: " + recommendations.getTracks().length);
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}