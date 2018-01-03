package com.wrapper.spotify.model_objects.specification;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#paging-object">Paging objects</a>
 * by building instances from this class. <br>
 * This offset-based paging object is a container for a set of objects.
 *
 * @param <T> The type of the objects contained in a paging object.
 */
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
  public int getLimit() {
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
   * Get the offset of the items returned (as set in the query or by default).
   *
   * @return The offset of the items returned (as set in the query or by default).
   */
  public int getOffset() {
    return offset;
  }

  /**
   * Get the URL to the previous page of items. ({@code null} if none)
   *
   * @return URL to the previous page of items. ({@code null} if none)
   */
  public String getPrevious() {
    return previous;
  }

  /**
   * Get the total number of items available to return.
   *
   * @return The total number of items available to return.
   */
  public int getTotal() {
    return total;
  }

  @Override
  public Builder<T> builder() {
    return new Builder<>();
  }

  /**
   * Builder class for building {@link Paging} instances.
   *
   * @param <T> The type of the objects contained in a paging object.
   */
  public static final class Builder<T> extends AbstractModelObject.Builder {
    private String href;
    private T[] items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

    /**
     * The href setter.
     *
     * @param href A link to the Web API endpoint returning the full result of the request.
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * The items setter.
     *
     * @param items A page of items.
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setItems(T... items) {
      this.items = items;
      return this;
    }

    /**
     * The request limit setter.
     *
     * @param limit The maximum number of items in the response (as set in the query or by default).
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setLimit(int limit) {
      this.limit = limit;
      return this;
    }

    /**
     * The next URL setter.
     *
     * @param next URL to the next page of items. ({@code null} if none)
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setNext(String next) {
      this.next = next;
      return this;
    }

    /**
     * The offset setter.
     *
     * @param offset The offset of the items returned (as set in the query or by default).
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setOffset(int offset) {
      this.offset = offset;
      return this;
    }

    /**
     * The previous URL setter.
     *
     * @param previous URL to the previous page of items. ({@code null} if none)
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setPrevious(String previous) {
      this.previous = previous;
      return this;
    }

    /**
     * The total amount setter.
     *
     * @param total The total number of items available to return.
     * @return A {@link Paging.Builder}.
     */
    public Builder<T> setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public Paging<T> build() {
      return new Paging<>(this);
    }
  }

  /**
   * JsonUtil class for building {@link Paging} instances.
   *
   * @param <X> The type of the objects contained in a paging object.
   */
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
