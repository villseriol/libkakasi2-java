// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiAllYomiTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        kakasi.configure(KakasiConstants.ASCII_CONFIG_DEBUG);
    }


    @Test
    public void testMultipleReadings() {
        // Single known word with multiple readings
        assertEquals("{nippon|nihon}", kakasi.run("日本"));
        assertEquals("{nichi|hi|ka|jitsu|bi|kusa|pi}", kakasi.run("日"));
        assertEquals("kayoubi", kakasi.run("火曜日"));
        assertEquals("{me|boku|ma|moku}", kakasi.run("目"));
        assertEquals("mokuteki", kakasi.run("目的"));
        assertEquals("mokuji", kakasi.run("目次"));
        assertEquals("mokuhyou", kakasi.run("目標"));

        // Second word is garbage, normal operation will prioritize the first
        assertEquals("{mezawari|mezahari}", kakasi.run("目障り"));

        // Words with one reading
        assertEquals("toukyou", kakasi.run("東京"));
        assertEquals("oosaka", kakasi.run("大阪"));
        assertEquals("kyouto", kakasi.run("京都"));
        assertEquals("sapporo", kakasi.run("札幌"));

        // Mixed sentence
        assertEquals("{nippon|nihon} no toukyou to oosaka", kakasi.run("日本の東京と大阪"));

        // Empty string
        assertEquals("", kakasi.run(""));

        // Non-Japanese characters
        assertEquals("123 abc !@#", kakasi.run("123 abc !@#"));
    }
}
