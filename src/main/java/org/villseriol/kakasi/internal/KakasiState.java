// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.internal;

import org.villseriol.kakasi.jni.SWIGTYPE_p_void;
import org.villseriol.kakasi.jni.kakasi;


public class KakasiState implements Runnable {
    private final SWIGTYPE_p_void handle;

    public KakasiState(final SWIGTYPE_p_void handle) {
        super();

        this.handle = handle;
    }


    public SWIGTYPE_p_void getHandle() {
        return handle;
    }


    @Override
    public void run() {
        kakasi.kakasi_close(handle);
    }
}
