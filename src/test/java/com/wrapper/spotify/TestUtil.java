package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.*;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.io.*;
import java.net.URI;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
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
      when(mockedHttpManager.get(any(URI.class), any(Header[].class))).thenReturn(fixture);
      when(mockedHttpManager.post(any(URI.class), any(Header[].class), anyListOf(NameValuePair.class))).thenReturn(fixture);
      when(mockedHttpManager.put(any(URI.class), any(Header[].class), anyListOf(NameValuePair.class))).thenReturn(fixture);
      when(mockedHttpManager.delete(any(URI.class), any(Header[].class))).thenReturn(fixture);

      return mockedHttpManager;
    }

    public static HttpManager returningString(String returnedString) throws
            IOException,
            NoContentException,
            BadRequestException,
            UnauthorizedException,
            ForbiddenException,
            NotFoundException,
            TooManyRequestsException,
            InternalServerErrorException,
            BadGatewayException,
            ServiceUnavailableException {
      final HttpManager mockedHttpManager = mock(HttpManager.class);
      when(mockedHttpManager.get(any(URI.class), any(Header[].class))).thenReturn(returnedString);
      when(mockedHttpManager.post(any(URI.class), any(Header[].class), anyListOf(NameValuePair.class))).thenReturn(returnedString);
      when(mockedHttpManager.put(any(URI.class), any(Header[].class), anyListOf(NameValuePair.class))).thenReturn(returnedString);
      when(mockedHttpManager.delete(any(URI.class), any(Header[].class))).thenReturn(returnedString);

      return mockedHttpManager;
    }
  }
}
