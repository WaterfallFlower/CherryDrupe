package kz.chesschicken.cherrydrupe.stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A set of tools to interact with files (resources) in .zip/.jar file, where classes are also located.
 * @author ChessChicken-KZ
 */
public class InJarStreamUtils {

    /**
     * A method to read lines of the provided file.
     * @param home Class in jar file, where the file is also located.
     * @param file Path to the file.
     * @return Lines of the file in {@link java.util.List<String>} format.
     */
    public static @Nullable List<String> readStringFile(@NotNull Class<?> home, @NotNull String file) {
        try(InputStream is = home.getResourceAsStream(file)) {
            if(is == null)
                return null;
            return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * A method to read byte[] data of the provided file.
     * @param home Class in jar file, where the file is also located.
     * @param file Path to the file.
     * @return The whole data of file in byte[] format.
     */
    public static byte @NotNull [] readBytesFile(@NotNull Class<?> home, @NotNull String file) {
        try(InputStream is = home.getResourceAsStream(file)) {
            if(is == null)
                return new byte[0];

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
