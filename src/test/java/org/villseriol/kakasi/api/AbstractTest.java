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


    public final String getLargeDictionary() throws URISyntaxException {
        URL url = getClass().getResource("/SKK-JISYO.L");
        File file = new File(url.toURI()); // converts to proper platform path
        String path = file.getAbsolutePath();

        return path;
    }

    public final String getGeoDictionary() throws URISyntaxException {
        URL url = getClass().getResource("/SKK-JISYO.geo");
        File file = new File(url.toURI()); // converts to proper platform path
        String path = file.getAbsolutePath();

        return path;
    }
}
