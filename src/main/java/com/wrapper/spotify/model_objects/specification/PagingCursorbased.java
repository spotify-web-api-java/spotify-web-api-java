package com.wrapper.spotify.model_objects.specification;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

import java.lang.reflect.ParameterizedType;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#cursor-based-paging-object">
 * cursor-based Paging objects</a> by building instances from this class. <br>
 * This cursor-based paging object is a container for a set of objects.
 *
 * @param <T> The type of the objects contained in a paging object.
 */
public class PagingCursorbased<T> extends AbstractModelObject {
  private final String href;
  private final T[] items;
  private final Integer limit;
  private final String next;
  private final Cursor[] cursors;
  private final Integer total;

  private PagingCursorbased(final PagingCursorbased.Builder<T> builder) {
    super(builder);

    this.href = builder.href;
    this.items = builder.items;
    this.limit = builder.limit;
    this.next = builder.next;
    this.cursors = builder.cursors;
    this.total = builder.total;
  }

  /**
   * Get a link to the Web API endpoint returning the full result of the request.
   *
   * @return A link to the Web API endpoint returning the full result of the request.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the items contained in the paging object.
   *
   * @return The items contained in the paging object.
   */
  public T[] getItems() {
    return items;
  }

  /**
   * Get the maximum number of items in the response (as set in the query or by default).
   *
   * @return The maximum number of items in the response (as set in the query or by default).
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   * Get the URL to the next page of items. ({@code null} if none)
   *
   * @return URL to the next page of items. ({@code null} if none)
   */
  public String getNext() {
    return next;
  }

  /**
   * Get the cursors used to find the next set of items.
   *
   * @return The cursors used to find the next set of items.
   */
  public Cursor[] getCursors() {
    return cursors;
  }

  /**
   * Get the total number of items available to return.
   *
   * @return The total number of items available to return.
   */
  public Integer getTotal() {
    return total;
  }

  @Override
  public Builder<T> builder() {
    return new Builder<>();
  }

  /**
   * Builder class for building {@link PagingCursorbased} instances.
   *
   * @param <T> The type of the objects contained in a paging object.
   */
  public static final class Builder<T> extends AbstractModelObject.Builder {
    private String href;
    private T[] items;
    private Integer limit;
    private String next;
    private Cursor[] cursors;
    private Integer total;

    /**
     * The href setter.
     *
     * @param href A link to the Web API endpoint returning the full result of the request.
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The items setter.
     *
     * @param items A page of items.
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setItems(T[] items) {
      this.items = items;
      return this;
    }

    /**
     * The request limit setter.
     *
     * @param limit The maximum number of items in the response (as set in the query or by default).
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

    /**
     * The next URL setter.
     *
     * @param next URL to the next page of items. ({@code null} if none)
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setNext(String next) {
      this.next = next;
      return this;
    }

    /**
     * The cursor setter.
     *
     * @param cursors The cursors used to find the next set of items.
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setCursors(Cursor... cursors) {
      this.cursors = cursors;
      return this;
    }

    /**
     * The total amount setter.
     *
     * @param total The total number of items available to return.
     * @return A {@link PagingCursorbased.Builder}.
     */
    public Builder<T> setTotal(Integer total) {
      this.total = total;
      return this;
    }

    @Override
    public PagingCursorbased<T> build() {
      return new PagingCursorbased<>(this);
    }
  }

  /**
   * JsonUtil class for building {@link PagingCursorbased} instances.
   *
   * @param <X> The type of the objects contained in a paging object.
   */
  @SuppressWarnings("unchecked")
  public static final class JsonUtil<X> extends AbstractModelObject.JsonUtil<PagingCursorbased<X>> {
    public PagingCursorbased<X> createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new Builder<X>()
              .setHref(
                      hasAndNotNull(jsonObject, "href")
                              ? jsonObject.get("href").getAsString()
                              : null)
              .setItems(
                      hasAndNotNull(jsonObject, "items")
                              ? createModelObjectArray(
                              jsonObject.getAsJsonArray("items"), (Class<X>) ((ParameterizedType) getClass()
                                      .getGenericSuperclass()).getActualTypeArguments()[0])
                              : null)
              .setLimit(
                      hasAndNotNull(jsonObject, "limit")
                              ? jsonObject.get("limit").getAsInt()
                              : null)
              .setNext(
                      hasAndNotNull(jsonObject, "next")
                              ? jsonObject.get("next").getAsString()
                              : null)
              .setCursors(
                      hasAndNotNull(jsonObject, "cursors")
                              ? new Cursor.JsonUtil().createModelObjectArray(
                              jsonObject.getAsJsonArray("cursors"))
                              : null)
              .setTotal(
                      hasAndNotNull(jsonObject, "total")
                              ? jsonObject.get("total").getAsInt()
                              : null)
              .build();
    }
  }
}
