package se.michaelthelin.spotify.model_objects.special;

import java.util.List;

import com.google.gson.JsonObject;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.IModelObject;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;

/**
 * Get the list of items that make up the user's queue.
 */
public class PlaybackQueue extends AbstractModelObject {
  /** The currently playing track or episode. */
  private final IPlaylistItem currentlyPlaying;
  /** The tracks or episodes in the queue. */
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

    public Builder() {
      super();
    }

    public Builder setCurrentlyPlaying(IPlaylistItem currentlyPlaying) {
      this.currentlyPlaying = currentlyPlaying;
      return this;
    }

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
   *
   * @deprecated Requests already return model objects. To read JSON obtained some other way, use
   *             {@link se.michaelthelin.spotify.model_objects.ModelObjects} instead. This will be removed
   *             in the next major version.
   */
  @Deprecated
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaybackQueue> {

    public JsonUtil() {
      super();
    }

    // Kept only so callers compiled against this override, whose erased return type differs from the
    // inherited one, still link. The model's JSON shape is handled by an adapter in ModelObjectGson.
    @Override
    public PlaybackQueue createModelObject(JsonObject jsonObject) {
      return super.createModelObject(jsonObject);
    }
  }
}
