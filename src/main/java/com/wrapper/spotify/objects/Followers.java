package com.wrapper.spotify.objects;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Followers extends AbstractModelObject {
  private final String href;
  private final int total;

  private Followers(final Followers.Builder builder) {
    super(builder);

    this.href = builder.href;
    this.total = builder.total;
  }

  public String getHref() {
    return href;
  }

  public int getTotal() {
    return total;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private int total;

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public Followers build() {
      return new Followers(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<Followers> {
    public Followers createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Followers.Builder()
              .setHref((jsonObject.get("href") instanceof JsonNull) ? null : jsonObject.get("href").getAsString())
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }
  }
}
