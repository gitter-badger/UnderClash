#!/bin/bash

# if type -p java; then
#     echo found java executable in PATH
#     _java=java
# elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
#     echo found java executable in JAVA_HOME
#     _java="$JAVA_HOME/bin/java"
# else
#     echo "no java"
# fi

# if [[ "$_java" ]]; then
#     version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
#     echo version "$version"
#     if [[ "$version" > "1.5" ]]; then
#         echo version is more than 1.5
#     else
#         echo version is less than 1.5
#     fi
# fi

export ACTIVATOR_VERSION=1.3.10

wget https://downloads.typesafe.com/typesafe-activator/${ACTIVATOR_VERSION}/typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
unzip -q typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
rm typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip
chmod a+x activator-${ACTIVATOR_VERSION}-minimal/bin/activator
