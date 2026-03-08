// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

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


    public final String getLargeDictionary() {
        return getClass().getClassLoader().getResource("SKK-JISYO.L").getPath();
    }


    public final String getGeoDictionary() {
        return getClass().getClassLoader().getResource("SKK-JISYO.geo").getPath();
    }
}
