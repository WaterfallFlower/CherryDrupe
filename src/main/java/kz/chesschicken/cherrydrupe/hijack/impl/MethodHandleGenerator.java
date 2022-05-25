package kz.chesschicken.cherrydrupe.hijack.impl;

import kz.chesschicken.cherrydrupe.function.FunctionEmpty;
import kz.chesschicken.cherrydrupe.function.FunctionObjectArray;
import kz.chesschicken.cherrydrupe.hijack.AbstractGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static kz.chesschicken.cherrydrupe.hijack.InstanceProvider.getLookup;

/**
 * The implementation of {@link AbstractGenerator} with {@link java.lang.invoke.MethodHandles.Lookup}.
 * @author ChessChicken-KZ
 */
public class MethodHandleGenerator extends AbstractGenerator {

    @Override
    public <T> @Nullable Function<Object, T> field$Getter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findGetter(source, field_name, field_type).invoke(o);
            } catch (Throwable e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public <T> @Nullable BiConsumer<Object, T> field$Setter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return (o, t) -> {
            try {
                getLookup().findSetter(source, field_name, field_type).invoke(o, t);
            } catch (Throwable e) {
                __processException(e);
            }
        };
    }

    @Override
    public <T> @Nullable FunctionEmpty<T> fieldStatic$Getter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return () -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findStaticGetter(source, field_name, field_type).invoke();
            } catch (Throwable e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public <T> @Nullable Consumer<T> fieldStatic$Setter(@NotNull Class<?> source, @NotNull String field_name, @NotNull Class<T> field_type) {
        return t -> {
            try {
                getLookup().findStaticSetter(source, field_name, field_type).invoke(t);
            }catch (Throwable e) {
                __processException(e);
            }
        };
    }

    @Override
    public <T> @Nullable FunctionObjectArray<T> method$Virtual(@NotNull Class<?> source, @NotNull String method_name, @NotNull Class<T> returnType, Class<?>... argumentsType) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findVirtual(source, method_name, MethodType.methodType(returnType, argumentsType)).invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public <T> @Nullable FunctionObjectArray<T> method$Static(@NotNull Class<?> source, @NotNull String method_name, @NotNull Class<T> returnType, Class<?>... argumentsType) {
        return o -> {
            try {
                MethodHandle a = getLookup().findStatic(source, method_name, MethodType.methodType(returnType, argumentsType));
                //noinspection unchecked
                return (T) a.invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                __processException(e);
                return __nullValue();
            }
        };
    }

    @Override
    public <T> @Nullable FunctionObjectArray<T> constructor$(@NotNull Class<?> source, Class<?>[] params) {
        return objectTypeArgs -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findConstructor(source, MethodType.methodType(source, params)).invokeWithArguments(Arrays.asList(objectTypeArgs));
            } catch (Throwable e) {
                __processException(e);
                return __nullValue();
            }
        };
    }
}
