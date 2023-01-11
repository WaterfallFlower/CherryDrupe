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

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A set of various system controlling methods, should be treated as a utility that can fully delete a folder, copy, move files, etc.
 *
 * @author ChessChicken-KZ
 * @since 0.3
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class SysTools {

    public static @NotNull String readFile(@NotNull InputStream stream, @Nullable Charset charset) throws IOException {
        StringBuilder retValue = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(stream, charset == null ? StandardCharsets.UTF_8 : charset);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            retValue.append(line).append("\n");
        }
        reader.close();
        isr.close();
        return retValue.toString();
    }

    public static byte @NotNull [] readFile(@NotNull InputStream stream) throws IOException {
        byte[] data = new byte[stream.available()];
        stream.read(data);
        stream.close();
        return data;
    }

    public static void writeFile(@NotNull File file, @NotNull String data, @Nullable Charset charset) throws IOException {
        writeFile(file, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset));
    }

    public static void writeFile(@NotNull File file, byte @NotNull [] data) throws IOException {
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    public static void moveFile(@NotNull String file, @NotNull String destination) throws IOException {
        moveFile(Paths.get(file), Paths.get(destination));
    }

    public static void moveFile(@NotNull File file, @NotNull File destination) throws IOException {
        moveFile(file.toPath(), destination.toPath());
    }

    public static void copyFile(@NotNull String file, @NotNull String destination) throws IOException {
        copyFile(Paths.get(file), Paths.get(destination));
    }

    public static void copyFile(@NotNull File file, @NotNull File destination) throws IOException {
        copyFile(file.toPath(), destination.toPath());
    }


    public static @NotNull List<String> readFileAsList(@NotNull File file) throws IOException {
        List<String> a = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            a.add(line);
        }
        br.close();
        fr.close();
        return a;
    }

    public static void deleteFolder(@NotNull File file) throws IOException {
        Files.walk(file.toPath()).sorted(Comparator.reverseOrder()).forEach(path -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @ApiStatus.Internal
    private static void moveFile(@NotNull Path file, @NotNull Path destination) throws IOException {
        Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    @ApiStatus.Internal
    private static void copyFile(@NotNull Path file, @NotNull Path destination) throws IOException {
        Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
