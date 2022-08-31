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
package kz.chesschicken.cherrydrupe.function;

/**
 * An interface functions with 3 arguments and 1 return value.
 * @param <A> First argument's type.
 * @param <B> Second argument's type.
 * @param <C> Third argument's type.
 * @param <RET> Return argument's type.
 * @author ChessChicken-KZ
 */
public interface Function3SETRET<A, B, C, RET> {
    RET apply(A a, B b, C c);
}
