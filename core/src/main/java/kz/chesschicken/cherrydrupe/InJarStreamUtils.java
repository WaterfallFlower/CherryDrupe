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
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A set of tools to interact with files (resources) in .zip/.jar file, where classes are also located.
 *
 * @author ChessChicken-KZ
 */
public class InJarStreamUtils {

    /**
     * Read all lines in a given file.
     *
     * @param home Class in jar file, where the file is also located.
     * @param file Path to the file.
     * @return Lines as {@link java.util.List<String>}.
     */
    public static @Nullable List<String> readStringFile(@NotNull Class<?> home, @NotNull String file) {
        try (InputStream is = home.getResourceAsStream(file)) {
            return is != null ? new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.toList()) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * A method to read byte[] data of the provided file.
     *
     * @param home Class in jar file, where the file is also located.
     * @param file Path to the file.
     * @return The whole data of file in byte[] format.
     */
    public static byte @NotNull [] readBytesFile(@NotNull Class<?> home, @NotNull String file) {
        try (InputStream is = home.getResourceAsStream(file)) {
            if (is == null) return new byte[0];
            ByteArrayOutputStream s1 = new ByteArrayOutputStream();
            byte[] b = new byte[4096];
            int i;
            while ((i = is.read(b)) != -1)
                s1.write(b, 0, i);
            return s1.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
