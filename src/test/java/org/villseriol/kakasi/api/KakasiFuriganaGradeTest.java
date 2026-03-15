// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KakasiFuriganaGradeTest {
    private Map<KakasiFuriganaGrade, Kakasi> instances = new HashMap<>();

    @BeforeEach
    public void setUp() {
        KakasiConfig config = new KakasiConfig(KakasiConstants.FURIGANA_CONFIG);
        config.setFuriganaLeft("{");
        config.setFuriganaRight("}");

        for (KakasiFuriganaGrade grade : KakasiFuriganaGrade.values()) {
            config.setFuriganaGrade(grade);

            Kakasi kakasi = new Kakasi();
            kakasi.configure(config);

            // warm up the instance
            String first = kakasi.run("にほんご");
            assertEquals("にほんご", first);

            instances.putIfAbsent(grade, kakasi);
        }
    }


    @Test
    public void testFurigana() {
        // touches all kanji above level 5
        Kakasi levelFive = instances.get(KakasiFuriganaGrade.FIVE);
        assertEquals("姿{すがた}", levelFive.run("姿"));

        // touches all kanji above level 6 (non-inclusive)
        Kakasi levelSix = instances.get(KakasiFuriganaGrade.SIX);
        assertEquals("姿", levelSix.run("姿"));

        // touches all kanji above level 6
        Kakasi levelSeven = instances.get(KakasiFuriganaGrade.SEVEN);
        assertEquals("姿", levelSeven.run("姿"));
    }
}
