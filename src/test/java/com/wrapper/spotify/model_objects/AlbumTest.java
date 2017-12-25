package com.wrapper.spotify.model_objects;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.objects.model_objects.Album;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlbumTest {
  @Test
  public void shouldCreateAlbum() throws Exception {
    Album album = new Album.JsonUtil().createModelObject(TestUtil.readTestData("album.json"));
    assertEquals("https://api.spotify.com/v1/albums/4pox3k0CGuwwAknR9GtcoX", album.getHref());
  }

//  @Test
//  public void shouldCreateSeveralAlbums() throws Exception {
//    Album[] albums = new Album.JsonUtil().createModelObjectArray(TestUtil.readTestData("album.json"));
//    assertEquals(1, albums.length);
//  }
}
