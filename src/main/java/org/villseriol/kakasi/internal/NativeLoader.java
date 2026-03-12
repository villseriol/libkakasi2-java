// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.villseriol.kakasi.jni.kakasi;


public final class NativeLoader {
    private static volatile boolean isLoaderLoaded = false;
    private static volatile boolean isDictionaryLoaded = false;

    private NativeLoader() {
        super();
    }


    /**
     * A helper method to return a file from resources. The resource is copied to a temporary file
     * using provided prefix and suffix. It is deleted on exit.
     *
     * @param path the resource path
     * @param prefix the prefix of the temporary file
     * @param suffix the suffix of the temporary file
     * @return the file
     */
    private static File loadFromResources(String path, String prefix, String suffix) {
        try (InputStream in = NativeLoader.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new RuntimeException("Resource not found: " + path);
            }

            Path temp = Files.createTempFile(prefix, suffix);
            temp.toFile().deleteOnExit();

            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);

            return temp.toFile();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load native library", e);
        }
    }


    public static File loadNewKakasiLibrary() {
        String os = System.getProperty("os.name").toLowerCase();
        String libFileName;
        String resourcePath;
        String tempSuffix;

        if (os.contains("win")) {
            libFileName = "libkakasi.dll";
            resourcePath = "/native/windows/" + libFileName;
            tempSuffix = ".dll";
        } else if (os.contains("linux")) {
            libFileName = "libkakasi.so";
            resourcePath = "/native/linux/" + libFileName;
            tempSuffix = ".so";
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os);
        }

        return loadFromResources(resourcePath, "libkakasi", tempSuffix);
    }


    public static synchronized void bootstrapDataFiles() {
        if (isDictionaryLoaded) {
            return; // load only once
        }

        File kanwadict = loadFromResources("/data/kanwadict", "kanwadict", ".tmp");
        File itaijidict = loadFromResources("/data/itaijidict", "itaijidict", ".tmp");

        kakasi.set_kanwadict(kanwadict.getAbsolutePath());
        kakasi.set_itaijidict(itaijidict.getAbsolutePath());

        isDictionaryLoaded = true;
    }


    public static synchronized void bootstrapLoaderLibrary() {
        if (isLoaderLoaded) {
            return; // load only once
        }

        String os = System.getProperty("os.name").toLowerCase();
        String libFileName;
        String resourcePath;
        String tempSuffix;

        if (os.contains("win")) {
            libFileName = "libkakasi_jni.dll";
            resourcePath = "/native/windows/" + libFileName;
            tempSuffix = ".dll";
        } else if (os.contains("linux")) {
            libFileName = "libkakasi_jni.so";
            resourcePath = "/native/linux/" + libFileName;
            tempSuffix = ".so";
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os);
        }

        File library = loadFromResources(resourcePath, "libkakasi_jni", tempSuffix);

        System.load(library.getAbsolutePath());

        isLoaderLoaded = true;
    }
}
