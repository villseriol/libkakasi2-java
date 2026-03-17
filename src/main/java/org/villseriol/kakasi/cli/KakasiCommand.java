// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import java.util.concurrent.Callable;

import org.villseriol.kakasi.api.Kakasi;
import org.villseriol.kakasi.api.KakasiConfig;
import org.villseriol.kakasi.api.KakasiKanjiGrade;
import org.villseriol.kakasi.api.KakasiRomaji;
import org.villseriol.kakasi.cli.converters.KakasiKanjiGradeConverter;
import org.villseriol.kakasi.cli.groups.KakasiCharsetGroup;
import org.villseriol.kakasi.cli.groups.KakasiFlagGroup;
import org.villseriol.kakasi.cli.groups.KakasiSeparatorGroup;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "kakasi-java", mixinStandardHelpOptions = true, versionProvider = KakasiVersionProvider.class,
        header = "KAKASI - Kanji Kana Simple Inverter (Java)")
public class KakasiCommand implements Callable<String> {
    private KakasiSeparatorGroup separatorGroup;
    private KakasiFlagGroup flagGroup;
    private KakasiCharsetGroup charsetGroup;

    private KakasiRomaji romaji;
    private String input;
    private KakasiKanjiGrade furiganaGrade;
    private KakasiKanjiGrade hiraganaGrade;

    @Option(names = { "-L" }, description = "minimum furigana kanji grade (${COMPLETION-CANDIDATES})",
            converter = KakasiKanjiGradeConverter.class, completionCandidates = KakasiKanjiGradeCandidates.class,
            paramLabel = "<grade>")
    public void setFuriganaGrade(KakasiKanjiGrade furiganaGrade) {
        this.furiganaGrade = furiganaGrade;
    }


    @Option(names = { "-l" }, description = "minimum hiragana kanji grade (${COMPLETION-CANDIDATES})",
            converter = KakasiKanjiGradeConverter.class, completionCandidates = KakasiKanjiGradeCandidates.class,
            paramLabel = "<grade>")
    public void setHiraganaGrade(KakasiKanjiGrade hiraganaGrade) {
        this.hiraganaGrade = hiraganaGrade;
    }


    @Option(names = { "-r" }, description = "romaji conversion system (${COMPLETION-CANDIDATES})",
            converter = KakasiRomajiConverter.class, completionCandidates = KakasiRomajiCandidates.class,
            defaultValue = "hepburn", showDefaultValue = Visibility.ALWAYS)
    public void setRomaji(KakasiRomaji romaji) {
        this.romaji = romaji;
    }


    @ArgGroup(heading = "flags:%n")
    public void setFlagGroup(KakasiFlagGroup flagGroup) {
        this.flagGroup = flagGroup;
    }


    @ArgGroup(heading = "separator:%n", exclusive = true)
    public void setSeparatorGroup(KakasiSeparatorGroup separatorGroup) {
        this.separatorGroup = separatorGroup;
    }


    @ArgGroup(heading = "Character Sets:%n")
    public void setCharsetGroup(KakasiCharsetGroup charsetGroup) {
        this.charsetGroup = charsetGroup;
    }


    @Parameters(index = "0", description = "the input string")
    public void setInput(String input) {
        this.input = input;
    }


    @Override
    public String call() throws Exception {
        KakasiConfig config = new KakasiConfig();

        config.setRomaji(romaji);
        config.setFuriganaGrade(furiganaGrade);
        config.setHiraganaGrade(hiraganaGrade);

        separatorGroup.visit(config);
        flagGroup.visit(config);
        charsetGroup.visit(config);

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
