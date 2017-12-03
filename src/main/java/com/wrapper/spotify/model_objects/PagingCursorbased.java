package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class PagingCursorbased<T> extends AbstractModelObject {
  private final String href;
  private final T[] items;
  private final int limit;
  private final String next;
  private final Cursor[] cursors;
  private final int total;

  private PagingCursorbased(final PagingCursorbased.Builder<T> builder) {
    super(builder);

    this.href = builder.href;
    this.items = builder.items;
    this.limit = builder.limit;
    this.next = builder.next;
    this.cursors = builder.cursors;
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

  public Cursor[] getCursors() {
    return cursors;
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
    private Cursor[] cursors;
    private int total;

    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder<T> setItems(T[] items) {
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

    public Builder<T> setCursors(Cursor[] cursors) {
      this.cursors = cursors;
      return this;
    }

    public Builder<T> setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public PagingCursorbased<T> build() {
      return new PagingCursorbased<>(this);
    }
  }

  public static final class JsonUtil<X> extends AbstractModelObject.JsonUtil<PagingCursorbased<X>> {
    public PagingCursorbased<X> createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder<X>()
              .setHref(jsonObject.get("href").getAsString())
              .setItems(createModelObjectArray(jsonObject.getAsJsonArray("items"), new TypeToken<X>() {
              }))
              .setLimit(jsonObject.get("limit").getAsInt())
              .setNext((jsonObject.get("next") instanceof JsonNull) ? null : jsonObject.get("next").getAsString())
              .setCursors(new Cursor.JsonUtil().createModelObjectArray(jsonObject.getAsJsonArray("cursors")))
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }
  }
}
