// This software is released into the Public Domain. See copying.txt for details.
package org.villseriol.kakasi.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.villseriol.kakasi.jni.kakasi;


public final class NativeLoader {
    private static boolean isLibraryLoaded = false;
    private static boolean isDataLoaded = false;

    private NativeLoader() {
        super();
    }


    public static void loadDataFiles() {
        if (isDataLoaded) {
            return; // load only once
        }

        Path tempDir;
        try {
            // Create a temporary folder
            tempDir = Files.createTempDirectory("kakasi_data");
            tempDir.toFile().deleteOnExit();

            String kanwaDictPath = "/data/kanwadict";
            try (InputStream in = NativeLoader.class.getResourceAsStream(kanwaDictPath)) {
                if (in == null) {
                    throw new RuntimeException("Resource not found: " + kanwaDictPath);
                }

                Path tempFile = tempDir.resolve("kanwadict");
                Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
                tempFile.toFile().deleteOnExit();

                kakasi.set_kanwadict(tempFile.toAbsolutePath().toString());
            }

            String itaijiDictPath = "/data/itaijidict";
            try (InputStream in = NativeLoader.class.getResourceAsStream(itaijiDictPath)) {
                if (in == null) {
                    throw new RuntimeException("Resource not found: " + itaijiDictPath);
                }

                Path tempFile = tempDir.resolve("itaijidict");
                Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
                tempFile.toFile().deleteOnExit();

                kakasi.set_itaijidict(tempFile.toAbsolutePath().toString());
            }

            isDataLoaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Kakasi data folder", e);
        }
    }


    public static void loadLibrary() {
        if (isLibraryLoaded) {
            return; // load only once
        }

        String libName = "libkakasi_jni.so"; // JNI library compiled for Linux

        try (InputStream in = NativeLoader.class.getResourceAsStream("/native/linux/" + libName)) {
            if (in == null) {
                throw new RuntimeException("Native library not found in resources: " + libName);
            }

            Path temp = Files.createTempFile("libkakasi_jni", ".so");
            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
            System.load(temp.toAbsolutePath().toString());
            isLibraryLoaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load native library", e);
        }
    }
}
