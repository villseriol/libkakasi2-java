# Development Guide

## Prerequisites

```sh
sudo apt get install \
    build-essential \
    x86_64-w64-mingw32-gcc \
    openjdk-11-jdk
```

## Building Kakasi From Source

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

### Windows

```sh
# 1) Download the archive
wget http://kakasi.namazu.org/stable/kakasi-2.3.6.tar.xz

# 2) Extract the archive
tar -xvf kakasi-2.3.6.tar.xz

# 2) Configure + make
cd kakasi-2.3.6
./configure \
    LDFLAGS="-L/tmp/iconv-win/lib" \
    CFLAGS="-fPIC -fsigned-char" \
    CPPFLAGS="-I/tmp/iconv-win/include" \
    LIBS="-liconv" \
    --host=x86_64-w64-mingw32 \
    --enable-shared \
    --enable-utf8 \
    --prefix=/tmp/kakasi-win/

make
make install
```

### Linux

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
    --enable-utf8 \
    --prefix=/tmp/kakasi-linux/

make
make install
```

## Compiling SWIG

```sh
./scripts/swig.sh
```

## Compiling Embedded Library

```sh
./scripts/compile-windows.sh
# or
./scripts/compile.sh
```
