# libkakasi2-java

This project implements a Java-wrapper around the [kakasi](http://kakasi.namazu.org/index.html.en) C
library. It provides:

- Instance-based access to the kakasi C library
- Portability between Windows and Unix systems

## Installation

See the [artifact repository](https://central.sonatype.com/artifact/io.github.villseriol/kakasi2)
for more.

```xml
<dependency>
    <groupId>io.github.villseriol</groupId>
    <artifactId>kakasi2</artifactId>
    <version>2.0.2</version>
</dependency>
```

## Usage

### Simple Usage

```java
Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG);
String out = kakasi.run("正直"); // shoujiki
```

### Try-Resources

```java
try (Kakasi kakasi = new Kakasi(KakasiConstants.ASCII_CONFIG)) {
    String out = kakasi.run("正直"); // shoujiki
} catch (Exception e) {
    // catch errors here
}
```

### Custom Configuration

```java
KakasiConfig config = new KakasiConfig();
config.setFurigana(true);
config.setFuriganaLeft("{");
config.setFuriganaRight("}");

List<KakasiTranslation> translations = new ArrayList<>();
translations.add(new KakasiTranslation(KakasiCharsetCategory.KANJI, KakasiCharsetCategory.KATAKANA));
config.setTranslations(translations);

Kakasi kakasi = new Kakasi(config);
String out = kakasi.run("山");
System.out.println(out);  // 山{ヤマ}
```

Is equivalent to,

```sh
> echo '山' | kakasi -iutf8 -outf8 -s -S  -f -rhepburn -Fl{ -Fr} -JH
> 山{ヤマ}
```

## Disclaimer

This program is free software: you can redistribute it and/or modify it under the terms of the GNU
General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not,
see <https://www.gnu.org/licenses/>.
