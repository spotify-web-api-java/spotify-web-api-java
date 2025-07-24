package se.michaelthelin.spotify.model_objects.miscellaneous;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonObject;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;

import java.util.Arrays;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-audio-analysis">
 * Audio Analysis objects</a> by building instances from this class. <br>
 * These objects contain a great amount of additional information to
 * {@link AudioFeatures} objects. <br><br>
 * <p>
 * <b>Note:</b> Audio Analysis data is created by the Software "Analyzer" by "The Echo Nest", which has been bought by
 * Spotify in the past. Since then, new Analyzer versions were created by Spotify but there is a lack of documentation
 * on the side of Spotify, so it is possible that this Javadoc page (and other Audio Analysis related Javadoc pages)
 * contains speculative information about a few of its corresponding methods.
 */
@JsonDeserialize(builder = AudioAnalysis.Builder.class)
public class AudioAnalysis extends AbstractModelObject {
  /** Array of bar intervals in the track. */
  private final AudioAnalysisMeasure[] bars;
  /** Array of beat intervals in the track. */
  private final AudioAnalysisMeasure[] beats;
  /** Metadata about the analysis process. */
  private final AudioAnalysisMeta meta;
  /** Array of section intervals in the track. */
  private final AudioAnalysisSection[] sections;
  /** Array of segment intervals in the track. */
  private final AudioAnalysisSegment[] segments;
  /** Array of tatum intervals in the track. */
  private final AudioAnalysisMeasure[] tatums;
  /** Overall track-level audio analysis. */
  private final AudioAnalysisTrack track;

  private AudioAnalysis(final Builder builder) {
    super(builder);

    this.bars = builder.bars;
    this.beats = builder.beats;
    this.meta = builder.meta;
    this.sections = builder.sections;
    this.segments = builder.segments;
    this.tatums = builder.tatums;
    this.track = builder.track;
  }

  /**
   * Get the list of bar markers, in seconds.  A bar (or measure) is a segment of time defined as a given number of
   * beats. Bar offsets also indicate downbeats, the first beat of the measure.
   *
   * @return The list of bar markers, in seconds.
   */
  public AudioAnalysisMeasure[] getBars() {
    return bars;
  }

  /**
   * Get the list of beat markers, in seconds. A beat is the basic time unit of a piece of music; for example, each tick
   * of a metronome. Beats are typically multiples of tatums.
   *
   * @return The list of beat markers, in seconds.
   */
  public AudioAnalysisMeasure[] getBeats() {
    return beats;
  }

  /**
   * Get the metadata of the analyzer software for the track.
   *
   * @return Analyze, compute, and track information.
   */
  public AudioAnalysisMeta getMeta() {
    return meta;
  }

  /**
   * Get the set of section markers, in seconds. Sections are defined by large variations in rhythm or timbre, e.g.
   * chorus, verse, bridge, guitar solo, etc. Each section contains its own descriptions of tempo, key, mode,
   * time_signature, and loudness
   *
   * @return The set of section markers, in seconds.
   */
  public AudioAnalysisSection[] getSections() {
    return sections;
  }

  /**
   * Get the set of sound entities (typically under a second) each relatively uniform in timbre and harmony.
   * Segments are characterized by their perceptual onsets and duration in seconds, loudness (dB), pitch and timbral
   * content.
   *
   * @return The set of sound entities (typically under a second) each relatively uniform in timbre and harmony.
   */
  public AudioAnalysisSegment[] getSegments() {
    return segments;
  }

  /**
   * Get the list of tatum markers, in seconds. Tatums represent the lowest regular pulse train that a listener
   * intuitively infers from the timing of perceived musical events (segments).
   *
   * @return Get the list of tatum markers, in seconds.
   */
  public AudioAnalysisMeasure[] getTatums() {
    return tatums;
  }

  /**
   * Get the track data of the audio analysis object.
   *
   * @return Track data of audio analysis object.
   */
  public AudioAnalysisTrack getTrack() {
    return track;
  }

  @Override
  public String toString() {
    return "AudioAnalysis(bars=" + Arrays.toString(bars) + ", beats=" + Arrays.toString(beats) + ", meta=" + meta
        + ", sections=" + Arrays.toString(sections) + ", segments=" + Arrays.toString(segments) + ", tatums="
        + Arrays.toString(tatums) + ", track=" + track + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link AudioAnalysis} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private AudioAnalysisMeasure[] bars;
    private AudioAnalysisMeasure[] beats;
    private AudioAnalysisMeta meta;
    private AudioAnalysisSection[] sections;
    private AudioAnalysisSegment[] segments;
    private AudioAnalysisMeasure[] tatums;
    private AudioAnalysisTrack track;

    /**
     * Default constructor.
     */
    public Builder() {
      super();
    }

    /**
     * Track bars setter.
     *
     * @param bars The list of bar markers, in seconds.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setBars(AudioAnalysisMeasure[] bars) {
      this.bars = bars;
      return this;
    }

    /**
     * The track beats setter.
     *
     * @param beats The list of beat markers, in seconds.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setBeats(AudioAnalysisMeasure[] beats) {
      this.beats = beats;
      return this;
    }

    /**
     * The anaylzer metadata setter.
     *
     * @param meta Analyze, compute, and track information.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setMeta(AudioAnalysisMeta meta) {
      this.meta = meta;
      return this;
    }

    /**
     * The track sections setter.
     *
     * @param sections The set of section markers, in seconds.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setSections(AudioAnalysisSection[] sections) {
      this.sections = sections;
      return this;
    }

    /**
     * The track segments setter.
     *
     * @param segments The set of sound entities (typically under a second) each relatively uniform in timbre and
     *                 harmony.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setSegments(AudioAnalysisSegment[] segments) {
      this.segments = segments;
      return this;
    }

    /**
     * The track tatums setter.
     *
     * @param tatums Get the list of tatum markers, in seconds.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setTatums(AudioAnalysisMeasure[] tatums) {
      this.tatums = tatums;
      return this;
    }

    /**
     * The track data setter.
     *
     * @param track Track data of audio analysis object.
     * @return An {@link AudioAnalysis.Builder}.
     */
    public Builder setTrack(AudioAnalysisTrack track) {
      this.track = track;
      return this;
    }

    @Override
    public AudioAnalysis build() {
      return new AudioAnalysis(this);
    }
  }

  /**
   * JsonUtil class for building {@link AudioAnalysis} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AudioAnalysis> {

    /**
     * Default constructor.
     */
    public JsonUtil() {
      super();
    }

    public AudioAnalysis createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AudioAnalysis.Builder()
        .setBars(
          hasAndNotNull(jsonObject, "bars")
            ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("bars"))
            : null)
        .setBeats(
          hasAndNotNull(jsonObject, "beats")
            ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("beats"))
            : null)
        .setMeta(
          hasAndNotNull(jsonObject, "meta")
            ? new AudioAnalysisMeta.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("meta"))
            : null)
        .setSections(
          hasAndNotNull(jsonObject, "sections")
            ? new AudioAnalysisSection.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("sections"))
            : null)
        .setSegments(
          hasAndNotNull(jsonObject, "segments")
            ? new AudioAnalysisSegment.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("segments"))
            : null)
        .setTatums(
          hasAndNotNull(jsonObject, "tatums")
            ? new AudioAnalysisMeasure.JsonUtil().createModelObjectArray(
            jsonObject.getAsJsonArray("tatums"))
            : null)
        .setTrack(
          hasAndNotNull(jsonObject, "track")
            ? new AudioAnalysisTrack.JsonUtil().createModelObject(
            jsonObject.getAsJsonObject("track"))
            : null)
        .build();
    }
  }
}
