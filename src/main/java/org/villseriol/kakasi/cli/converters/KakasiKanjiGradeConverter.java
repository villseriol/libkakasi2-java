// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli.converters;

import org.villseriol.kakasi.api.KakasiKanjiGrade;

import picocli.CommandLine.ITypeConverter;


public class KakasiKanjiGradeConverter implements ITypeConverter<KakasiKanjiGrade> {
    @Override
    public KakasiKanjiGrade convert(String value) throws Exception {
        for (KakasiKanjiGrade v : KakasiKanjiGrade.values()) {
            if (value.equals(v.getCode())) {
                return v;
            }
        }

        throw new IllegalArgumentException("Unknown kanji grade.");
    }

}
