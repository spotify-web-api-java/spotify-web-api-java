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

It is mandatory to stay as close to the
[Web API Endpoint Reference](https://developer.spotify.com/web-api/endpoint-reference/) as possible. Before changing
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

### Documentation
Document your source code! It's easy because one can most likely just stick to the online API reference. This is a
requirement. Not because your contributed source code is unreadable. Instead, the aim is to keep the Javadoc complete
and - accompanying this - to keep this project as accessible as possible to developers like yourself.

### Test Coverage
Unit tests help to assure that functionality works. Due to that contribution approval requires them to be included for
feature additions or altered for feature changes. A unit test must check all top-level properties of a JSON fixture,
none below. For arrays the length is to be checked.

#### Fixtures
A unit test builds upon JSON fixtures. For code changes, fixture files must only be updated when...
- the online examples change
- the online examples are outdated, compared against the reference description and/or the
[Web API Object Model](https://developer.spotify.com/web-api/object-model/).

## Contribution Flow
1. Create a fork from this repository
2. Create a branch in your fork in which you develop your contribution (one branch per feature/fix)
3. Create meaningful and well-separated commits
4. Make sure your contribution follows the contribution guidelines above
5. Create a pull request from your feature branch to the correct branch of this project

## Release Workflow (Maintainers Only)

Follow these steps to publish a new release:

### 1. Update version numbers

Update the version string in **three places**:

- **`pom.xml`** — the `<version>` element near the top of the file:
  ```xml
  <version>X.Y.Z</version>
  ```
- **`README.md`** — two occurrences: the Maven `<version>` snippet and the Gradle `implementation` line:
  ```xml
  <version>X.Y.Z</version>
  ```
  ```groovy
  implementation 'se.michaelthelin.spotify:spotify-web-api-java:X.Y.Z'
  ```

### 2. Regenerate Javadoc

The `apidocs/` directory at the project root is the published API documentation and must be regenerated on every release. The `release` Maven profile is configured to delete `apidocs/` and regenerate it.

Run:
```bash
mvn clean package -P release -DskipTests=false -Dgpg.skip=true -Dmaven.deploy.skip=true
```

This will:
- Delete `target/` and `apidocs/` (via `maven-clean-plugin` fileset in the `release` profile)
- Compile sources and run all tests (must be 100% green)
- Regenerate `apidocs/` via the `maven-javadoc-plugin` in the `release` profile

### 3. Commit and tag

Commit all changed files (version bumps + regenerated `apidocs/`):
```bash
git add pom.xml README.md apidocs/
git commit -m "X.Y.Z"
```

Create a lightweight Git tag matching the version exactly (the CI tag pattern is `X.Y.Z` or `X.Y.Z-RCN`):
```bash
git tag X.Y.Z
```

Push the commit and tag:
```bash
git push origin <branch>
git push origin X.Y.Z
```

### 4. Automated release via CI

Pushing a tag triggers the GitHub Actions CI workflow (`.github/workflows/ci.yml`), which automatically:
- Builds and tests on Java 17 and Java 25
- Deploys the artifact to Maven Central (Sonatype) via `mvn deploy -P release`
- Creates a GitHub Release via `changelogithub`
