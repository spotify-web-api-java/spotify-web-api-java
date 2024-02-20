package se.michaelthelin.spotify;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Source: <a href="https://gist.github.com/EmilHernvall/953733#file-base64-java">EmilHernvall/Base64.java</a>
 * Due to Java version support issues with DataTypeConverter (&lt;=1.7) class and Base64 (&gt;=1.8) class.
 */
public class Base64 {
  private static final char[] ENCODE_TABLE_BASE = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
    'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
  private static final int[] DECODE_TABLE_BASE = {
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
    55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2,
    3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
    20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30,
    31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
    48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  private static final int MASK_8_BITS = 0xFF;
  private static final int MASK_16_BITS = 0xFF0000;
  private static final int MASK_18_BITS = 0xFC0000;
  private static final int MASK_24_BITS = 0xFFFFFF;
  private static final int SHIFT_6 = 6;
  private static final int SHIFT_8 = 8;
  private static final int SHIFT_12 = 12;
  private static final int SHIFT_16 = 16;
  private static final int SHIFT_18 = 18;

  public static String encode(byte[] data) {
    StringBuilder buffer = new StringBuilder();
    int pad = 0;
    for (int i = 0; i < data.length; i += 3) {

      int b = ((data[i] & MASK_8_BITS) << SHIFT_16) & MASK_24_BITS;
      if (i + 1 < data.length) {
        b |= (data[i + 1] & MASK_8_BITS) << SHIFT_8;
      } else {
        pad++;
      }
      if (i + 2 < data.length) {
        b |= (data[i + 2] & MASK_8_BITS);
      } else {
        pad++;
      }

      for (int j = 0; j < 4 - pad; j++) {
        int c = (b & MASK_18_BITS) >> SHIFT_18;
        buffer.append(ENCODE_TABLE_BASE[c]);
        b <<= SHIFT_6;
      }
    }
    for (int j = 0; j < pad; j++) {
      buffer.append("=");
    }

    return buffer.toString();
  }

  public static byte[] decode(String data) {
    byte[] bytes = data.getBytes();
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    for (int i = 0; i < bytes.length; ) {
      int b;
      if (DECODE_TABLE_BASE[bytes[i]] != -1) {
        b = (DECODE_TABLE_BASE[bytes[i]] & MASK_8_BITS) << SHIFT_18;
      }
      // skip unknown characters
      else {
        i++;
        continue;
      }

      int num = 0;
      if (i + 1 < bytes.length && DECODE_TABLE_BASE[bytes[i + 1]] != -1) {
        b = b | ((DECODE_TABLE_BASE[bytes[i + 1]] & MASK_8_BITS) << SHIFT_12);
        num++;
      }
      if (i + 2 < bytes.length && DECODE_TABLE_BASE[bytes[i + 2]] != -1) {
        b = b | ((DECODE_TABLE_BASE[bytes[i + 2]] & MASK_8_BITS) << SHIFT_6);
        num++;
      }
      if (i + 3 < bytes.length && DECODE_TABLE_BASE[bytes[i + 3]] != -1) {
        b = b | (DECODE_TABLE_BASE[bytes[i + 3]] & MASK_8_BITS);
        num++;
      }

      while (num > 0) {
        int c = (b & MASK_16_BITS) >> SHIFT_16;
        buffer.write((char) c);
        b <<= SHIFT_8;
        num--;
      }
      i += 4;
    }
    return buffer.toByteArray();
  }
}
