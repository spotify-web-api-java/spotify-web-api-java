package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.WebApiException;

import java.io.*;
import java.nio.CharBuffer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtil {

  private static final String TEST_DATA_DIR = "src/test/fixtures/";

  private static final int MAX_TEST_DATA_FILE_SIZE = 65536;

  public static String readTestData(String fileName) throws IOException {
    return readFromFile(new File(TEST_DATA_DIR, fileName));
  }

  private static String readFromFile(File file) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
    StringBuilder out = new StringBuilder();
    String line;
    while ((line = in.readLine()) != null) {
      out.append(line);
    }
    in.close();
    return out.toString();
  }

  public static class MockedHttpManager {

    public static HttpManager returningJson(String jsonFixture) throws Exception {
      // Mocked HTTP Manager to get predictable responses
      final HttpManager mockedHttpManager = mock(HttpManager.class);
      final String fixture = readTestData(jsonFixture);
      when(mockedHttpManager.get((UtilProtos.Url) any())).thenReturn(fixture);
      when(mockedHttpManager.post((UtilProtos.Url) any())).thenReturn(fixture);
      when(mockedHttpManager.put((UtilProtos.Url) any())).thenReturn(fixture);
      when(mockedHttpManager.delete((UtilProtos.Url) any())).thenReturn(fixture);

      return mockedHttpManager;
    }

    public static HttpManager returningString(String returnedString) throws IOException, WebApiException {
      final HttpManager mockedHttpManager = mock(HttpManager.class);
      when(mockedHttpManager.get((UtilProtos.Url) any())).thenReturn(returnedString);
      when(mockedHttpManager.post((UtilProtos.Url) any())).thenReturn(returnedString);
      when(mockedHttpManager.put((UtilProtos.Url) any())).thenReturn(returnedString);
      when(mockedHttpManager.delete((UtilProtos.Url) any())).thenReturn(returnedString);

      return mockedHttpManager;
    }
  }

}
