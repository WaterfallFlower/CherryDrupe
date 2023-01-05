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
package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.NotNull;

public class ColorTools {

    public static int intRGB(int r, int g, int b) {
        return ((r & 0x0FF) << 16) | ((g & 0x0FF) << 8) | (b & 0x0FF);
    }

    public static int intARGB(int r, int g, int b, int a) {
        return (a << 24) | ((r & 0x0FF) << 16) | ((g & 0x0FF) << 8) | (b & 0x0FF);
    }

    public static int @NotNull [] RGBAInt(int i) {
        return new int[] {
                (i & 0xff0000) >> 16,
                (i & 0xff00) >> 8,
                i & 0xff,
                (i & 0xff000000) >>> 24,
        };
    }

    public static float @NotNull [] RGBAFloat(int i) {
        return new float[] {
                1.0F * ((i & 0xff0000) >> 16) / 255,
                1.0F * ((i & 0xff00) >> 8) / 255,
                1.0F * (i & 0xff) / 255,
                1.0F * ((i & 0xff000000) >>> 24) / 255,
        };
    }

    public static float @NotNull [] ARGBFloat(int a) {
        return new float[] {
                (float)(a >> 24 & 255) / 255.0F,
                (float)(a >> 16 & 255) / 255.0F,
                (float)(a >> 8 & 255) / 255.0F,
                (float)(a & 255) / 255.0F
        };
    }

    public static byte @NotNull [] getRGBColorTime() {
        int rgb = java.awt.Color.HSBtoRGB((float) (System.currentTimeMillis() % 11520L) / 11520.0F, 1.0F, 1.0F);
        return new byte[] { (byte) (rgb >> 16 & 0xFF), (byte) (rgb >> 8 & 0xFF), (byte) (rgb & 0xFF) };
    }

}
