// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;


public abstract class AbstractTest {
    public final KakasiConfig createAllToAsciiConfig() {
        return new KakasiConfig() {
            {
                setSeparatorEnabled(true);

                setTranslations(new HashSet<>() {
                    {
                        add(new KakasiTranslation(KakasiCharsetCategory.HIRAGANA, KakasiCharsetCategory.ASCII));
                        add(new KakasiTranslation(KakasiCharsetCategory.KANJI, KakasiCharsetCategory.ASCII));
                        add(new KakasiTranslation(KakasiCharsetCategory.KATAKANA_JIS, KakasiCharsetCategory.ASCII));
                        add(new KakasiTranslation(KakasiCharsetCategory.SIGN, KakasiCharsetCategory.ASCII));
                        add(new KakasiTranslation(KakasiCharsetCategory.KATAKANA, KakasiCharsetCategory.ASCII));
                    }
                });
            }
        };
    }


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
