// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli.groups;

import org.villseriol.kakasi.api.KakasiConfig;

import picocli.CommandLine.Option;


public class KakasiSeparatorGroup implements KakasiGroupVisitor {
    private boolean separator;
    private String customSeparatorValue;

    @Option(names = { "-S" }, description = "manually set separator", paramLabel = "<separator>")
    public void setCustomSeparatorValue(String customSeparatorValue) {
        this.customSeparatorValue = customSeparatorValue;
    }


    @Option(names = { "-s" }, description = "insert space between words (with -J option)", type = Boolean.class,
            defaultValue = "false")
    public void setSeparator(Boolean separator) {
        this.separator = Boolean.TRUE.equals(separator);
    }


    public String getCustomSeparatorValue() {
        return customSeparatorValue;
    }


    public boolean isSeparator() {
        return separator;
    }


    @Override
    public void visit(KakasiConfig config) {
        if (separator) {
            config.setSeparator(" ");
        } else if (customSeparatorValue != null && !"".equals(customSeparatorValue)) {
            config.setSeparator(customSeparatorValue);
        }
    }
}
