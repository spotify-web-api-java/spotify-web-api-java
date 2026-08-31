package se.michaelthelin.spotify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

class SpotifyApiTest {

  @Test
  void parseDefaultDateParsesPlainDate() throws ParseException {
    Date date = SpotifyApi.parseDefaultDate("2014-07-21T20:00:00");

    Assertions.assertEquals(
      Instant.parse("2014-07-21T20:00:00Z"),
      date.toInstant());
  }

  @Test
  void parseDefaultDateTruncatesTrailingZuluDesignator() throws ParseException {
    Date date = SpotifyApi.parseDefaultDate("2014-07-21T20:00:00Z");

    Assertions.assertEquals(
      Instant.parse("2014-07-21T20:00:00Z"),
      date.toInstant());
  }

  @Test
  void parseDefaultDateTruncatesTrailingMillisAndZuluDesignator() throws ParseException {
    Date date = SpotifyApi.parseDefaultDate("2015-01-08T09:30:26.879Z");

    Assertions.assertEquals(
      Instant.parse("2015-01-08T09:30:26Z"),
      date.toInstant());
  }

  @Test
  void parseDefaultDateThrowsParseExceptionForNullInput() {
    Assertions.assertThrows(ParseException.class, () -> SpotifyApi.parseDefaultDate(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "not-a-date", "2014-07-21"})
  void parseDefaultDateThrowsParseExceptionForMalformedInput(String date) {
    Assertions.assertThrows(ParseException.class, () -> SpotifyApi.parseDefaultDate(date));
  }

  @Test
  void formatDefaultDateFormatsUsingGmtTimeZone() {
    Date date = Date.from(Instant.parse("2014-07-21T20:00:00Z").atZone(ZoneId.of("GMT")).toInstant());

    Assertions.assertEquals("2014-07-21T20:00:00", SpotifyApi.formatDefaultDate(date));
  }
}
