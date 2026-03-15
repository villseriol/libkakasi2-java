// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiFuriganaTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        KakasiConfig config = new KakasiConfig(KakasiConstants.FURIGANA_CONFIG);
        config.setFuriganaLeft("{");
        config.setFuriganaRight("}");

        kakasi.configure(config);

        String first = kakasi.run("にほんご");
        assertEquals("にほんご", first);
    }


    @Test
    public void testFurigana() {
        assertEquals("日本{にっぽん}", kakasi.run("日本"));
        assertEquals("山{やま}", kakasi.run("山"));
        assertEquals("派{は}", kakasi.run("派"));
    }
}
