package com.wrapper.spotify;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UrlUtilsTest {

    @Test
    public void testPathAndParameterExtraction(){

        assertEquals("/v1/users/johndoe/starred/tracks",
                UrlUtil.getPath("https://api.spotify.com/v1/users/johndoe/starred/tracks?offset=100&limit=100" ));

        assertEquals(
                "100offset",
                UrlUtil.getParameter(
                        "https://api.spotify.com/v1/users/johndoe/starred/tracks?offset=100offset&limit=100limit",
                        "offset")
                );
        assertEquals(
                "100limit",
                UrlUtil.getParameter(
                        "https://api.spotify.com/v1/users/johndoe/starred/tracks?offset=100offset&limit=100limit",
                        "limit")
                );

        assertEquals(
                "",
                UrlUtil.getParameter(
                        "https://api.spotify.com/v1/users/johndoe/starred/tracks?offset=100offset&limit=100limit",
                        "UNKOWN")
                );

    }
}
