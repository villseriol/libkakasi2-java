#!/bin/bash
set -e

JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
KAKASI_STATIC_LIB=~/dev/kakasi-2.3.6/lib/.libs/libkakasi.a

GIT_DIR=$(git rev-parse --show-toplevel)

gcc -fPIC -shared "${GIT_DIR}/src/main/resources/kakasi_wrap.c" \
    -I"${JAVA_HOME}/include" \
    -I"${JAVA_HOME}/include/linux" \
    "${KAKASI_STATIC_LIB}" \
    -o "${GIT_DIR}/src/main/resources/native/linux/libkakasi_jni.so"
