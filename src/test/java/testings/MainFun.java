package testings;

import kz.chesschicken.cherrydrupe.tryint.TryReturn;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainFun {
    public static void main(String[] args) {

        TryReturn<String, IOException> try1 = new TryReturn<String, IOException>() {
            @Override
            public @NotNull String _try() throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/text.txt"));
                StringBuilder stringBuilder = new StringBuilder();
                String s;

                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                    stringBuilder.append(System.lineSeparator());
                }

                return stringBuilder.toString();
            }

            @Override
            public @NotNull String _catch(@NotNull IOException e) {
                System.out.println("Oops... caught an exception!");
                e.printStackTrace();
                return "";
            }
        };

        try1.apply();


    }
}