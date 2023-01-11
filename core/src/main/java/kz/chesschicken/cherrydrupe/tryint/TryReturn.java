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
package kz.chesschicken.cherrydrupe.tryint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An interface implementation of the "try" statement.
 *
 * @param <R> The interface's return type.
 * @param <T> An exception's type.
 * @author ChessChicken-KZ
 */
@SuppressWarnings("UnusedReturnValue")
public interface TryReturn<R, T extends Throwable> {

    @Nullable R _try() throws T;

    @Nullable R _catch(@NotNull T t);

    default void _finally() {
    }

    @SuppressWarnings("unchecked")
    @Nullable
    default R apply() {
        try {
            return _try();
        } catch (Throwable t) {
            return _catch((T) t);
        } finally {
            _finally();
        }
    }
}
