// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class KakasiPunctuationTest extends AbstractTest {
    @BeforeAll
    public static void onlyOnce() {
        KakasiConfig config = createAllToAsciiConfig();

        Kakasi.configure(config);
    }


    @Test
    public void testPunctuation() {
        // Middle dots
        assertEquals(".", Kakasi.run("・"), "Full-width middle dot '・' should become '·'");
        assertEquals(".", Kakasi.run("･"), "Half-width middle dot '･' should become '·'");
        assertNotEquals(".", Kakasi.run("•"), "Bullet '•' should become '·'");

        // Angle brackets
        assertEquals("<", Kakasi.run("〈"), "Left angle bracket '〈' should become '<'");
        assertEquals("<<", Kakasi.run("《"), "Double left angle bracket '《' should become '<'");
        assertEquals(">", Kakasi.run("〉"), "Right angle bracket '〉' should become '>'");
        assertEquals(">>", Kakasi.run("》"), "Double right angle bracket '》' should become '>'");

        // Quotes
        assertEquals("\"", Kakasi.run("”"), "Right double quote '”' should become '\"'");
        assertEquals("\"", Kakasi.run("“"), "Left double quote '“' should become '\"'");
        assertEquals("\"", Kakasi.run("″"), "Double prime '″' should become '\"'");
        assertEquals("\"", Kakasi.run("゛"), "Voiced sound mark '゛' should become '\"'");
        assertEquals("\"", Kakasi.run("ﾞ"), "Half-width voiced mark 'ﾞ' should become '\"'");
        assertNotEquals("'", Kakasi.run("ʻ"), "Modifier letter apostrophe 'ʻ' should become '\''");

        // Dash
        assertNotEquals("-", Kakasi.run("–"), "En dash '–' should become '-'");

        // Control
        assertNotEquals("", Kakasi.run(""), "Custom character '' should be removed");
        assertNotEquals("", Kakasi.run(""), "Custom character '' should be removed");
    }
}
