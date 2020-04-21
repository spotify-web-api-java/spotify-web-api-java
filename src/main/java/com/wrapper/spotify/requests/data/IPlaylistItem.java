package com.wrapper.spotify.requests.data;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.IModelObject;
import com.wrapper.spotify.model_objects.specification.Episode;
import com.wrapper.spotify.model_objects.specification.Track;


/**
 * This interface represents objects returned by the API that can be played, saved in a playlist, etc,
 * currently {@link Episode} and {@link Track}.
 */
public interface IPlaylistItem extends IModelObject {

  /**
   * Get the type of the IPlaylistItem.
   * Possible values: {@code ModelObjectType.TRACK} or {@code ModelObjectType.EPISODE}
   *
   * @return The type of the IPlaylistItem.
   */
  ModelObjectType getType();
}
