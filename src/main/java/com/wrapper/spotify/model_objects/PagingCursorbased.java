package com.wrapper.spotify.model_objects;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * Retrieve information about
 *     <a href="https://developer.spotify.com/web-api/object-model/#cursor-based-paging-object">
 *     cursor-based Paging objects</a> by building instances from this class. <br>
 * This cursor-based paging object is a container for a set of objects.
 *
 * @param <T> The type of the objects contained in a paging object.
 */
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
  public int getTotal() {
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
    private int limit;
    private String next;
    private Cursor[] cursors;
    private int total;

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
    public Builder<T> setLimit(int limit) {
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
    public Builder<T> setCursors(Cursor[] cursors) {
      this.cursors = cursors;
      return this;
    }

        /**
         * The total amount setter.
         *
         * @param total The total number of items available to return.
         * @return A {@link PagingCursorbased.Builder}.
         */
    public Builder<T> setTotal(int total) {
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