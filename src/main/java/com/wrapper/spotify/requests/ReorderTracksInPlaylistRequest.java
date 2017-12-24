package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.SnapshotResult;
import net.sf.json.JSONObject;

import java.io.IOException;

public class ReorderTracksInPlaylistRequest extends AbstractRequest
{
    public ReorderTracksInPlaylistRequest(Builder builder)
    {
        super(builder);
    }

    public SettableFuture<SnapshotResult> getAsync() {
        final SettableFuture<SnapshotResult> removeTrackFuture = SettableFuture.create();

        try {
            final String jsonString = putJson();
            removeTrackFuture.set(JsonUtil.createSnapshotResponse(jsonString));
        } catch (Exception e) {
            removeTrackFuture.setException(e);
        }

        return removeTrackFuture;
    }

    public SnapshotResult get() throws IOException, WebApiException
    {
        final String jsonString = putJson();
        return JsonUtil.createSnapshotResponse(jsonString);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        private JSONObject jsonBody;

        public Builder rangeStart(int rangeStart) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }

            jsonBody.put("range_start", rangeStart);
            return body(jsonBody);
        }

        public Builder insertBefore(int insertBefore) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }

            jsonBody.put("insert_before", insertBefore);
            return body(jsonBody);
        }

        public Builder rangeLength(int rangeLength) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }

            jsonBody.put("range_length", rangeLength);
            return body(jsonBody);
        }

        public Builder snapshotId(String snapshotId) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }
            jsonBody.put("snapshot_id", String.valueOf(snapshotId));
            return body(jsonBody);
        }

        public ReorderTracksInPlaylistRequest build() {
            header("Content-Type", "application/json");
            return new ReorderTracksInPlaylistRequest(this);
        }

    }
}
