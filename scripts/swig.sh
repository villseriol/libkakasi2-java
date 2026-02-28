#!/bin/bash
set -e

GIT_DIR=$(git rev-parse --show-toplevel)

swig \
    -outdir "${GIT_DIR}/src/main/java/org/villseriol/kakasi/jni" \
    -package org.villseriol.kakasi.jni \
    -I/usr/include \
    -java "${GIT_DIR}/src/main/resources/kakasi.i"
