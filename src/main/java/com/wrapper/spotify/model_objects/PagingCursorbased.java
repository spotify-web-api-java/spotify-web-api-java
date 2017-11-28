package com.wrapper.spotify.model_objects;

import java.util.ArrayList;
import java.util.List;

public class PagingCursorbased<T> {

  private String href;
  private List<T> items = new ArrayList<>();
  private int limit;
  private String next;
  private List<Cursor> cursors;
  private int total;

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public List<Cursor> getCursors() {
    return cursors;
  }

  public void setCursors(List<Cursor> cursors) {
    this.cursors = cursors;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
