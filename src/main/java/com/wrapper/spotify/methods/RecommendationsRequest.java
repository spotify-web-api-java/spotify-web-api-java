package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by doug on 6/16/16.
 */
public class RecommendationsRequest extends AbstractRequest {
    public RecommendationsRequest(Builder builder) {
        super(builder);
    }


    public SettableFuture<List<Track>> getAsync() {
        SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

        try {
            final String jsonString = getJson();
            final JSONObject jsonObject = JSONObject.fromObject(jsonString);

            tracksFuture.set(JsonUtil.createTracks(jsonObject));
        } catch (Exception e) {
            tracksFuture.setException(e);
        }

        return tracksFuture;
    }

    public List<Track> get() throws IOException, WebApiException {
        final String jsonString = getJson();
        final JSONObject jsonObject = JSONObject.fromObject(jsonString);

        return JsonUtil.createTracks(jsonObject);
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        public Builder() {
            path("/v1/recommendations");
        }

        public Builder seedTrack(String ... seedTracks) {
            return seedTrack(Lists.newArrayList(seedTracks));
        }

        public Builder seedTrack(List<String> seedTracks) {
            assert (seedTracks.size() > 0);
            return parameter("seed_tracks", Joiner.on(",").join(seedTracks));
        }

        public Builder seedArtists(String... seedArtists) {
            return seedArtists(Lists.newArrayList(seedArtists));
        }

        public Builder seedArtists(List<String> seedArtists) {
            assert (seedArtists.size() > 0);
            return parameter("seed_artists", Joiner.on(",").join(seedArtists));
        }

        public Builder seedGenre(String... seedGenre) {
            return seedGenre(Lists.newArrayList(seedGenre));
        }

        public Builder seedGenre(List<String> seedGenre) {
            assert (seedGenre.size() > 0);
            return parameter("seed_genres", Joiner.on(",").join(seedGenre));
        }

        public Builder acousticness(float acousticness){
            return parameter("acousticness", String.valueOf(acousticness));
        }

        public Builder danceability(float danceability){
            return parameter("danceability", String.valueOf(danceability));
        }

        public Builder durationMs(int duration_ms){
            return parameter("duration_ms", String.valueOf(duration_ms));
        }

        public Builder energy(float energy){
            return parameter("energy", String.valueOf(energy));
        }

        public Builder instrumentalness(float instrumentalness){
            return parameter("instrumentalness", String.valueOf(instrumentalness));
        }

        public Builder key(int key){
            return parameter("key", String.valueOf(key));
        }

        public Builder liveness(float liveness){
            return parameter("liveness", String.valueOf(liveness));
        }

        public Builder loudness(float loudness){
            return parameter("loudness", String.valueOf(loudness));
        }

        public Builder mode(int mode){
            return parameter("mode", String.valueOf(mode));
        }

        public Builder popularity(int popularity){
            return parameter("popularity", String.valueOf(popularity));
        }

        public Builder speechiness(float speechiness){
            return parameter("speechiness", String.valueOf(speechiness));
        }

        public Builder tempo(float tempo){
            return parameter("tempo", String.valueOf(tempo));
        }

        public Builder timeSignature(int timeSignature){
            return parameter("time_signature", String.valueOf(timeSignature));
        }

        public Builder valence(float valence){
            return parameter("valence", String.valueOf(valence));
        }




        public Builder market(String market) {
            assert (market != null);
            return parameter("market", market);
        }

        public Builder limit(int limit) {
            assert (limit > 0);
            return parameter("limit", String.valueOf(limit));
        }


        public Builder offset(int offset) {
            assert (offset >= 0);
            return parameter("offset", String.valueOf(offset));
        }

        public RecommendationsRequest build() {
            return new RecommendationsRequest(this);
        }

    }
}
