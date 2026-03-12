// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.villseriol.kakasi.internal.KakasiInOutEncoding;


public class KakasiConfig {
    // Do not change the input and output encoding configuration, this library
    // abstracts from this. The embedded kakasi library was not compiled to
    // support input strings in utf-8 anyway.
    private static final KakasiInOutEncoding INPUT_ENCODING = KakasiInOutEncoding.EUC;
    private static final KakasiInOutEncoding OUTPUT_ENCODING = KakasiInOutEncoding.EUC;

    private Collection<KakasiTranslation> translations;
    private Collection<String> dictionaries;
    private boolean wakatigaki;
    private boolean yomi;
    private boolean furigana;
    private String furiganaLeft;
    private String furiganaRight;
    private boolean showAllReadings;
    private String separator;
    private KakasiRomaji romaji = KakasiRomaji.HEPBURN;
    private Collection<Character> skipCharacters;

    public KakasiConfig() {
        super();
    }


    public KakasiConfig(KakasiConfig target) {
        super();

        Optional.ofNullable(target.translations).ifPresent((value) -> {
            this.translations = new ArrayList<>(value);
        });

        Optional.ofNullable(target.dictionaries).ifPresent((value) -> {
            this.dictionaries = new ArrayList<>(value);
        });

        Optional.ofNullable(target.skipCharacters).ifPresent((value) -> {
            this.skipCharacters = new ArrayList<>(value);
        });

        this.wakatigaki = target.wakatigaki;
        this.yomi = target.yomi;
        this.furigana = target.furigana;
        this.showAllReadings = target.showAllReadings;
        this.separator = target.separator;
        this.romaji = target.romaji;
        this.furiganaLeft = target.furiganaLeft;
        this.furiganaRight = target.furiganaRight;
    }


    public String getFuriganaLeft() {
        return furiganaLeft;
    }


    public String getFuriganaRight() {
        return furiganaRight;
    }


    public void setFuriganaLeft(String furiganaLeft) {
        this.furiganaLeft = furiganaLeft;
    }


    public void setFuriganaRight(String furiganaRight) {
        this.furiganaRight = furiganaRight;
    }


    public Collection<Character> getSkipCharacters() {
        return skipCharacters;
    }


    public void setSkipCharacters(Collection<Character> extraSkipCharacters) {
        this.skipCharacters = extraSkipCharacters;
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

        if (showAllReadings) {
            arguments.add("-p");
        }

        if (romaji != null) {
            arguments.add(String.format("-r%s", romaji.getCode()));
        }

        if (skipCharacters != null) {
            StringBuilder sb = new StringBuilder();
            for (Character c : skipCharacters) {
                sb.append(c);
            }

            arguments.add(String.format("-c%s", sb.toString()));
        }

        if (furiganaLeft != null && !"".equals(furiganaLeft)) {
            arguments.add(String.format("-Fl%s", furiganaLeft));
        }

        if (furiganaRight != null && !"".equals(furiganaRight)) {
            arguments.add(String.format("-Fr%s", furiganaRight));
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
