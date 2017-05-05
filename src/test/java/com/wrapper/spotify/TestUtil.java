package com.wrapper.spotify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.wrapper.spotify.exceptions.WebApiException;

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
    String currentLine;
    StringBuilder result = new StringBuilder();
    InputStreamReader reader = new InputStreamReader(
        new FileInputStream(file), "UTF-8");
    BufferedReader bufReader = new BufferedReader(reader);
    while ((currentLine = bufReader.readLine()) != null) {
      result.append(currentLine);
    }
    return result.toString();
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
