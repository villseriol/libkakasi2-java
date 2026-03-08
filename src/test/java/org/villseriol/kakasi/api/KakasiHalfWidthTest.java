// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiHalfWidthTest extends AbstractTest {
    @BeforeEach
    public void setUp() {
        KakasiConfig config = createAllToAsciiConfig();

        Kakasi.configure(config);
    }


    /**
     * The underlying library does not handle half-width characters.
     */
    @Test
    public void testHalfWidthKatakana() {
        // Vowels
        assertNotEquals("a", Kakasi.run("ｱ"));
        assertNotEquals("i", Kakasi.run("ｲ"));
        assertNotEquals("u", Kakasi.run("ｳ"));
        assertNotEquals("e", Kakasi.run("ｴ"));
        assertNotEquals("o", Kakasi.run("ｵ"));

        // K-group
        assertNotEquals("ka", Kakasi.run("ｶ"));
        assertNotEquals("ki", Kakasi.run("ｷ"));
        assertNotEquals("ku", Kakasi.run("ｸ"));
        assertNotEquals("ke", Kakasi.run("ｹ"));
        assertNotEquals("ko", Kakasi.run("ｺ"));

        // S-group
        assertNotEquals("sa", Kakasi.run("ｻ"));
        assertNotEquals("shi", Kakasi.run("ｼ"));
        assertNotEquals("su", Kakasi.run("ｽ"));
        assertNotEquals("se", Kakasi.run("ｾ"));
        assertNotEquals("so", Kakasi.run("ｿ"));

        // T-group
        assertNotEquals("ta", Kakasi.run("ﾀ"));
        assertNotEquals("chi", Kakasi.run("ﾁ"));
        assertNotEquals("tsu", Kakasi.run("ﾂ"));
        assertNotEquals("te", Kakasi.run("ﾃ"));
        assertNotEquals("to", Kakasi.run("ﾄ"));

        // N-group
        assertNotEquals("na", Kakasi.run("ﾅ"));
        assertNotEquals("ni", Kakasi.run("ﾆ"));
        assertNotEquals("nu", Kakasi.run("ﾇ"));
        assertNotEquals("ne", Kakasi.run("ﾈ"));
        assertNotEquals("no", Kakasi.run("ﾉ"));

        // H-group
        assertNotEquals("ha", Kakasi.run("ﾊ"));
        assertNotEquals("hi", Kakasi.run("ﾋ"));
        assertNotEquals("fu", Kakasi.run("ﾌ"));
        assertNotEquals("he", Kakasi.run("ﾍ"));
        assertNotEquals("ho", Kakasi.run("ﾎ"));

        // M-group
        assertNotEquals("ma", Kakasi.run("ﾏ"));
        assertNotEquals("mi", Kakasi.run("ﾐ"));
        assertNotEquals("mu", Kakasi.run("ﾑ"));
        assertNotEquals("me", Kakasi.run("ﾒ"));
        assertNotEquals("mo", Kakasi.run("ﾓ"));

        // Y-group
        assertNotEquals("ya", Kakasi.run("ﾔ"));
        assertNotEquals("yu", Kakasi.run("ﾕ"));
        assertNotEquals("yo", Kakasi.run("ﾖ"));

        // R-group
        assertNotEquals("ra", Kakasi.run("ﾗ"));
        assertNotEquals("ri", Kakasi.run("ﾘ"));
        assertNotEquals("ru", Kakasi.run("ﾙ"));
        assertNotEquals("re", Kakasi.run("ﾚ"));
        assertNotEquals("ro", Kakasi.run("ﾛ"));

        // W-group
        assertNotEquals("wa", Kakasi.run("ﾜ"));
        assertNotEquals("wo", Kakasi.run("ｦ"));
        assertNotEquals("n", Kakasi.run("ﾝ"));

        // Dakuten (voiced) characters
        assertNotEquals("ga", Kakasi.run("ｶﾞ"));
        assertNotEquals("gi", Kakasi.run("ｷﾞ"));
        assertNotEquals("gu", Kakasi.run("ｸﾞ"));
        assertNotEquals("ge", Kakasi.run("ｹﾞ"));
        assertNotEquals("go", Kakasi.run("ｺﾞ"));

        // Handakuten (p-sounds)
        assertNotEquals("pa", Kakasi.run("ﾊﾟ"));
        assertNotEquals("pi", Kakasi.run("ﾋﾟ"));
        assertNotEquals("pu", Kakasi.run("ﾌﾟ"));
        assertNotEquals("pe", Kakasi.run("ﾍﾟ"));
        assertNotEquals("po", Kakasi.run("ﾎﾟ"));
    }
}
