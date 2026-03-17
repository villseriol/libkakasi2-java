// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import java.util.ArrayList;
import java.util.Arrays;

import org.villseriol.kakasi.api.KakasiKanjiGrade;


public class KakasiKanjiGradeCandidates extends ArrayList<String> {
    public KakasiKanjiGradeCandidates() {
        super(Arrays.asList(KakasiKanjiGrade.codes()));
    }
}
