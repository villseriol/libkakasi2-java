// This software is released into the Public Domain. See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiTest {
    @BeforeEach
    public void setUp() {
        KakasiConfig config = KakasiConfig.createDefaultConfig();
        Kakasi.configure(config);
    }


    @AfterEach
    public void tareDown() {
        // Do nothing
    }


    @Test
    public void testSimpleAscii() {
        assertEquals("test (villseriol)", Kakasi.run("test (villseriol)"));
        assertEquals("test", Kakasi.run("test"));
    }


    @Test
    public void testZenkaku() {
        assertEquals("Fullwidth", Kakasi.run("Ｆｕｌｌｗｉｄｔｈ"));
    }


    @Test
    public void testKatakana() {
        assertEquals("aporobe^kari^", Kakasi.run("アポロベーカリー"));
    }


    @Test
    public void testKanji() {
        // Known issues with kakasi and dictionary entry priority
        assertNotEquals("nihon", Kakasi.run("日本"));

        // Only works when using .geo dictionary, but causes other entries to fail

        // For OSM projects, exclude the .geo dictionary
        // If the entry is of geographic importance, it most likely has an english/romaji entry anyway
        assertNotEquals("shirahanechou", Kakasi.run("白羽根町"));

        assertEquals("a^kanso^ shuu", Kakasi.run("アーカンソー州"));
        assertEquals("Fullwidth & kanji", Kakasi.run("Ｆｕｌｌｗｉｄｔｈ ＆ 漢字"));
        assertEquals("anan ichiritsu aba kubou . minzoku shiryoukan", Kakasi.run("髙座公民館"));
        assertEquals("anan ichiritsu aba kubou . minzoku shiryoukan", Kakasi.run("阿南市立阿波公方・民俗資料館"));
    }
}
