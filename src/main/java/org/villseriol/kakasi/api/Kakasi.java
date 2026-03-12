// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.io.Closeable;
import java.io.File;
import java.lang.ref.Cleaner;
import java.lang.ref.Cleaner.Cleanable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.villseriol.kakasi.internal.KakasiState;
import org.villseriol.kakasi.internal.NativeLoader;
import org.villseriol.kakasi.jni.SWIGTYPE_p_void;
import org.villseriol.kakasi.jni.kakasi;


/**
 * Create an instance of this class to access functions inside the kakasi C
 * library.
 */
public class Kakasi implements Closeable {
    private static final Charset EUC_JP = Charset.forName("EUC-JP");
    private static final CharsetEncoder ENCODER = EUC_JP.newEncoder();
    private static final int PADDING = (int) Math.ceil(ENCODER.maxBytesPerChar());

    private static final Cleaner CLEANER = Cleaner.create();

    // Cleaner registration
    private final Cleanable cleanable;
    private final KakasiState state;

    static {
        NativeLoader.bootstrapLoaderLibrary();
        NativeLoader.bootstrapDataFiles();
    }

    /**
     * Create an instance and configure it with an empty config.
     */
    public Kakasi() {
        this(KakasiConstants.EMPTY_CONFIG);
    }


    /**
     * Create an instance and immediately configure it.
     *
     * @param config the config
     */
    public Kakasi(final KakasiConfig config) {
        super();

        File libraryFile = NativeLoader.loadNewKakasiLibrary();
        SWIGTYPE_p_void libraryHandle = kakasi.load_library(libraryFile.getAbsolutePath());

        this.state = new KakasiState(libraryHandle, libraryFile);

        this.cleanable = CLEANER.register(this, this.state);

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

        byte[] encodedIn = eucEncodeFromUtf(input);
        byte[] terminatedIn = new byte[encodedIn.length + PADDING];
        System.arraycopy(encodedIn, 0, terminatedIn, 0, encodedIn.length);

        SWIGTYPE_p_void handle = this.state.getHandle();

        byte[] encodedOut = kakasi.kakasi_do(handle, terminatedIn);

        return utfDecodeFromEuc(encodedOut);
    }


    /**
     * Decode the provided byte array.
     *
     * @param input the byte array
     * @return the string
     */
    private static String utfDecodeFromEuc(byte[] input) {
        ByteBuffer buffer = ByteBuffer.wrap(input);

        return EUC_JP.decode(buffer).toString();
    }


    /**
     * Encode the provided string into a byte array using the EUC-JP encoding.
     *
     * @param input the string
     * @return the byte array
     */
    private static byte[] eucEncodeFromUtf(String input) {
        return input.getBytes(EUC_JP);
    }


    @Override
    public void close() {
        cleanable.clean();
    }
}
