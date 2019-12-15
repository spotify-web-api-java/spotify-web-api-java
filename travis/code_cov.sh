set -ev
if [ "${TRAVIS_JDK_VERSION}" = "openjdk8" ]; then 
  mvn cobertura:cobertura 
  bash <(curl -s https://codecov.io/bash)
fi
