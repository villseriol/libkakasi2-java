// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiPunctuationTest extends AbstractTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        KakasiConfig config = createAllToAsciiConfig();

        kakasi.configure(config);
    }


    @Test
    public void testPunctuation() {
        // Middle dots
        assertEquals(".", kakasi.run("・"), "Full-width middle dot '・' should become '·'");
        assertEquals(".", kakasi.run("･"), "Half-width middle dot '･' should become '·'");
        assertNotEquals(".", kakasi.run("•"), "Bullet '•' should become '·'");

        // Angle brackets
        assertEquals("<", kakasi.run("〈"), "Left angle bracket '〈' should become '<'");
        assertEquals("<<", kakasi.run("《"), "Double left angle bracket '《' should become '<'");
        assertEquals(">", kakasi.run("〉"), "Right angle bracket '〉' should become '>'");
        assertEquals(">>", kakasi.run("》"), "Double right angle bracket '》' should become '>'");

        // Quotes
        assertEquals("\"", kakasi.run("”"), "Right double quote '”' should become '\"'");
        assertEquals("\"", kakasi.run("“"), "Left double quote '“' should become '\"'");
        assertEquals("\"", kakasi.run("″"), "Double prime '″' should become '\"'");
        assertEquals("\"", kakasi.run("゛"), "Voiced sound mark '゛' should become '\"'");
        assertEquals("\"", kakasi.run("ﾞ"), "Half-width voiced mark 'ﾞ' should become '\"'");
        assertEquals("(maru)", kakasi.run("ﾟ"), "Half-width voiced mark 'ﾟ' should become '(maru)'");
        assertNotEquals("'", kakasi.run("ʻ"), "Modifier letter apostrophe 'ʻ' should become '\''");

        // Dash
        assertNotEquals("-", kakasi.run("–"), "En dash '–' should become '-'");

        // Control
        assertNotEquals("", kakasi.run(""), "Custom character '' should be removed");
        assertNotEquals("", kakasi.run(""), "Custom character '' should be removed");
    }
}
