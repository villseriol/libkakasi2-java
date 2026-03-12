#!/bin/bash
set -e

JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
GIT_DIR="$(dirname "$SCRIPT_DIR")"

gcc -fPIC -shared "${GIT_DIR}/src/main/resources/kakasi_wrap.c" \
    -I"${JAVA_HOME}/include" \
    -I"${JAVA_HOME}/include/linux" \
    -o "${GIT_DIR}/src/main/resources/native/linux/libkakasi_jni.so"
