// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiFullWidthTest extends AbstractTest {
    @BeforeEach
    public void setUp() {
        KakasiConfig config = createAllToAsciiConfig();

        Kakasi.configure(config);
    }


    @Test
    public void testFullWidthHiragana() {
        // Vowels
        assertEquals("a", Kakasi.run("あ"));
        assertEquals("i", Kakasi.run("い"));
        assertEquals("u", Kakasi.run("う"));
        assertEquals("e", Kakasi.run("え"));
        assertEquals("o", Kakasi.run("お"));

        // K consonants
        assertEquals("ka", Kakasi.run("か"));
        assertEquals("ki", Kakasi.run("き"));
        assertEquals("ku", Kakasi.run("く"));
        assertEquals("ke", Kakasi.run("け"));
        assertEquals("ko", Kakasi.run("こ"));

        // S consonants
        assertEquals("sa", Kakasi.run("さ"));
        assertEquals("shi", Kakasi.run("し"));
        assertEquals("su", Kakasi.run("す"));
        assertEquals("se", Kakasi.run("せ"));
        assertEquals("so", Kakasi.run("そ"));

        // T consonants
        assertEquals("ta", Kakasi.run("た"));
        assertEquals("chi", Kakasi.run("ち"));
        assertEquals("tsu", Kakasi.run("つ"));
        assertEquals("te", Kakasi.run("て"));
        assertEquals("to", Kakasi.run("と"));

        // N consonants
        assertEquals("na", Kakasi.run("な"));
        assertEquals("ni", Kakasi.run("に"));
        assertEquals("nu", Kakasi.run("ぬ"));
        assertEquals("ne", Kakasi.run("ね"));
        assertEquals("no", Kakasi.run("の"));

        // H consonants
        assertEquals("ha", Kakasi.run("は"));
        assertEquals("hi", Kakasi.run("ひ"));
        assertEquals("fu", Kakasi.run("ふ"));
        assertEquals("he", Kakasi.run("へ"));
        assertEquals("ho", Kakasi.run("ほ"));

        // M consonants
        assertEquals("ma", Kakasi.run("ま"));
        assertEquals("mi", Kakasi.run("み"));
        assertEquals("mu", Kakasi.run("む"));
        assertEquals("me", Kakasi.run("め"));
        assertEquals("mo", Kakasi.run("も"));

        // Y consonants
        assertEquals("ya", Kakasi.run("や"));
        assertEquals("yu", Kakasi.run("ゆ"));
        assertEquals("yo", Kakasi.run("よ"));

        // R consonants
        assertEquals("ra", Kakasi.run("ら"));
        assertEquals("ri", Kakasi.run("り"));
        assertEquals("ru", Kakasi.run("る"));
        assertEquals("re", Kakasi.run("れ"));
        assertEquals("ro", Kakasi.run("ろ"));

        // W consonants
        assertEquals("wa", Kakasi.run("わ"));
        assertEquals("wo", Kakasi.run("を"));
        assertEquals("n", Kakasi.run("ん"));

        // Dakuten
        assertEquals("ga", Kakasi.run("が"));
        assertEquals("gi", Kakasi.run("ぎ"));
        assertEquals("gu", Kakasi.run("ぐ"));
        assertEquals("ge", Kakasi.run("げ"));
        assertEquals("go", Kakasi.run("ご"));

        assertEquals("za", Kakasi.run("ざ"));
        assertEquals("ji", Kakasi.run("じ"));
        assertEquals("zu", Kakasi.run("ず"));
        assertEquals("ze", Kakasi.run("ぜ"));
        assertEquals("zo", Kakasi.run("ぞ"));

        assertEquals("da", Kakasi.run("だ"));
        assertEquals("ji", Kakasi.run("ぢ"));
        assertEquals("zu", Kakasi.run("づ"));
        assertEquals("de", Kakasi.run("で"));
        assertEquals("do", Kakasi.run("ど"));

        assertEquals("ba", Kakasi.run("ば"));
        assertEquals("bi", Kakasi.run("び"));
        assertEquals("bu", Kakasi.run("ぶ"));
        assertEquals("be", Kakasi.run("べ"));
        assertEquals("bo", Kakasi.run("ぼ"));

        // Handakuten
        assertEquals("pa", Kakasi.run("ぱ"));
        assertEquals("pi", Kakasi.run("ぴ"));
        assertEquals("pu", Kakasi.run("ぷ"));
        assertEquals("pe", Kakasi.run("ぺ"));
        assertEquals("po", Kakasi.run("ぽ"));
    }


    @Test
    public void testFullWidthKatakana() {
        assertEquals("a", Kakasi.run("ア"));
        assertEquals("i", Kakasi.run("イ"));
        assertEquals("u", Kakasi.run("ウ"));
        assertEquals("e", Kakasi.run("エ"));
        assertEquals("o", Kakasi.run("オ"));

        assertEquals("ka", Kakasi.run("カ"));
        assertEquals("ki", Kakasi.run("キ"));
        assertEquals("ku", Kakasi.run("ク"));
        assertEquals("ke", Kakasi.run("ケ"));
        assertEquals("ko", Kakasi.run("コ"));

        assertEquals("sa", Kakasi.run("サ"));
        assertEquals("shi", Kakasi.run("シ"));
        assertEquals("su", Kakasi.run("ス"));
        assertEquals("se", Kakasi.run("セ"));
        assertEquals("so", Kakasi.run("ソ"));

        assertEquals("ta", Kakasi.run("タ"));
        assertEquals("chi", Kakasi.run("チ"));
        assertEquals("tsu", Kakasi.run("ツ"));
        assertEquals("te", Kakasi.run("テ"));
        assertEquals("to", Kakasi.run("ト"));

        assertEquals("na", Kakasi.run("ナ"));
        assertEquals("ni", Kakasi.run("ニ"));
        assertEquals("nu", Kakasi.run("ヌ"));
        assertEquals("ne", Kakasi.run("ネ"));
        assertEquals("no", Kakasi.run("ノ"));

        assertEquals("ha", Kakasi.run("ハ"));
        assertEquals("hi", Kakasi.run("ヒ"));
        assertEquals("fu", Kakasi.run("フ"));
        assertEquals("he", Kakasi.run("ヘ"));
        assertEquals("ho", Kakasi.run("ホ"));

        assertEquals("ma", Kakasi.run("マ"));
        assertEquals("mi", Kakasi.run("ミ"));
        assertEquals("mu", Kakasi.run("ム"));
        assertEquals("me", Kakasi.run("メ"));
        assertEquals("mo", Kakasi.run("モ"));

        assertEquals("ya", Kakasi.run("ヤ"));
        assertEquals("yu", Kakasi.run("ユ"));
        assertEquals("yo", Kakasi.run("ヨ"));

        assertEquals("ra", Kakasi.run("ラ"));
        assertEquals("ri", Kakasi.run("リ"));
        assertEquals("ru", Kakasi.run("ル"));
        assertEquals("re", Kakasi.run("レ"));
        assertEquals("ro", Kakasi.run("ロ"));

        assertEquals("wa", Kakasi.run("ワ"));
        assertEquals("wo", Kakasi.run("ヲ"));
        assertEquals("n", Kakasi.run("ン"));

        // Dakuten
        assertEquals("ga", Kakasi.run("ガ"));
        assertEquals("gi", Kakasi.run("ギ"));
        assertEquals("gu", Kakasi.run("グ"));
        assertEquals("ge", Kakasi.run("ゲ"));
        assertEquals("go", Kakasi.run("ゴ"));

        // Handakuten
        assertEquals("pa", Kakasi.run("パ"));
        assertEquals("pi", Kakasi.run("ピ"));
        assertEquals("pu", Kakasi.run("プ"));
        assertEquals("pe", Kakasi.run("ペ"));
        assertEquals("po", Kakasi.run("ポ"));
    }


    @Test
    public void testFullWidthDigits() {
        assertEquals("0", Kakasi.run("０"));
        assertEquals("1", Kakasi.run("１"));
        assertEquals("2", Kakasi.run("２"));
        assertEquals("3", Kakasi.run("３"));
        assertEquals("4", Kakasi.run("４"));
        assertEquals("5", Kakasi.run("５"));
        assertEquals("6", Kakasi.run("６"));
        assertEquals("7", Kakasi.run("７"));
        assertEquals("8", Kakasi.run("８"));
        assertEquals("9", Kakasi.run("９"));
    }


    @Test
    public void testFullWidthLetters() {
        // Uppercase letters
        assertEquals("A", Kakasi.run("Ａ"));
        assertEquals("B", Kakasi.run("Ｂ"));
        assertEquals("C", Kakasi.run("Ｃ"));
        assertEquals("D", Kakasi.run("Ｄ"));
        assertEquals("E", Kakasi.run("Ｅ"));
        assertEquals("F", Kakasi.run("Ｆ"));
        assertEquals("G", Kakasi.run("Ｇ"));
        assertEquals("H", Kakasi.run("Ｈ"));
        assertEquals("I", Kakasi.run("Ｉ"));
        assertEquals("J", Kakasi.run("Ｊ"));
        assertEquals("K", Kakasi.run("Ｋ"));
        assertEquals("L", Kakasi.run("Ｌ"));
        assertEquals("M", Kakasi.run("Ｍ"));
        assertEquals("N", Kakasi.run("Ｎ"));
        assertEquals("O", Kakasi.run("Ｏ"));
        assertEquals("P", Kakasi.run("Ｐ"));
        assertEquals("Q", Kakasi.run("Ｑ"));
        assertEquals("R", Kakasi.run("Ｒ"));
        assertEquals("S", Kakasi.run("Ｓ"));
        assertEquals("T", Kakasi.run("Ｔ"));
        assertEquals("U", Kakasi.run("Ｕ"));
        assertEquals("V", Kakasi.run("Ｖ"));
        assertEquals("W", Kakasi.run("Ｗ"));
        assertEquals("X", Kakasi.run("Ｘ"));
        assertEquals("Y", Kakasi.run("Ｙ"));
        assertEquals("Z", Kakasi.run("Ｚ"));

        // Lowercase letters
        assertEquals("a", Kakasi.run("ａ"));
        assertEquals("b", Kakasi.run("ｂ"));
        assertEquals("c", Kakasi.run("ｃ"));
        assertEquals("d", Kakasi.run("ｄ"));
        assertEquals("e", Kakasi.run("ｅ"));
        assertEquals("f", Kakasi.run("ｆ"));
        assertEquals("g", Kakasi.run("ｇ"));
        assertEquals("h", Kakasi.run("ｈ"));
        assertEquals("i", Kakasi.run("ｉ"));
        assertEquals("j", Kakasi.run("ｊ"));
        assertEquals("k", Kakasi.run("ｋ"));
        assertEquals("l", Kakasi.run("ｌ"));
        assertEquals("m", Kakasi.run("ｍ"));
        assertEquals("n", Kakasi.run("ｎ"));
        assertEquals("o", Kakasi.run("ｏ"));
        assertEquals("p", Kakasi.run("ｐ"));
        assertEquals("q", Kakasi.run("ｑ"));
        assertEquals("r", Kakasi.run("ｒ"));
        assertEquals("s", Kakasi.run("ｓ"));
        assertEquals("t", Kakasi.run("ｔ"));
        assertEquals("u", Kakasi.run("ｕ"));
        assertEquals("v", Kakasi.run("ｖ"));
        assertEquals("w", Kakasi.run("ｗ"));
        assertEquals("x", Kakasi.run("ｘ"));
        assertEquals("y", Kakasi.run("ｙ"));
        assertEquals("z", Kakasi.run("ｚ"));
    }
}
