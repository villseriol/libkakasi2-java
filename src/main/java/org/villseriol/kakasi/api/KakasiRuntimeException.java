// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

public class KakasiRuntimeException extends RuntimeException {
    public KakasiRuntimeException() {
        super();
    }


    public KakasiRuntimeException(String message) {
        super(message);
    }


    public KakasiRuntimeException(Throwable cause) {
        super(cause);
    }


    public KakasiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
