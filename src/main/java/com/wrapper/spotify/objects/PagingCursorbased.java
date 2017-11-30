package com.wrapper.spotify.objects;

import java.util.List;

public class PagingCursorbased<T> extends AbstractModelObject {
  private final String href;
  private final List<T> items;
  private final int limit;
  private final String next;
  private final List<Cursor> cursors;
  private final int total;

  private PagingCursorbased(final PagingCursorbased.Builder builder) {
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
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<PagingCursorbased.Builder> {
    private String href;
    private List items;
    private int limit;
    private String next;
    private List<Cursor> cursors;
    private int total;

    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder setItems(List items) {
      this.items = items;
      return this;
    }

    public Builder setLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder setNext(String next) {
      this.next = next;
      return this;
    }

    public Builder setCursors(List<Cursor> cursors) {
      this.cursors = cursors;
      return this;
    }

    public Builder setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public PagingCursorbased build() {
      return new PagingCursorbased(this);
    }
  }
}
