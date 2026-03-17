// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import java.util.ArrayList;
import java.util.Arrays;

import org.villseriol.kakasi.api.KakasiRomaji;


public class KakasiRomajiCandidates extends ArrayList<String> {
    public KakasiRomajiCandidates() {
        super(Arrays.asList(KakasiRomaji.codes()));
    }
}
