package com.wrapper.spotify;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.*;
import java.net.URI;
import java.util.logging.Level;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtil {

  private static final String FIXTURE_DIR = "src/test/fixtures/";

  private static String readTestData(String fileName) throws IOException {
    return readFromFile(new File(FIXTURE_DIR, fileName));
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

  protected static String readFromFileTry(File file) {
    try {
      return readFromFile(file);
    } catch (IOException e) {
      SpotifyApi.LOGGER.log(
              Level.SEVERE,
              "IOException while trying to read from file \"" + file.getName() + "\"");
      return null;
    }
  }

  public static class MockedHttpManager {

    public static IHttpManager returningJson(String jsonFixture) throws Exception {

      // Mocked HTTP Manager to get predictable responses
      final IHttpManager mockedHttpManager = mock(IHttpManager.class);
      final String fixture = readTestData(jsonFixture);

      when(mockedHttpManager.get(any(URI.class), any(Header[].class))).thenReturn(fixture);
      when(mockedHttpManager.post(any(URI.class), any(Header[].class), any(HttpEntity.class))).thenReturn(fixture);
      when(mockedHttpManager.put(any(URI.class), any(Header[].class), any(HttpEntity.class))).thenReturn(fixture);
      when(mockedHttpManager.delete(any(URI.class), any(Header[].class), any(HttpEntity.class))).thenReturn(fixture);

      return mockedHttpManager;
    }
  }
}
