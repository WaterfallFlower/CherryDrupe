package kz.chesschicken.cherrydrupe.jcheck;

import org.jetbrains.annotations.NotNull;

/**
 * A utility class for Java version checking tools.
 * <br>
 * For enum list, look at {@link EnumJavaVersion}.
 */
public class JavaCheck {

    public static void assertJava(@NotNull EnumJavaVersion gotten, byte @NotNull [] okay) {
        assertJava(EnumJavaVersion.CURRENT_JAVA_VERSION, gotten, okay);
    }

    public static void assertJava(@NotNull EnumJavaVersion ref, @NotNull EnumJavaVersion gotten, byte @NotNull [] okay) {
        byte f = EnumJavaVersion.compareJavaVersions(ref, gotten);
        for(byte a : okay) {
            if(a == f) continue;
            throw new UnsupportedJavaVersionException(ref, gotten);
        }
    }
}
