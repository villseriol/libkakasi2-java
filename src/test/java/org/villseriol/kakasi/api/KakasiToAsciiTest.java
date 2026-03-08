// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiToAsciiTest extends AbstractTest {
    @BeforeEach
    public void setUp() {
        KakasiConfig config = createAllToAsciiConfig();

        Kakasi.configure(config);
    }


    @Test
    public void testUntouched() {
        assertEquals("test (villseriol)", Kakasi.run("test (villseriol)"));
        assertEquals("test", Kakasi.run("test"));
    }


    @Test
    public void testZenkaku() {
        assertEquals("Fullwidth", Kakasi.run("Ｆｕｌｌｗｉｄｔｈ"));
    }


    @Test
    public void testAdjective() {
        assertEquals("shoujiki", Kakasi.run("正直"));
        assertEquals("byoudou", Kakasi.run("平等"));
        assertEquals("shounin", Kakasi.run("商人"));
        assertEquals("kokkyou", Kakasi.run("国境"));
        assertEquals("ichiban", Kakasi.run("一番"));
    }


    @Test
    public void testNoun() {
        assertEquals("furo", Kakasi.run("風呂"));
        assertEquals("hakubutsukan", Kakasi.run("博物館"));
        assertNotEquals("nihon", Kakasi.run("日本"));
        assertEquals("aporobe^kari^", Kakasi.run("アポロベーカリー"));
        assertEquals("a^kanso^ shuu", Kakasi.run("アーカンソー州"));

        assertNotEquals("shirahanechou", Kakasi.run("白羽根町"));
        assertEquals("anan ichiritsu awa kubou . minzoku shiryoukan", Kakasi.run("阿南市立阿波公方・民俗資料館"));
    }
}
