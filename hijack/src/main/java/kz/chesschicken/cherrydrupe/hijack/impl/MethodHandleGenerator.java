package kz.chesschicken.cherrydrupe.hijack.impl;

import kz.chesschicken.cherrydrupe.function.FunctionRET;
import kz.chesschicken.cherrydrupe.function.FunctionARRSETRET;
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
 * The more specified and easy to use wrapper, designed specially for {@link java.lang.invoke.MethodHandles.Lookup} class.
 *
 * @author ChessChicken-KZ
 * @since 0.2
 */
public class MethodHandleGenerator {

    public static <T> @NotNull Function<@NotNull Object, @Nullable T> generateFieldGetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type,
            @Nullable Function<Throwable, T> onException
    ) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findGetter(source, field_name, field_type).invoke(o);
            } catch (Throwable e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    public static <T> @NotNull BiConsumer<@NotNull Object, @Nullable T> generateFieldSetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type,
            @Nullable Consumer<Throwable> onException
    ) {
        return (o, t) -> {
            try {
                getLookup().findSetter(source, field_name, field_type).invoke(o, t);
            } catch (Throwable e) {
                if(onException != null)
                    onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    public static <T> @NotNull FunctionRET<@Nullable T> generateStaticFieldGetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type,
            @Nullable Function<Throwable, T> onException
    ) {
        return () -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findStaticGetter(source, field_name, field_type).invoke();
            } catch (Throwable e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    public static <T> @NotNull Consumer<@Nullable T> generateStaticFieldSetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type,
            @Nullable Consumer<Throwable> onException
    ) {
        return t -> {
            try {
                getLookup().findStaticSetter(source, field_name, field_type).invoke(t);
            }catch (Throwable e) {
                if(onException != null) onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    public static <T> @NotNull FunctionARRSETRET<@Nullable T> generateMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> returnType,
            Class<?>[] params,
            @Nullable Function<Throwable, T> onException
    ) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findVirtual(source, method_name, MethodType.methodType(returnType, params)).invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    public static <T> @NotNull FunctionARRSETRET<@Nullable T> generateStaticMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> returnType,
            Class<?>[] params,
            @Nullable Function<Throwable, T> onException
    ) {
        return o -> {
            try {
                MethodHandle a = getLookup().findStatic(source, method_name, MethodType.methodType(returnType, params));
                //noinspection unchecked
                return (T) a.invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    public static <T> @NotNull FunctionARRSETRET<T> generateConstructor(
            @NotNull Class<?> source,
            Class<?>[] params,
            @Nullable Function<Throwable, T> onException
    ) {
        return objectTypeArgs -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findConstructor(source, MethodType.methodType(source, params)).invokeWithArguments(Arrays.asList(objectTypeArgs));
            } catch (Throwable e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }
}
