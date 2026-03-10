// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


public class KakasiMultipleConfigurationTest extends AbstractTest {

    /**
     * Test to ensure that multiple configured instances of kakasi do not conflict with each other.
     */
    @Test
    public void testSameWordMultipleConfiguration() {
        Kakasi kakasiB = createWithGeoDictionary();
        Kakasi kakasiA = createWithNoDictionary();

        assertEquals("kagoyamachi", kakasiB.run("籠屋町"));
        assertEquals("kago ya machi", kakasiA.run("籠屋町"));
    }


    /**
     * Initializes a kakasi instance with no dictionaries.
     *
     * @return the instance
     */
    private Kakasi createWithNoDictionary() {
        Kakasi result = new Kakasi(KakasiConstants.ASCII_CONFIG);

        String first = result.run("にほんご");
        assertEquals(first, "nihongo");

        return result;
    }


    /**
     * Initializes a kakasi instance with the geo dictionary.
     *
     * @return the instance
     */
    private Kakasi createWithGeoDictionary() {
        Kakasi result = new Kakasi();

        KakasiConfig config = new KakasiConfig(KakasiConstants.ASCII_CONFIG);
        assertDoesNotThrow(() -> {
            String geo = getGeoDictionary();
            config.setDictionaries(List.of(geo));
        });

        result.configure(config);

        String first = result.run("にほんご");
        assertEquals(first, "nihongo");

        return result;
    }
}
