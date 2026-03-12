// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Im not entirely sure what the use-case of this feature is. The library
 * appears to successfully skip the specified characters but then appends them
 * at the end of the word. Very useless...
 */
public class KakasiSkipCharactersTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        KakasiConfig config = new KakasiConfig(KakasiConstants.ASCII_CONFIG);
        config.setSkipCharacters(List.of('/'));
        config.setSeparator("");

        kakasi.configure(config);
    }


    @Test
    public void testSkippingCharactersInJukugo() {
        assertEquals("tokushima///(nippon)", kakasi.run("徳///島(日本)"));
    }
}
