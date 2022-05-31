package kz.chesschicken.cherrydrupe.tryint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An interface implementation of the "try" statement.
 * @param <R> The interface's return type.
 * @param <T> An exception's type.
 * @author ChessChicken-KZ
 */
@SuppressWarnings("UnusedReturnValue")
public interface TryReturn<R, T extends Throwable> {

    @Nullable R _try() throws T;

    @Nullable R _catch(@NotNull T t);

    default void _finally() {}

    @Nullable default R apply() {
        try {
            return _try();
        } catch (Throwable t) {
            //noinspection unchecked
            return _catch((T) t);
        } finally {
            _finally();
        }
    }
}
