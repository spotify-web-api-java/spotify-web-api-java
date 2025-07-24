package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/object-model/#play-history-object">
 * Play History objects</a> by building instances from this class.
 */
@JsonDeserialize(builder = PlayHistory.Builder.class)
public class PlayHistory extends AbstractModelObject {
  /** The track that was played. */
  private final Track track;
  /** The date and time the track was played. */
  private final Date playedAt;
  /** The context within which the track was played. */
  private final Context context;

  private PlayHistory(final Builder builder) {
    super(builder);

    this.track = builder.track;
    this.playedAt = builder.playedAt;
    this.context = builder.context;
  }

  /**
   * Get the track the user listened to.
   *
   * @return The track the user listened to.
   */
  public Track getTrack() {
    return track;
  }

  /**
   * Get the date and time the track was played.
   *
   * @return The date and time the track was played.
   */
  public Date getPlayedAt() {
    return playedAt;
  }

  /**
   * Get the context the track was played from.
   *
   * @return The context the track was played from.
   */
  public Context getContext() {
    return context;
  }

  @Override
  public String toString() {
    return "PlayHistory(track=" + track + ", playedAt=" + playedAt + ", context=" + context + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link PlayHistory} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Track track;
    private Date playedAt;
    private Context context;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * The track setter.
     *
     * @param track The track the user listened to.
     * @return A {@link PlayHistory.Builder}.
     */
    public Builder setTrack(Track track) {
      this.track = track;
      return this;
    }

    /**
     * The played at date setter.
     *
     * @param playedAt The date and time the track was played.
     * @return A {@link PlayHistory.Builder}.
     */
    public Builder setPlayedAt(Date playedAt) {
      this.playedAt = playedAt;
      return this;
    }

    /**
     * The context setter.
     *
     * @param context The context the track was played from.
     * @return A {@link PlayHistory.Builder}.
     */
    public Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    @Override
    public PlayHistory build() {
      return new PlayHistory(this);
    }
  }

  /**
   * JsonUtil class for building {@link PlayHistory} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlayHistory> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public PlayHistory createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      try {
        return new Builder()
          .setTrack(
            hasAndNotNull(jsonObject, "track")
              ? new Track.JsonUtil().createModelObject(
              jsonObject.getAsJsonObject("track"))
              : null)
          .setPlayedAt(
            hasAndNotNull(jsonObject, "played_at")
              ? SpotifyApi.parseDefaultDate(jsonObject.get("played_at").getAsString())
              : null)
          .setContext(
            hasAndNotNull(jsonObject, "context")
              ? new Context.JsonUtil().createModelObject(
              jsonObject.getAsJsonObject("context"))
              : null)
          .build();
      } catch (ParseException e) {
        SpotifyApi.LOGGER.log(Level.SEVERE, e.getMessage());
        return null;
      }
    }
  }
}
