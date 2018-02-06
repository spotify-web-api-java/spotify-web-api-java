package com.wrapper.spotify.requests.data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

/**
 * Create a playlist-style listening experience based on seed artists, tracks and genres. <br><br>
 * <p>
 * Recommendations are generated based on the available information for a given seed entity and matched against similar
 * artists and tracks. If there is sufficient information about the provided seeds, a list of tracks will be returned
 * together with pool size details. <br><br>
 * <p>
 * For artists and tracks that are very new or obscure there might not be enough data to generate a list of tracks.
 */
public class GetRecommendationsRequest extends AbstractDataRequest {

  /**
   * The private {@link GetRecommendationsRequest} constructor.
   *
   * @param builder A {@link GetRecommendationsRequest.Builder}.
   */
  private GetRecommendationsRequest(final Builder builder) {
    super(builder);
  }

  /**
   * Get the {@link Recommendations}.
   *
   * @return A {@link Recommendations} object.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  @SuppressWarnings("unchecked")
  public Recommendations execute() throws
          IOException,
          SpotifyWebApiException {
    return new Recommendations.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetRecommendationsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    /**
     * Create a new {@link GetRecommendationsRequest.Builder} instance.
     *
     * @param accessToken Required. A valid access token from the Spotify Accounts service.
     */
    public Builder(final String accessToken) {
      super(accessToken);
    }

    /**
     * The limit setter.
     *
     * @param limit Optional. The target size of the list of recommended tracks. For seeds with unusually small pools or
     *              when highly restrictive filtering is applied, it may be impossible to generate the requested number
     *              of recommended tracks. Debugging information for such cases is available in the response. Default:
     *              20. Minimum: 1. Maximum: 100.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (1 <= limit && limit <= 100);
      return setQueryParameter("limit", limit);
    }

    /**
     * The country code setter.
     *
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track
     *               Relinking. Because {@code min_*}, {@code max_*} and {@code target_*} are applied to pools before
     *               relinking, the generated results may not precisely match the filters applied. Original,
     *               non-relinked tracks are available via the {@code linked_from} attribute of the relinked track
     *               response.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikipedia: ISO 3166-1 alpha-2 country codes</a>
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * The maximum acousticness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_acousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                         high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_acousticness(final Float max_acousticness) {
      assert (max_acousticness != null);
      assert (0.0 <= max_acousticness && max_acousticness <= 1.0);
      return setQueryParameter("max_acousticness", max_acousticness);
    }

    /**
     * The maximum danceability setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_danceability Danceability describes how suitable a track is for dancing based on a combination of
     *                         musical elements including tempo, rhythm stability, beat strength, and overall
     *                         regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_danceability(final Float max_danceability) {
      assert (max_danceability != null);
      assert (0.0 <= max_danceability && max_danceability <= 1.0);
      return setQueryParameter("max_danceability", max_danceability);
    }

    /**
     * The maximum duration setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_duration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_duration_ms(final Integer max_duration_ms) {
      assert (max_duration_ms != null);
      return setQueryParameter("max_duration_ms", max_duration_ms);
    }

    /**
     * The maximum energy setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_energy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                   activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                   has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                   to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                   entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_energy(final Float max_energy) {
      assert (max_energy != null);
      assert (0.0 <= max_energy && max_energy <= 1.0);
      return setQueryParameter("max_energy", max_energy);
    }

    /**
     * The maximum instrumentalness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_instrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                             instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                             closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                             contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                             seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_instrumentalness(final Float max_instrumentalness) {
      assert (max_instrumentalness != null);
      assert (0.0 <= max_instrumentalness && max_instrumentalness <= 1.0);
      return setQueryParameter("max_instrumentalness", max_instrumentalness);
    }

    /**
     * The maximum key setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_key The key the track is in. Integers map to pitches using standard
     *                <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_key(final Integer max_key) {
      assert (max_key != null);
      assert (0 <= max_key && max_key <= 11);
      return setQueryParameter("max_key", max_key);
    }

    /**
     * The maximum liveness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_liveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                     increased probability that the track was performed live. A value above 0.8 provides strong
     *                     likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_liveness(final Float max_liveness) {
      assert (max_liveness != null);
      assert (0.0 <= max_liveness && max_liveness <= 1.0);
      return setQueryParameter("max_liveness", max_liveness);
    }

    /**
     * The maximum loudness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_loudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                     entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                     quality of a sound that is the primary psychological correlate of physical strength
     *                     (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_loudness(final Float max_loudness) {
      assert (max_loudness != null);
      return setQueryParameter("max_loudness", max_loudness);
    }

    /**
     * The maximum mode setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_mode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                 content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_mode(final Integer max_mode) {
      assert (max_mode != null);
      assert (max_mode == 0 || max_mode == 1);
      return setQueryParameter("max_mode", max_mode);
    }

    /**
     * The maximum popularity setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_popularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                       popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                       total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_popularity(final Integer max_popularity) {
      assert (max_popularity != null);
      assert (0 <= max_popularity && max_popularity <= 100);
      return setQueryParameter("max_popularity", max_popularity);
    }

    /**
     * The maximum speechiness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_speechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                        speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                        attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                        spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both
     *                        music and speech, either in sections or layered, including such cases as rap music. Values
     *                        below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_speechiness(final Float max_speechiness) {
      assert (max_speechiness != null);
      assert (0.0 <= max_speechiness && max_speechiness <= 1.0);
      return setQueryParameter("max_speechiness", max_speechiness);
    }

    /**
     * The maximum tempo setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_tempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                  is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_tempo(final Float max_tempo) {
      assert (max_tempo != null);
      assert (max_tempo >= 0);
      return setQueryParameter("max_tempo", max_tempo);
    }

    /**
     * The maximum time signature setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_time_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                           notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_time_signature(final Integer max_time_signature) {
      assert (max_time_signature != null);
      return setQueryParameter("max_time_signature", max_time_signature);
    }

    /**
     * The maximum valence setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param max_valence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                    high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                    valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder max_valence(final Float max_valence) {
      assert (max_valence != null);
      assert (0.0 <= max_valence && max_valence <= 1.0);
      return setQueryParameter("max_valence", max_valence);
    }

    /**
     * The minimum acousticness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_acousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                         high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_acousticness(final Float min_acousticness) {
      assert (min_acousticness != null);
      assert (0.0 <= min_acousticness && min_acousticness <= 1.0);
      return setQueryParameter("min_acousticness", min_acousticness);
    }

    /**
     * The minimum danceability setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_danceability Danceability describes how suitable a track is for dancing based on a combination of
     *                         musical elements including tempo, rhythm stability, beat strength, and overall
     *                         regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_danceability(final Float min_danceability) {
      assert (min_danceability != null);
      assert (0.0 <= min_danceability && min_danceability <= 1.0);
      return setQueryParameter("min_danceability", min_danceability);
    }

    /**
     * The minimum duration setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_duration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_duration_ms(final Integer min_duration_ms) {
      assert (min_duration_ms != null);
      return setQueryParameter("min_duration_ms", min_duration_ms);
    }

    /**
     * The minimum energy setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_energy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                   activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                   has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                   to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                   entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_energy(final Float min_energy) {
      assert (min_energy != null);
      assert (0.0 <= min_energy && min_energy <= 1.0);
      return setQueryParameter("min_energy", min_energy);
    }

    /**
     * The minimum instrumentalness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_instrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                             instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                             closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                             contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                             seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_instrumentalness(final Float min_instrumentalness) {
      assert (min_instrumentalness != null);
      assert (0.0 <= min_instrumentalness && min_instrumentalness <= 1.0);
      return setQueryParameter("min_instrumentalness", min_instrumentalness);
    }

    /**
     * The minimum key setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_key The key the track is in. Integers map to pitches using standard
     *                <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_key(final Integer min_key) {
      assert (min_key != null);
      assert (0 <= min_key && min_key <= 11);
      return setQueryParameter("min_key", min_key);
    }

    /**
     * The minimum liveness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_liveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                     increased probability that the track was performed live. A value above 0.8 provides strong
     *                     likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_liveness(final Float min_liveness) {
      assert (min_liveness != null);
      assert (0.0 <= min_liveness && min_liveness <= 1.0);
      return setQueryParameter("min_liveness", min_liveness);
    }

    /**
     * The minimum loudness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_loudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                     entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                     quality of a sound that is the primary psychological correlate of physical strength
     *                     (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_loudness(final Float min_loudness) {
      assert (min_loudness != null);
      return setQueryParameter("min_loudness", min_loudness);
    }

    /**
     * The minimum mode setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_mode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                 content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_mode(final Integer min_mode) {
      assert (min_mode != null);
      assert (min_mode == 0 || min_mode == 1);
      return setQueryParameter("min_mode", min_mode);
    }

    /**
     * The minimum popularity setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_popularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                       popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                       total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_popularity(final Integer min_popularity) {
      assert (min_popularity != null);
      assert (0 <= min_popularity && min_popularity <= 100);
      return setQueryParameter("min_popularity", min_popularity);
    }

    /**
     * The minimum speechiness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_speechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                        speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                        attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                        spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both
     *                        music and speech, either in sections or layered, including such cases as rap music. Values
     *                        below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_speechiness(final Float min_speechiness) {
      assert (min_speechiness != null);
      assert (0.0 <= min_speechiness && min_speechiness <= 1.0);
      return setQueryParameter("min_speechiness", min_speechiness);
    }

    /**
     * The minimum tempo setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_tempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                  is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_tempo(final Float min_tempo) {
      assert (min_tempo != null);
      assert (min_tempo >= 0);
      return setQueryParameter("min_tempo", min_tempo);
    }

    /**
     * The minimum time signature setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_time_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                           notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_time_signature(final Integer min_time_signature) {
      assert (min_time_signature != null);
      return setQueryParameter("min_time_signature", min_time_signature);
    }

    /**
     * The minimum valence setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param min_valence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                    high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                    valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder min_valence(final Float min_valence) {
      assert (min_valence != null);
      assert (0.0 <= min_valence && min_valence <= 1.0);
      return setQueryParameter("min_valence", min_valence);
    }

    /**
     * The seed artists setter.
     *
     * @param seed_artists A comma separated list of Spotify IDs for seed seed_artists. Up to 5 seed values may be
     *                     provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seed_artists(final String seed_artists) {
      assert (seed_artists != null);
      assert (seed_artists.split(",").length <= 5);
      return setQueryParameter("seed_artists", seed_artists);
    }

    /**
     * The seed genres setter.
     *
     * @param seed_genres A comma separated list of any seed_genres in the set of available genre seeds. Up to 5 seed
     *                    values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seed_genres(final String seed_genres) {
      assert (seed_genres != null);
      assert (seed_genres.split(",").length <= 5);
      return setQueryParameter("seed_genres", seed_genres);
    }

    /**
     * The seed tracks setter.
     *
     * @param seed_tracks A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in
     *                    any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seed_tracks(final String seed_tracks) {
      assert (seed_tracks != null);
      assert (seed_tracks.split(",").length <= 5);
      return setQueryParameter("seed_tracks", seed_tracks);
    }

    /**
     * The target acousticness setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param target_acousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                            high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_acousticness(final Float target_acousticness) {
      assert (target_acousticness != null);
      assert (0.0 <= target_acousticness && target_acousticness <= 1.0);
      return setQueryParameter("target_acousticness", target_acousticness);
    }

    /**
     * The target danceability setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param target_danceability Danceability describes how suitable a track is for dancing based on a combination of
     *                            musical elements including tempo, rhythm stability, beat strength, and overall
     *                            regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_danceability(final Float target_danceability) {
      assert (target_danceability != null);
      assert (0.0 <= target_danceability && target_danceability <= 1.0);
      return setQueryParameter("target_danceability", target_danceability);
    }

    /**
     * The target duration setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_duration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_duration_ms(final Integer target_duration_ms) {
      assert (target_duration_ms != null);
      return setQueryParameter("target_duration_ms", target_duration_ms);
    }

    /**
     * The target energy setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_energy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                      activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death
     *                      metal has high energy, while a Bach prelude scores low on the scale. Perceptual features
     *                      contributing to this attribute include dynamic range, perceived loudness, timbre, onset
     *                      rate, and general entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_energy(final Float target_energy) {
      assert (target_energy != null);
      assert (0.0 <= target_energy && target_energy <= 1.0);
      return setQueryParameter("target_energy", target_energy);
    }

    /**
     * The target instrumentalness setter. Tracks with the attribute value nearest to the target value will be
     * preferred. All target values will be weighed equally in ranking results.
     *
     * @param target_instrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                                instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal".
     *                                The closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                                contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                                seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_instrumentalness(final Float target_instrumentalness) {
      assert (target_instrumentalness != null);
      assert (0.0 <= target_instrumentalness && target_instrumentalness <= 1.0);
      return setQueryParameter("target_instrumentalness", target_instrumentalness);
    }

    /**
     * The target key setter. Tracks with the attribute value nearest to the target value will be preferred. All target
     * values will be weighed equally in ranking results.
     *
     * @param target_key The key the track is in. Integers map to pitches using standard
     *                   <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                   C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_key(final Integer target_key) {
      assert (target_key != null);
      assert (0 <= target_key && target_key <= 11);
      return setQueryParameter("target_key", target_key);
    }

    /**
     * The target liveness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_liveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                        increased probability that the track was performed live. A value above 0.8 provides strong
     *                        likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_liveness(final Float target_liveness) {
      assert (target_liveness != null);
      assert (0.0 <= target_liveness && target_liveness <= 1.0);
      return setQueryParameter("target_liveness", target_liveness);
    }

    /**
     * The target loudness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_loudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                        entire track and are useful for comparing relative loudness of seed_tracks. Loudness is
     *                        the quality of a sound that is the primary psychological correlate of physical strength
     *                        (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_loudness(final Float target_loudness) {
      assert (target_loudness != null);
      return setQueryParameter("target_loudness", target_loudness);
    }

    /**
     * The target mode setter. Tracks with the attribute value nearest to the target value will be preferred. All target
     * values will be weighed equally in ranking results.
     *
     * @param target_mode Mode indicates the modality (major or minor) of a track, the type of scale from which its
     *                    melodic content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_mode(final Integer target_mode) {
      assert (target_mode != null);
      assert (target_mode == 0 || target_mode == 1);
      return setQueryParameter("target_mode", target_mode);
    }

    /**
     * The target popularity setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_popularity The value will be between 0 and 100, with 100 being the most
     *                          popular. The popularity is calculated by algorithm and is based, in the most part, on
     *                          the total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_popularity(final Integer target_popularity) {
      assert (target_popularity != null);
      assert (0 <= target_popularity && target_popularity <= 100);
      return setQueryParameter("target_popularity", target_popularity);
    }

    /**
     * The target speechiness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_speechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                           speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                           attribute value. Values above 0.66 describe seed_tracks that are probably made entirely
     *                           of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain
     *                           both music and speech, either in sections or layered, including such cases as rap music.
     *                           Values below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_speechiness(final Float target_speechiness) {
      assert (target_speechiness != null);
      assert (0.0 <= target_speechiness && target_speechiness <= 1.0);
      return setQueryParameter("target_speechiness", target_speechiness);
    }

    /**
     * The target tempo setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_tempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology,
     *                     tempo is the speed or pace of a given piece and derives directly from the average beat
     *                     duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_tempo(final Float target_tempo) {
      assert (target_tempo != null);
      assert (target_tempo >= 0);
      return setQueryParameter("target_tempo", target_tempo);
    }

    /**
     * The target time signature setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param target_time_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                              notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_time_signature(final Integer target_time_signature) {
      assert (target_time_signature != null);
      return setQueryParameter("target_time_signature", target_time_signature);
    }

    /**
     * The target valence setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param target_valence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks
     *                       with high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks
     *                       with low valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder target_valence(final Float target_valence) {
      assert (target_valence != null);
      assert (0.0 <= target_valence && target_valence <= 1.0);
      return setQueryParameter("target_valence", target_valence);
    }

    /**
     * The request build method.
     *
     * @return A custom {@link GetRecommendationsRequest}.
     */
    @Override
    public GetRecommendationsRequest build() {
      setPath("/v1/recommendations");
      return new GetRecommendationsRequest(this);
    }
  }
}
