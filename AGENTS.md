# AGENTS.md

This file provides context for AI coding agents working in this repository.

Read [CONTRIBUTING.md](CONTRIBUTING.md) first: it carries the rules a contribution is judged against and the release procedure, and it applies to agents exactly as it does to people.
This file covers the mechanics of the codebase and does not repeat them.

## Project Overview

**spotify-web-api-java** is a Java wrapper library for the [Spotify Web API](https://developer.spotify.com/documentation/web-api/). It provides typed request builders and model objects for all Spotify API endpoints.

- **Group ID:** `se.michaelthelin.spotify`
- **Artifact ID:** `spotify-web-api-java`
- **Build Tool:** Maven (`pom.xml`)
- **Java Version:** compiled with `--release 9`; CI builds on JDK 17 and 25

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
│   └── requests/data/               # Hand-written request infrastructure (bases, paging interfaces,
│                                    # the two non-generated concrete classes)
├── generated/java/se/michaelthelin/spotify/requests/data/
│   ├── albums/                      # Generated request classes, one subfolder per API category.
│   ├── artists/                     # Same package as main, committed so a build needs no Python;
│   └── ...                          # pom.xml adds the root, and a generated file is never edited by hand
└── test/
    ├── java/se/michaelthelin/spotify/
    │   ├── ITest.java               # Shared test constants (IDs, names, etc.)
    │   ├── TestUtil.java            # Mock HTTP manager helpers
    │   └── requests/data/           # Unit tests mirroring main structure
    └── fixtures/requests/data/      # JSON fixture files for mock HTTP responses
```

## Choosing a branch

When unsure whether a change belongs on `main` or `beta`: read step 5 and the japicmp paragraph in the "Contribution Flow" section of [CONTRIBUTING.md](CONTRIBUTING.md).

## Adding a New Endpoint

To add a new Spotify API endpoint, follow these steps:

### 1. Generate the request class and its SpotifyApi method

Request classes and the `SpotifyApi` methods that create them are generated from `spec/openapi.yaml`; never edit them by hand, because CI fails when a generated file differs from what the generator produces.
Generated classes are written to `src/generated/java`, in the same package as the hand-written request infrastructure in `src/main/java`; `pom.xml` adds that root via `build-helper-maven-plugin`, so the JAR and the module see both roots.

Add one line to `codegen/requests.yaml` under the class's package, mapping the class name to the spec's `operationId`, then run the generator:

```bash
./codegen/generate.py
```

The generator reads the verb, path, parameters, their types and argument checks, the Javadoc, the deprecation and the response type from the spec.
The `SpotifyApi` method takes the operation's required parameters; every parameter also has a setter on the builder, named exactly like the wire parameter.
An entry only says more where this library deliberately differs from the spec; the keys (`returns`, `key`, `fixed`, `params`, `args`) are described at the top of `codegen/requests.yaml`.
If the operation uses a shape the generator does not support yet, it stops with the reason; extend `codegen/generate.py` rather than writing the class by hand.
It needs PyYAML (`pip install pyyaml`).

If the spec itself is updated, rerun the generator and review the diff: it is the API change.

The vendored `spec/openapi.yaml` is not the published spec verbatim; before updating it, read the "Integrity" section of [CONTRIBUTING.md](CONTRIBUTING.md), which says what the copy adds and why.

### 2. Add a fixture file

Create `src/test/fixtures/requests/data/<category>/<EndpointName>Request.json` with a sample API response.

### 3. Add a test class

Create `src/test/java/se/michaelthelin/spotify/requests/data/<category>/<EndpointName>RequestTest.java`.

Extend `AbstractDataTest<ReturnType>`. Tests should:
- Verify the built URI using `assertEquals("https://api.spotify.com:443/v1/...", request.getUri().toString())`
- Verify headers with `assertHasHeader(...)` and `assertHasAuthorizationHeader(...)`
- Verify body parameters with `assertHasBodyParameter(...)` (from `se.michaelthelin.spotify.Assertions`)
- Verify the deserialized response via `shouldReturnDefault(...)` for both sync and async execution

Common test constants are in `ITest.java` (e.g., `ITest.NAME`, `ITest.ID_PLAYLIST`, `ITest.PUBLIC`).

### 4. Add an example and index it

Create `examples/.../<EndpointName>Example.java` and link it from the example index in `README.md`.
Mark the link with ⚠️ if the request class carries `@Deprecated`.

The examples are a separate Maven module that compiles against the installed library, which is how a missing `exports` gets caught; the build command is in the "Development Setup" section of [CONTRIBUTING.md](CONTRIBUTING.md), and the module rules an example must follow are in its "Integrity" section.

`./ci/check-docs.py` enforces all of this and runs in CI.
It checks that every `SpotifyApi` method has an example, that example file names match their request class, that the README index is complete and its ⚠️ markers match the code, that each request's verb and path exist in `spec/openapi.yaml` with matching deprecation, and that every current-API name written in `MIGRATION.md` still resolves.

### Releasing

When asked to cut a release or RC, or before changing a version number: read the [Release Workflow (Maintainers Only)](CONTRIBUTING.md#release-workflow-maintainers-only) section of [CONTRIBUTING.md](CONTRIBUTING.md) in full before touching anything.
It names the files that carry the version, the command that regenerates `apidocs/`, and the commit, tag, and push order, and step 3 states the tag patterns CI reacts to and how the next RC number is derived.

### Renaming or removing public API

When renaming or removing a public name, or when a change might be binary or source incompatible: before writing it, read the japicmp paragraph in the "Contribution Flow" section and the "Documentation" section of [CONTRIBUTING.md](CONTRIBUTING.md); they cover which branch the change belongs on and how a `MIGRATION.md` entry must be written so `ci/check-docs.py` accepts it.

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

- **Model objects are deserialized reflectively** by the shared Gson in
  `internal/ModelObjectGson.java`, so a new field needs no parsing code.
  To read JSON outside a request, callers use the public `model_objects/ModelObjects`.
  Add `@SerializedName` only when the wire name is not the snake_case form of the field,
  and add a type adapter only for a shape reflection cannot express.
- **Trailing underscores** on Java-reserved words: e.g., `public_` for the `public` field.
- **Assertions** in tests are imported from both JUnit 5 (`org.junit.jupiter.api.Assertions`) and the project's own `se.michaelthelin.spotify.Assertions`.
- **Nullable fields** from the API should be tested with `assertNull(...)` when the fixture returns `null`.
- **POST/PUT** requests always send a JSON body, an empty `{}` if they have no body parameters; the generator does this.
