/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2022 ChessChicken-KZ

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package kz.chesschicken.cherrydrupe.jcheck;

import kz.chesschicken.cherrydrupe.function.FunctionRET;
import org.jetbrains.annotations.NotNull;

/**
 * An enum list of java versions.
 * @author ChessChicken-KZ
 */
public enum EnumJavaVersion {
    /**
     * Isn't supported by the library.
     */
    @Deprecated
    JAVA_6("Java 1.6", (byte) 6),
    /**
     * Isn't supported by the library.
     */
    @Deprecated
    JAVA_7("Java 1.7", (byte) 7),
    JAVA_8("Java 1.8", (byte) 8),
    JAVA_9("Java 9", (byte) 9),
    JAVA_10("Java 10", (byte) 10),
    JAVA_11("Java 11", (byte) 11),
    JAVA_12("Java 12", (byte) 12),
    JAVA_13("Java 13", (byte) 13),
    JAVA_14("Java 14", (byte) 14),
    JAVA_15("Java 15", (byte) 15),
    JAVA_16("Java 16", (byte) 16),
    JAVA_17("Java 17", (byte) 17),
    JAVA_18("Java 18", (byte) 18),
    UNKNOWN("Unknown", Byte.MAX_VALUE);

    public final String HUMAN_READABLE_NAME;
    public final byte CODE;

    EnumJavaVersion(String a, byte b) {
        HUMAN_READABLE_NAME = a;
        CODE = b;
    }

    public static final @NotNull EnumJavaVersion CURRENT_JAVA_VERSION = ((FunctionRET<EnumJavaVersion>) () -> {
        String a = System.getProperty("java.version");
        a = a.startsWith("1.") ? a.substring(2, 3) : ((a.contains(".")) ? a.substring(0, a.indexOf(".")) : a);
        try {
            return EnumJavaVersion.valueOf("JAVA_" + a);
        } catch (IllegalArgumentException e) {
            return EnumJavaVersion.UNKNOWN;
        }
    }).apply();

    @SuppressWarnings("UseCompareMethod")
    public static byte compareJavaVersions(@NotNull EnumJavaVersion a, @NotNull EnumJavaVersion b) {
        if(a.CODE < b.CODE) return -1;
        if(a.CODE > b.CODE) return 1;
        return 0;
    }
}
