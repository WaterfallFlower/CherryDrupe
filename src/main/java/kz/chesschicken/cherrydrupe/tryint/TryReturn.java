package kz.chesschicken.cherrydrupe.tryint;

/**
 * An interface implementation of the "try" statement.
 * @param <R> The interface's return type.
 * @param <T> An exception's type.
 * @author ChessChicken-KZ
 */
public interface TryReturn<R, T extends Throwable> {

    R _try() throws T;

    R _catch(T t);

    default void _finally() {}

    default R apply() {
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
