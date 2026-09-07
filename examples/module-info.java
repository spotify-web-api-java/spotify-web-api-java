/**
 * Compiles the examples as a module of their own, which is what makes them a consumer rather than
 * another source root of the library. Only the library is required here: everything else the
 * examples touch, Gson and HttpComponents among it, has to reach them through the library's own
 * {@code requires transitive}, so a missing export or a requires that should have been transitive
 * fails this build.
 */
module se.michaelthelin.spotify.examples {
  requires se.michaelthelin.spotify;
}
