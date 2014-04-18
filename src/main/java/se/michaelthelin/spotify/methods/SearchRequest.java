package se.michaelthelin.spotify.methods;

public class SearchRequest extends AbstractRequest {

  protected SearchRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder query(String query) {
      assert (query != null);

      path("/v1/search");

      StringBuilder stringBuilder = new StringBuilder();
      String massagedQuery = query.replace(" ", "+");
      return parameter("q", massagedQuery);
    }

    public SearchRequest build() {
      return new SearchRequest(this);
    }

  }

}
