package se.michaelthelin.spotify.model_objects.specification;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.interfaces.IHasTotal;

import java.util.Arrays;

/**
 * Retrieve information about
 * <a href="https://developer.spotify.com/web-api/object-model/#paging-object">Paging objects</a>
 * by building instances from this class. <br>
 * This offset-based paging object is a container for a set of objects.
 *
 * @param <T> The type of the objects contained in a paging object.
 */
public class Paging<T> extends AbstractModelObject implements IHasTotal {
  /** The Spotify Web API endpoint URL. */
  private final String href;
  /** Array of items in the paging object. */
  private final T[] items;
  /** Maximum number of items in the response. */
  private final Integer limit;
  /** URL to the next page of items. */
  private final String next;
  /** Offset of the items returned. */
  private final Integer offset;
  /** URL to the previous page of items. */
  private final String previous;
  /** Total number of items available to return. */
  private final Integer total;

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
   * Get the offset of the items returned (as set in the query or by default).
   *
   * @return The offset of the items returned (as set in the query or by default).
   */
  public Integer getOffset() {
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
  public Integer getTotal() {
    return total;
  }

  @Override
  public String toString() {
    return "Paging(href=" + href + ", items=" + Arrays.toString(items) + ", limit=" + limit + ", next=" + next
        + ", offset=" + offset + ", previous=" + previous + ", total=" + total + ")";
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
    private Integer limit;
    private String next;
    private Integer offset;
    private String previous;
    private Integer total;

    public Builder() {
      super();
    }

    public Builder<T> setHref(String href) {
      this.href = href;
      return this;
    }

    public Builder<T> setItems(T[] items) {
      this.items = items;
      return this;
    }

    public Builder<T> setLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public Builder<T> setNext(String next) {
      this.next = next;
      return this;
    }

    public Builder<T> setOffset(Integer offset) {
      this.offset = offset;
      return this;
    }

    public Builder<T> setPrevious(String previous) {
      this.previous = previous;
      return this;
    }

    public Builder<T> setTotal(Integer total) {
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
  @SuppressWarnings("unchecked")
  public static final class JsonUtil<X> extends AbstractModelObject.JsonUtil<Paging<X>> {

    public JsonUtil() {
      super();
    }

  }
}
