// Not part of the build: the jar ships as an automatic module via the Automatic-Module-Name
// manifest entry, and an automatic module opens every package anyway. This file is kept as the
// descriptor to use if the library is ever built as an explicit module, in which case Gson needs
// the model packages opened to it in order to populate their fields reflectively.
module se.michaelthelin.spotify {
  requires com.google.gson;
  requires nv.i18n;
  requires org.apache.httpcomponents.client5.httpclient5.cache;
  requires org.apache.httpcomponents.client5.httpclient5;
  requires org.apache.httpcomponents.core5.httpcore5;

  opens se.michaelthelin.spotify.model_objects.credentials to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.miscellaneous to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.special to com.google.gson;
  opens se.michaelthelin.spotify.model_objects.specification to com.google.gson;
}
