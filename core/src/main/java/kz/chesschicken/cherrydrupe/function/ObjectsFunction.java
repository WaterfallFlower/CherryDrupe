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
package kz.chesschicken.cherrydrupe.function;

/**
 * A functional interface with argument as Object array and specific return.
 *
 * @param <O> Return type.
 * @author ChessChicken-KZ
 * @since 0.1
 */
@FunctionalInterface
public interface ObjectsFunction<O> {
    O apply(Object... o);
}
