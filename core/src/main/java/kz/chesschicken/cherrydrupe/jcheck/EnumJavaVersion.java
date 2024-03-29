/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2023 ChessChicken-KZ

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

import org.jetbrains.annotations.NotNull;

/**
 * An enum list with java versions, starting from 1.6 till 18.
 *
 * @author ChessChicken-KZ
 * @since 0.1
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
    JAVA_19("Java 19", (byte) 19),
    JAVA_20("Java 20", (byte) 20),
    /**
     * Only being chosen by system if the Java Runtime version is newer that any available in enum list.
     */
    NEWER_VERSION("Unknown New Version", Byte.MAX_VALUE),
    /**
     * Unknown Java version.
     */
    UNKNOWN("Unknown", Byte.MIN_VALUE);

    public final String simpleName;
    public final byte version;

    EnumJavaVersion(String a, byte b) {
        simpleName = a;
        version = b;
    }

    public static EnumJavaVersion CURRENT_JAVA_VERSION;

    public static @NotNull EnumJavaVersion getCurrentJavaVersion() {
        if (CURRENT_JAVA_VERSION == null) {
            String a = System.getProperty("java.version");
            a = a.startsWith("1.") ? a.substring(2, 3) : ((a.contains(".")) ? a.substring(0, a.indexOf(".")) : a);
            try {
                return EnumJavaVersion.valueOf("JAVA_" + a);
            } catch (IllegalArgumentException e) {
                return Byte.parseByte(a) > EnumJavaVersion.JAVA_18.version ? EnumJavaVersion.NEWER_VERSION : EnumJavaVersion.UNKNOWN;
            }
        }
        return CURRENT_JAVA_VERSION;
    }

    public static int compareJavaVersions(@NotNull EnumJavaVersion a, @NotNull EnumJavaVersion b) {
        return Byte.compare(a.version, b.version);
    }
}
