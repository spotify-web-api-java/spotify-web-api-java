package com.wrapper.spotify.requests.data.browse;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Recommendations;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetRecommendationsRequest extends AbstractRequest {

  private GetRecommendationsRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Recommendations get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Recommendations.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<Recommendations> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Recommendations.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * @param limit Optional. The target size of the list of recommended tracks. For seeds with unusually small pools or
     *              when highly restrictive filtering is applied, it may be impossible to generate the requested number
     *              of recommended tracks. Debugging information for such cases is available in the response. Default:
     *              20. Minimum: 1. Maximum: 100.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder limit(final Integer limit) {
      assert (limit > 0 && limit <= 100);
      return setQueryParameter("limit", limit);
    }

    /**
     * @param market Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track
     *               Relinking. Because min_*, max_* and target_* are applied to pools before relinking, the generated
     *               results may not precisely match the filters applied. Original, non-relinked tracks are available
     *               via the linked_from attribute of the relinked track response.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setQueryParameter("market", market);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high
     *                        confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxAcousticness(final Float maxAcousticness) {
      assert (maxAcousticness >= 0 && maxAcousticness <= 1);
      return setQueryParameter("max_acousticness", maxAcousticness);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                        musical elements including tempo, rhythm stability, beat strength, and overall regularity.
     *                        A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxDanceability(final Float maxDanceability) {
      assert (maxDanceability >= 0 && maxDanceability <= 1);
      return setQueryParameter("max_danceability", maxDanceability);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxDuration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxDuration_ms(final Integer maxDuration_ms) {
      return setQueryParameter("max_duration_ms", maxDuration_ms);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                  activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                  has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                  to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                  entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxEnergy(final Float maxEnergy) {
      assert (maxEnergy >= 0 && maxEnergy <= 1);
      return setQueryParameter("max_energy", maxEnergy);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                            instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                            closer the instrumentalness value is to 1.0, the greater likelihood the track contains
     *                            no vocal content. Values above 0.5 are intended to represent instrumental seed_tracks,
     *                            but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxInstrumentalness(final Float maxInstrumentalness) {
      assert (maxInstrumentalness >= 0 && maxInstrumentalness <= 1);
      return setQueryParameter("max_instrumentalness", maxInstrumentalness);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxKey The key the track is in. Integers map to pitches using standard
     *               <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 = C♯/D♭,
     *               2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxKey(final Integer maxKey) {
      assert (maxKey >= 0 && maxKey <= 11);
      return setQueryParameter("max_key", maxKey);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                    increased probability that the track was performed live. A value above 0.8 provides strong
     *                    likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxLiveness(final Float maxLiveness) {
      assert (maxLiveness >= 0 && maxLiveness <= 1);
      return setQueryParameter("max_liveness", maxLiveness);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                    entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                    quality of a sound that is the primary psychological correlate of physical strength
     *                    (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxLoudness(final Float maxLoudness) {
      return setQueryParameter("max_loudness", maxLoudness);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxMode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxMode(final Integer maxMode) {
      assert (maxMode == 0 || maxMode <= 1);
      return setQueryParameter("max_mode", maxMode);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxPopularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                      popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                      total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxPopularity(final Integer maxPopularity) {
      assert (maxPopularity >= 0 && maxPopularity <= 100);
      return setQueryParameter("max_popularity", maxPopularity);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                       speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                       attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                       spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both music
     *                       and speech, either in sections or layered, including such cases as rap music. Values below
     *                       0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxSpeechiness(final Float maxSpeechiness) {
      assert (maxSpeechiness >= 0 && maxSpeechiness <= 1);
      return setQueryParameter("max_speechiness", maxSpeechiness);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                 is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxTempo(final Float maxTempo) {
      assert (maxTempo >= 0);
      return setQueryParameter("max_tempo", maxTempo);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxTime_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                          notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxTime_signature(final Integer maxTime_signature) {
      return setQueryParameter("max_time_signature", maxTime_signature);
    }

    /**
     * Optional. A hard ceiling. Tracks with the attribute value above the maximum value will be omitted.
     *
     * @param maxValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                   high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                   valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder maxValence(final Float maxValence) {
      assert (maxValence >= 0 && maxValence <= 1);
      return setQueryParameter("max_valence", maxValence);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high
     *                        confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minAcousticness(final Float minAcousticness) {
      assert (minAcousticness >= 0 && minAcousticness <= 1);
      return setQueryParameter("min_acousticness", minAcousticness);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                        musical elements including tempo, rhythm stability, beat strength, and overall regularity.
     *                        A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minDanceability(final Float minDanceability) {
      assert (minDanceability >= 0 && minDanceability <= 1);
      return setQueryParameter("min_danceability", minDanceability);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minDuration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minDuration_ms(final Integer minDuration_ms) {
      return setQueryParameter("min_duration_ms", minDuration_ms);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                  activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death metal
     *                  has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing
     *                  to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general
     *                  entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minEnergy(final Float minEnergy) {
      assert (minEnergy >= 0 && minEnergy <= 1);
      return setQueryParameter("min_energy", minEnergy);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                            instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal". The
     *                            closer the instrumentalness value is to 1.0, the greater likelihood the track contains
     *                            no vocal content. Values above 0.5 are intended to represent instrumental seed_tracks,
     *                            but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minInstrumentalness(final Float minInstrumentalness) {
      assert (minInstrumentalness >= 0 && minInstrumentalness <= 1);
      return setQueryParameter("min_instrumentalness", minInstrumentalness);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minKey The key the track is in. Integers map to pitches using standard
     *               <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 = C♯/D♭,
     *               2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minKey(final Integer minKey) {
      assert (minKey >= 0 && minKey <= 11);
      return setQueryParameter("min_key", minKey);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                    increased probability that the track was performed live. A value above 0.8 provides strong
     *                    likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minLiveness(final Float minLiveness) {
      assert (minLiveness >= 0 && minLiveness <= 1);
      return setQueryParameter("min_liveness", minLiveness);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                    entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                    quality of a sound that is the primary psychological correlate of physical strength
     *                    (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minLoudness(final Float minLoudness) {
      return setQueryParameter("min_loudness", minLoudness);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minMode Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic
     *                content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minMode(final Integer minMode) {
      assert (minMode == 0 || minMode <= 1);
      return setQueryParameter("min_mode", minMode);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minPopularity The popularity of the track. The value will be between 0 and 100, with 100 being the most
     *                      popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                      total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minPopularity(final Integer minPopularity) {
      assert (minPopularity >= 0 && minPopularity <= 100);
      return setQueryParameter("min_popularity", minPopularity);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                       speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                       attribute value. Values above 0.66 describe seed_tracks that are probably made entirely of
     *                       spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both music
     *                       and speech, either in sections or layered, including such cases as rap music. Values below
     *                       0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minSpeechiness(final Float minSpeechiness) {
      assert (minSpeechiness >= 0 && minSpeechiness <= 1);
      return setQueryParameter("min_speechiness", minSpeechiness);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo
     *                 is the speed or pace of a given piece and derives directly from the average beat duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minTempo(final Float minTempo) {
      assert (minTempo >= 0);
      return setQueryParameter("min_tempo", minTempo);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minTime_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                          notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minTime_signature(final Integer minTime_signature) {
      return setQueryParameter("min_time_signature", minTime_signature);
    }

    /**
     * Optional. A hard floor. Tracks with the attribute value below the minimum value will be omitted.
     *
     * @param minValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with
     *                   high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low
     *                   valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder minValence(final Float minValence) {
      assert (minValence >= 0 && minValence <= 1);
      return setQueryParameter("min_valence", minValence);
    }

    /**
     * @param artists A comma separated list of Spotify IDs for seed seed_artists. Up to 5 seed values may be provided
     *                in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder seed_artists(String... artists) {
      return setQueryParameter("seed_artists", Joiner.on(",").join(artists));
    }

    /**
     * @param seed_genres A comma separated list of any seed_genres in the set of available genre seeds. Up to 5 seed
     *                    values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder seed_genres(String... seed_genres) {
      return setQueryParameter("seed_genres", Joiner.on(",").join(seed_genres));
    }

    /**
     * @param seed_tracks A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in
     *                    any combination of seed_artists, seed_tracks and seed_genres.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder seed_tracks(String... seed_tracks) {
      return setQueryParameter("seed_tracks", Joiner.on(",").join(seed_tracks));
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetAcousticness A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents
     *                           high confidence the track is acoustic.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetAcousticness(final Float targetAcousticness) {
      assert (targetAcousticness >= 0 && targetAcousticness <= 1);
      return setQueryParameter("target_acousticness", targetAcousticness);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetDanceability Danceability describes how suitable a track is for dancing based on a combination of
     *                           musical elements including tempo, rhythm stability, beat strength, and overall
     *                           regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetDanceability(final Float targetDanceability) {
      assert (targetDanceability >= 0 && targetDanceability <= 1);
      return setQueryParameter("target_danceability", targetDanceability);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetDuration_ms The duration of the track in milliseconds.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetDuration_ms(final Integer targetDuration_ms) {
      return setQueryParameter("target_duration_ms", targetDuration_ms);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetEnergy Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and
     *                     activity. Typically, energetic seed_tracks feel fast, loud, and noisy. For example, death
     *                     metal has high energy, while a Bach prelude scores low on the scale. Perceptual features
     *                     contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate,
     *                     and general entropy.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetEnergy(final Float targetEnergy) {
      assert (targetEnergy >= 0 && targetEnergy <= 1);
      return setQueryParameter("target_energy", targetEnergy);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetInstrumentalness Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as
     *                               instrumental in this context. Rap or spoken word seed_tracks are clearly "vocal".
     *                               The closer the instrumentalness value is to 1.0, the greater likelihood the track
     *                               contains no vocal content. Values above 0.5 are intended to represent instrumental
     *                               seed_tracks, but confidence is higher as the value approaches 1.0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetInstrumentalness(final Float targetInstrumentalness) {
      assert (targetInstrumentalness >= 0 && targetInstrumentalness <= 1);
      return setQueryParameter("target_instrumentalness", targetInstrumentalness);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetKey The key the track is in. Integers map to pitches using standard
     *                  <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 =
     *                  C♯/D♭, 2 = D, and so on.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetKey(final Integer targetKey) {
      assert (targetKey >= 0 && targetKey <= 11);
      return setQueryParameter("target_key", targetKey);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetLiveness Detects the presence of an audience in the recording. Higher liveness values represent an
     *                       increased probability that the track was performed live. A value above 0.8 provides strong
     *                       likelihood that the track is live.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetLiveness(final Float targetLiveness) {
      assert (targetLiveness >= 0 && targetLiveness <= 1);
      return setQueryParameter("target_liveness", targetLiveness);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetLoudness The overall loudness of a track in decibels (dB). Loudness values are averaged across the
     *                       entire track and are useful for comparing relative loudness of seed_tracks. Loudness is the
     *                       quality of a sound that is the primary psychological correlate of physical strength
     *                       (amplitude). Values typical range between -60 and 0 db.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetLoudness(final Float targetLoudness) {
      return setQueryParameter("target_loudness", targetLoudness);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetMode Mode indicates the modality (major or minor) of a track, the type of scale from which its
     *                   melodic content is derived. Major is represented by 1 and minor is 0.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetMode(final Integer targetMode) {
      assert (targetMode == 0 || targetMode <= 1);
      return setQueryParameter("target_mode", targetMode);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetPopularity The value will be between 0 and 100, with 100 being the most
     *                         popular. The popularity is calculated by algorithm and is based, in the most part, on the
     *                         total number of plays the track has had and how recent those plays are.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetPopularity(final Integer targetPopularity) {
      assert (targetPopularity >= 0 && targetPopularity <= 100);
      return setQueryParameter("target_popularity", targetPopularity);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetSpeechiness Speechiness detects the presence of spoken words in a track. The more exclusively
     *                          speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the
     *                          attribute value. Values above 0.66 describe seed_tracks that are probably made entirely
     *                          of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may contain both
     *                          music and speech, either in sections or layered, including such cases as rap music.
     *                          Values below 0.33 most likely represent music and other non-speech-like seed_tracks.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetSpeechiness(final Float targetSpeechiness) {
      assert (targetSpeechiness >= 0 && targetSpeechiness <= 1);
      return setQueryParameter("target_speechiness", targetSpeechiness);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetTempo The overall estimated tempo of a track in beats per minute (BPM). In musical terminology,
     *                    tempo is the speed or pace of a given piece and derives directly from the average beat
     *                    duration.
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetTempo(final Float targetTempo) {
      assert (targetTempo >= 0);
      return setQueryParameter("target_tempo", targetTempo);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetTime_signature An estimated overall time signature of a track. The time signature (meter) is a
     *                             notational convention to specify how many beats are in each bar (or measure).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetTime_signature(final Integer targetTime_signature) {
      return setQueryParameter("target_time_signature", targetTime_signature);
    }

    /**
     * Optional. Tracks with the attribute value nearest to the target value will be preferred. All target values will
     * be weighed equally in ranking results.
     *
     * @param targetValence A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks
     *                      with high valence sound more positive (e.g. happy, cheerful, euphoric), while seed_tracks
     *                      with low valence sound more negative (e.g. sad, depressed, angry).
     * @return A {@link GetRecommendationsRequest.Builder}.
     */
    public Builder targetValence(final Float targetValence) {
      assert (targetValence >= 0 && targetValence <= 1);
      return setQueryParameter("target_valence", targetValence);
    }

    @Override
    public GetRecommendationsRequest build() {
      setPath("/v1/recommendations");
      return new GetRecommendationsRequest(this);
    }
  }
}
