package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Paging<T> extends AbstractModelObject {
  private final String href;
  private final T[] items;
  private final int limit;
  private final String next;
  private final int offset;
  private final String previous;
  private final int total;

  private Paging(final Paging.Builder<T> builder) {
    super(builder);

    this.href = builder.href;
    this.items = builder.items;
    this.limit = builder.limit;
    this.next = builder.next;
    this.offset = builder.offset;
    this.previous = builder.previous;
    this.total = builder.total;
  }

  public String getHref() {
    return href;
  }

  public T[] getItems() {
    return items;
  }

  public int getLimit() {
    return limit;
  }

  public String getNext() {
    return next;
  }

  public int getOffset() {
    return offset;
  }

  public String getPrevious() {
    return previous;
  }

  public int getTotal() {
    return total;
  }

  @Override
  public Builder<T> builder() {
    return new Builder<>();
  }

  public static final class Builder<T> extends AbstractModelObject.Builder {
    private String href;
    private T[] items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder<T> setItems(T... items) {
      this.items = items;
      return this;
    }

    public Builder<T> setLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder<T> setNext(String next) {
      this.next = next;
      return this;
    }

    public Builder<T> setOffset(int offset) {
      this.offset = offset;
      return this;
    }

    public Builder<T> setPrevious(String previous) {
      this.previous = previous;
      return this;
    }

    public Builder<T> setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public Paging<T> build() {
      return new Paging<>(this);
    }
  }

  public static final class JsonUtil<X> extends AbstractModelObject.JsonUtil<Paging<X>> {
    public Paging<X> createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Paging.Builder<X>()
              .setHref(jsonObject.get("href").getAsString())
              .setItems(createModelObjectArray(jsonObject.getAsJsonArray("items"), new TypeToken<X>(getClass()) {
              }))
              .setLimit(jsonObject.get("limit").getAsInt())
              .setNext((jsonObject.get("next") instanceof JsonNull) ? null : jsonObject.get("next").getAsString())
              .setOffset(jsonObject.get("offset").getAsInt())
              .setPrevious((jsonObject.get("previous") instanceof JsonNull) ? null : jsonObject.get("previous").getAsString())
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }
  }
}
