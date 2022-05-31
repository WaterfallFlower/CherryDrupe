package kz.chesschicken.cherrydrupe.tryint;

/**
 * An interface implementation of the "try" statement.
 * @param <T> An exception's type.
 * @author ChessChicken-KZ
 */
public interface Try<T extends Throwable> extends TryReturn<Void, T> {
}
