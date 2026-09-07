# Contributing to Spotify Web API Java

## Your Questions
Please consider asking questions in help networks like
[StackOverflow](https://stackoverflow.com/questions/tagged/spotify-web-api-java) using the label `spotify-web-api-java`.
Posting your question there will result in quick and helpful answers.

Issues should only be used to file bug reports and ask questions that are targeted specifically at **this** project's
source code.

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

## Contribution Completeness
A contribution has to be *complete* to get accepted. If you cannot complete the requirements, you're welcome to ask for
help. But bear in mind that this can take its time.

A complete contribution includes the following features:

### Integrity
Make sure that your contribution embeds well into the already existing code. For example, the unified folder/package
structure must be used wherever possible.

The library is an explicit Java module, so a class in a **new** package is unreachable for callers until that package is added to `exports` in `src/main/java/module-info.java`.
The examples are compiled as a separate module against the built library, which is what turns a forgotten `exports` into a build failure here rather than in someone else's project.

### Documentation
Document what a reader of the published API reference needs: types, getters and the methods on `SpotifyApi`.
Model builder setters and default constructors deliberately carry no prose, because restating the signature adds nothing that the matching getter does not already say, so Javadoc runs with `doclint` set to `all,-missing`.

What is still enforced is that every reference resolves: a dead `{@link}` fails the build, and the Javadoc step runs in CI before a tag can publish anything.

A new endpoint also needs an example under `examples/` and a link from the index in `README.md`, and a renamed or removed public name needs an entry in `MIGRATION.md`.
`ci/check-docs.py` fails without them.

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

Pre-releases are cut from `beta` and tagged `X.Y.Z-RCN`; stable releases are cut from `main` and tagged `X.Y.Z`. CI reacts to both patterns and to nothing else.

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
