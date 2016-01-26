package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.PlaylistTrackPosition;
import com.wrapper.spotify.models.SnapshotResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class RemoveTrackFromPlaylistRequest extends AbstractRequest
{
    public RemoveTrackFromPlaylistRequest(Builder builder)
    {
        super(builder);
    }

    public SettableFuture<SnapshotResult> getAsync() {
        final SettableFuture<SnapshotResult> removeTrackFuture = SettableFuture.create();

        try {
            final String jsonString = deleteJson();
            removeTrackFuture.set(JsonUtil.createSnapshotResponse(jsonString));
        } catch (Exception e) {
            removeTrackFuture.setException(e);
        }

        return removeTrackFuture;
    }

    public SnapshotResult get() throws IOException, WebApiException
    {
        final String jsonString = deleteJson();
        return JsonUtil.createSnapshotResponse(jsonString);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractRequest.Builder<Builder> {

        private JSONObject jsonBody;

        public Builder tracks(List<PlaylistTrackPosition> playlistTrackPositions) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }

            final JSONArray jsonArrayTrackUri = new JSONArray();
            for (PlaylistTrackPosition playlistTrackPosition : playlistTrackPositions)
            {
                jsonArrayTrackUri.add(getJsonFromPlaylistTrackPosition(playlistTrackPosition));
            }
            jsonBody.put("tracks", jsonArrayTrackUri);
            return body(jsonBody);
        }

        public Builder snapshotId(String snapshotId) {
            if (jsonBody == null) {
                jsonBody = new JSONObject();
            }
            jsonBody.put("snapshot_id",String.valueOf(snapshotId));
            return body(jsonBody);
        }

        public RemoveTrackFromPlaylistRequest build() {
            header("Content-Type", "application/json");
            return new RemoveTrackFromPlaylistRequest(this);
        }

    }

    private static JSONObject getJsonFromPlaylistTrackPosition(PlaylistTrackPosition playlistTrackPosition)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uri", playlistTrackPosition.getUri());
        if (playlistTrackPosition.getPositions() != null && playlistTrackPosition.getPositions().length != 0)
        {
            JSONArray positionArray = new JSONArray();
            for (int pos : playlistTrackPosition.getPositions())
            {
                positionArray.add(pos);
            }
            jsonObject.put("positions", positionArray);
        }
        return jsonObject;
    }
}
