package se.michaelthelin.spotify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MiscTest {
  private static final String BASE_64_TEST_DATA = "Hello World";

  @Test
  public void testCustomBase64() {
    byte[] testDataBytes = BASE_64_TEST_DATA.getBytes();
    String encoded = Base64.encode(testDataBytes);
    byte[] decoded = Base64.decode(encoded);
    Assertions.assertArrayEquals(testDataBytes, decoded);
  }
}
