// This software is released into the Public Domain.  See copying.txt for details.
package org.villseriol.kakasi.cli;

import picocli.CommandLine.IVersionProvider;


public class KakasiVersionProvider implements IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        String version = KakasiVersionProvider.class.getPackage().getImplementationVersion();

        // @formatter:off
        return new String[] {version};
        // @formatter:on
    }
}
