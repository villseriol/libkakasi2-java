// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiHalfWidthTest extends AbstractTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        kakasi.configure(KakasiConstants.ASCII_CONFIG);
    }


    /**
     * The underlying library does not handle half-width characters.
     */
    @Test
    public void testHalfWidthKatakana() {
        // Vowels
        assertNotEquals("a", kakasi.run("ｱ"));
        assertNotEquals("i", kakasi.run("ｲ"));
        assertNotEquals("u", kakasi.run("ｳ"));
        assertNotEquals("e", kakasi.run("ｴ"));
        assertNotEquals("o", kakasi.run("ｵ"));

        // K-group
        assertNotEquals("ka", kakasi.run("ｶ"));
        assertNotEquals("ki", kakasi.run("ｷ"));
        assertNotEquals("ku", kakasi.run("ｸ"));
        assertNotEquals("ke", kakasi.run("ｹ"));
        assertNotEquals("ko", kakasi.run("ｺ"));

        // S-group
        assertNotEquals("sa", kakasi.run("ｻ"));
        assertNotEquals("shi", kakasi.run("ｼ"));
        assertNotEquals("su", kakasi.run("ｽ"));
        assertNotEquals("se", kakasi.run("ｾ"));
        assertNotEquals("so", kakasi.run("ｿ"));

        // T-group
        assertNotEquals("ta", kakasi.run("ﾀ"));
        assertNotEquals("chi", kakasi.run("ﾁ"));
        assertNotEquals("tsu", kakasi.run("ﾂ"));
        assertNotEquals("te", kakasi.run("ﾃ"));
        assertNotEquals("to", kakasi.run("ﾄ"));

        // N-group
        assertNotEquals("na", kakasi.run("ﾅ"));
        assertNotEquals("ni", kakasi.run("ﾆ"));
        assertNotEquals("nu", kakasi.run("ﾇ"));
        assertNotEquals("ne", kakasi.run("ﾈ"));
        assertNotEquals("no", kakasi.run("ﾉ"));

        // H-group
        assertNotEquals("ha", kakasi.run("ﾊ"));
        assertNotEquals("hi", kakasi.run("ﾋ"));
        assertNotEquals("fu", kakasi.run("ﾌ"));
        assertNotEquals("he", kakasi.run("ﾍ"));
        assertNotEquals("ho", kakasi.run("ﾎ"));

        // M-group
        assertNotEquals("ma", kakasi.run("ﾏ"));
        assertNotEquals("mi", kakasi.run("ﾐ"));
        assertNotEquals("mu", kakasi.run("ﾑ"));
        assertNotEquals("me", kakasi.run("ﾒ"));
        assertNotEquals("mo", kakasi.run("ﾓ"));

        // Y-group
        assertNotEquals("ya", kakasi.run("ﾔ"));
        assertNotEquals("yu", kakasi.run("ﾕ"));
        assertNotEquals("yo", kakasi.run("ﾖ"));

        // R-group
        assertNotEquals("ra", kakasi.run("ﾗ"));
        assertNotEquals("ri", kakasi.run("ﾘ"));
        assertNotEquals("ru", kakasi.run("ﾙ"));
        assertNotEquals("re", kakasi.run("ﾚ"));
        assertNotEquals("ro", kakasi.run("ﾛ"));

        // W-group
        assertNotEquals("wa", kakasi.run("ﾜ"));
        assertNotEquals("wo", kakasi.run("ｦ"));
        assertNotEquals("n", kakasi.run("ﾝ"));

        // Dakuten (voiced) characters
        assertNotEquals("ga", kakasi.run("ｶﾞ"));
        assertNotEquals("gi", kakasi.run("ｷﾞ"));
        assertNotEquals("gu", kakasi.run("ｸﾞ"));
        assertNotEquals("ge", kakasi.run("ｹﾞ"));
        assertNotEquals("go", kakasi.run("ｺﾞ"));

        // Handakuten (p-sounds)
        assertNotEquals("pa", kakasi.run("ﾊﾟ"));
        assertNotEquals("pi", kakasi.run("ﾋﾟ"));
        assertNotEquals("pu", kakasi.run("ﾌﾟ"));
        assertNotEquals("pe", kakasi.run("ﾍﾟ"));
        assertNotEquals("po", kakasi.run("ﾎﾟ"));
    }
}
