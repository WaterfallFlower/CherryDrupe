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
package kz.chesschicken.cherrydrupe.hijack;

import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * Small utility class, which provides working instances of {@link Unsafe} and {@link MethodHandles.Lookup} (low-level).
 * <p>
 * This utilities class provides two public methods:
 *     <ul>
 *         <li>{@link Unsafe} instance {@link InstanceProvider#getUnsafe()}, with full permission.</li>
 *         <li>{@link MethodHandles.Lookup} instance {@link InstanceProvider#getLookup()} with full trusted permission.</li>
 *     </ul>
 * </p>
 *
 * @author ChessChicken-KZ
 * @see kz.chesschicken.cherrydrupe.hijack.impl.MethodHandleUtilities
 * @see kz.chesschicken.cherrydrupe.hijack.impl.UnsafeUtilities
 * @since 0.1
 */
public class InstanceProvider {

    /**
     * A method that returns Unsafe instance with all permissions.
     *
     * @return The instance of Unsafe class.
     */
    public static @NotNull Unsafe getUnsafe() {
        return UNSAFE_INSTANCE;
    }

    /**
     * A method that returns MethodHandles.Lookup instance with TRUSTED level of access.
     *
     * @return The instance of MethodHandles.Lookup class.
     */
    public static @NotNull MethodHandles.Lookup getLookup() {
        return IMPL_LOOKUP;
    }

    static Unsafe UNSAFE_INSTANCE;
    static MethodHandles.Lookup IMPL_LOOKUP;

    static {
        try {
            Field u = Unsafe.class.getDeclaredField("theUnsafe");
            u.setAccessible(true);
            UNSAFE_INSTANCE = (Unsafe) u.get(null);

            Field i = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            IMPL_LOOKUP = (MethodHandles.Lookup) UNSAFE_INSTANCE.getObject(UNSAFE_INSTANCE.staticFieldBase(i), UNSAFE_INSTANCE.staticFieldOffset(i));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
