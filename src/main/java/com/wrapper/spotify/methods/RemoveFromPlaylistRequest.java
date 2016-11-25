package com.wrapper.spotify.methods;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.WebApiException;

public class RemoveFromPlaylistRequest extends AbstractRequest {

	public RemoveFromPlaylistRequest(Builder builder) {
		super(builder);
	}
	
	public SettableFuture<String> getAsync() {
	    final SettableFuture<String> removeFromPlaylistFuture = SettableFuture.create();

	    final String response;
	    try {
	      response = deleteJson();
	      removeFromPlaylistFuture.set(response);
	    } catch (IOException e) {
	    	removeFromPlaylistFuture.setException(e);
	    } catch (WebApiException e) {
	    	removeFromPlaylistFuture.setException(e);
	    }

	    return removeFromPlaylistFuture;
	  }

	  public String get() throws IOException, WebApiException {
	    return deleteJson();
	  }
	
	public static Builder builder() {
	    return new Builder();
	  }

	  public static class Builder extends AbstractRequest.Builder<Builder> {

	    public Builder tracks(String snapshotId, List<Integer> positions) {
	      JSONObject jsonBody = new JSONObject();
	      jsonBody.put("positions", JSONArray.fromObject(positions));
	      jsonBody.put("snapshot_id", snapshotId);
	      	      
	      return body(jsonBody);
	    }

	    @Override
	    public RemoveFromPlaylistRequest build() {
	      return new RemoveFromPlaylistRequest(this);
	    }
	  }

}
