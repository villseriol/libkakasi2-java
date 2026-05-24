/* 
 * unsigned char *NIOBUFFER typemaps. 
 * This is for mapping Java nio buffers to C char arrays.
 * It is useful for performance critical code as it reduces the memory copy an marshaling overhead.
 * Note: The Java buffer has to be allocated with allocateDirect.
 *
 * Example usage wrapping:
 *   %apply unsigned char *NIOBUFFER { unsigned char *buf };
 *   void foo(unsigned char *buf);
 *  
 * Java usage:
 *   java.nio.ByteBuffer b = ByteBuffer.allocateDirect(20); 
 *   modulename.foo(b);
 */
%typemap(jni) unsigned char *NIOBUFFER "jobject"  
%typemap(jtype) unsigned char *NIOBUFFER "java.nio.ByteBuffer"  
%typemap(jstype) unsigned char *NIOBUFFER "java.nio.ByteBuffer"  
%typemap(javain,
  pre="  assert $javainput.isDirect() : \"Buffer must be allocated direct.\";") unsigned char *NIOBUFFER "$javainput"
%typemap(javaout) unsigned char *NIOBUFFER {  
  return $jnicall;  
}  
%typemap(in) unsigned char *NIOBUFFER {  
  $1 = (unsigned char *) JCALL1(GetDirectBufferAddress, jenv, $input); 
  if ($1 == NULL) {  
    SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "Unable to get address of a java.nio.ByteBuffer direct byte buffer. Buffer must be a direct buffer and not a non-direct buffer.");  
  }  
}  
%typemap(memberin) unsigned char *NIOBUFFER {  
  if ($input) {  
    $1 = $input;  
  } else {  
    $1 = 0;  
  }  
}  
%typemap(freearg) unsigned char *NIOBUFFER ""  

%apply unsigned char *NIOBUFFER { kakasi_byte_t *buffer };