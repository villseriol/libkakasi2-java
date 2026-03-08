// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class KakasiInitializationTest {
    @Test
    public void testInitialization() {
        Kakasi kakasiA = new Kakasi();
        assertNotNull(kakasiA);

        Kakasi kakasiB = new Kakasi();
        assertNotNull(kakasiB);

        assertNotEquals(kakasiA, kakasiB);
    }
}
