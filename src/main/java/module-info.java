// This software is released into the Public Domain.  See copying.txt for details.
module org.villseriol.kakasi {
    exports org.villseriol.kakasi.api;
    exports org.villseriol.kakasi.cli;
    exports org.villseriol.kakasi.cli.groups;

    opens org.villseriol.kakasi.cli.groups to info.picocli;
    opens org.villseriol.kakasi.cli.converters to info.picocli;

    requires info.picocli;
}
