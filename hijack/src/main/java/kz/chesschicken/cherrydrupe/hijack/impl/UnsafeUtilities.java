package kz.chesschicken.cherrydrupe.hijack.impl;

import kz.chesschicken.cherrydrupe.hijack.GlobalExceptionProcessor;
import kz.chesschicken.cherrydrupe.function.FunctionRET;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static kz.chesschicken.cherrydrupe.hijack.InstanceProvider.getUnsafe;

/**
 * The more intelligent and easy to use {@link sun.misc.Unsafe} wrapper.
 * @author ChessChicken-KZ
 */
public class UnsafeUtilities {

    /**
     * A method that will try to get a value from specified field.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param instance Instance of the class where the field is located.
     * @param <T> Return type.
     * @return A value of specified field with specified return type.
     * @throws NoSuchFieldException Will be thrown if field with given name doesn't exist.
     */
    public static <T> @Nullable T getField(@NotNull Class<?> source, @NotNull String field_name, @NotNull Object instance) throws NoSuchFieldException {
        //noinspection unchecked
        return (T) getUnsafe().getObject(instance, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)));
    }

    /**
     * A method that will try to get a value from specified static field.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param <T> Return type.
     * @return A value of specified static field with specified return type.
     * @throws NoSuchFieldException Will be thrown if field with given name doesn't exist.
     */
    public static <T> @Nullable T getStaticField(@NotNull Class<?> source, @NotNull String field_name) throws NoSuchFieldException {
        Field f = source.getDeclaredField(field_name);
        //noinspection unchecked
        return (T) getUnsafe().getObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f));
    }

    /**
     * A method that will try to set specified field with given value.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param instance Instance of the class where the field is located.
     * @param value A new value for the field.
     * @param <T> The new value's type.
     * @throws NoSuchFieldException Will be thrown if field with given name doesn't exist.
     */
    public static <T> void setField(@NotNull Class<?> source, @NotNull String field_name, @NotNull Object instance, @Nullable T value) throws NoSuchFieldException {
        getUnsafe().putObject(instance, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)), value);
    }

    /**
     * A method that will try to set specified static field with given value.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param value A new value for the static field.
     * @param <T> The new value's type.
     * @throws NoSuchFieldException Will be thrown if static field with given name doesn't exist.
     */
    public static <T> void setStaticField(@NotNull Class<?> source, @NotNull String field_name, @Nullable T value) throws NoSuchFieldException {
        Field f = source.getDeclaredField(field_name);
        getUnsafe().putObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f), value);
    }

    public static @NotNull <T> Function<@NotNull Object, @Nullable T> generateGetter(@NotNull Class<?> source, @NotNull String field_name) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getUnsafe().getObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)));
            } catch (NoSuchFieldException e) {
                GlobalExceptionProcessor.processException(e);
                return GlobalExceptionProcessor.safeNullValue();
            }
        };
    }

    public static @NotNull <T> BiConsumer<@NotNull Object, @Nullable  T> generateSetter(@NotNull Class<?> source, @NotNull String field_name) {
        return (o, t) -> {
            try {
                getUnsafe().putObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)), t);
            } catch (NoSuchFieldException e) {
                GlobalExceptionProcessor.processException(e);
            }
        };
    }

    public static @NotNull <T> FunctionRET<@Nullable T> generateStaticGetter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return () -> {
            try {
                Field f = source.getDeclaredField(field_name);
                //noinspection unchecked
                return (T) getUnsafe().getObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f));
            } catch (NoSuchFieldException e) {
                GlobalExceptionProcessor.processException(e);
                return GlobalExceptionProcessor.safeNullValue();
            }
        };
    }

    public static @NotNull <T> Consumer<@Nullable T> generateStaticSetter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return t -> {
            try {
                Field f = source.getDeclaredField(field_name);
                getUnsafe().putObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f), t);
            } catch (NoSuchFieldException e) {
                GlobalExceptionProcessor.processException(e);
            }
        };
    }
}
