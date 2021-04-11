package com.wrapper.spotify.requests.data.browse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.requests.data.AbstractDataRequest;
import org.apache.hc.core5.http.ParseException;

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
@JsonDeserialize(builder = GetRecommendationsRequest.Builder.class)
public class GetRecommendationsRequest extends AbstractDataRequest<Recommendations> {

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
  public Recommendations execute() throws
    IOException,
    SpotifyWebApiException,
    ParseException {
    return new Recommendations.JsonUtil().createModelObject(getJson());
  }

  /**
   * Builder class for building a {@link GetRecommendationsRequest}.
   */
  public static final class Builder extends AbstractDataRequest.Builder<Recommendations, Builder> {

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
     * @param maxAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                         high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxAcousticness(final Float maxAcousticness) {
      assert (maxAcousticness != null);
      assert (0.0 <= maxAcousticness && maxAcousticness <= 1.0);
      return setQueryParameter("max_acousticness", maxAcousticness);
    }

    /**
     * The maximum danceability setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                         musical elements including tempo, rhythm stability, beat strength, and overall
     *                         regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxDanceability(final Float maxDanceability) {
      assert (maxDanceability != null);
      assert (0.0 <= maxDanceability && maxDanceability <= 1.0);
      return setQueryParameter("max_danceability", maxDanceability);
    }

    /**
     * The maximum duration setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxDurationMs The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxDurationMs(final Integer maxDurationMs) {
      assert (maxDurationMs != null);
      return setQueryParameter("max_duration_ms", maxDurationMs);
    }

    /**
     * The maximum energy setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                   activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                   has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                   to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                   entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxEnergy(final Float maxEnergy) {
      assert (maxEnergy != null);
      assert (0.0 <= maxEnergy && maxEnergy <= 1.0);
      return setQueryParameter("max_energy", maxEnergy);
    }

    /**
     * The maximum instrumentalness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                             instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                             closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                             contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                             seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxInstrumentalness(final Float maxInstrumentalness) {
      assert (maxInstrumentalness != null);
      assert (0.0 <= maxInstrumentalness && maxInstrumentalness <= 1.0);
      return setQueryParameter("max_instrumentalness", maxInstrumentalness);
    }

    /**
     * The maximum key setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxKey The key the track is in. Integers map to pitches using standard
     *                <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxKey(final Integer maxKey) {
      assert (maxKey != null);
      assert (0 <= maxKey && maxKey <= 11);
      return setQueryParameter("max_key", maxKey);
    }

    /**
     * The maximum liveness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                     increased probability that the track was performed live. A value above 0.8 provides strong
     *                     likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxLiveness(final Float maxLiveness) {
      assert (maxLiveness != null);
      assert (0.0 <= maxLiveness && maxLiveness <= 1.0);
      return setQueryParameter("max_liveness", maxLiveness);
    }

    /**
     * The maximum loudness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                     entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                     quality of a sound that is the primary psychological correlate of physical strength
     *                     (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxLoudness(final Float maxLoudness) {
      assert (maxLoudness != null);
      return setQueryParameter("max_loudness", maxLoudness);
    }

    /**
     * The maximum mode setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxmode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                 content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxmode(final Integer maxmode) {
      assert (maxmode != null);
      assert (maxmode == 0 || maxmode == 1);
      return setQueryParameter("max_mode", maxmode);
    }

    /**
     * The maximum popularity setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxPopularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                       popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                       total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxPopularity(final Integer maxPopularity) {
      assert (maxPopularity != null);
      assert (0 <= maxPopularity && maxPopularity <= 100);
      return setQueryParameter("max_popularity", maxPopularity);
    }

    /**
     * The maximum speechiness setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                        speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                        attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                        spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both
     *                        music and speech, either in sections or layered, including such cases as rap music. Values
     *                        below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxSpeechiness(final Float maxSpeechiness) {
      assert (maxSpeechiness != null);
      assert (0.0 <= maxSpeechiness && maxSpeechiness <= 1.0);
      return setQueryParameter("max_speechiness", maxSpeechiness);
    }

    /**
     * The maximum tempo setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                  is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxTempo(final Float maxTempo) {
      assert (maxTempo != null);
      assert (maxTempo >= 0);
      return setQueryParameter("max_tempo", maxTempo);
    }

    /**
     * The maximum time signature setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxTimeSignature An estimated overall time signature of a track. The time signature (meter) is a
     *                           notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxTimeSignature(final Integer maxTimeSignature) {
      assert (maxTimeSignature != null);
      return setQueryParameter("max_time_signature", maxTimeSignature);
    }

    /**
     * The maximum valence setter. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                    high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                    valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxValence(final Float maxValence) {
      assert (maxValence != null);
      assert (0.0 <= maxValence && maxValence <= 1.0);
      return setQueryParameter("max_valence", maxValence);
    }

    /**
     * The minimum acousticness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                         high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minAcousticness(final Float minAcousticness) {
      assert (minAcousticness != null);
      assert (0.0 <= minAcousticness && minAcousticness <= 1.0);
      return setQueryParameter("minAcousticness", minAcousticness);
    }

    /**
     * The minimum danceability setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                         musical elements including tempo, rhythm stability, beat strength, and overall
     *                         regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minDanceability(final Float minDanceability) {
      assert (minDanceability != null);
      assert (0.0 <= minDanceability && minDanceability <= 1.0);
      return setQueryParameter("min_danceability", minDanceability);
    }

    /**
     * The minimum duration setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minDurationMs The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minDurationMs(final Integer minDurationMs) {
      assert (minDurationMs != null);
      return setQueryParameter("min_duration_ms", minDurationMs);
    }

    /**
     * The minimum energy setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                   activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                   has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                   to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                   entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minEnergy(final Float minEnergy) {
      assert (minEnergy != null);
      assert (0.0 <= minEnergy && minEnergy <= 1.0);
      return setQueryParameter("min_energy", minEnergy);
    }

    /**
     * The minimum instrumentalness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                             instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                             closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                             contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                             seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minInstrumentalness(final Float minInstrumentalness) {
      assert (minInstrumentalness != null);
      assert (0.0 <= minInstrumentalness && minInstrumentalness <= 1.0);
      return setQueryParameter("min_instrumentalness", minInstrumentalness);
    }

    /**
     * The minimum key setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minKey The key the track is in. Integers map to pitches using standard
     *                <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minKey(final Integer minKey) {
      assert (minKey != null);
      assert (0 <= minKey && minKey <= 11);
      return setQueryParameter("min_key", minKey);
    }

    /**
     * The minimum liveness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                     increased probability that the track was performed live. A value above 0.8 provides strong
     *                     likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minLiveness(final Float minLiveness) {
      assert (minLiveness != null);
      assert (0.0 <= minLiveness && minLiveness <= 1.0);
      return setQueryParameter("min_liveness", minLiveness);
    }

    /**
     * The minimum loudness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                     entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                     quality of a sound that is the primary psychological correlate of physical strength
     *                     (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minLoudness(final Float minLoudness) {
      assert (minLoudness != null);
      return setQueryParameter("min_loudness", minLoudness);
    }

    /**
     * The minimum mode setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minMode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                 content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minMode(final Integer minMode) {
      assert (minMode != null);
      assert (minMode == 0 || minMode == 1);
      return setQueryParameter("min_mode", minMode);
    }

    /**
     * The minimum popularity setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minPopularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                       popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                       total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minPopularity(final Integer minPopularity) {
      assert (minPopularity != null);
      assert (0 <= minPopularity && minPopularity <= 100);
      return setQueryParameter("min_popularity", minPopularity);
    }

    /**
     * The minimum speechiness setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                        speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                        attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                        spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both
     *                        music and speech, either in sections or layered, including such cases as rap music. Values
     *                        below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minSpeechiness(final Float minSpeechiness) {
      assert (minSpeechiness != null);
      assert (0.0 <= minSpeechiness && minSpeechiness <= 1.0);
      return setQueryParameter("min_speechiness", minSpeechiness);
    }

    /**
     * The minimum tempo setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                  is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minTempo(final Float minTempo) {
      assert (minTempo != null);
      assert (minTempo >= 0);
      return setQueryParameter("min_tempo", minTempo);
    }

    /**
     * The minimum time signature setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minTimeSignature An estimated overall time signature of a track. The time signature (meter) is a
     *                           notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minTimeSignature(final Integer minTimeSignature) {
      assert (minTimeSignature != null);
      return setQueryParameter("min_time_signature", minTimeSignature);
    }

    /**
     * The minimum valence setter. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                    high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                    valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minValence(final Float minValence) {
      assert (minValence != null);
      assert (0.0 <= minValence && minValence <= 1.0);
      return setQueryParameter("min_valence", minValence);
    }

    /**
     * The seed artists setter.
     *
     * @param seedArtists A comma separated list of Spotify IDs for seed seed_artists. Up to 5 seed values may be
     *                     provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seedArtists(final String seedArtists) {
      assert (seedArtists != null);
      assert (seedArtists.split(",").length <= 5);
      return setQueryParameter("seed_artists", seedArtists);
    }

    /**
     * The seed genres setter.
     *
     * @param seedGenres A comma separated list of any seed_genres in the set of available genre seeds. Up to 5 seed
     *                    values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seedGenres(final String seedGenres) {
      assert (seedGenres != null);
      assert (seedGenres.split(",").length <= 5);
      return setQueryParameter("seed_genres", seedGenres);
    }

    /**
     * The seed tracks setter.
     *
     * @param seedTracks A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in
     *                    any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     * @see <a href="https://developer.spotify.com/web-api/user-guide/#spotify-uris-and-ids">Spotify URIs &amp; IDs</a>
     */
    public Builder seedTracks(final String seedTracks) {
      assert (seedTracks != null);
      assert (seedTracks.split(",").length <= 5);
      return setQueryParameter("seed_tracks", seedTracks);
    }

    /**
     * The target acousticness setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param targetAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                            high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetAcousticness(final Float targetAcousticness) {
      assert (targetAcousticness != null);
      assert (0.0 <= targetAcousticness && targetAcousticness <= 1.0);
      return setQueryParameter("target_acousticness", targetAcousticness);
    }

    /**
     * The target danceability setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param targetDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                            musical elements including tempo, rhythm stability, beat strength, and overall
     *                            regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetDanceability(final Float targetDanceability) {
      assert (targetDanceability != null);
      assert (0.0 <= targetDanceability && targetDanceability <= 1.0);
      return setQueryParameter("target_danceability", targetDanceability);
    }

    /**
     * The target duration setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetDurationMs The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetDurationMs(final Integer targetDurationMs) {
      assert (targetDurationMs != null);
      return setQueryParameter("target_duration_ms", targetDurationMs);
    }

    /**
     * The target energy setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                      activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death
     *                      metal has high energy, while a Bach prelude scores low on the scale. Perceptual features
     *                      contributing to this attribute include dynamic range, perceived loudness, timbre, onset
     *                      rate, and general entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetEnergy(final Float targetEnergy) {
      assert (targetEnergy != null);
      assert (0.0 <= targetEnergy && targetEnergy <= 1.0);
      return setQueryParameter("target_energy", targetEnergy);
    }

    /**
     * The target instrumentalness setter. Tracks with the attribute value nearest to the target value will be
     * preferred. All target values will be weighed equally in ranking results.
     *
     * @param targetInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                                instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal".
     *                                The closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                                contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                                seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetInstrumentalness(final Float targetInstrumentalness) {
      assert (targetInstrumentalness != null);
      assert (0.0 <= targetInstrumentalness && targetInstrumentalness <= 1.0);
      return setQueryParameter("target_instrumentalness", targetInstrumentalness);
    }

    /**
     * The target key setter. Tracks with the attribute value nearest to the target value will be preferred. All target
     * values will be weighed equally in ranking results.
     *
     * @param targetKey The key the track is in. Integers map to pitches using standard
     *                   <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                   C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetKey(final Integer targetKey) {
      assert (targetKey != null);
      assert (0 <= targetKey && targetKey <= 11);
      return setQueryParameter("target_key", targetKey);
    }

    /**
     * The target liveness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                        increased probability that the track was performed live. A value above 0.8 provides strong
     *                        likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetLiveness(final Float targetLiveness) {
      assert (targetLiveness != null);
      assert (0.0 <= targetLiveness && targetLiveness <= 1.0);
      return setQueryParameter("target_liveness", targetLiveness);
    }

    /**
     * The target loudness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                        entire track and are useful for comparing relative loudness of seed_tracks. Loudness is
     *                        the quality of a sound that is the primary psychological correlate of physical strength
     *                        (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetLoudness(final Float targetLoudness) {
      assert (targetLoudness != null);
      return setQueryParameter("target_loudness", targetLoudness);
    }

    /**
     * The target mode setter. Tracks with the attribute value nearest to the target value will be preferred. All target
     * values will be weighed equally in ranking results.
     *
     * @param targetMode Mode indicates the modality (major or minor) of a track, the type of scale from which its
     *                    melodic content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetMode(final Integer targetMode) {
      assert (targetMode != null);
      assert (targetMode == 0 || targetMode == 1);
      return setQueryParameter("target_mode", targetMode);
    }

    /**
     * The target popularity setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetPopularity The value will be between 0 and 100, with 100 being the most
     *                          popular. The popularity is calculated by algorithm and is based, in the most part, on
     *                          the total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetPopularity(final Integer targetPopularity) {
      assert (targetPopularity != null);
      assert (0 <= targetPopularity && targetPopularity <= 100);
      return setQueryParameter("target_popularity", targetPopularity);
    }

    /**
     * The target speechiness setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                           speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                           attribute value. Values above 0.66 describe seed_tracks that are probably made entirely
     *                           of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain
     *                           both music and speech, either in sections or layered, including such cases as rap music.
     *                           Values below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetSpeechiness(final Float targetSpeechiness) {
      assert (targetSpeechiness != null);
      assert (0.0 <= targetSpeechiness && targetSpeechiness <= 1.0);
      return setQueryParameter("target_speechiness", targetSpeechiness);
    }

    /**
     * The target tempo setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology,
     *                     tempo is the speed or pace of a given piece and derives directly from the average beat
     *                     duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetTempo(final Float targetTempo) {
      assert (targetTempo != null);
      assert (targetTempo >= 0);
      return setQueryParameter("target_tempo", targetTempo);
    }

    /**
     * The target time signature setter. Tracks with the attribute value nearest to the target value will be preferred.
     * All target values will be weighed equally in ranking results.
     *
     * @param targetTimeSignature An estimated overall time signature of a track. The time signature (meter) is a
     *                              notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetTimeSignature(final Integer targetTimeSignature) {
      assert (targetTimeSignature != null);
      return setQueryParameter("target_time_signature", targetTimeSignature);
    }

    /**
     * The target valence setter. Tracks with the attribute value nearest to the target value will be preferred. All
     * target values will be weighed equally in ranking results.
     *
     * @param targetValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks
     *                       with high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks
     *                       with low valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetValence(final Float targetValence) {
      assert (targetValence != null);
      assert (0.0 <= targetValence && targetValence <= 1.0);
      return setQueryParameter("target_valence", targetValence);
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

    @Override
    protected Builder self() {
      return this;
    }
  }
}
