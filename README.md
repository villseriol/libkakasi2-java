# libkakasi2-java

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
To maintain the status of this library as a wrapper, I decided that I would make no modifications to the source code to get things working.

There were a few issues that I had to overcome during the build process:
1. `iconv` was not working in the `.dll` build, meaning I had to turn off utf8 processing
2. I tried using `msys2` to build natively on Windows, but cross compiling ended up being simpler
3. `kakasi` embeds `KANWADICT` and `ITAIJIDICT` paths at compile-time, meaning I had to get clever about setting this at runtime.

#### Windows
```sh
# 1) Download the archive
wget http://kakasi.namazu.org/stable/kakasi-2.3.6.tar.xz

# 2) Extract the archive
tar -xvf kakasi-2.3.6.tar.xz

# 2) Configure + make
cd kakasi-2.3.6
./configure \
    CFLAGS="-fPIC" \
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
    CFLAGS="-fPIC" \
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

## License
Under normal circumstances, I would have preferred using the shared-library implementation of `kakasi` to decouple the underlying library from my wrapper. The reasons for using the static library were:
1. The small size of the underlying library
2. The frequency of updates to the library
3. The platform independent nature of Java
4. Standalone projects being easier to integrate

As such, this project inherits a version of the GPL license originally used in version 2.3.6 of the `kakasi` C library.

Based on a work from http://kakasi.namazu.org/index.html.en