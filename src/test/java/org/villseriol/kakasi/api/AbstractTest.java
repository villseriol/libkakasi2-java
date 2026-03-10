// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public abstract class AbstractTest {
    private String loadTestResource(String path) throws URISyntaxException {
        URL url = getClass().getResource(path);
        File file = new File(url.toURI());

        return file.getAbsolutePath();
    }


    public final String getLargeDictionary() throws URISyntaxException {
        return loadTestResource("/SKK-JISYO.L");
    }


    public final String getGeoDictionary() throws URISyntaxException {
        return loadTestResource("/SKK-JISYO.geo");
    }
}
