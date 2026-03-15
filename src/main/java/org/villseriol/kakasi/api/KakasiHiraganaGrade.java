// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

/**
 * Used to configure the minimum kanji grade to kanji-hiragana translations.
 */
public enum KakasiHiraganaGrade {
    /**
     *
     */
    ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    /**
     *
     */
    SEVEN("7"), EIGHT("8"), NINE("9"), J("j"), N("n");

    private final String code;

    KakasiHiraganaGrade(final String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
