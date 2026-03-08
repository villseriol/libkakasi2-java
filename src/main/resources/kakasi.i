/* kakasi.i */
%module kakasi
%{
    /* Includes the header in the wrapper code */
    // #include "libkakasi.h"
    #include <stdlib.h>
    #include <string.h>
    #ifdef _WIN32
      #include <windows.h>
    #else
      #include <dlfcn.h>
    #endif

    typedef int (*kakasi_getopt_argv_t)(int argc, char **argv);
    typedef char* (*kakasi_do_t)(char *str);

    #ifdef _WIN32
    // Windows version
    static void portable_setenv(const char *name, const char *value) {
        _putenv_s(name, value);   // overwrites existing value
    }

    static void *portable_load_library(const char *path) {

    }
    #else
    // POSIX version
    static void portable_setenv(const char *name, const char *value) {
        setenv(name, value, 1);   // 1 = overwrite
    }

    static void *portable_load_library(const char *path) {
        return dlopen(path, RTLD_NOW);
    }

    static int portable_kakasi_getopt_argv(void *handle, int argc, char **argv) {
        kakasi_getopt_argv_t f = (kakasi_getopt_argv_t) dlsym(handle, "kakasi_getopt_argv");
        return f(argc, argv);
    }

    static char *portable_kakasi_do(void *handle, char *str) {
        kakasi_do_t f = (kakasi_do_t) dlsym(handle, "kakasi_do");
        return f(str);
    }

    static int portable_kakasi_close(void *handle) {
        return dlclose(handle);
    }
    #endif
%}

%include "various.i"

%apply char *BYTE { char *str };

/* -------------------------------------------------------------
 * SWIG library containing argc and argv multi-argument typemaps
 * ------------------------------------------------------------- */

%typemap(jni) (int argc, char **argv) "jobjectArray"
%typemap(jtype) (int argc, char **argv) "String[]"
%typemap(jstype) (int argc, char **argv) "String[]"

%typemap(in) (int argc, char **argv) {
  $1_ltype i, len;
  if ($input == (jobjectArray)0) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null array");
    return $null;
  }
  len = ($1_ltype)JCALL1(GetArrayLength, jenv, $input);
  if (len < 0) {
    SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "array length negative");
    return $null;
  }
  $2 = ($2_ltype) malloc((len+1)*sizeof($*2_ltype));
  if ($2 == NULL) {
    SWIG_JavaThrowException(jenv, SWIG_JavaOutOfMemoryError, "memory allocation failed");
    return $null;
  }
  $1 = len;
  for (i = 0; i < len; i++) {
    jstring j_string = (jstring)JCALL2(GetObjectArrayElement, jenv, $input, (jsize)i);
    const char *c_string = JCALL2(GetStringUTFChars, jenv, j_string, 0);
    $2[i] = ($*2_ltype)c_string;
  }
  $2[i] = NULL;
}

%typemap(freearg) (int argc, char **argv) {
  free((void *)$2);
}

/* Parse the header file to generate wrappers */
%inline %{
void set_kanwadict(const char *path) {
    portable_setenv("KANWADICT", path);
}

void set_itaijidict(const char *path) {
    portable_setenv("ITAIJIDICT", path);
}

void *load_library(const char *lib) {
    return portable_load_library(lib);
}

int kakasi_getopt_argv(void *handle, int argc, char **argv) {
    return portable_kakasi_getopt_argv(handle, argc, argv);
}

char *kakasi_do(void *handle, char *str) {
    return portable_kakasi_do(handle, str);
}

int kakasi_close(void *handle) {
    return portable_kakasi_close(handle);
}
%}