// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.ArrayList;
import java.util.List;


/**
 * A romaji enum used for configuring the romanization method.
 */
public enum KakasiRomaji {
    /**
     * Hepburn romanization method.
     */
    HEPBURN("hepburn"),
    /**
     * Kunrei romanization method.
     */
    KUNREI("kunrei");

    private final String code;

    KakasiRomaji(final String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }


    public static String[] codes() {
        List<String> result = new ArrayList<>();
        for (KakasiRomaji v : KakasiRomaji.values()) {
            result.add(v.getCode());
        }
        return result.toArray(String[]::new);
    }
}
