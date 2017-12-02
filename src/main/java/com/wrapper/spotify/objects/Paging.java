package com.wrapper.spotify.objects;

import com.google.common.reflect.TypeToken;
import net.sf.json.JSONObject;

import java.util.List;

public class Paging<T> extends AbstractModelObject {
  private final String href;
  private final List<?> items;
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

  public List<?> getItems() {
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
    private List<?> items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder<T> setItems(List<?> items) {
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

  public static final class JsonUtilPaging extends AbstractModelObject.JsonUtilPaging {
    public <X> Paging<X> createModelObject(JSONObject jsonObject, TypeToken<X> type) {
      if (jsonObject == null || jsonObject.isNullObject()) {
        return null;
      }

      return new Paging.Builder<X>()
              .setHref(jsonObject.getString("href"))
              .setItems(createModelObjectList(jsonObject.getJSONArray("items"), type))
              .setLimit(jsonObject.getInt("limit"))
              .setNext(jsonObject.getString("next"))
              .setOffset(jsonObject.getInt("offset"))
              .setPrevious(jsonObject.getString("previous"))
              .setTotal(jsonObject.getInt("total"))
              .build();
    }
  }
}
