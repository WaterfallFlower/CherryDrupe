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
 * Something to be thrown...
 * //TODO: Finish description...
 */
public class UnsupportedJavaVersionException extends RuntimeException {
    private static final long serialVersionUID = 4559356163768608667L;

    public UnsupportedJavaVersionException(@NotNull EnumJavaVersion current, @NotNull EnumJavaVersion required) {
        super("Required Java version: " + required.HUMAN_READABLE_NAME + ", but got: " + current.HUMAN_READABLE_NAME);
    }
}
