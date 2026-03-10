// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.internal;

import java.io.File;

import org.villseriol.kakasi.jni.SWIGTYPE_p_void;
import org.villseriol.kakasi.jni.kakasi;


public class KakasiState implements Runnable {
    private final SWIGTYPE_p_void handle;
    private final File library;
    private boolean closed;

    public KakasiState(final SWIGTYPE_p_void handle, final File library) {
        super();

        this.handle = handle;
        this.library = library;
    }


    public SWIGTYPE_p_void getHandle() {
        return handle;
    }


    public File getLibrary() {
        return library;
    }


    public boolean isClosed() {
        return closed;
    }


    @Override
    public void run() {
        // mark as closed even if the next steps fail
        closed = true;

        kakasi.kakasi_close(handle);
        library.delete();
    }
}
