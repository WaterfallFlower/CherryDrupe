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
package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.NotNull;

public class PrimitiveTransform {

    public static short @NotNull [] intToShort(int @NotNull [] i) {
        short[] s = new short[i.length];
        for(int q = 0; q < s.length; q++)
            s[q] = (short) i[q];
        return s;
    }

    public static int @NotNull [] shortToInt(short @NotNull [] s) {
        int[] a = new int[s.length];
        for(int q = 0; q < s.length; q++)
            a[q] = s[q];
        return a;
    }

    public static byte @NotNull [] intToByte(int @NotNull [] i) {
        byte[] b = new byte[i.length];
        for(int q = 0; q < b.length; q++)
            b[q] = (byte) i[q];
        return b;
    }

    public static int @NotNull [] byteToInt(byte @NotNull [] b) {
        int[] i = new int[b.length];
        for(int q = 0; q < b.length; q++)
            i[q] = b[q];
        return i;
    }

}
