UnderClash
==========

What is it?
-----------

UnderClash is a Clash of Clans clan management tool.

 * Github Issues: [![Github Issues](https://img.shields.io/github/issues/lrobinot/UnderClash.svg)](https://github.com/lrobinot/UnderClash/issues)
 * Drone CI Status: [![Drone CI Status](https://drone.io/github.com/lrobinot/UnderClash/status.png)](https://drone.io/github.com/lrobinot/UnderClash/latest)
 * Distelli CI Status: https://www.distelli.com/lrobinot/apps/UnderClash
 * Travis CI Status: [![Travis CI Status](https://img.shields.io/travis/lrobinot/UnderClash.svg)](https://travis-ci.org/lrobinot/UnderClash)

Setup development environment
-----------------------------

The technologies used by this project are:

  * Backend container, Oracle JVM http://www.java.com
  * Backend development stack, Lightbend Play! Framework (for scala) https://www.playframework.com

To install all needed softwares:
```bash
./setup.sh
```

Running development version
---------------------------

To run a development version:
```bash
activator-<version>-minimal/bin/activator ~run
```
