#!/usr/bin/env bash

#set -x

ACTIVATOR_VERSION=1.3.10

if type -p java >/dev/null 2>&1 ; then
  _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
  _java="$JAVA_HOME/bin/java"
else
  sudo add-apt-repository --yes ppa:webupd8team/java
  sudo apt-get update
  sudo apt-get install --yes oracle-java8-installer
fi

if [[ "$_java" ]]; then
  version=$("$_java" -version 2>&1 | grep "version" | awk '{print $3}' | tr -d \" | awk '{split($0, array, ".")} END{print array[2]}')
  echo "Found java version ${version}"
  if [[ $version -lt 8 ]]; then
    echo Only version 1.8+ is supported
    exit 1
  else
    echo "  ... ok"
  fi
fi

if ! type -p unzip >/dev/null 2>&1 ; then
  sudo apt-get install --yes unzip
fi

wget -q http://downloads.typesafe.com/typesafe-activator/${ACTIVATOR_VERSION}/typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
unzip -qo typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
rm typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
chmod a+x activator-${ACTIVATOR_VERSION}-minimal/bin/activator
ln -sf activator-${ACTIVATOR_VERSION}-minimal/bin/activator activator

# Install git hooks
ln -s -f ../../bin/git-hooks.post-commit.sh .git/hooks/post-commit

echo "Setup complete ..."
