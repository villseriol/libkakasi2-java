#!/bin/bash
set -e

JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
KAKASI_STATIC_LIB=/tmp/kakasi-win/lib/libkakasi.a

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
GIT_DIR="$(dirname "$SCRIPT_DIR")"

x86_64-w64-mingw32-gcc -fPIC -shared "${GIT_DIR}/src/main/resources/kakasi_wrap.c" \
    -I"${JAVA_HOME}/include" \
    -I"${JAVA_HOME}/include/linux" \
    -I"/tmp/kakasi-win/include" \
    "${KAKASI_STATIC_LIB}" \
    -o "${GIT_DIR}/src/main/resources/native/windows/libkakasi_jni.dll"
