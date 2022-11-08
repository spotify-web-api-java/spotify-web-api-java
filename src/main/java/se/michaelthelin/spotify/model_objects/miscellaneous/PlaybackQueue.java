package se.michaelthelin.spotify.model_objects.miscellaneous;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IModelObject;
import se.michaelthelin.spotify.model_objects.specification.*;

import java.util.List;

@JsonDeserialize(builder = CurrentlyPlaying.Builder.class)
public class PlaybackQueue extends AbstractModelObject{

  private final List<Track> queue;

  private PlaybackQueue(final Builder builder) {
    super(builder);
    this.queue = builder.queue;
  }

  public List<Track> getQueue() {
    return queue;
  }

  @Override
  public String toString() {
    return "PlaybackQueue{" +
      "queue=" + queue +
      '}';
  }

  @Override
  public IModelObject.Builder builder() {
    return new Builder();
  }


  public static final class Builder extends AbstractModelObject.Builder {

    private List<Track> queue;

    public Builder setQueue(List<Track> queue) {
      this.queue = queue;
      return this;
    }

    @Override
    public PlaybackQueue build() {
      return new PlaybackQueue(this);
    }
  }

  /**
   * JsonUtil class for building {@link CurrentlyPlaying} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaybackQueue> {
    public PlaybackQueue createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new PlaybackQueue.Builder()
        .setQueue(
          hasAndNotNull(jsonObject, "queue")
            ? List.of(new Track.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("queue")))
            : null)
        .build();
    }
  }
}
