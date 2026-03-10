// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

/**
 * A level enum used for configuring the hiragana and furigana.
 *
 * @author Villseriol
 */
public enum KakasiLevel {
    /**
     *
     */
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), J("l"), N("n");

    private final String code;

    KakasiLevel(final String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}
