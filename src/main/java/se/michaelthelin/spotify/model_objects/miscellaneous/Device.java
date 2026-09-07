package se.michaelthelin.spotify.model_objects.miscellaneous;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

import java.util.Objects;

/**
 * Retrieve information about <a href="https://developer.spotify.com/documentation/web-api/reference/get-a-users-available-devices">Device
 * objects</a> by creating instances from this class.
 */
public class Device extends AbstractModelObject {
  /** The device ID. This may be null. */
  private final String id;
  /** If this device is the currently active device. */
  private final Boolean is_active;
  /** If this device is currently in a private session. */
  private final Boolean is_private_session;
  /** Whether controlling this device is restricted. */
  private final Boolean is_restricted;
  /** The name of the device. */
  private final String name;
  /** If this device can be used to set the volume. */
  private final Boolean supports_volume;
  /** Device type, such as "computer", "smartphone" or "speaker". */
  private final String type;
  /** The current volume in percent. This may be null. */
  private final Integer volume_percent;

  private Device(final Builder builder) {
    super(builder);

    this.id = builder.id;
    this.is_active = builder.is_active;
    this.is_private_session = builder.is_private_session;
    this.is_restricted = builder.is_restricted;
    this.name = builder.name;
    this.supports_volume = builder.supports_volume;
    this.type = builder.type;
    this.volume_percent = builder.volume_percent;
  }

  /**
   * Get the ID of the device.
   *
   * @return The device ID. This may be {@code null}.
   */
  public String getId() {
    return id;
  }

  /**
   * Check whether the device is the currently active device.
   *
   * @return If this device is the currently active device.
   */
  public Boolean getIs_active() {
    return is_active;
  }

  /**
   * Get whether the device is in a private session. Access to playback info is limited on clients in a private session.
   *
   * @return Whether the user has put the client into a private session.
   */
  public Boolean getIs_private_session() {
    return is_private_session;
  }

  /**
   * Check whether the device is restricted or not. Restricted devices don't accept Spotify Web API calls.
   *
   * @return Whether controlling this device is restricted.
   */
  public Boolean getIs_restricted() {
    return is_restricted;
  }

  /**
   * Get the name of the device.
   *
   * @return The name of the device.
   */
  public String getName() {
    return name;
  }

  /**
   * Check whether the device can be used to set the volume.
   *
   * @return If this device can be used to set the volume.
   */
  public Boolean getSupports_volume() {
    return supports_volume;
  }

  /**
   * Get the type of the device.
   *
   * @return Device type, such as "Computer", "Smartphone" or "Speaker".
   */
  public String getType() {
    return type;
  }

  /**
   * Get the current volume of the device in percent.
   *
   * @return The current volume in percent. This may be {@code null}.
   */
  public Integer getVolume_percent() {
    return volume_percent;
  }

  @Override
  public String toString() {
    return "Device(id=" + id + ", is_active=" + is_active + ", is_private_session=" + is_private_session + ", is_restricted=" + is_restricted + ", name=" + name
      + ", supports_volume=" + supports_volume + ", type=" + type + ", volume_percent=" + volume_percent + ")";
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Device} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String id;
    private Boolean is_active;
    private Boolean is_private_session;
    private Boolean is_restricted;
    private String name;
    private Boolean supports_volume;
    private String type;
    private Integer volume_percent;

    public Builder() {
      super();
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setIs_active(Boolean is_active) {
      this.is_active = is_active;
      return this;
    }

    public Builder setIs_private_session(Boolean is_private_session) {
      this.is_private_session = is_private_session;
      return this;
    }

    public Builder setIs_restricted(Boolean is_restricted) {
      this.is_restricted = is_restricted;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setSupports_volume(Boolean supports_volume) {
      this.supports_volume = supports_volume;
      return this;
    }

    public Builder setType(String type) {
      this.type = type;
      return this;
    }

    public Builder setVolume_percent(Integer volume_percent) {
      this.volume_percent = volume_percent;
      return this;
    }

    @Override
    public Device build() {
      return new Device(this);
    }
  }

  /**
   * JsonUtil class for building {@link Device} instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Device> {

    public JsonUtil() {
      super();
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Device device = (Device) o;
    return Objects.equals(id, device.id) && Objects.equals(name, device.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
