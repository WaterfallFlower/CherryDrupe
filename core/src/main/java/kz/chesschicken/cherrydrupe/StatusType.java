package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Avoid using this until I get the idea of this.
 * @author ChessChicken-KZ
 * @param <T> Something...
 */
@ApiStatus.Experimental
public class StatusType<T> {
    public static enum Status {
        SUCCESS,
        FAIL,
        NULL
    }

    @Nullable T value;
    @NotNull Status status;

    public @Nullable T getValue() {
        return this.value;
    }

    public @NotNull Status getStatus() {
        return this.status;
    }

    StatusType(@Nullable T t, @NotNull Status s) {
        this.value = t;
        this.status = s;
    }

    public static <A> @NotNull StatusType<A> success(@Nullable A a) {
        return new StatusType<>(a, Status.SUCCESS);
    }

    public static <A> @NotNull StatusType<A> fail(@Nullable A a) {
        return new StatusType<>(a, Status.FAIL);
    }

    public static <A> @NotNull StatusType<A> _null() {
        return new StatusType<>(null, Status.NULL);
    }
}
