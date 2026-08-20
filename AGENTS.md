# AGENTS.md

This file provides context for AI coding agents working in this repository.

## Project Overview

**spotify-web-api-java** is a Java wrapper library for the [Spotify Web API](https://developer.spotify.com/documentation/web-api/). It provides typed request builders and model objects for all Spotify API endpoints.

- **Group ID:** `se.michaelthelin.spotify`
- **Artifact ID:** `spotify-web-api-java`
- **Current Version:** `10.0.0-RC1`
- **Build Tool:** Maven (`pom.xml`)
- **Java Version:** 17+

## Repository Structure

```
src/
├── main/java/se/michaelthelin/spotify/
│   ├── SpotifyApi.java              # Main entry point – all endpoint builders exposed here
│   ├── SpotifyHttpManager.java      # HTTP client wrapper
│   ├── enums/                       # Enum types (ModelObjectType, etc.)
│   ├── exceptions/                  # Exception hierarchy
│   ├── model_objects/               # POJOs for API response objects
│   │   ├── specification/           # Core model objects (Playlist, Track, Album, etc.)
│   │   ├── special/                 # Composite/special models
│   │   └── miscellaneous/           # Supporting models
│   └── requests/
│       └── data/                    # One subfolder per API category
│           ├── albums/
│           ├── artists/
│           ├── playlists/           # Playlist endpoint request classes
│           └── ...
└── test/
    ├── java/se/michaelthelin/spotify/
    │   ├── ITest.java               # Shared test constants (IDs, names, etc.)
    │   ├── TestUtil.java            # Mock HTTP manager helpers
    │   └── requests/data/           # Unit tests mirroring main structure
    └── fixtures/requests/data/      # JSON fixture files for mock HTTP responses
```

## Adding a New Endpoint

To add a new Spotify API endpoint, follow these steps:

### 1. Create a Request class

Create `src/main/java/se/michaelthelin/spotify/requests/data/<category>/<EndpointName>Request.java`.

Key patterns:
- Extend `AbstractDataRequest<ReturnType>` (or `AbstractDataPagingRequest` for paginated results).
- Annotate with `@JsonDeserialize(builder = <ClassName>.Builder.class)`.
- Implement `execute()` which calls `getJson()`, `postJson()`, `putJson()`, or `deleteJson()` as appropriate.
- Add a `static final class Builder` with:
  - Path parameters set via `setPathParameter("key", value)`
  - Query parameters set via `setQueryParameter("key", value)`
  - Body parameters set via `setBodyParameter("key", value)`
  - `build()` method that calls `setPath("/v1/...")` and `setContentType(...)` for POST/PUT requests
  - `self()` returning `this`

**Endpoint URL reference:** Use the new `POST /v1/me/playlists` style paths (not the older `/v1/users/{user_id}/...` style where Spotify has migrated endpoints).

### 2. Expose the method in SpotifyApi

In `SpotifyApi.java`, add a public method that:
- Returns `<EndpointName>Request.Builder`
- Creates a new builder via `new <EndpointName>Request.Builder(accessToken)`
- Chains `.setDefaults(httpManager, scheme, host, port)`
- Sets any required path parameters

Methods are grouped by API category (albums, artists, playlists, etc.).

### 3. Add a fixture file

Create `src/test/fixtures/requests/data/<category>/<EndpointName>Request.json` with a sample API response.

### 4. Add a test class

Create `src/test/java/se/michaelthelin/spotify/requests/data/<category>/<EndpointName>RequestTest.java`.

Extend `AbstractDataTest<ReturnType>`. Tests should:
- Verify the built URI using `assertEquals("https://api.spotify.com:443/v1/...", request.getUri().toString())`
- Verify headers with `assertHasHeader(...)` and `assertHasAuthorizationHeader(...)`
- Verify body parameters with `assertHasBodyParameter(...)` (from `se.michaelthelin.spotify.Assertions`)
- Verify the deserialized response via `shouldReturnDefault(...)` for both sync and async execution

Common test constants are in `ITest.java` (e.g., `ITest.NAME`, `ITest.ID_PLAYLIST`, `ITest.PUBLIC`).

## Building and Testing

```bash
# Compile
mvn compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest="CreatePlaylistRequestTest"

# Run tests matching a pattern
mvn test -Dtest="*Playlist*"
```

## Key Conventions

- **Trailing underscores** on Java-reserved words: e.g., `public_` for the `public` field.
- **Assertions** in tests are imported from both JUnit 5 (`org.junit.jupiter.api.Assertions`) and the project's own `se.michaelthelin.spotify.Assertions`.
- **Nullable fields** from the API should be tested with `assertNull(...)` when the fixture returns `null`.
- **POST/PUT** requests must set `ContentType.APPLICATION_JSON` in the `build()` method.
- **Path patterns** use `{param_name}` placeholders set with `setPathParameter("param_name", value)`.
