package se.michaelthelin.spotify.model_objects.specification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#resume-point-object">Resume Point objects</a>
 * by building instances from this class.
 */
@JsonDeserialize(builder = ResumePoint.Builder.class)
public class ResumePoint extends AbstractModelObject {
  private final Boolean fullyPlayed;
  private final Integer resumePositionMs;

  private ResumePoint(final Builder builder) {
    super(builder);
    this.fullyPlayed = builder.fullyPlayed;
    this.resumePositionMs = builder.resumePositionMs;
  }

  /**
   * Check whether the episode has been fully played by the user.
   *
   * @return If {@code true}, the episode has been fully played by the user.
   */
  public Boolean getFullyPlayed() {
    return fullyPlayed;
  }

  /**
   * Get the user’s most recent position in the episode in milliseconds.
   *
   * @return The user’s most recent position in the episode in milliseconds.
   */
  public Integer getResumePositionMs() {
    return resumePositionMs;
  }

  @Override
  public String toString() {
    return "ResumePoint(fullyPlayed=" + fullyPlayed + ", resumePositionMs=" + resumePositionMs + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link ResumePoint} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private Boolean fullyPlayed;
    private Integer resumePositionMs;

    /**
     * Set whether the episode has been fully played by the user.
     *
     * @param fullyPlayed {@code true} if episode has been fully played by the user.
     * @return A {@link ResumePoint.Builder}.
     */
    public Builder setFullyPlayed(Boolean fullyPlayed) {
      this.fullyPlayed = fullyPlayed;
      return this;
    }

    /**
     * Set the user’s most recent position in the episode in milliseconds.
     *
     * @param resumePositionMs The user’s most recent position in the episode in milliseconds.
     * @return A {@link ResumePoint.Builder}.
     */
    public Builder setResumePositionMs(Integer resumePositionMs) {
      this.resumePositionMs = resumePositionMs;
      return this;
    }

    @Override
    public ResumePoint build() {
      return new ResumePoint(this);
    }
  }

  /**
   * JsonUtil class for building {@link ResumePoint} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<ResumePoint> {
    @Override
    public ResumePoint createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder()
        .setFullyPlayed(
          hasAndNotNull(jsonObject, "fully_played")
            ? jsonObject.get("fully_played").getAsBoolean()
            : null)
        .setResumePositionMs(
          hasAndNotNull(jsonObject, "resume_position_ms")
            ? jsonObject.get("resume_position_ms").getAsInt()
            : null)
        .build();
    }
  }

}
