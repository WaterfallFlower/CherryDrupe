package kz.chesschicken.cherrydrupe.jcheck;

public class JavaCheck {
    public static void assertJava(EnumJavaVersion gotten, byte[] okay) {
        assertJava(EnumJavaVersion.CURRENT_JAVA_VERSION, gotten, okay);
    }

    public static void assertJava(EnumJavaVersion ref, EnumJavaVersion gotten, byte[] okay) {
        byte f = EnumJavaVersion.compareJavaVersions(ref, gotten);
        for(byte a : okay) {
            if(a == f) continue;
            throw new UnsupportedJavaVersionException(ref, gotten);
        }
    }
}
