package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.AudioFeature;
import net.sf.json.JSONObject;

import java.io.IOException;

public class AudioFeatureRequest extends AbstractRequest {


    public AudioFeatureRequest(Builder builder) {
        super(builder);
    }

    public SettableFuture<AudioFeature> getAsync() {
        SettableFuture<AudioFeature> audioFeatureFuture = SettableFuture.create();

        try {
            String jsonString = getJson();
            audioFeatureFuture.set(createAudioFeature(JSONObject.fromObject(jsonString)));
        } catch (Exception e) {
            audioFeatureFuture.setException(e);
        }

        return audioFeatureFuture;
    }

    public AudioFeature get() throws IOException, WebApiException {
        String jsonString = getJson();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return createAudioFeature(jsonObject);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        /**
         * The audio request with the given song id.
         *
         * @param id The id for the song.
         * @return AlbumRequest
         */
        public Builder id(String id) {
            assert (id != null);
            return path(String.format("/v1/audio-features/%s", id));
        }

        public AudioFeatureRequest build() {
            return new AudioFeatureRequest(this);
        }

    }


    private static AudioFeature createAudioFeature(JSONObject audioFeatureJson) {
        if (audioFeatureJson == null || audioFeatureJson.isNullObject()) {
            return null;
        }

        AudioFeature audioFeature = new AudioFeature();
        audioFeature.setDanceability(audioFeatureJson.getDouble("danceability"));
        audioFeature.setEnergy(audioFeatureJson.getDouble("energy"));
        audioFeature.setKey(audioFeatureJson.getInt("key"));
        audioFeature.setLoudness(audioFeatureJson.getDouble("loudness"));
        audioFeature.setMode(audioFeatureJson.getInt("mode"));
        audioFeature.setSpeechiness(audioFeatureJson.getDouble("speechiness"));
        audioFeature.setAcousticness(audioFeatureJson.getDouble("acousticness"));
        audioFeature.setInstrumentalness(audioFeatureJson.getDouble("instrumentalness"));
        audioFeature.setLiveness(audioFeatureJson.getDouble("liveness"));
        audioFeature.setValence(audioFeatureJson.getDouble("valence"));
        audioFeature.setTempo(audioFeatureJson.getDouble("tempo"));
        audioFeature.setType(audioFeatureJson.getString("type"));
        audioFeature.setId(audioFeatureJson.getString("id"));
        audioFeature.setUri(audioFeatureJson.getString("uri"));
        audioFeature.setTrackHref(audioFeatureJson.getString("track_href"));
        audioFeature.setAnalysisUrl(audioFeatureJson.getString("analysis_url"));
        audioFeature.setDurationMs(audioFeatureJson.getInt("duration_ms"));
        audioFeature.setTimeSignature(audioFeatureJson.getInt("time_signature"));


        return audioFeature;
    }

}
