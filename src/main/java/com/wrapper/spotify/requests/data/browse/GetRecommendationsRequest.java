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
     * Optional. The target size of the list of recommended tracks. For seeds with unusually small pools or when highly
     * restrictive filtering is applied, it may be impossible to generate the requested number of recommended tracks.
     * Debugging information for such cases is available in the response. Default: 20. Minimum: 1. Maximum: 100.
     */
    public Builder limit(final Integer limit) {
      assert (limit > 0 && limit <= 100);
      return setFormParameter("limit", limit);
    }

    /**
     * Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track Relinking.
     * Because min_*, max_* and target_* are applied to pools before relinking, the generated results may not precisely
     * match the filters applied. Original, non-relinked tracks are available via the linked_from attribute of the
     * relinked track response.
     */
    public Builder market(final CountryCode market) {
      assert (market != null);
      return setFormParameter("market", market.toString());
    }

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track
     * is acoustic.
     */
    public Builder maxAcousticness(final Float maxAcousticness) {
      assert (maxAcousticness >= 0 && maxAcousticness <= 1);
      return setFormParameter("max_acousticness", maxAcousticness);
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements including
     * tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most
     * danceable.
     */
    public Builder maxDanceability(final Float maxDanceability) {
      assert (maxDanceability >= 0 && maxDanceability <= 1);
      return setFormParameter("max_danceability", maxDanceability);
    }

    /**
     * The duration of the track in milliseconds.
     */
    public Builder maxDuration_ms(final Integer maxDuration_ms) {
      return setFormParameter("max_duration_ms", maxDuration_ms);
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically,
     * energetic seed_tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude
     * scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived
     * loudness, timbre, onset rate, and general entropy.
     */
    public Builder maxEnergy(final Float maxEnergy) {
      assert (maxEnergy >= 0 && maxEnergy <= 1);
      return setFormParameter("max_energy", maxEnergy);
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word seed_tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater
     * likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental
     * seed_tracks, but confidence is higher as the value approaches 1.0.
     */
    public Builder maxInstrumentalness(final Float maxInstrumentalness) {
      assert (maxInstrumentalness >= 0 && maxInstrumentalness <= 1);
      return setFormParameter("max_instrumentalness", maxInstrumentalness);
    }

    /**
     * The key the track is in. Integers map to pitches using standard
     * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 = C♯/D♭, 2 = D, and
     * so on.
     */
    public Builder maxKey(final Integer maxKey) {
      assert (maxKey >= 0 && maxKey <= 11);
      return setFormParameter("max_key", maxKey);
    }

    /**
     * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability
     * that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
     */
    public Builder maxLiveness(final Float maxLiveness) {
      assert (maxLiveness >= 0 && maxLiveness <= 1);
      return setFormParameter("max_liveness", maxLiveness);
    }

    /**
     * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are
     * useful for comparing relative loudness of seed_tracks. Loudness is the quality of a sound that is the primary
     * psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
     */
    public Builder maxLoudness(final Float maxLoudness) {
      return setFormParameter("max_loudness", maxLoudness);
    }

    /**
     * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is
     * derived.
     * Major is represented by 1 and minor is 0.
     */
    public Builder maxMode(final Integer maxMode) {
      assert (maxMode == 0 || maxMode <= 1);
      return setFormParameter("max_mode", maxMode);
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular. The popularity
     * is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how
     * recent those plays are.
     */
    public Builder maxPopularity(final Integer maxPopularity) {
      assert (maxPopularity >= 0 && maxPopularity <= 100);
      return setFormParameter("max_popularity", maxPopularity);
    }

    /**
     * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g.
     * talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe seed_tracks
     * that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may
     * contain both music and speech, either in sections or layered, including such cases as rap music. Values below
     * 0.33 most likely represent music and other non-speech-like seed_tracks.
     */
    public Builder maxSpeechiness(final Float maxSpeechiness) {
      assert (maxSpeechiness >= 0 && maxSpeechiness <= 1);
      return setFormParameter("max_speechiness", maxSpeechiness);
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or
     * pace of a given piece and derives directly from the average beat duration.
     */
    public Builder maxTempo(final Float maxTempo) {
      assert (maxTempo >= 0);
      return setFormParameter("max_tempo", maxTempo);
    }

    /**
     * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify
     * how many beats are in each bar (or measure).
     */
    public Builder maxTime_signature(final Integer maxTime_signature) {
      return setFormParameter("max_time_signature", maxTime_signature);
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound
     * more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low valence sound more negative (e.g. sad, depressed, angry).
     */
    public Builder maxValence(final Float maxValence) {
      assert (maxValence >= 0 && maxValence <= 1);
      return setFormParameter("max_valence", maxValence);
    }

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track
     * is acoustic.
     */
    public Builder minAcousticness(final Float minAcousticness) {
      assert (minAcousticness >= 0 && minAcousticness <= 1);
      return setFormParameter("min_acousticness", minAcousticness);
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements including
     * tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most
     * danceable.
     */
    public Builder minDanceability(final Float minDanceability) {
      assert (minDanceability >= 0 && minDanceability <= 1);
      return setFormParameter("min_danceability", minDanceability);
    }

    /**
     * The duration of the track in milliseconds.
     */
    public Builder minDuration_ms(final Integer minDuration_ms) {
      return setFormParameter("min_duration_ms", minDuration_ms);
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically,
     * energetic seed_tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude
     * scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived
     * loudness, timbre, onset rate, and general entropy.
     */
    public Builder minEnergy(final Float minEnergy) {
      assert (minEnergy >= 0 && minEnergy <= 1);
      return setFormParameter("min_energy", minEnergy);
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word seed_tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater
     * likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental
     * seed_tracks, but confidence is higher as the value approaches 1.0.
     */
    public Builder minInstrumentalness(final Float minInstrumentalness) {
      assert (minInstrumentalness >= 0 && minInstrumentalness <= 1);
      return setFormParameter("min_instrumentalness", minInstrumentalness);
    }

    /**
     * The key the track is in. Integers map to pitches using standard
     * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 = C♯/D♭, 2 = D, and
     * so on.
     */
    public Builder minKey(final Integer minKey) {
      assert (minKey >= 0 && minKey <= 11);
      return setFormParameter("min_key", minKey);
    }

    /**
     * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability
     * that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
     */
    public Builder minLiveness(final Float minLiveness) {
      assert (minLiveness >= 0 && minLiveness <= 1);
      return setFormParameter("min_liveness", minLiveness);
    }

    /**
     * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are
     * useful for comparing relative loudness of seed_tracks. Loudness is the quality of a sound that is the primary
     * psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
     */
    public Builder minLoudness(final Float minLoudness) {
      return setFormParameter("min_loudness", minLoudness);
    }

    /**
     * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is
     * derived.
     * Major is represented by 1 and minor is 0.
     */
    public Builder minMode(final Integer minMode) {
      assert (minMode == 0 || minMode <= 1);
      return setFormParameter("min_mode", minMode);
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular. The popularity
     * is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how
     * recent those plays are.
     */
    public Builder minPopularity(final Integer minPopularity) {
      assert (minPopularity >= 0 && minPopularity <= 100);
      return setFormParameter("min_popularity", minPopularity);
    }

    /**
     * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g.
     * talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe seed_tracks
     * that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may
     * contain both music and speech, either in sections or layered, including such cases as rap music. Values below
     * 0.33 most likely represent music and other non-speech-like seed_tracks.
     */
    public Builder minSpeechiness(final Float minSpeechiness) {
      assert (minSpeechiness >= 0 && minSpeechiness <= 1);
      return setFormParameter("min_speechiness", minSpeechiness);
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or
     * pace of a given piece and derives directly from the average beat duration.
     */
    public Builder minTempo(final Float minTempo) {
      assert (minTempo >= 0);
      return setFormParameter("min_tempo", minTempo);
    }

    /**
     * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify
     * how many beats are in each bar (or measure).
     */
    public Builder minTime_signature(final Integer minTime_signature) {
      return setFormParameter("min_time_signature", minTime_signature);
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound
     * more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low valence sound more negative (e.g. sad,
     * depressed, angry).
     */
    public Builder minValence(final Float minValence) {
      assert (minValence >= 0 && minValence <= 1);
      return setFormParameter("min_valence", minValence);
    }

    /**
     * A comma separated list of Spotify IDs for seed seed_artists. Up to 5 seed values may be provided in any
     * combination of seed_artists, seed_tracks and seed_genres.
     */
      return setFormParameter("seed_artists", Joiner.on(",").join(artists));
    public Builder seed_artists(String... artists) {
    }

    /**
     * A comma separated list of any seed_genres in the set of available genre seeds. Up to 5 seed values may be
     * provided in any combination of seed_artists, seed_tracks and seed_genres.
     */
      return setFormParameter("seed_genres", Joiner.on(",").join(seed_genres));
    public Builder seed_genres(String... seed_genres) {
    }

    /**
     * A comma separated list of Spotify IDs for a seed track. Up to 5 seed values may be provided in any combination of
     * seed_artists, seed_tracks and seed_genres.
     */
      return setFormParameter("seed_tracks", Joiner.on(",").join(seed_tracks));
    public Builder seed_tracks(String... seed_tracks) {
    }

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track
     * is acoustic.
     */
    public Builder targetAcousticness(final Float targetAcousticness) {
      assert (targetAcousticness >= 0 && targetAcousticness <= 1);
      return setFormParameter("target_acousticness", targetAcousticness);
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements including
     * tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most
     * danceable.
     */
    public Builder targetDanceability(final Float targetDanceability) {
      assert (targetDanceability >= 0 && targetDanceability <= 1);
      return setFormParameter("target_danceability", targetDanceability);
    }

    /**
     * The duration of the track in milliseconds.
     */
    public Builder targetDuration_ms(final Integer targetDuration_ms) {
      return setFormParameter("target_duration_ms", targetDuration_ms);
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically,
     * energetic seed_tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude
     * scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived
     * loudness, timbre, onset rate, and general entropy.
     */
    public Builder targetEnergy(final Float targetEnergy) {
      assert (targetEnergy >= 0 && targetEnergy <= 1);
      return setFormParameter("target_energy", targetEnergy);
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word seed_tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater
     * likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental
     * seed_tracks, but confidence is higher as the value approaches 1.0.
     */
    public Builder targetInstrumentalness(final Float targetInstrumentalness) {
      assert (targetInstrumentalness >= 0 && targetInstrumentalness <= 1);
      return setFormParameter("target_instrumentalness", targetInstrumentalness);
    }

    /**
     * The key the track is in. Integers map to pitches using standard
     * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch Class notation</a>. E.g. 0 = C, 1 = C♯/D♭, 2 = D, and
     * so on.
     */
    public Builder targetKey(final Integer targetKey) {
      assert (targetKey >= 0 && targetKey <= 11);
      return setFormParameter("target_key", targetKey);
    }

    /**
     * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability
     * that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
     */
    public Builder targetLiveness(final Float targetLiveness) {
      assert (targetLiveness >= 0 && targetLiveness <= 1);
      return setFormParameter("target_liveness", targetLiveness);
    }

    /**
     * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are
     * useful for comparing relative loudness of seed_tracks. Loudness is the quality of a sound that is the primary
     * psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
     */
    public Builder targetLoudness(final Float targetLoudness) {
      return setFormParameter("target_loudness", targetLoudness);
    }

    /**
     * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is
     * derived.
     * Major is represented by 1 and minor is 0.
     */
    public Builder targetMode(final Integer targetMode) {
      assert (targetMode == 0 || targetMode <= 1);
      return setFormParameter("target_mode", targetMode);
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular. The popularity
     * is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how
     * recent those plays are.
     */
    public Builder targetPopularity(final Integer targetPopularity) {
      assert (targetPopularity >= 0 && targetPopularity <= 100);
      return setFormParameter("target_popularity", targetPopularity);
    }

    /**
     * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g.
     * talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe seed_tracks
     * that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe seed_tracks that may
     * contain both music and speech, either in sections or layered, including such cases as rap music. Values below
     * 0.33 most likely represent music and other non-speech-like seed_tracks.
     */
    public Builder targetSpeechiness(final Float targetSpeechiness) {
      assert (targetSpeechiness >= 0 && targetSpeechiness <= 1);
      return setFormParameter("target_speechiness", targetSpeechiness);
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or
     * pace of a given piece and derives directly from the average beat duration.
     */
    public Builder targetTempo(final Float targetTempo) {
      assert (targetTempo >= 0);
      return setFormParameter("target_tempo", targetTempo);
    }

    /**
     * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify
     * how many beats are in each bar (or measure).
     */
    public Builder targetTime_signature(final Integer targetTime_signature) {
      return setFormParameter("target_time_signature", targetTime_signature);
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound
     * more positive (e.g. happy, cheerful, euphoric), while seed_tracks with low valence sound more negative (e.g. sad,
     * depressed, angry).
     */
    public Builder targetValence(final Float targetValence) {
      assert (targetValence >= 0 && targetValence <= 1);
      return setFormParameter("target_valence", targetValence);
    }

    @Override
    public GetRecommendationsRequest build() {
      setPath("/v1/recommendations");
      return new GetRecommendationsRequest(this);
    }
  }
}
