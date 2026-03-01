# libkakasi2-java

## Getting Started

### Building Kakasi From Source
```sh
CFLAGS="-fPIC" ./configure --disable-shared --enable-static --prefix=/tmp/kakasi-linux/

CFLAGS="-fPIC" ./configure CFLAGS="-I/tmp/iconv-win/include" LDFLAGS="-L/tmp/iconv-win/lib/" --host=x86_64-w64-mingw32 --disable-shared --enable-static --with-libiconv-prefix=/tmp/iconv-win/ ac_cv_header_dlfcn_h=no ac_cv_func_iconv_open=yes LIBS="-liconv" ac_cv_lib_e_main=no ac_cv_header_dlfcn_h=no --build=$(gcc -dumpmachine) --disable-plugins


./configure   --host=x86_64-w64-mingw32    --disable-shared   --enable-static   CFLAGS="-fPIC -I/tmp/iconv-win/include"   LDFLAGS="-L/tmp/iconv-win/lib"   LIBS="-liconv"   ac_cv_header_dlfcn_h=no   ac_cv_lib_e_main=no   am_cv_func_iconv_works=yes am_cv_func_iconv=yes am_cv_lib_iconv=yes --disable-utf8 --prefix=/tmp/kakasi-win/


make clean

make
```

### Compiling SWIG


### Compiling Embedded Library