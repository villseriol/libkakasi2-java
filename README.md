# libkakasi2-java

## Getting Started

### Building Kakasi From Source
To maintain the status of this library as a wrapper, I decided that I would make no modifications to the source code to get things working.

There were a few issues that I had to overcome during the build process:
1. `iconv` was not working in the `.dll` build, meaning I had to turn off utf8 processing
2. I tried using `msys2` to build natively on Windows, but cross compiling ended up being simpler
3. `kakasi` embeds `KANWADICT` and `ITAIJIDICT` paths at compile-time, meaning I had to get clever about setting this at runtime.

#### Windows
Download the dependencies required to build the library.

```sh
sudo apt get install \
    build-essential \
    x86_64-w64-mingw32-gcc
```

Build the static library for Windows.
```sh
./configure \
    CFLAGS="-fPIC" \
    --host=x86_64-w64-mingw32 \
    --disable-shared \
    --enable-static \
    --disable-utf8 \
    --prefix=/tmp/kakasi-win/

make
make install
```

#### Linux
Download the dependencies required to build the library.
```sh
sudo apt get install build-essential
```

Build the static library for Linux.
```sh
./configure \
    CFLAGS="-fPIC" \
    --disable-shared \
    --enable-static \
    --prefix=/tmp/kakasi-linux/

make
make install
```

### Compiling SWIG


### Compiling Embedded Library


## License
Under normal circumstances, I would have preferred using the shared-library implementation of `kakasi` to decouple the underlying library from my wrapper. The reasons for using the static library were:
1. The small size of the underlying library
2. The frequency of updates to the library
3. The platform independent nature of Java
4. Standalone projects being easier to integrate

As such, this project inherits a version of the GPL license originally used in version 2.3.6 of the `kakasi` C library.

Based on a work from http://kakasi.namazu.org/index.html.en