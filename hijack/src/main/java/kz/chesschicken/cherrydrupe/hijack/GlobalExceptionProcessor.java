package kz.chesschicken.cherrydrupe.hijack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple exception processor for
 * @author ChessChicken-KZ
 */
public class GlobalExceptionProcessor {

    /**
     * A method that resets all the properties set in generator.
     */
    public static void resetProperties() {
        EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
        EXCEPTION_HANDLE = null;
        DEFAULT_NULL_VALUE = null;
    }

    /**
     * A method to set the generator's behaviour when an exception is caught.
     * <br>
     * See {@link EnumExceptionBehaviour} for possible behaviours.
     * @param a an enum state, representing exception behaviour.
     */
    public static void setExceptionBehaviour(@NotNull EnumExceptionBehaviour a) {
        EXCEPTION_BEHAVIOUR = a;
    }

    /**
     * A method to get value from field that contains throwable.
     * @return "null" if empty or some {@link Throwable} value.
     */
    public static @Nullable Throwable throwable() {
        return EXCEPTION_HANDLE;
    }

    /**
     * A method to set the default value instead of "null".
     * <br>
     * Could be useful if needed to avoid NullPointerExceptions.
     * @param t an object to replace with.
     * @param <T> some generic type of the object.
     */
    public static <T> void setDefaultNullValue(@Nullable T t) {
        DEFAULT_NULL_VALUE = t;
    }

    public static void processException(@NotNull Throwable e) {
        switch (EXCEPTION_BEHAVIOUR) {
            case SKIP_EXCEPTION:
                return;
            case PRINT_EXCEPTION: {
                e.printStackTrace();
                break;
            }
            case CATCH_EXCEPTION: {
                EXCEPTION_HANDLE = e;
                break;
            }
            case THROW_EXCEPTION: {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable T safeNullValue() {
        return (T) DEFAULT_NULL_VALUE;
    }

    static EnumExceptionBehaviour EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
    static Throwable EXCEPTION_HANDLE = null;
    static Object DEFAULT_NULL_VALUE = null;
}
