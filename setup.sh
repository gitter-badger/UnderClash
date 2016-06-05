#!/bin/bash

export ACTIVATOR_VERSION=1.3.10

sudo add-apt-repository --yes ppa:webupd8team/java
sudo apt-get update
sudo apt-get install --yes oracle-java8-installer unzip

mkdir -p ~/bin

curl -sL https://downloads.typesafe.com/typesafe-activator/${ACTIVATOR_VERSION}/typesafe-activator-${ACTIVATOR_VERSION}-minimal.zip -o /tmp/activator.zip
(cd ~ ; unzip -qo /tmp/activator.zip)
ln -sf ~/activator-${ACTIVATOR_VERSION}-minimal/bin/activator ~/bin/activator
