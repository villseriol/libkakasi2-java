// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.HashSet;


public final class KakasiConstants {

    private KakasiConstants() {
        super();
    }

    /**
     * A config for converting all japanese characters to ascii.
     */
    public static final KakasiConfig ASCII_CONFIG = new KakasiConfig() {
        {
            setSeparator(" ");

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
    };;
}
