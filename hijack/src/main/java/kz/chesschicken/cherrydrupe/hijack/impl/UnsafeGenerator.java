package kz.chesschicken.cherrydrupe.hijack.impl;

import kz.chesschicken.cherrydrupe.hijack.AbstractGenerator;
import kz.chesschicken.cherrydrupe.function.FunctionRET;
import kz.chesschicken.cherrydrupe.hijack.api.IFieldGenerator;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static kz.chesschicken.cherrydrupe.hijack.InstanceProvider.getUnsafe;

/**
 * The implementation of {@link AbstractGenerator} with {@link sun.misc.Unsafe}.
 * @author ChessChicken-KZ
 */
public class UnsafeGenerator extends AbstractGenerator implements IFieldGenerator {

    @Override
    public @NotNull <T> Function<Object, T> field$Getter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getUnsafe().getObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)));
            } catch (NoSuchFieldException e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public @NotNull <T> BiConsumer<Object, T> field$Setter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return (o, t) -> {
            try {
                getUnsafe().putObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)), t);
            } catch (NoSuchFieldException e) {
                __processException(e);
            }
        };
    }

    @Override
    public @NotNull <T> FunctionRET<T> fieldStatic$Getter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return () -> {
            try {
                Field f = source.getDeclaredField(field_name);
                //noinspection unchecked
                return (T) getUnsafe().getObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f));
            } catch (NoSuchFieldException e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public @NotNull <T> Consumer<T> fieldStatic$Setter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return t -> {
            try {
                Field f = source.getDeclaredField(field_name);
                getUnsafe().putObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f), t);
            } catch (NoSuchFieldException e) {
                __processException(e);
            }
        };
    }
}
