# libkakasi2-java

## About

This project implements a Java-wrapper around the [kakasi](http://kakasi.namazu.org/index.html.en) C
library. The impetus for this project was the need to integrate with pre-existing OSM tools like
`osmium` and `osmosis`. See [this](https://github.com/villseriol/osmosis) project as an example.

What this project provides is:

- A faithful wrapper around the kakasi C library
- Instance-based access to the kakasi C library
- Portability between Windows and Unix systems
- Clearer unit tests and documentation of behaviour

## Installation

See the [artifact repository](https://central.sonatype.com/artifact/io.github.villseriol/kakasi2)
for installation instructions.

## Usage

Using instance method.

```java
Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG);
String out = kakasi.run("正直"); // shoujiki
```

Using try-resources method.

```java
try (Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG)) {
    String out = kakasi.run("正直"); // shoujiki
} catch (Exception e) {
    // catch errors here
}
```

## Maintainers Guide

### Prerequisites

```sh
sudo apt get install \
    build-essential \
    x86_64-w64-mingw32-gcc \
    openjdk-11-jdk
```

### Building Kakasi From Source

To maintain the status of this library as a wrapper, I decided that I would make no modifications to
the source code to get things working.

There were a few issues that I had to overcome during the build process:

1. `iconv` was not working in the `.dll` build, meaning I had to turn off utf8 processing
2. I tried using `msys2` to build natively on Windows, but cross compiling ended up being simpler
3. `kakasi` embeds `KANWADICT` and `ITAIJIDICT` paths at compile-time, meaning I had to get clever
   about setting this at runtime.
4. The static implementation meant that it was impossible to guarantee what configuration kakasi
   would have at runtime. This is because configuring kakasi more than once would override previous
   configurations. I updated everything to be instance-based by adding a wrapper around
   system-specific loaders that allow scoped library loading.

#### Windows

```sh
# 1) Download the archive
wget http://kakasi.namazu.org/stable/kakasi-2.3.6.tar.xz

# 2) Extract the archive
tar -xvf kakasi-2.3.6.tar.xz

# 2) Configure + make
cd kakasi-2.3.6
./configure \
    CFLAGS="-fPIC -fsigned-char" \
    --host=x86_64-w64-mingw32 \
    --enable-shared \
    --disable-utf8 \
    --prefix=/tmp/kakasi-win/

make
make install
```

#### Linux

```sh
# 1) Download the archive
wget http://kakasi.namazu.org/stable/kakasi-2.3.6.tar.xz

# 2) Extract the archive
tar -xvf kakasi-2.3.6.tar.xz

# 2) Configure + make
cd kakasi-2.3.6
./configure \
    CFLAGS="-fPIC -fsigned-char" \
    --enable-shared \
    --disable-utf8 \
    --prefix=/tmp/kakasi-linux/

make
make install
```

### Compiling SWIG

```sh
./scripts/swig.sh
```

### Compiling Embedded Library

```sh
./scripts/compile-windows.sh
# or
./scripts/compile.sh
```

## Disclaimer

This program is free software: you can redistribute it and/or modify it under the terms of the GNU
General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not,
see <https://www.gnu.org/licenses/>.
