// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public abstract class AbstractResourcesTest {
    /**
     * Loads a test resource given a path.
     *
     * @param path the path
     * @return the absolute path
     *
     * @throws URISyntaxException
     */
    private String getTestResourcePath(String path) throws URISyntaxException {
        URL url = getClass().getResource(path);
        File file = new File(url.toURI());

        return file.getAbsolutePath();
    }


    /**
     * Loads the SKK large dictionary path from test resources.
     *
     * @return the absolute path
     * @throws URISyntaxException
     */
    public final String getLargeDictionaryPath() throws URISyntaxException {
        return getTestResourcePath("/SKK-JISYO.L");
    }


    /**
     * Loads the SKK geo dictionary path from test resources.
     *
     * @return the absolute path
     * @throws URISyntaxException
     */
    public final String getGeoDictionaryPath() throws URISyntaxException {
        return getTestResourcePath("/SKK-JISYO.geo");
    }
}
