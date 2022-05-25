package kz.chesschicken.cherrydrupe.jcheck;

public class UnsupportedJavaVersionException extends RuntimeException {
    private static final long serialVersionUID = 4559356163768608667L;

    public UnsupportedJavaVersionException(EnumJavaVersion current, EnumJavaVersion required) {
        super("Required Java version: " + required.HUMAN_READABLE_NAME + ", but got: " + current.HUMAN_READABLE_NAME);
    }
}
