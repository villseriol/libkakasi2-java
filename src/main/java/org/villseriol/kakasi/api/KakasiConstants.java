// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.HashSet;


public final class KakasiConstants {

    private KakasiConstants() {
        super();
    }

    /**
     * No config.
     */
    public static final KakasiConfig EMPTY_CONFIG = new KakasiConfig();

    /**
     * Converts all japanese to ascii and shows all readings for each word.
     */
    public static final KakasiConfig ASCII_ALL_READINGS_CONFIG = new KakasiConfig() {
        {
            setSeparator(" ");
            setShowAllReadings(true);

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

    /**
     * Adds furigana to all kanji characters.
     */
    public static final KakasiConfig FURIGANA_CONFIG = new KakasiConfig() {
        {
            setSeparator(" ");
            setFuriganaGrade(KakasiFuriganaGrade.ALL);

            setTranslations(new HashSet<>() {
                {
                    add(new KakasiTranslation(KakasiCharsetCategory.KANJI, KakasiCharsetCategory.HIRAGANA));
                }
            });
        }
    };

    /**
     * Converts all japanese to ascii.
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
