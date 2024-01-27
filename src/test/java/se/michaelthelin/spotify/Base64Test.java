package se.michaelthelin.spotify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64Test {
  private static final String BASE_64_TEST_DATA = "Hello World";
  private static final String BASE_64_TEST_DATA_ENCODED = "SGVsbG8gV29ybGQ=";

  @Test
  public void testCustomBase64() {
    byte[] testDataBytes = BASE_64_TEST_DATA.getBytes();

    String encoded = Base64.encode(testDataBytes);
    Assertions.assertEquals(BASE_64_TEST_DATA_ENCODED, encoded);

    byte[] decoded = Base64.decode(encoded);
    Assertions.assertArrayEquals(testDataBytes, decoded);
  }
}
