package kz.chesschicken.cherrydrupe.function;

/**
 * An interface functions with 3 arguments and 1 return value.
 * @param <A> First argument's type.
 * @param <B> Second argument's type.
 * @param <C> Third argument's type.
 * @param <RET> Return argument's type.
 * @author ChessChicken-KZ
 */
public interface Function3SETRET<A, B, C, RET> {
    RET apply(A a, B b, C c);
}
