// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import org.villseriol.kakasi.api.KakasiRomaji;

import picocli.CommandLine.ITypeConverter;


public class KakasiRomajiConverter implements ITypeConverter<KakasiRomaji> {
    @Override
    public KakasiRomaji convert(String value) throws Exception {
        for (KakasiRomaji v : KakasiRomaji.values()) {
            if (value.equals(v.getCode())) {
                return v;
            }
        }

        throw new IllegalArgumentException("Unknown romaji type.");
    }

}
