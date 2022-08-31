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
 * The implementation of {@link GlobalExceptionProcessor} with {@link sun.misc.Unsafe}.
 * @author ChessChicken-KZ
 */
public class UnsafeGenerator {

    public static <T> @Nullable T getField(@NotNull Class<?> source, @NotNull String field_name, @NotNull Object instance) throws NoSuchFieldException {
        //noinspection unchecked
        return (T) getUnsafe().getObject(instance, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)));
    }

    public static <T> @Nullable T getStaticField(@NotNull Class<?> source, @NotNull String field_name) throws NoSuchFieldException {
        Field f = source.getDeclaredField(field_name);
        //noinspection unchecked
        return (T) getUnsafe().getObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f));
    }

    public static <T> void setField(@NotNull Class<?> source, @NotNull String field_name, @NotNull Object instance, @Nullable T value) throws NoSuchFieldException {
        getUnsafe().putObject(instance, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)), value);
    }

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
