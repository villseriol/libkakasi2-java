// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiToAsciiTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        kakasi.configure(KakasiConstants.ASCII_CONFIG);
    }


    @Test
    public void testUntouched() {
        assertEquals("test (villseriol)", kakasi.run("test (villseriol)"));
        assertEquals("test", kakasi.run("test"));
    }


    @Test
    public void testZenkaku() {
        assertEquals("Fullwidth", kakasi.run("Ｆｕｌｌｗｉｄｔｈ"));
    }


    @Test
    public void testAdjective() {
        assertEquals("shoujiki", kakasi.run("正直"));
        assertEquals("byoudou", kakasi.run("平等"));
        assertEquals("shounin", kakasi.run("商人"));
        assertEquals("kokkyou", kakasi.run("国境"));
        assertEquals("ichiban", kakasi.run("一番"));
    }


    @Test
    public void testNoun() {
        assertEquals("furo", kakasi.run("風呂"));
        assertEquals("hakubutsukan", kakasi.run("博物館"));
        assertNotEquals("nihon", kakasi.run("日本"));
        assertEquals("aporobe^kari^", kakasi.run("アポロベーカリー"));
        assertEquals("a^kanso^ shuu", kakasi.run("アーカンソー州"));

        assertNotEquals("shirahanechou", kakasi.run("白羽根町"));
        assertEquals("anan ichiritsu awa kubou . minzoku shiryoukan", kakasi.run("阿南市立阿波公方・民俗資料館"));
    }
}
