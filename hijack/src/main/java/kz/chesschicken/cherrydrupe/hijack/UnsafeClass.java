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
package kz.chesschicken.cherrydrupe.hijack;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class UnsafeClass {

    @SuppressWarnings("unchecked")
    @ApiStatus.Experimental
    public static <T> @Nullable T initClass(Class<T> target, Consumer<T> header) {
        try {
            T a = (T) InstanceProvider.UNSAFE_INSTANCE.allocateInstance(target);
            header.accept(a);
            return a;
        }catch (InstantiationException error) {
            return null;
        }
    }

}
