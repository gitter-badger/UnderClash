UnderClash
==========

What is it?
-----------

UnderClash is a Clash of Clans clan management tool.

Github Issues [![Github Issues](https://img.shields.io/github/issues/lrobinot/UnderClash.svg)](https://github.com/lrobinot/UnderClash/issues) -- Drone CI Status [![Drone CI Status](https://drone.io/github.com/lrobinot/UnderClash/status.png)](https://drone.io/github.com/lrobinot/UnderClash/latest) -- Travis CI Status [![Travis CI Status](https://img.shields.io/travis/lrobinot/UnderClash.svg)](https://travis-ci.org/lrobinot/UnderClash)

Setup development environment
-----------------------------

The technologies used by this project are:

  * Backend container, Oracle JVM http://www.java.com
  * Backend development stack, Lightbend Play! Framework (for scala) https://www.playframework.com

### Install Java 8

```bash
sudo add-apt-repository --yes ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
```

### Install Lightbend Activator

```bash
mkdir -p ~/bin
curl -sL https://downloads.typesafe.com/typesafe-activator/1.3.9/typesafe-activator-1.3.10-minimal.zip -o /tmp/activator.zip
(cd ~ ; unzip -qo /tmp/activator.zip)
ln -sf ~/activator-1.3.10-minimal/bin/activator ~/bin/activator
```

### All in one installer

To install all needed softwares:
```bash
curl -sL https://raw.githubusercontent.com/lrobinot/UnderClash/master/setup.sh | bash -
```
