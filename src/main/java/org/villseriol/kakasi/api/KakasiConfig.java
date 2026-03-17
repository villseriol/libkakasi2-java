// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.villseriol.kakasi.internal.KakasiInOutEncoding;


public class KakasiConfig {
    // Do not change the input and output encoding configuration, this library
    // abstracts from this.
    // 1. If utf8 input is provided, kakasi converts it to euc anyway
    // 2. The utf8 output being computed in C leads to small performance gains
    private static final KakasiInOutEncoding INPUT_ENCODING = KakasiInOutEncoding.EUC;
    private static final KakasiInOutEncoding OUTPUT_ENCODING = KakasiInOutEncoding.UTF8;

    private Collection<KakasiTranslation> translations;
    private Collection<String> dictionaries;
    private boolean wakatigaki;
    private boolean capitalise;
    private boolean uppercase;
    private boolean yomi;
    private String furiganaLeft;
    private String furiganaRight;
    private boolean showAllReadings;
    private String separator;
    private KakasiRomaji romaji = KakasiRomaji.HEPBURN;
    private Collection<Character> skipCharacters;
    private KakasiKanjiGrade hiraganaGrade;
    private KakasiKanjiGrade furiganaGrade;

    public KakasiConfig() {
        super();
    }


    public KakasiConfig(KakasiConfig other) {
        super();

        Optional.ofNullable(other.translations).ifPresent((value) -> {
            this.translations = new ArrayList<>(value);
        });

        Optional.ofNullable(other.dictionaries).ifPresent((value) -> {
            this.dictionaries = new ArrayList<>(value);
        });

        Optional.ofNullable(other.skipCharacters).ifPresent((value) -> {
            this.skipCharacters = new ArrayList<>(value);
        });

        this.wakatigaki = other.wakatigaki;
        this.yomi = other.yomi;
        this.showAllReadings = other.showAllReadings;
        this.separator = other.separator;
        this.romaji = other.romaji;
        this.furiganaLeft = other.furiganaLeft;
        this.furiganaRight = other.furiganaRight;
        this.furiganaGrade = other.furiganaGrade;
        this.hiraganaGrade = other.hiraganaGrade;
        this.capitalise = other.capitalise;
        this.uppercase = other.uppercase;
    }


    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }


    public boolean isUppercase() {
        return uppercase;
    }


    public void setCapitalise(boolean capitalise) {
        this.capitalise = capitalise;
    }


    public boolean isCapitalise() {
        return capitalise;
    }


    public KakasiKanjiGrade getFuriganaGrade() {
        return furiganaGrade;
    }


    /**
     * All kanji above the provided grade (non-inclusive) will have attached
     * furigana (if kanji-hiragana is enabled). Overridden by hiragana grade.
     *
     * @param furiganaGrade the grade
     */
    public void setFuriganaGrade(KakasiKanjiGrade furiganaGrade) {
        this.furiganaGrade = furiganaGrade;
    }


    public KakasiKanjiGrade getHiraganaGrade() {
        return hiraganaGrade;
    }


    /**
     * All kanji above the provided grade (non-inclusive) will be translated to
     * hiragana (if kanji-hiragana is enabled). Takes precedence over furigana
     * grade.
     *
     * @param hiraganaGrade the grade
     */
    public void setHiraganaGrade(KakasiKanjiGrade hiraganaGrade) {
        this.hiraganaGrade = hiraganaGrade;
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

        if (uppercase) {
            arguments.add("-U");
        }

        if (yomi) {
            arguments.add("-y");
        }

        if (wakatigaki) {
            arguments.add("-w");
        }

        if (furiganaGrade != null) {
            arguments.add(String.format("-L%s", furiganaGrade.getCode()));
        }

        if (showAllReadings) {
            arguments.add("-p");
        }

        if (romaji != null) {
            arguments.add(String.format("-r%s", romaji.getCode()));
        }

        if (capitalise) {
            arguments.add("-C");
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

        if (hiraganaGrade != null) {
            arguments.add(String.format("-l%s", hiraganaGrade.getCode()));
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
