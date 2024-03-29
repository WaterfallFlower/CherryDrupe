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

/**
 * An interface implementation of the "try" statement.
 *
 * @param <T> An exception's type.
 * @author ChessChicken-KZ
 */
public interface Try<T extends Throwable> {

    void _try() throws T;

    void _catch(@NotNull T t);

    default void _finally() {
    }

    @SuppressWarnings("unchecked")
    default void apply() {
        try {
            _try();
        } catch (Throwable t) {
            _catch((T) t);
        } finally {
            _finally();
        }
    }

}
