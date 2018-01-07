package com.wrapper.spotify;

import java.io.ByteArrayOutputStream;

/**
 * Source: https://gist.github.com/EmilHernvall/953733#file-base64-java
 * Due to Java version support issues with Datatypeconverter (&lt;=1.7) class and Base64 (&gt;=1.8) class.
 */
public class Base64 {
  public static String encode(byte[] data) {
    char[] tbl = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    StringBuilder buffer = new StringBuilder();
    int pad = 0;
    for (int i = 0; i < data.length; i += 3) {

      int b = ((data[i] & 0xFF) << 16) & 0xFFFFFF;
      if (i + 1 < data.length) {
        b |= (data[i + 1] & 0xFF) << 8;
      } else {
        pad++;
      }
      if (i + 2 < data.length) {
        b |= (data[i + 2] & 0xFF);
      } else {
        pad++;
      }

      for (int j = 0; j < 4 - pad; j++) {
        int c = (b & 0xFC0000) >> 18;
        buffer.append(tbl[c]);
        b <<= 6;
      }
    }
    for (int j = 0; j < pad; j++) {
      buffer.append("=");
    }

    return buffer.toString();
  }

  public static byte[] decode(String data) {
    int[] tbl = {
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
    byte[] bytes = data.getBytes();
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    for (int i = 0; i < bytes.length; ) {
      int b;
      if (tbl[bytes[i]] != -1) {
        b = (tbl[bytes[i]] & 0xFF) << 18;
      }
      // skip unknown characters
      else {
        i++;
        continue;
      }

      int num = 0;
      if (i + 1 < bytes.length && tbl[bytes[i + 1]] != -1) {
        b = b | ((tbl[bytes[i + 1]] & 0xFF) << 12);
        num++;
      }
      if (i + 2 < bytes.length && tbl[bytes[i + 2]] != -1) {
        b = b | ((tbl[bytes[i + 2]] & 0xFF) << 6);
        num++;
      }
      if (i + 3 < bytes.length && tbl[bytes[i + 3]] != -1) {
        b = b | (tbl[bytes[i + 3]] & 0xFF);
        num++;
      }

      while (num > 0) {
        int c = (b & 0xFF0000) >> 16;
        buffer.write((char) c);
        b <<= 8;
        num--;
      }
      i += 4;
    }
    return buffer.toByteArray();
  }
}
