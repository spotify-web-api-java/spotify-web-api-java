package com.wrapper.spotify.objects;

import java.util.List;

public class Paging<T> extends AbstractModelObject {
  private final String href;
  private final List<T> items;
  private final int limit;
  private final String next;
  private final int offset;
  private final String previous;
  private final int total;

  private Paging(final Paging.Builder builder) {
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

  public List<T> getItems() {
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
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder<Paging.Builder> {
    private String href;
    private List items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
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

    public Builder setOffset(int offset) {
      this.offset = offset;
      return this;
    }

    public Builder setPrevious(String previous) {
      this.previous = previous;
      return this;
    }

    public Builder setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public Paging build() {
      return new Paging(this);
    }
  }
}
