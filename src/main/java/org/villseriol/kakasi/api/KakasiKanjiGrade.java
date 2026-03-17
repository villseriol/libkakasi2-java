// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.ArrayList;
import java.util.List;


/**
 * Used to configure the minimum kanji grade for kanji-hiragana translations.
 * Split into three main categories: 小学校1年生～6年生, 常用漢字(中学校卒業程度), 人名用漢字
 */
public enum KakasiKanjiGrade {
    /**
     * Grade 0 (no grade).
     */
    ZERO("0"),
    /**
     * Grade 1 kanji (taught in the first year of Japanese elementary school).
     */
    ONE("1"),
    /**
     * Grade 2 kanji.
     */
    TWO("2"),
    /**
     * Grade 3 kanji.
     */
    THREE("3"),
    /**
     * Grade 4 kanji.
     */
    FOUR("4"),
    /**
     * Grade 5 kanji.
     */
    FIVE("5"),
    /**
     * Grade 6 kanji (final set taught in Japanese elementary school).
     */
    SIX("6"),
    /**
     * Grade 7 kanji (junior high school level).
     */
    SEVEN("7"),
    /**
     * Grade 8 kanji (junior high school level).
     */
    EIGHT("8"),
    /**
     * Grade 9 kanji (extended or advanced kanji set).
     */
    NINE("9"),
    /**
     * Jinmeiyō kanji (kanji permitted for use in personal names).
     */
    J("j"),
    /**
     * Non-standard or additional kanji not included in the standard grade sets.
     */
    N("n");

    private final String code;

    KakasiKanjiGrade(final String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }


    public static String[] codes() {
        List<String> result = new ArrayList<>();
        for (KakasiKanjiGrade v : KakasiKanjiGrade.values()) {
            result.add(v.getCode());
        }
        return result.toArray(String[]::new);
    }

}
