// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiGeoTest extends AbstractTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        KakasiConfig config = new KakasiConfig(KakasiConstants.ASCII_CONFIG);

        assertDoesNotThrow(() -> {
            String geo = getGeoDictionaryPath();
            config.setDictionaries(List.of(geo));
        });

        kakasi.configure(config);

        String first = kakasi.run("にほんご");
        assertEquals(first, "nihongo");
    }


    @Test
    public void testNoun() {
        assertEquals("kagoyamachi", kakasi.run("籠屋町"));
    }
}
