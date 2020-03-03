# Shop

Scala implementation of shop.

## Prerequisites

I'm sure the local environment is set up for Scala/Java, but just in case, for any Mac user (apologies to everyone else):

If you don't have [Homebrew](https://brew.sh/):

```bash
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

And if you don't have [Java](https://www.java.com/) and/or [SBT](https://www.scala-sbt.org/):

```bash
$ brew install homebrew/cask/java

$ brew install sbt
```

## SBT

From root of this project...

#### Test

```bash
$ sbt clean coverage test
```

#### Generate coverage reports

```bash
$ sbt coverageReport
```
