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

import org.jetbrains.annotations.NotNull;

/**
 * The utility class with Java version check methods.
 * <p>
 *     For enum list, look at {@link EnumJavaVersion}.
 * </p>
 * @author ChessChicken-KZ
 * @see EnumJavaVersion
 * @see UnsupportedJavaVersionException
 */
public class JavaCheck {

    public static void assertJava(@NotNull EnumJavaVersion gotten, int @NotNull [] okay) {
        assertJava(EnumJavaVersion.CURRENT_JAVA_VERSION, gotten, okay);
    }

    public static void assertJava(@NotNull EnumJavaVersion ref, @NotNull EnumJavaVersion gotten, int @NotNull [] okay) {
        int f = EnumJavaVersion.compareJavaVersions(ref, gotten);
        for(int a : okay) {
            if(a == f) continue;
            throw new UnsupportedJavaVersionException(ref, gotten);
        }
    }
}
