// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.io.Closeable;
import java.io.File;
import java.lang.ref.Cleaner;
import java.lang.ref.Cleaner.Cleanable;
import java.nio.charset.Charset;

import org.villseriol.kakasi.internal.KakasiState;
import org.villseriol.kakasi.internal.NativeLoader;
import org.villseriol.kakasi.jni.SWIGTYPE_p_void;
import org.villseriol.kakasi.jni.kakasi;


public class Kakasi implements Closeable {
    private static final Charset EUC_JP = Charset.forName("EUC-JP");
    private static final Cleaner CLEANER = Cleaner.create();

    // Cleaner registration
    private final Cleanable cleanable;
    private final KakasiState state;

    static {
        NativeLoader.bootstrapLoaderLibrary();
        NativeLoader.bootstrapDataFiles();
    }

    public Kakasi() {
        super();

        File libraryFile = NativeLoader.loadNewKakasiLibrary();
        SWIGTYPE_p_void libraryHandle = kakasi.load_library(libraryFile.getAbsolutePath());

        this.state = new KakasiState(libraryHandle, libraryFile);

        this.cleanable = CLEANER.register(this, this.state);
    }


    public Kakasi(final KakasiConfig config) {
        this();

        this.configure(config);
    }


    /**
     * Configure that kakasi shared library globally using the specified config.
     *
     * @param config the config
     * @return true if successful, false otherwise
     */
    public boolean configure(final KakasiConfig config) {
        if (state.isClosed()) {
            throw new KakasiRuntimeException("Kakasi instance has already been closed.");
        }

        String[] argv = config.getArguments();

        SWIGTYPE_p_void handle = this.state.getHandle();

        int success = kakasi.kakasi_getopt_argv(handle, argv);

        return success == 0;
    }


    /**
     * Run kakasi on the provided string and return a converted string.
     *
     * @param input the string
     * @return the converted string
     */
    public String run(final String input) {
        if (state.isClosed()) {
            throw new KakasiRuntimeException("Kakasi instance has already been closed.");
        }

        StringBuilder sb = new StringBuilder(input);
        boolean isNullTerminated = input.endsWith("\0");
        if (!isNullTerminated) {
            sb.append("\0");
        }

        byte[] encodedIn = encodeToEuc(sb.toString());

        SWIGTYPE_p_void handle = this.state.getHandle();

        return kakasi.kakasi_do(handle, encodedIn);
    }


    /**
     * Encode the provided string into a byte array using the EUC-JP encoding.
     *
     * @param input the string
     * @return the byte array
     */
    private static byte[] encodeToEuc(String input) {
        return input.getBytes(EUC_JP);
    }


    @Override
    public void close() {
        cleanable.clean();
    }
}
