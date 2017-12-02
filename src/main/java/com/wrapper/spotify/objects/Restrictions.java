package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

public class Restrictions extends AbstractModelObject {
  private final String reason;

  private Restrictions(final Restrictions.Builder builder) {
    super(builder);

    this.reason = builder.reason;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String reason;

    public Builder setReason(String reason) {
      this.reason = reason;
      return this;
    }

    @Override
    public Restrictions build() {
      return new Restrictions(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Restrictions> {
    public Restrictions createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Restrictions.Builder()
              .setReason(jsonObject.getString("reason"))
              .build();
    }
  }
}
