/* kakasi.i */
%module kakasi
%{
    /* Includes the header in the wrapper code */
    #include "libkakasi.h"
    #include <stdlib.h>
    #include <string.h>

    #ifdef _WIN32
    // Windows version
    static void portable_setenv(const char *name, const char *value) {
        _putenv_s(name, value);   // overwrites existing value
    }
    #else
    // POSIX version
    static void portable_setenv(const char *name, const char *value) {
        setenv(name, value, 1);   // 1 = overwrite
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
int kakasi_getopt_argv(int argc, char **argv);
char *kakasi_do(char *str);

%inline %{
void set_kanwadict(const char *path) {
    portable_setenv("KANWADICT", path);
}

void set_itaijidict(const char *path) {
    portable_setenv("ITAIJIDICT", path);
}
%}