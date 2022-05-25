package kz.chesschicken.cherrydrupe.hijack;

import kz.chesschicken.cherrydrupe.hijack.api.IMethodGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractGenerator implements IMethodGenerator {

    public void resetProperties() {
        this.EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
        this.EXCEPTION_HANDLE = null;
        this.DEFAULT_NULL_VALUE = null;
    }

    public void setExceptionBehaviour(EnumExceptionBehaviour a) {
        this.EXCEPTION_BEHAVIOUR = a;
    }

    public @Nullable Throwable throwable() {
        return this.EXCEPTION_HANDLE;
    }

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
    protected <T> @Nullable T getDefaultNullValue() {
        return (T) this.DEFAULT_NULL_VALUE;
    }

    protected EnumExceptionBehaviour EXCEPTION_BEHAVIOUR = EnumExceptionBehaviour.PRINT_EXCEPTION;
    protected Throwable EXCEPTION_HANDLE = null;
    protected Object DEFAULT_NULL_VALUE = null;
}
