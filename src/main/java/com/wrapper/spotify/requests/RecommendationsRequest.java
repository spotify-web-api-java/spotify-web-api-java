package com.wrapper.spotify.requests;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.AlbumSimplified;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.model_objects.Track;

import java.io.IOException;

public class RecommendationsRequest extends AbstractRequest {

    private RecommendationsRequest(final Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Track[] get() throws
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
        return new Track.JsonUtil().createModelObjectArray(new JsonParser().parse(getJson()).getAsJsonObject().get("albums").getAsJsonArray());
    }

    public SettableFuture<Track[]> getAsync() throws
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
        return getAsync(new Track.JsonUtil().createModelObjectArray(new JsonParser().parse(getJson()).getAsJsonObject().get("albums").getAsJsonArray()));
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        private Builder addList(String parameterName, String[] list) {
            assert (list != null);
            String listParameter = Joiner.on(",").join(list);
            return setParameter(parameterName, listParameter);
        }

        /**
         * Optional. The target size of the list of recommended tracks.
         * For seeds with unusually small pools or when highly restrictive filtering is applied, it may be impossible
         * to generate the requested number of recommended tracks. Debugging information for such cases is available
         * in the response. Default: 20. Minimum: 1. Maximum: 100.
         */
        public Builder limit(int limit) {
            assert (limit > 0 && limit <= 100);
            return setParameter("limit", String.valueOf(limit));
        }

        /**
         * Optional. An ISO 3166-1 alpha-2 country code. Provide this parameter if you want to apply Track Relinking.
         * Because min_*, max_* and target_* are applied to pools before relinking, the generated results may not
         * precisely match the filters applied. Original, non-relinked tracks are available via the linked_from
         * attribute of the relinked track response.
         */
        public Builder market(String market) {
            assert (market != null);
            return setParameter("market", market);
        }

        /**
         * A comma separated list of Spotify IDs for seed artists.
         * Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
         */
        public Builder artists(String[] artists) {
            return addList("seed_artists", artists);
        }

        /**
         * A comma separated list of Spotify IDs for a seed track.
         * Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
         */
        public Builder tracks(String[] tracks) {
            return addList("seed_tracks", tracks);
        }

        /**
         * A comma separated list of any genres in the set of available genre seeds.
         * Up to 5 seed values may be provided in any combination of seed_artists, seed_tracks and seed_genres.
         */
        public Builder genres(String[] genres) {
            return addList("seed_genres", genres);
        }

        /**
         * Required. A valid access token from the Spotify Accounts service
         */
        public Builder accessToken(String accessToken) {
            return setHeaderParameter("Authorization", "Bearer " + accessToken);
        }


        /**
         * The key the track is in. Integers map to pitches using standard Pitch Class notation.
         * E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
         * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch class</a>
         * @param key 0 to 11
         */
        public Builder maxKey(int key){
            assert (key >= 0 && key <= 11);
            return setParameter("max_key", Integer.toString(key));
        }
        /**
         * The key the track is in. Integers map to pitches using standard Pitch Class notation.
         * E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
         * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch class</a>
         * @param key 0 to 11
         */
        public Builder minKey(int key){
            assert (key >= 0 && key <= 11);
            return setParameter("min_key", Integer.toString(key));
        }
        /**
         * The key the track is in. Integers map to pitches using standard Pitch Class notation.
         * E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
         * <a href="https://en.wikipedia.org/wiki/Pitch_class">Pitch class</a>
         * @param key 0 to 11
         */
        public Builder targetKey(int key){
            assert (key >= 0 && key <= 11);
            return setParameter("target_key", Integer.toString(key));
        }

        /**
         * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived.
         * Major is represented by 1 and minor is 0.
         */
        public Builder maxMode(int mode){
            assert (mode == 0 || mode <= 1);
            return setParameter("max_mode", Integer.toString(mode));
        }
        /**
         * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived.
         * Major is represented by 1 and minor is 0.
         */
        public Builder minMode(int mode){
            assert (mode == 0 || mode <= 1);
            return setParameter("min_mode", Integer.toString(mode));
        }
        /**
         * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived.
         * Major is represented by 1 and minor is 0.
         */
        public Builder targetMode(int mode){
            assert (mode == 0 || mode <= 1);
            return setParameter("target_mode", Integer.toString(mode));
        }

        /**
         * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
         * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the
         * track has had and how recent those plays are.
         */
        public Builder maxPopularity(int popularity){
            assert (popularity >= 0 && popularity <= 100);
            return setParameter("max_popularity", Integer.toString(popularity));
        }
        /**
         * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
         * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the
         * track has had and how recent those plays are.
         */
        public Builder minPopularity(int popularity){
            assert (popularity >= 0 && popularity <= 100);
            return setParameter("min_popularity", Integer.toString(popularity));
        }
        /**
         * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
         * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the
         * track has had and how recent those plays are.
         */
        public Builder targetPopularity(int popularity){
            assert (popularity >= 0 && popularity <= 100);
            return setParameter("target_popularity", Integer.toString(popularity));
        }

        /**
         * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
         */
        public Builder maxTimeSignature(int time_signature){
            return setParameter("max_time_signature", Integer.toString(time_signature));
        }
        /**
         * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
         */
        public Builder minTimeSignature(int time_signature){
            return setParameter("min_time_signature", Integer.toString(time_signature));
        }
        /**
         * An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
         */
        public Builder targetTimeSignature(int time_signature){
            return setParameter("target_time_signature", Integer.toString(time_signature));
        }

        /**
         * The duration of the track in milliseconds.
         */
        public Builder maxDuration(int duration_ms){
            return setParameter("max_duration_ms", Integer.toString(duration_ms));
        }
        /**
         * The duration of the track in milliseconds.
         */
        public Builder minDuration(int duration_ms){
            return setParameter("min_duration_ms", Integer.toString(duration_ms));
        }
        /**
         * The duration of the track in milliseconds.
         */
        public Builder targetDuration(int duration_ms){
            return setParameter("target_duration_ms", Integer.toString(duration_ms));
        }

        /**
         * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
         */
        public Builder maxLiveness(float liveness){
            assert (liveness >= 0 && liveness <= 1);
            return setParameter("max_liveness", Float.toString(liveness));
        }
        /**
         * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
         */
        public Builder minLiveness(float liveness){
            assert (liveness >= 0 && liveness <= 1);
            return setParameter("min_liveness", Float.toString(liveness));
        }
        /**
         * Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
         */
        public Builder targetLiveness(float liveness){
            assert (liveness >= 0 && liveness <= 1);
            return setParameter("target_liveness", Float.toString(liveness));
        }

        /**
         * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
         */
        public Builder maxAcousticness(float acousticness){
            assert (acousticness >= 0 && acousticness <= 1);
            return setParameter("max_acousticness", Float.toString(acousticness));
        }
        /**
         * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
         */
        public Builder minAcousticness(float acousticness){
            assert (acousticness >= 0 && acousticness <= 1);
            return setParameter("min_acousticness", Float.toString(acousticness));
        }
        /**
         * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
         */
        public Builder targetAcousticness(float acousticness){
            assert (acousticness >= 0 && acousticness <= 1);
            return setParameter("target_acousticness", Float.toString(acousticness));
        }

        /**
         * Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
         */
        public Builder maxDanceability(float danceability){
            assert (danceability >= 0 && danceability <= 1);
            return setParameter("max_danceability", Float.toString(danceability));
        }
        /**
         * Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
         */
        public Builder minDanceability(float danceability){
            assert (danceability >= 0 && danceability <= 1);
            return setParameter("min_danceability", Float.toString(danceability));
        }
        /**
         * Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
         */
        public Builder targetDanceability(float danceability){
            assert (danceability >= 0 && danceability <= 1);
            return setParameter("target_danceability", Float.toString(danceability));
        }

        /**
         * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically, energetic tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
         */
        public Builder maxEnergy(float energy){
            assert (energy >= 0 && energy <= 1);
            return setParameter("max_energy", Float.toString(energy));
        }
        /**
         * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically, energetic tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
         */
        public Builder minEnergy(float energy){
            assert (energy >= 0 && energy <= 1);
            return setParameter("min_energy", Float.toString(energy));
        }
        /**
         * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically, energetic tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
         */
        public Builder targetEnergy(float energy){
            assert (energy >= 0 && energy <= 1);
            return setParameter("target_energy", Float.toString(energy));
        }

        /**
         * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
         */
        public Builder maxInstrumentalness(float instrumentalness){
            assert (instrumentalness >= 0 && instrumentalness <= 1);
            return setParameter("max_instrumentalness", Float.toString(instrumentalness));
        }
        /**
         * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
         */
        public Builder minInstrumentalness(float instrumentalness){
            assert (instrumentalness >= 0 && instrumentalness <= 1);
            return setParameter("min_instrumentalness", Float.toString(instrumentalness));
        }
        /**
         * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
         */
        public Builder targetInstrumentalness(float instrumentalness){
            assert (instrumentalness >= 0 && instrumentalness <= 1);
            return setParameter("target_instrumentalness", Float.toString(instrumentalness));
        }

        /**
         * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe tracks that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music. Values below 0.33 most likely represent music and other non-speech-like tracks.
         */
        public Builder maxSpeechiness(float speechiness){
            assert (speechiness >= 0 && speechiness <= 1);
            return setParameter("max_speechiness", Float.toString(speechiness));
        }
        /**
         * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe tracks that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music. Values below 0.33 most likely represent music and other non-speech-like tracks.
         */
        public Builder minSpeechiness(float speechiness){
            assert (speechiness >= 0 && speechiness <= 1);
            return setParameter("min_speechiness", Float.toString(speechiness));
        }
        /**
         * Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe tracks that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music. Values below 0.33 most likely represent music and other non-speech-like tracks.
         */
        public Builder targetSpeechiness(float speechiness){
            assert (speechiness >= 0 && speechiness <= 1);
            return setParameter("target_speechiness", Float.toString(speechiness));
        }


        /**
         * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are useful for comparing relative loudness of tracks. Loudness is the quality of a sound that is the primary psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
         */
        public Builder maxLoudness(float loudness){
            return setParameter("max_loudness", Float.toString(loudness));
        }
        /**
         * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are useful for comparing relative loudness of tracks. Loudness is the quality of a sound that is the primary psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
         */
        public Builder minLoudness(float loudness){
            return setParameter("min_loudness", Float.toString(loudness));
        }
        /**
         * The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are useful for comparing relative loudness of tracks. Loudness is the quality of a sound that is the primary psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
         */
        public Builder targetLoudness(float loudness){
            return setParameter("target_loudness", Float.toString(loudness));
        }

        /**
         * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry).
         */
        public Builder maxValence(float valence){
            assert (valence >= 0 && valence <= 1);
            return setParameter("max_valence", Float.toString(valence));
        }
        /**
         * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry).
         */
        public Builder minValence(float valence){
            assert (valence >= 0 && valence <= 1);
            return setParameter("min_valence", Float.toString(valence));
        }
        /**
         * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry).
         */
        public Builder targetValence(float valence){
            assert (valence >= 0 && valence <= 1);
            return setParameter("target_valence", Float.toString(valence));
        }

        /**
         * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
         */
        public Builder maxTempo(float tempo){
            assert (tempo >= 0);
            return setParameter("max_tempo", Float.toString(tempo));
        }
        /**
         * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
         */
        public Builder minTempo(float tempo){
            assert (tempo >= 0);
            return setParameter("min_tempo", Float.toString(tempo));
        }
        /**
         * The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
         */
        public Builder targetTempo(float tempo){
            assert (tempo >= 0);
            return setParameter("target_tempo", Float.toString(tempo));
        }

        @Override
        public RecommendationsRequest build() {
            setPath("/v1/recommendations");
            return new RecommendationsRequest(this);
        }
    }
}
