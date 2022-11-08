package se.michaelthelin.spotify.model_objects.special;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IModelObject;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.*;

import java.util.List;

/**
 * Includes tracks that are in the queue of the user for the upcoming playback.
 * <p>
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#playback-queue-object">
 *
 */
@JsonDeserialize(builder = CurrentlyPlaying.Builder.class)
public class PlaybackQueue extends AbstractModelObject{

  private final List<Track> queue;

  private PlaybackQueue(final Builder builder) {
    super(builder);
    this.queue = builder.queue;
  }

  /**
   * Get the tracks that are in the queue of the user for the upcoming playback.
   *
   * @return The tracks that are in the queue of the user for the upcoming playback.
   */
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

  /**
   * Builder class for building {@link PlaybackQueue} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {

    private List<Track> queue;

    /**
     * The tracks that are in the queue of the user for the upcoming playback setter.
     *
     * @param queue The tracks that are in the queue of the user for the upcoming playback.
     * @return A {@link PlaybackQueue.Builder}.
     */
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
   * JsonUtil class for building {@link PlaybackQueue} instances.
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
