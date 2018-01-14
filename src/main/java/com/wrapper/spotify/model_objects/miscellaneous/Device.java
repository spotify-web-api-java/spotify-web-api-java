package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about <a href="https://developer.spotify.com/web-api/get-a-users-available-devices/">Device
 * objects</a> by creating instances from this class.
 */
public class Device extends AbstractModelObject {
  private final String id;
  private final Boolean is_active;
  private final Boolean is_restricted;
  private final String name;
  private final String type;
  private final Integer volume_percent;

  private Device(final Builder builder) {
    super(builder);

    this.id = builder.id;
    this.is_active = builder.is_active;
    this.is_restricted = builder.is_restricted;
    this.name = builder.name;
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
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building {@link Device} instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String id;
    private Boolean is_active;
    private Boolean is_restricted;
    private String name;
    private String type;
    private Integer volume_percent;

    /**
     * The device ID setter.
     *
     * @param id The device ID. This may be {@code null}.
     * @return A {@link Device.Builder}.
     */
    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    /**
     * The active device state setter.
     *
     * @param is_active If this device is the currently active device.
     * @return A {@link Device.Builder}.
     */
    public Builder setIs_active(Boolean is_active) {
      this.is_active = is_active;
      return this;
    }

    /**
     * The device restriction state setter.
     *
     * @param is_restricted Whether controlling this device is restricted.
     * @return A {@link Device.Builder}.
     */
    public Builder setIs_restricted(Boolean is_restricted) {
      this.is_restricted = is_restricted;
      return this;
    }

    /**
     * The device name setter.
     *
     * @param name The name of the device.
     * @return A {@link Device.Builder}.
     */
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    /**
     * The device type setter.
     *
     * @param type Device type, such as "Computer", "Smartphone" or "Speaker".
     * @return A {@link Device.Builder}.
     */
    public Builder setType(String type) {
      this.type = type;
      return this;
    }

    /**
     * The device volume setter.
     *
     * @param volume_percent The current volume in percent. This may be {@code null}.
     * @return A {@link Device.Builder}.
     */
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
    public Device createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Device.Builder()
              .setId(
                      hasAndNotNull(jsonObject, "id")
                              ? jsonObject.get("id").getAsString()
                              : null)
              .setIs_active(
                      hasAndNotNull(jsonObject, "is_active")
                              ? jsonObject.get("is_active").getAsBoolean()
                              : null)
              .setIs_restricted(
                      hasAndNotNull(jsonObject, "is_restricted")
                              ? jsonObject.get("is_restricted").getAsBoolean()
                              : null)
              .setName(
                      hasAndNotNull(jsonObject, "name")
                              ? jsonObject.get("name").getAsString()
                              : null)
              .setType(
                      hasAndNotNull(jsonObject, "type")
                              ? jsonObject.get("type").getAsString()
                              : null)
              .setVolume_percent(
                      hasAndNotNull(jsonObject, "volume_percent")
                              ? jsonObject.get("volume_percent").getAsInt()
                              : null)
              .build();
    }
  }
}
