package se.michaelthelin.spotify.requests.data.personalization.interfaces;

import se.michaelthelin.spotify.model_objects.IModelObject;

/**
 * Interface for model objects that represent either artists or tracks in personalization requests.
 * This is a marker interface used to type-constrain personalization API responses.
 */
public interface IArtistTrackModelObject extends IModelObject {
}
