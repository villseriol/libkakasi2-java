/* kakasi.i */
%module kakasi
%{
    /* Includes the header in the wrapper code */
    #include <stdlib.h>
    #include <string.h>
    #ifdef _WIN32
      #include <windows.h>
    #else
      #include <dlfcn.h>
    #endif

    typedef char euc_byte_t;
    typedef int (*kakasi_getopt_argv_t)(int argc, char **argv);
    typedef char* (*kakasi_do_t)(char *str);

    #ifdef _WIN32
    // Windows version
    static void portable_setenv(const char *name, const char *value)
    {
        _putenv_s(name, value); // overwrites existing value
    }

    static void *portable_load_library(const char *path)
    {
        return LoadLibrary(path);
    }

    static int portable_kakasi_getopt_argv(void *handle, int argc, char **argv)
    {
        kakasi_getopt_argv_t f = (kakasi_getopt_argv_t)GetProcAddress(handle, "kakasi_getopt_argv");
        return f(argc, argv);
    }

    static char *portable_kakasi_do(void *handle, char *str)
    {
        kakasi_do_t f = (kakasi_do_t)GetProcAddress(handle, "kakasi_do");
        return f(str);
    }

    static int portable_kakasi_close(void *handle)
    {
        return FreeLibrary(handle);
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

%include "argv.i"

/* euc_byte_t * - treat as byte[] */
%typemap(in, noblock=1) euc_byte_t * {
 $1 = 0;
  if ($input) {
    $1 = ($1_ltype)JCALL2(GetByteArrayElements, jenv, $input, 0);
    if (!$1) return $null;
  }
}

%typemap(directorout, noblock=1, warning=SWIGWARN_TYPEMAP_DIRECTOROUT_PTR_MSG) euc_byte_t * {
  $1 = 0;
  if ($input) {
    $result = ($1_ltype)JCALL2(GetByteArrayElements, jenv, $input, 0);
    if (!$result) return $null;
  }
}

%typemap(directorin, descriptor="[B", noblock=1) euc_byte_t * {
  $input = 0;
  if ($1) {
    int len = strlen($1);
    jbyteArray arr = JCALL1(NewByteArray, jenv, len);
    $input = JCALL4(SetByteArrayRegion, jenv, arr, 0, len, (const euc_byte_t *)$1);
    free((void*)$1);
    if (!$input) return $null;
  }
  Swig::LocalRefGuard $1_refguard(jenv, $input);
}

%typemap(freearg, noblock=1) euc_byte_t * { if ($1) JCALL3(ReleaseByteArrayElements, jenv, $input, (euc_byte_t *)$1, 0); }
%typemap(out, noblock=1) euc_byte_t * { 
    if ($1) {
        int len = strlen($1);
        $result = JCALL1(NewByteArray, jenv, len);
        JCALL4(SetByteArrayRegion, jenv, $result, 0, len, (const euc_byte_t *)$1);
        free((void*)$1);
    } 
}
%typemap(javadirectorin) euc_byte_t * "$jniinput"
%typemap(javadirectorout) euc_byte_t * "$javacall"

%typemap(jni) euc_byte_t * "jbyteArray"
%typemap(jtype) euc_byte_t * "byte[]"
%typemap(jstype) euc_byte_t * "byte[]"
%typemap(javain) euc_byte_t * "$javainput"
%typemap(javaout) euc_byte_t * {
    return $jnicall;
  }

/* Parse the header file to generate wrappers */
%inline %{
void set_kanwadict(char *path) {
    portable_setenv("KANWADICT", path);
}

void set_itaijidict(char *path) {
    portable_setenv("ITAIJIDICT", path);
}

void *load_library(char *lib) {
    return portable_load_library(lib);
}

int kakasi_getopt_argv(void *handle, int argc, char **argv) {
    return portable_kakasi_getopt_argv(handle, argc, argv);
}

euc_byte_t *kakasi_do(void *handle, euc_byte_t *str) {
   return portable_kakasi_do(handle, str);
}

int kakasi_close(void *handle) {
    return portable_kakasi_close(handle);
}
%}