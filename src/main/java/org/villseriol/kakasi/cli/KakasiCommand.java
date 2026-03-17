// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import java.util.concurrent.Callable;

import org.villseriol.kakasi.api.Kakasi;
import org.villseriol.kakasi.api.KakasiConfig;
import org.villseriol.kakasi.api.KakasiKanjiGrade;
import org.villseriol.kakasi.api.KakasiRomaji;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "kakasi", mixinStandardHelpOptions = true, versionProvider = KakasiVersionProvider.class,
        header = "KAKASI - Kanji Kana Simple Inverter (Java)")
public class KakasiCommand implements Callable<String> {
    private Boolean separator;
    private String customSeparatorValue;

    private KakasiRomaji romaji;
    private String input;
    private KakasiKanjiGrade furiganaGrade;
    private KakasiKanjiGrade hiraganaGrade;
    private Boolean showAllReadings;

    @Option(names = { "-p" }, description = "list all readings", type = Boolean.class, defaultValue = "false")
    public void setShowAllReadings(Boolean showAllReadings) {
        this.showAllReadings = showAllReadings;
    }


    @Option(names = { "-L" }, description = "minimum furigana kanji grade ${COMPLETION-CANDIDATES}",
            converter = KakasiKanjiGradeConverter.class, completionCandidates = KakasiKanjiGradeCandidates.class,
            paramLabel = "<grade>")
    public void setFuriganaGrade(KakasiKanjiGrade furiganaGrade) {
        this.furiganaGrade = furiganaGrade;
    }


    @Option(names = { "-l" }, description = "minimum hiragana kanji grade ${COMPLETION-CANDIDATES}",
            converter = KakasiKanjiGradeConverter.class, completionCandidates = KakasiKanjiGradeCandidates.class,
            paramLabel = "<grade>")
    public void setHiraganaGrade(KakasiKanjiGrade hiraganaGrade) {
        this.hiraganaGrade = hiraganaGrade;
    }


    @Option(names = { "-r" }, description = "romaji conversion system ${COMPLETION-CANDIDATES}",
            converter = KakasiRomajiConverter.class, completionCandidates = KakasiRomajiCandidates.class)
    public void setRomaji(KakasiRomaji romaji) {
        this.romaji = romaji;
    }


    @Option(names = { "-S" }, description = "set separator", paramLabel = "<separator>")
    public void setCustomSeparatorValue(String customSeparatorValue) {
        this.customSeparatorValue = customSeparatorValue;
    }


    @Option(names = { "-s" }, description = "enable separator", type = Boolean.class)
    public void setSeparator(Boolean separator) {
        this.separator = separator;
    }


    @Parameters(index = "0", description = "the input string")
    public void setInput(String input) {
        this.input = input;
    }


    @Override
    public String call() throws Exception {
        KakasiConfig config = new KakasiConfig();
        if (separator) {
            config.setSeparator(" ");

            if (customSeparatorValue != null && !"".equals(customSeparatorValue)) {
                config.setSeparator(customSeparatorValue);
            }
        }

        config.setRomaji(romaji);
        config.setFuriganaGrade(furiganaGrade);
        config.setHiraganaGrade(hiraganaGrade);
        config.setShowAllReadings(showAllReadings);

        try (Kakasi instance = new Kakasi(config)) {
            return instance.run(input);
        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) {
        int success = new CommandLine(new KakasiCommand()).execute(args);
        System.exit(success);
    }
}
