# Contributing to Spotify Web API Java

Questions about *using* the library are best asked as described under [Support](README.md#support) in the README;
this guide is about changing it.

## Information Quality
Every constructive contribution is highly appreciated. They help to keep this project up-to-date and running,
especially because currently only voluntary maintainers and contributors take care of this repository.

At the same time it is strongly recommended to follow certain guidelines when making contributions to open source
projects like this one. They ensure fast processing times and stable releases.

It is mandatory to stay as close to Spotify's own API description as possible.
A copy of it is vendored at `spec/openapi.yaml`, and that copy is what `ci/check-docs.py` checks a request's verb, path and deprecation against, so it is the first place to look rather than the
[Web API Endpoint Reference](https://developer.spotify.com/documentation/web-api/reference/). Before changing
anything in the code base, make sure to take one  - or more - looks into related files/classes and [the project's
documentation](https://spotify-web-api-java.github.io/spotify-web-api-java/apidocs/). The whole code follows simple
and consistent code conventions for naming rules and alike. Those should be easy to grasp by comparing to the online
developer's reference mentioned above. If not: feel free to just ask. It saves your time to be in the clear about those
conventions before starting to contribute as it significantly lowers the probability to receive change requests from
maintainers and thereby the time one has to wait for merges.

Issues that do not provide enough information, that are unclear or not understandable will be closed until more
qualitative information is provided.

## Development Setup
Building needs a JDK 17 or later (CI builds on 17 and 25; the library itself targets Java 9) and Maven:

```bash
# build, test and install the library into the local Maven repository
mvn clean install

# run the tests only
mvn test
```

Changing a request class, or anything in `spec/openapi.yaml` or `codegen/requests.yaml`, also needs Python 3 with
PyYAML (`pip install pyyaml`) to run the generator:

```bash
# regenerate the request classes and their SpotifyApi methods
./codegen/generate.py

# only check that the committed ones are up to date, as CI does
./codegen/generate.py --check
```

The examples under `examples/` are a separate Maven project that compiles against the installed library as its own module, so build the library first and then the examples:

```bash
mvn -B install -DskipTests=true && mvn -B -f examples/pom.xml clean compile
```

The `clean` matters, because an incremental build reuses classes compiled against the previous module descriptor.
`./ci/check-docs.py` checks the documentation against the code; CI runs both.
[AGENTS.md](AGENTS.md) carries the step-by-step recipe for adding an endpoint.

## Code Overview
The hand-written code lives in `src/main/java/se/michaelthelin/spotify/`, divided into four parts that `SpotifyApi` ties
together. The generated request classes sit in `src/generated/java/` under the same packages; Maven adds that directory
as a second source root, so the jar contains both.

- **`enums/`** holds the fixed value sets Spotify uses, such as `ModelObjectType` or `CountryCode`.
- **`exceptions/`** has one exception per HTTP status code the API answers with, each carrying Spotify's error
  description.
- **`model_objects/`** holds the objects responses are read into. `specification/` has the objects Spotify's reference
  defines, `miscellaneous/` the ones it mentions without defining them, and `special/` the ones that only appear in
  responses. A model object has private fields, public getters and a builder, and is read reflectively by a shared
  Gson instance, so a new field needs no parsing code. `ModelObjects` reads model objects from JSON obtained outside a
  request.
- **`requests/`** mirrors Spotify's endpoints, under `authorization/` and `data/<category>/`. A data request extends
  `AbstractDataRequest`, implements `execute()` and embeds a builder, and `SpotifyApi` has one method per request that
  creates that builder. The data requests and those `SpotifyApi` methods are generated from `spec/openapi.yaml`, as
  described under [Integrity](#integrity): `codegen/requests.yaml` maps each class to its spec operation, and the
  methods sit between `// <generated-requests>` markers in `SpotifyApi.java`. `src/main/java` keeps the hand-written
  infrastructure (base classes, paging interfaces) and the two requests the generator does not produce,
  `GetUsersTopArtistsAndTracksRequest` and `AddCustomPlaylistCoverImageRequest`.

The tests in `src/test/java/se/michaelthelin/spotify/` mirror this structure and use [JUnit](https://junit.org/) and
[Mockito](https://site.mockito.org/). They read the responses they expect from JSON fixtures in `src/test/fixtures/`;
see [Fixtures](#fixtures) for when those may change.

## Contribution Completeness
A contribution has to be *complete* to get accepted. If you cannot complete the requirements, you're welcome to ask for
help. But bear in mind that this can take its time.

A complete contribution includes the following features:

### Integrity
Make sure that your contribution embeds well into the already existing code. For example, the unified folder/package
structure must be used wherever possible.

The data request classes and the `SpotifyApi` methods that create them are generated from `spec/openapi.yaml` by `codegen/generate.py`, so a new endpoint is one line in `codegen/requests.yaml` plus a run of that script, and a generated file is never edited by hand. The generated classes are committed under `src/generated/java`, in the same package as the hand-written code. [AGENTS.md](AGENTS.md#adding-a-new-endpoint) describes the steps.

The vendored `spec/openapi.yaml` is the published spec plus the constraints the generated setters assert.
Where the published spec states a bound only in prose, the copy carries it as a schema field (`minimum`, `maximum` or `enum`), because the generated checks read schema fields.
Count limits on comma-separated parameters are read from the description phrasing `Maximum: N IDs`, `URIs` or `id`, so those bounds stay in the description.

The library is an explicit Java module, so a class in a **new** package is unreachable for callers until that package is added to `exports` in `src/main/java/module-info.java`.
The examples are compiled as a separate module against the built library, which is what turns a forgotten `exports` into a build failure here rather than in someone else's project.
An example may only use packages the library exports, and only types reachable through the library's own `requires transitive`.

### Documentation
Document what a reader of the published API reference needs: types, getters and the methods on `SpotifyApi`.
Model builder setters and default constructors deliberately carry no prose, because restating the signature adds nothing that the matching getter does not already say, so Javadoc runs with `doclint` set to `all,-missing`.

What is still enforced is that every reference resolves: a dead `{@link}` fails the build, and the Javadoc step runs in CI before a tag can publish anything.

A new endpoint also needs an example under `examples/` and a link from the index in `README.md`, and a renamed or removed public name needs an entry in `MIGRATION.md`.
`ci/check-docs.py` fails without them.

In `MIGRATION.md`, record the change under the relevant `####` heading.
A table column whose header names a major version older than the one in `pom.xml` (e.g. `v9` while the library is on 10, `v10` while it is on 11), and code lines under a marker like `// v10`, are read as describing a released past; every other name the guide mentions must still resolve against the current API, and a ⚠️ in the guide must match the code's deprecation state.

### Test Coverage
Unit tests help to assure that functionality works. Due to that contribution approval requires them to be included for
feature additions or altered for feature changes. A unit test must check all top-level properties of a JSON fixture,
none below. For arrays the length is to be checked.

#### Fixtures
A unit test builds upon JSON fixtures. For code changes, fixture files must only be updated when...
- the responses Spotify actually returns change
- a fixture is outdated, compared against the schema for that response in `spec/openapi.yaml`

## Contribution Flow
1. Create a fork from this repository
2. Create a branch in your fork in which you develop your contribution (one branch per feature/fix)
3. Create meaningful and well-separated commits
4. Make sure your contribution follows the contribution guidelines above
5. Create a pull request against the branch the change belongs on:
   - `main` for anything that can ship in a patch or minor release, which is most contributions: fixes, dependency updates, and new features such as an added endpoint or a new parameter
   - `beta` for changes that break the published API, since those have to wait for the next major version

   `beta` accumulates that next major and is released as `X.Y.Z-RCN` until it is ready, so a breaking change only reaches `main` through the release pull request. While a `beta` line is open, follow-up fixes to code that exists only there belong on `beta` too. Ask in the issue if you are unsure.

   CI enforces this split: it compares the built jar against the last stable release with [japicmp](https://siom79.github.io/japicmp/) and fails on any binary or source incompatible change, unless the version in `pom.xml` has a higher major number than that release, as it does on `beta`. Watch for breaks that do not look like one, such as dropping a covariant `execute()` override in favour of the inherited generic one, which changes the erased return type. To run the check locally, use `mvn package -DskipTests && mvn japicmp:cmp -Dapi.baseline=<last stable release>` and read the report in `target/japicmp/`.

## Release Workflow (Maintainers Only)

Follow these steps to publish a new release:

### 1. Update version numbers

The version lives in **three files**:

- **`pom.xml`** — the `<version>` element near the top
- **`README.md`** — two occurrences, the Maven `<version>` snippet and the Gradle `implementation` line
- **`examples/pom.xml`** — the examples are a separate Maven project, so they carry their own copy

`ci/check-docs.py` fails the build when `examples/pom.xml` falls behind `pom.xml`, so a missed bump is caught rather than shipped.

### 2. Regenerate Javadoc

The `apidocs/` directory at the project root is the published API documentation and must be regenerated on every release.
The `release` profile deletes and regenerates it, and because the library is an explicit module, Javadoc writes the pages under `apidocs/se.michaelthelin.spotify/`.

Run:
```bash
mvn clean package -P release -DskipTests=false -Dgpg.skip=true -Dmaven.deploy.skip=true
```

This will:
- Delete `target/` and `apidocs/` (via `maven-clean-plugin` fileset in the `release` profile)
- Compile sources and run all tests (must be 100% green)
- Regenerate `apidocs/` via the `maven-javadoc-plugin` in the `release` profile

### 3. Commit and tag

Pre-releases are cut from `beta` and tagged `X.Y.Z-RCN`, with `N` one past the last `X.Y.Z-RC` tag on that branch; stable releases are cut from `main` and tagged `X.Y.Z`. CI reacts to both patterns and to nothing else.

Commit the version bumps together with the regenerated documentation:
```bash
git add pom.xml README.md examples/pom.xml apidocs/
git commit -m "X.Y.Z"
```

Create a lightweight tag matching the version exactly, and push the commit before the tag so CI validates the code before it publishes anything:
```bash
git tag X.Y.Z
git push origin <branch>
git push origin X.Y.Z
```

A pre-release line becomes stable by merging `beta` into `main` through the release pull request and tagging there. That merge is also what publishes the documentation, since GitHub Pages serves from `main`.

### 4. Automated release via CI

Pushing a tag triggers the GitHub Actions CI workflow (`.github/workflows/ci.yml`), which automatically:
- Builds and tests on Java 17 and Java 25
- Deploys the artifact to Maven Central (Sonatype) via `mvn deploy -P release`
- Creates a GitHub Release via `changelogithub`
