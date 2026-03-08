// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiBufferInitializeTest extends AbstractTest {
    @BeforeEach
    public void setUp() {
        KakasiConfig config = createAllToAsciiConfig();

        Kakasi.configure(config);
    }


    /**
     * This is a known issue in the underlying library. If the first phrase you pass into the library is not in
     * the regular character set, the internal buffers fail to initialize. As a workaround, you should immediately
     * run with dummy data to ensure correct initialization.
     */
    @Test
    public void testBadBufferInitialization() {
        assertNotEquals("ka a . ga^den", Kakasi.run("珈亜・ガーデン"));
    }


    @Test
    public void testCorrectBufferInitialization() {
        assertEquals("ga^den", Kakasi.run("ガーデン"));
        assertEquals("ka a . ga^den", Kakasi.run("珈亜・ガーデン"));
    }

}
