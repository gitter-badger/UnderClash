#!/usr/bin/env bash

set -x

export ACTIVATOR_VERSION=1.3.10

if type -p java 2>&1 >/dev/null; then
  _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
  _java="$JAVA_HOME/bin/java"
else
  sudo add-apt-repository --yes ppa:webupd8team/java
  sudo apt-get update
  sudo apt-get install --yes oracle-java8-installer
fi

if [[ "$_java" ]]; then
  version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
  if [[ "$version" < "1.8" ]]; then
    echo Only version 1.8+ is supported
  fi
fi

if ! type -p unzip 2>&1 >/dev/null ; then
  sudo apt-get install --yes unzip
fi

wget -q http://downloads.typesafe.com/typesafe-activator/${ACTIVATOR_VERSION}/typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
unzip -qo typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
rm typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
chmod a+x activator-${ACTIVATOR_VERSION}-minimal/bin/activator
ln -sf activator-${ACTIVATOR_VERSION}-minimal/bin/activator activator

echo "Setup complete..."
