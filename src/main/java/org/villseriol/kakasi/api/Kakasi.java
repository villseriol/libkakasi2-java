// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.villseriol.kakasi.internal.NativeLoader;
import org.villseriol.kakasi.jni.kakasi;


public final class Kakasi {
    private static final Charset EUC_JP = Charset.forName("EUC-JP");

    static {
        NativeLoader.loadLibrary();
        NativeLoader.loadDataFiles();
    }

    private Kakasi() {
        super();
    }


    /**
     * Configure that kakasi shared library globally using the specified config.
     *
     * @param config the config
     * @return true if successful, false otherwise
     */
    public static boolean configure(final KakasiConfig config) {
        String[] argv = config.getArguments();

        int success = kakasi.kakasi_getopt_argv(argv);

        return success == 0;
    }


    /**
     * Run kakasi on the provided string and return a converted string.
     *
     * @param input the string
     * @return the converted string
     */
    public static String run(final String input) {
        StringBuilder sb = new StringBuilder(input);
        boolean isNullTerminated = input.endsWith("\0");
        if (!isNullTerminated) {
            sb.append("\0");
        }

        byte[] encodedIn = encodeToEuc(sb.toString());

        return kakasi.kakasi_do(encodedIn);
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


    /**
     * Encode the provided string into a byte array using the UTF-8 encoding.
     *
     * @param input the string
     * @return the byte array
     */
    private static byte[] encodeToUtf8(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }
}
