// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class KakasiConfig {
    // Do not change the input and output encoding configuration, this library abstracts from this
    // The embedded kakasi library was not compiled to support input utf-8
    private static final KakasiInOutEncoding INPUT_ENCODING = KakasiInOutEncoding.EUC;
    private static final KakasiInOutEncoding OUTPUT_ENCODING = KakasiInOutEncoding.UTF8;

    private Collection<KakasiTranslation> translations = new ArrayList<>();
    private Collection<String> dictionaries = new ArrayList<>();
    private boolean wakatigaki;
    private boolean yomi;
    private boolean furigana;
    private boolean showAllReadings;
    private String separator;
    private KakasiRomaji romaji = KakasiRomaji.HEPBURN;

    public KakasiConfig() {
        super();
    }


    public KakasiConfig(KakasiConfig target) {
        super();

        this.translations = new ArrayList<>(target.translations);
        this.dictionaries = new ArrayList<>(target.dictionaries);
        this.wakatigaki = target.wakatigaki;
        this.yomi = target.yomi;
        this.furigana = target.furigana;
        this.showAllReadings = target.showAllReadings;
        this.separator = target.separator;
        this.romaji = target.romaji;
    }


    public void setShowAllReadings(boolean showAllReadings) {
        this.showAllReadings = showAllReadings;
    }


    public boolean isShowAllReadings() {
        return showAllReadings;
    }


    public void setFurigana(boolean furigana) {
        this.furigana = furigana;
    }


    public boolean isFurigana() {
        return furigana;
    }


    public void setYomi(boolean yomi) {
        this.yomi = yomi;
    }


    public boolean isYomi() {
        return yomi;
    }


    public KakasiRomaji getRomaji() {
        return romaji;
    }


    public void setRomaji(KakasiRomaji romaji) {
        this.romaji = romaji;
    }


    public void setWakatigaki(boolean wakatigaki) {
        this.wakatigaki = wakatigaki;
    }


    public boolean isWakatigaki() {
        return wakatigaki;
    }


    public Collection<String> getDictionaries() {
        return dictionaries;
    }


    public void setDictionaries(Collection<String> dictionaries) {
        this.dictionaries = dictionaries;
    }


    public void setSeparator(String separator) {
        this.separator = separator;
    }


    public String getSeparator() {
        return separator;
    }


    public void setTranslations(Collection<KakasiTranslation> translations) {
        this.translations = translations;
    }


    public Collection<KakasiTranslation> getTranslations() {
        return translations;
    }


    public String[] getArguments() {
        List<String> arguments = new ArrayList<>();
        arguments.add(String.format("-i%s", INPUT_ENCODING.getCode()));
        arguments.add(String.format("-o%s", OUTPUT_ENCODING.getCode()));

        if (separator != null && !"".equals(separator)) {
            arguments.add("-s");
            arguments.add(String.format("-S%s", separator));
        }

        if (yomi) {
            arguments.add("-y");
        }

        if (wakatigaki) {
            arguments.add("-w");
        }

        if (furigana) {
            arguments.add("-f");
        }

        if (romaji != null) {
            arguments.add(String.format("-r%s", romaji.getCode()));
        }

        if (translations != null) {
            for (KakasiTranslation t : translations) {
                arguments.add(String.format("-%s%s", t.getFrom().getCode(), t.getTo().getCode()));
            }
        }

        if (dictionaries != null) {
            for (String d : dictionaries) {
                arguments.add(d);
            }
        }

        return arguments.toArray(String[]::new);
    }
}
