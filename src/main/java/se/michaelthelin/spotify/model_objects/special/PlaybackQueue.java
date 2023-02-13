package se.michaelthelin.spotify.model_objects.special;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IModelObject;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.Episode;
import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * Get the list of items that make up the user's queue.
 */
@JsonDeserialize(builder = CurrentlyPlaying.Builder.class)
public class PlaybackQueue extends AbstractModelObject {
  private final IPlaylistItem currentlyPlaying;
  private final List<IPlaylistItem> queue;

  private PlaybackQueue(final Builder builder) {
    super(builder);
    this.currentlyPlaying = builder.currentlyPlaying;
    this.queue = builder.queue;
  }

  /**
   * Get the user's currently playing item.
   *
   * @return the user's currently playing item
   */
  public IPlaylistItem getCurrentlyPlaying() {
    return currentlyPlaying;
  }

  /**
   * Get the list of items that make up the user's queue.
   *
   * @return The items that are in the user's queue for the upcoming playback.
   */
  public List<IPlaylistItem> getQueue() {
    return queue;
  }

  @Override
  public String toString() {
    return "PlaybackQueue{" +
      "currentlyPlaying=" + currentlyPlaying +
      ", queue=" + queue +
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

    private IPlaylistItem currentlyPlaying;
    private List<IPlaylistItem> queue;

    /**
     * The item representing the user's currently playing item setter.
     *
     * @param currentlyPlaying The item representing the user's currently playing item.
     * @return A {@link PlaybackQueue.Builder}.
     */
    public Builder setCurrentlyPlaying(IPlaylistItem currentlyPlaying) {
      this.currentlyPlaying = currentlyPlaying;
      return this;
    }

    /**
     * The items that are in the user's queue for the upcoming playback setter.
     *
     * @param queue The items that are in the user's queue for the upcoming playback.
     * @return A {@link PlaybackQueue.Builder}.
     */
    public Builder setQueue(List<IPlaylistItem> queue) {
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

      IPlaylistItem currentlyPlaying = hasAndNotNull(jsonObject, "currently_playing")
        ? asPlaylistItem(jsonObject.getAsJsonObject("currently_playing"))
        : null;

      List<IPlaylistItem> queue = new ArrayList<>();
      if (hasAndNotNull(jsonObject, "queue")) {
        for (JsonElement jsonElement : jsonObject.getAsJsonArray("queue")) {
          IPlaylistItem queueItem = asPlaylistItem(jsonElement.getAsJsonObject());
          queue.add(queueItem);
        }
      }

      return new PlaybackQueue.Builder()
        .setCurrentlyPlaying(currentlyPlaying)
        .setQueue(queue)
        .build();
    }

    private IPlaylistItem asPlaylistItem(JsonObject trackObj) {
      IPlaylistItem item = null;
      if (hasAndNotNull(trackObj, "type")) {
        String type = trackObj.get("type").getAsString().toLowerCase();

        if (type.equals("track")) {
          item = new Track.JsonUtil().createModelObject(trackObj);
        } else if (type.equals("episode")) {
          item = new Episode.JsonUtil().createModelObject(trackObj);
        }
      }
      return item;
    }
  }
}
