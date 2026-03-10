// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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


    @Test
    public void testFinalization() {
        Kakasi kakasi = new Kakasi();
        assertDoesNotThrow(() -> {
            kakasi.close();
        });

        assertThrows(KakasiRuntimeException.class, () -> {
            kakasi.run("にほんご");
        });
    }


    @Test
    public void testTryWithResources() {
        try (Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG)) {
            boolean success = kakasi.configure(KakasiConstants.ASCII_CONFIG);
            assertTrue(success);

            String result = kakasi.run("にほんご");
            assertNotNull(result);
            assertEquals("nihongo", result);
        } catch (Exception e) {
            fail();
        }
    }


    /**
    * This is a known issue in the underlying library. If the first phrase you pass into the library is not in
    * the regular character set, the internal buffers fail to initialize. As a workaround, you should immediately
    * run it with dummy data to ensure correct behaviour.
    */
    @Test
    public void testBadBufferInitialization() {
        try (Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG)) {
            assertNotEquals("ka a . ga^den", kakasi.run("珈亜・ガーデン"));
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testCorrectBufferInitialization() {
        try (Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG)) {
            assertEquals("ga^den", kakasi.run("ガーデン"));
            assertEquals("ka a . ga^den", kakasi.run("珈亜・ガーデン"));
        } catch (Exception e) {
            fail();
        }
    }
}
