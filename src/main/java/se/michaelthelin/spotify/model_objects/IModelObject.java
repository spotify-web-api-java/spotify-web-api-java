package se.michaelthelin.spotify.model_objects;

import java.io.Serializable;

/**
 * Interface with methods used in model objects.
 */
public interface IModelObject extends Serializable {

  /**
   * Create a builder for building an instance of a model object. <br>
   * The type of the builder and its methods depend on its corresponding implementation.
   *
   * @return A builder object.
   */
  Builder builder();

  /**
   * Interface with methods used in builder classes of model objects.
   */
  interface Builder {

    /**
     * Build a model object with the information set in the builder object. <br>
     * The type of the model object and its methods depend on its corresponding implementation.
     *
     * @return A model object.
     */
    IModelObject build();
  }
}
