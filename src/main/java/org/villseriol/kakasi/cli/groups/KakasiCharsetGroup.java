// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli.groups;

import java.util.ArrayList;
import java.util.List;

import org.villseriol.kakasi.api.KakasiCharsetCategory;
import org.villseriol.kakasi.api.KakasiConfig;
import org.villseriol.kakasi.cli.converters.KakasiCharsetCategoryConverter;

import picocli.CommandLine.Option;


public class KakasiCharsetGroup implements KakasiGroupVisitor {
    private KakasiCharsetCategory kanji;

    @Option(names = { "-J" }, description = "(${COMPLETION-CANDIDATES})",
            converter = KakasiCharsetCategoryConverter.class, completionCandidates = KanjiCandidates.class,
            paramLabel = "<code>")
    public void setKanji(KakasiCharsetCategory kanji) {
        this.kanji = kanji;
    }


    @Override
    public void visit(KakasiConfig config) {
        // follow the pattern and add the translations here
    }

    static final class KanjiCandidates extends ArrayList<String> {
        private KanjiCandidates() {
            super(List.of(KakasiCharsetCategory.ASCII.getCode(), KakasiCharsetCategory.GRAPHIC.getCode()));
        }
    }
}
