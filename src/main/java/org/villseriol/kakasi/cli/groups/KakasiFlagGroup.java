// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli.groups;

import org.villseriol.kakasi.api.KakasiConfig;

import picocli.CommandLine.Option;


public class KakasiFlagGroup implements KakasiGroupVisitor {
    private boolean wakatigaki;
    private boolean showAllReadings;
    private boolean displayYomi;
    private boolean capitalise;
    private boolean uppercase;

    @Option(names = { "-U" }, description = "romaji Upcase (with -Ja or -Jj option)", type = Boolean.class,
            defaultValue = "false")
    public void setUppercase(Boolean uppercase) {
        this.uppercase = Boolean.TRUE.equals(uppercase);
    }


    public boolean isUppercase() {
        return uppercase;
    }


    @Option(names = { "-w" }, description = "wakatigaki mode", type = Boolean.class, defaultValue = "false")
    public void setWakatigaki(Boolean wakatigaki) {
        this.wakatigaki = Boolean.TRUE.equals(wakatigaki);
    }


    public boolean isWakatigaki() {
        return wakatigaki;
    }


    @Option(names = { "-C" }, description = "romaji Capitalize (with -Ja or -Jj option)", type = Boolean.class,
            defaultValue = "false")
    public void setCapitalise(Boolean capitalise) {
        this.capitalise = Boolean.TRUE.equals(capitalise);
    }


    public boolean isCapitalise() {
        return capitalise;
    }


    @Option(names = { "-y" }, description = "display yomi of each kanji characters", type = Boolean.class,
            defaultValue = "false")
    public void setDisplayYomi(Boolean displayYomi) {
        this.displayYomi = Boolean.TRUE.equals(displayYomi);
    }


    public boolean isDisplayYomi() {
        return displayYomi;
    }


    @Option(names = { "-p" }, description = "list all readings (with -J option)", type = Boolean.class,
            defaultValue = "false")
    public void setShowAllReadings(Boolean showAllReadings) {
        this.showAllReadings = Boolean.TRUE.equals(showAllReadings);
    }


    public boolean isShowAllReadings() {
        return showAllReadings;
    }


    @Override
    public void visit(KakasiConfig config) {
        config.setShowAllReadings(showAllReadings);
        config.setWakatigaki(wakatigaki);
        config.setYomi(displayYomi);
        config.setCapitalise(capitalise);
        config.setUppercase(uppercase);
    }

}
