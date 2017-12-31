package com.wrapper.spotify.model_objects;

import com.google.gson.JsonObject;

public class Device extends AbstractModelObject {
  private final String id;
  private final Boolean is_active;
  private final Boolean is_restricted;
  private final String name;
  private final String type;
  private final Integer volume_percent;

  private Device(final Device.Builder builder) {
    super(builder);

    this.id = builder.id;
    this.is_active = builder.is_active;
    this.is_restricted = builder.is_restricted;
    this.name = builder.name;
    this.type = builder.type;
    this.volume_percent = builder.volume_percent;
  }

  public String getId() {
    return id;
  }

  public Boolean getIs_active() {
    return is_active;
  }

  public Boolean getIs_restricted() {
    return is_restricted;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public Integer getVolume_percent() {
    return volume_percent;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String id;
    private Boolean is_active;
    private Boolean is_restricted;
    private String name;
    private String type;
    private Integer volume_percent;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setIs_active(Boolean is_active) {
      this.is_active = is_active;
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

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Device> {
    public Device createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Device.Builder()
              .setId(jsonObject.get("id").getAsString())
              .setIs_active(jsonObject.get("is_active").getAsBoolean())
              .setIs_restricted(jsonObject.get("is_restricted").getAsBoolean())
              .setName(jsonObject.get("name").getAsString())
              .setType(jsonObject.get("type").getAsString())
              .setVolume_percent(jsonObject.get("volume_percent").getAsInt())
              .build();
    }
  }
}
