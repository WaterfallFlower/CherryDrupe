package kz.chesschicken.cherrydrupe.hijack;

import kz.chesschicken.cherrydrupe.hijack.api.IFieldGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A fundament for generator implementations.
 * @author ChessChicken-KZ
 */
public abstract class AbstractGenerator {

    /**
     * A method that resets all the properties set in generator.
     */
    public void resetProperties() {
        this.EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
        this.EXCEPTION_HANDLE = null;
        this.DEFAULT_NULL_VALUE = null;
    }

    /**
     * A method to set the generator's behaviour when an exception is caught.
     * <br>
     * See {@link EnumExceptionBehaviour} for possible behaviours.
     * @param a an enum state, representing exception behaviour.
     */
    public void setExceptionBehaviour(@NotNull EnumExceptionBehaviour a) {
        this.EXCEPTION_BEHAVIOUR = a;
    }

    /**
     * A method to get value from field that contains throwable.
     * @return "null" if empty or some {@link Throwable} value.
     */
    public @Nullable Throwable throwable() {
        return this.EXCEPTION_HANDLE;
    }

    /**
     * A method to set the default value instead of "null".
     * <br>
     * Could be useful if needed to avoid NullPointerExceptions.
     * @param t an object to replace with.
     * @param <T> some generic type of the object.
     */
    public <T> void setDefaultNullValue(@Nullable T t) {
        this.DEFAULT_NULL_VALUE = t;
    }

    protected void __processException(@NotNull Throwable e) {
        switch (this.EXCEPTION_BEHAVIOUR) {
            case SKIP_EXCEPTION:
                return;
            case PRINT_EXCEPTION: {
                e.printStackTrace();
                break;
            }
            case CATCH_EXCEPTION: {
                this.EXCEPTION_HANDLE = e;
                break;
            }
            case THROW_EXCEPTION: {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> @Nullable T __nullValue() {
        return (T) this.DEFAULT_NULL_VALUE;
    }

    EnumExceptionBehaviour EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
    Throwable EXCEPTION_HANDLE = null;
    Object DEFAULT_NULL_VALUE = null;
}
