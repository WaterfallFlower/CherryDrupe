package kz.chesschicken.cherrydrupe.jcheck;

import org.jetbrains.annotations.NotNull;

/**
 * Something to be thrown...
 * //TODO: Finish description...
 */
public class UnsupportedJavaVersionException extends RuntimeException {
    private static final long serialVersionUID = 4559356163768608667L;

    public UnsupportedJavaVersionException(@NotNull EnumJavaVersion current, @NotNull EnumJavaVersion required) {
        super("Required Java version: " + required.HUMAN_READABLE_NAME + ", but got: " + current.HUMAN_READABLE_NAME);
    }
}
