package com.wrapper.spotify.objects;

import net.sf.json.JSONObject;

import java.util.List;

public class PagingCursorbased<T> extends AbstractModelObject {
  private final String href;
  private final List<T> items;
  private final int limit;
  private final String next;
  private final List<Cursor> cursors;
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

  public List<T> getItems() {
    return items;
  }

  public int getLimit() {
    return limit;
  }

  public String getNext() {
    return next;
  }

  public List<Cursor> getCursors() {
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
    private List<T> items;
    private int limit;
    private String next;
    private List<Cursor> cursors;
    private int total;

    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder<T> setItems(List items) {
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

    public Builder<T> setCursors(List<Cursor> cursors) {
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

  public static final class JsonUtil<T> extends AbstractModelObject.JsonUtil<PagingCursorbased> {
    public PagingCursorbased<T> createModelObject(JSONObject jsonObject) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new PagingCursorbased.Builder<T>()
              .setHref(jsonObject.getString("href"))
              .setItems(createModelObjectList(jsonObject.getJSONArray("items")))
              .setLimit(jsonObject.getInt("limit"))
              .setNext(jsonObject.getString("next"))
              .setCursors(new Cursor.JsonUtil().createModelObjectList(jsonObject.getJSONArray("cursors")))
              .setTotal(jsonObject.getInt("total"))
              .build();
    }
  }
}
