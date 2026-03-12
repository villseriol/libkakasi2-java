// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiFullWidthTest {
    private Kakasi kakasi = new Kakasi();

    @BeforeEach
    public void setUp() {
        kakasi.configure(KakasiConstants.ASCII_CONFIG);
    }


    @Test
    public void testFullWidthHiragana() {
        // Vowels
        assertEquals("a", kakasi.run("あ"));
        assertEquals("i", kakasi.run("い"));
        assertEquals("u", kakasi.run("う"));
        assertEquals("e", kakasi.run("え"));
        assertEquals("o", kakasi.run("お"));

        // K consonants
        assertEquals("ka", kakasi.run("か"));
        assertEquals("ki", kakasi.run("き"));
        assertEquals("ku", kakasi.run("く"));
        assertEquals("ke", kakasi.run("け"));
        assertEquals("ko", kakasi.run("こ"));

        // S consonants
        assertEquals("sa", kakasi.run("さ"));
        assertEquals("shi", kakasi.run("し"));
        assertEquals("su", kakasi.run("す"));
        assertEquals("se", kakasi.run("せ"));
        assertEquals("so", kakasi.run("そ"));

        // T consonants
        assertEquals("ta", kakasi.run("た"));
        assertEquals("chi", kakasi.run("ち"));
        assertEquals("tsu", kakasi.run("つ"));
        assertEquals("te", kakasi.run("て"));
        assertEquals("to", kakasi.run("と"));

        // N consonants
        assertEquals("na", kakasi.run("な"));
        assertEquals("ni", kakasi.run("に"));
        assertEquals("nu", kakasi.run("ぬ"));
        assertEquals("ne", kakasi.run("ね"));
        assertEquals("no", kakasi.run("の"));

        // H consonants
        assertEquals("ha", kakasi.run("は"));
        assertEquals("hi", kakasi.run("ひ"));
        assertEquals("fu", kakasi.run("ふ"));
        assertEquals("he", kakasi.run("へ"));
        assertEquals("ho", kakasi.run("ほ"));

        // M consonants
        assertEquals("ma", kakasi.run("ま"));
        assertEquals("mi", kakasi.run("み"));
        assertEquals("mu", kakasi.run("む"));
        assertEquals("me", kakasi.run("め"));
        assertEquals("mo", kakasi.run("も"));

        // Y consonants
        assertEquals("ya", kakasi.run("や"));
        assertEquals("yu", kakasi.run("ゆ"));
        assertEquals("yo", kakasi.run("よ"));

        // R consonants
        assertEquals("ra", kakasi.run("ら"));
        assertEquals("ri", kakasi.run("り"));
        assertEquals("ru", kakasi.run("る"));
        assertEquals("re", kakasi.run("れ"));
        assertEquals("ro", kakasi.run("ろ"));

        // W consonants
        assertEquals("wa", kakasi.run("わ"));
        assertEquals("wo", kakasi.run("を"));
        assertEquals("n", kakasi.run("ん"));

        // Dakuten
        assertEquals("ga", kakasi.run("が"));
        assertEquals("gi", kakasi.run("ぎ"));
        assertEquals("gu", kakasi.run("ぐ"));
        assertEquals("ge", kakasi.run("げ"));
        assertEquals("go", kakasi.run("ご"));

        assertEquals("za", kakasi.run("ざ"));
        assertEquals("ji", kakasi.run("じ"));
        assertEquals("zu", kakasi.run("ず"));
        assertEquals("ze", kakasi.run("ぜ"));
        assertEquals("zo", kakasi.run("ぞ"));

        assertEquals("da", kakasi.run("だ"));
        assertEquals("ji", kakasi.run("ぢ"));
        assertEquals("zu", kakasi.run("づ"));
        assertEquals("de", kakasi.run("で"));
        assertEquals("do", kakasi.run("ど"));

        assertEquals("ba", kakasi.run("ば"));
        assertEquals("bi", kakasi.run("び"));
        assertEquals("bu", kakasi.run("ぶ"));
        assertEquals("be", kakasi.run("べ"));
        assertEquals("bo", kakasi.run("ぼ"));

        // Handakuten
        assertEquals("pa", kakasi.run("ぱ"));
        assertEquals("pi", kakasi.run("ぴ"));
        assertEquals("pu", kakasi.run("ぷ"));
        assertEquals("pe", kakasi.run("ぺ"));
        assertEquals("po", kakasi.run("ぽ"));
    }


    @Test
    public void testFullWidthKatakana() {
        assertEquals("a", kakasi.run("ア"));
        assertEquals("i", kakasi.run("イ"));
        assertEquals("u", kakasi.run("ウ"));
        assertEquals("e", kakasi.run("エ"));
        assertEquals("o", kakasi.run("オ"));

        assertEquals("ka", kakasi.run("カ"));
        assertEquals("ki", kakasi.run("キ"));
        assertEquals("ku", kakasi.run("ク"));
        assertEquals("ke", kakasi.run("ケ"));
        assertEquals("ko", kakasi.run("コ"));

        assertEquals("sa", kakasi.run("サ"));
        assertEquals("shi", kakasi.run("シ"));
        assertEquals("su", kakasi.run("ス"));
        assertEquals("se", kakasi.run("セ"));
        assertEquals("so", kakasi.run("ソ"));

        assertEquals("ta", kakasi.run("タ"));
        assertEquals("chi", kakasi.run("チ"));
        assertEquals("tsu", kakasi.run("ツ"));
        assertEquals("te", kakasi.run("テ"));
        assertEquals("to", kakasi.run("ト"));

        assertEquals("na", kakasi.run("ナ"));
        assertEquals("ni", kakasi.run("ニ"));
        assertEquals("nu", kakasi.run("ヌ"));
        assertEquals("ne", kakasi.run("ネ"));
        assertEquals("no", kakasi.run("ノ"));

        assertEquals("ha", kakasi.run("ハ"));
        assertEquals("hi", kakasi.run("ヒ"));
        assertEquals("fu", kakasi.run("フ"));
        assertEquals("he", kakasi.run("ヘ"));
        assertEquals("ho", kakasi.run("ホ"));

        assertEquals("ma", kakasi.run("マ"));
        assertEquals("mi", kakasi.run("ミ"));
        assertEquals("mu", kakasi.run("ム"));
        assertEquals("me", kakasi.run("メ"));
        assertEquals("mo", kakasi.run("モ"));

        assertEquals("ya", kakasi.run("ヤ"));
        assertEquals("yu", kakasi.run("ユ"));
        assertEquals("yo", kakasi.run("ヨ"));

        assertEquals("ra", kakasi.run("ラ"));
        assertEquals("ri", kakasi.run("リ"));
        assertEquals("ru", kakasi.run("ル"));
        assertEquals("re", kakasi.run("レ"));
        assertEquals("ro", kakasi.run("ロ"));

        assertEquals("wa", kakasi.run("ワ"));
        assertEquals("wo", kakasi.run("ヲ"));
        assertEquals("n", kakasi.run("ン"));

        // Dakuten
        assertEquals("ga", kakasi.run("ガ"));
        assertEquals("gi", kakasi.run("ギ"));
        assertEquals("gu", kakasi.run("グ"));
        assertEquals("ge", kakasi.run("ゲ"));
        assertEquals("go", kakasi.run("ゴ"));

        // Handakuten
        assertEquals("pa", kakasi.run("パ"));
        assertEquals("pi", kakasi.run("ピ"));
        assertEquals("pu", kakasi.run("プ"));
        assertEquals("pe", kakasi.run("ペ"));
        assertEquals("po", kakasi.run("ポ"));
    }


    @Test
    public void testFullWidthDigits() {
        assertEquals("0", kakasi.run("０"));
        assertEquals("1", kakasi.run("１"));
        assertEquals("2", kakasi.run("２"));
        assertEquals("3", kakasi.run("３"));
        assertEquals("4", kakasi.run("４"));
        assertEquals("5", kakasi.run("５"));
        assertEquals("6", kakasi.run("６"));
        assertEquals("7", kakasi.run("７"));
        assertEquals("8", kakasi.run("８"));
        assertEquals("9", kakasi.run("９"));
    }


    @Test
    public void testFullWidthLetters() {
        // Uppercase letters
        assertEquals("A", kakasi.run("Ａ"));
        assertEquals("B", kakasi.run("Ｂ"));
        assertEquals("C", kakasi.run("Ｃ"));
        assertEquals("D", kakasi.run("Ｄ"));
        assertEquals("E", kakasi.run("Ｅ"));
        assertEquals("F", kakasi.run("Ｆ"));
        assertEquals("G", kakasi.run("Ｇ"));
        assertEquals("H", kakasi.run("Ｈ"));
        assertEquals("I", kakasi.run("Ｉ"));
        assertEquals("J", kakasi.run("Ｊ"));
        assertEquals("K", kakasi.run("Ｋ"));
        assertEquals("L", kakasi.run("Ｌ"));
        assertEquals("M", kakasi.run("Ｍ"));
        assertEquals("N", kakasi.run("Ｎ"));
        assertEquals("O", kakasi.run("Ｏ"));
        assertEquals("P", kakasi.run("Ｐ"));
        assertEquals("Q", kakasi.run("Ｑ"));
        assertEquals("R", kakasi.run("Ｒ"));
        assertEquals("S", kakasi.run("Ｓ"));
        assertEquals("T", kakasi.run("Ｔ"));
        assertEquals("U", kakasi.run("Ｕ"));
        assertEquals("V", kakasi.run("Ｖ"));
        assertEquals("W", kakasi.run("Ｗ"));
        assertEquals("X", kakasi.run("Ｘ"));
        assertEquals("Y", kakasi.run("Ｙ"));
        assertEquals("Z", kakasi.run("Ｚ"));

        // Lowercase letters
        assertEquals("a", kakasi.run("ａ"));
        assertEquals("b", kakasi.run("ｂ"));
        assertEquals("c", kakasi.run("ｃ"));
        assertEquals("d", kakasi.run("ｄ"));
        assertEquals("e", kakasi.run("ｅ"));
        assertEquals("f", kakasi.run("ｆ"));
        assertEquals("g", kakasi.run("ｇ"));
        assertEquals("h", kakasi.run("ｈ"));
        assertEquals("i", kakasi.run("ｉ"));
        assertEquals("j", kakasi.run("ｊ"));
        assertEquals("k", kakasi.run("ｋ"));
        assertEquals("l", kakasi.run("ｌ"));
        assertEquals("m", kakasi.run("ｍ"));
        assertEquals("n", kakasi.run("ｎ"));
        assertEquals("o", kakasi.run("ｏ"));
        assertEquals("p", kakasi.run("ｐ"));
        assertEquals("q", kakasi.run("ｑ"));
        assertEquals("r", kakasi.run("ｒ"));
        assertEquals("s", kakasi.run("ｓ"));
        assertEquals("t", kakasi.run("ｔ"));
        assertEquals("u", kakasi.run("ｕ"));
        assertEquals("v", kakasi.run("ｖ"));
        assertEquals("w", kakasi.run("ｗ"));
        assertEquals("x", kakasi.run("ｘ"));
        assertEquals("y", kakasi.run("ｙ"));
        assertEquals("z", kakasi.run("ｚ"));
    }
}
