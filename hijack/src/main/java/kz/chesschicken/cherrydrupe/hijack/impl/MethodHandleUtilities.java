/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2023 ChessChicken-KZ

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package kz.chesschicken.cherrydrupe.hijack.impl;

import kz.chesschicken.cherrydrupe.function.NulliFunction;
import kz.chesschicken.cherrydrupe.function.ObjectsFunction;
import org.jetbrains.annotations.ApiStatus;
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
 * <p>
 * After a heavy refactor of the library methods became static and now you can access without creating "dynamic context".
 * Methods which generate functions are provided with and without safe "exception process" function.
 * </p>
 *
 * <p>
 * List of methods generators:
 *     <ul>
 *         <li>Getting field (exception process): {@link MethodHandleUtilities#generateFieldGetter(Class, String, Class, Function)}.</li>
 *         <li>Getting field: {@link MethodHandleUtilities#generateFieldGetter(Class, String, Class)}.</li>
 *         <li>Getting static field (exception process): {@link MethodHandleUtilities#generateStaticFieldGetter(Class, String, Class, Function)}.</li>
 *         <li>Getting static field: {@link MethodHandleUtilities#generateStaticFieldGetter(Class, String, Class)}.</li>
 *
 *         <li>Setting field (exception process): {@link MethodHandleUtilities#generateFieldSetter(Class, String, Class, Consumer)}.</li>
 *         <li>Setting field: {@link MethodHandleUtilities#generateFieldSetter(Class, String, Class)}.</li>
 *         <li>Setting static field (exception process): {@link MethodHandleUtilities#generateStaticFieldSetter(Class, String, Class, Consumer)}.</li>
 *         <li>Setting static field: {@link MethodHandleUtilities#generateStaticFieldSetter(Class, String, Class)}.</li>
 *
 *         <li>Constructor (exception process): {@link MethodHandleUtilities#generateConstructor(Class, Class[], Function)}.</li>
 *         <li>Constructor: {@link MethodHandleUtilities#generateConstructor(Class, Class[])}.</li>
 *
 *         <li>Method accessor (exception process): {@link MethodHandleUtilities#generateMethod(Class, String, Class, Class[], Function)}.</li>
 *         <li>Method accessor: {@link MethodHandleUtilities#generateMethod(Class, String, Class, Class[])}.</li>
 *
 *         <li>Static method accessor (exception process): {@link MethodHandleUtilities#generateStaticMethod(Class, String, Class, Class[], Function)}.</li>
 *         <li>Static method accessor: {@link MethodHandleUtilities#generateStaticMethod(Class, String, Class, Class[])}.</li>
 *     </ul>
 * </p>
 *
 * @author ChessChicken-KZ
 * @see UnsafeUtilities Wrapper for Unsafe.
 * @since 0.2
 */
public class MethodHandleUtilities {

    /**
     * A method that generates a function of field's getter. The method provides safe "exception" function.
     *
     * @param source      A class where the field is located.
     * @param field_name  The field's name.
     * @param field_type  The field's type class.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The field's type.
     * @return A function that will handle getter of the field.
     */
    @ApiStatus.AvailableSince("0.2")
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
                if (onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of field's getter.
     *
     * @param source     A class where the field is located.
     * @param field_name The field's name.
     * @param field_type The field's type class.
     * @param <T>        The field's type.
     * @return A function that will handle getter of the field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull Function<@NotNull Object, @Nullable T> generateFieldGetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    ) {
        return generateFieldGetter(source, field_name, field_type, null);
    }

    /**
     * A method that generates a function of field's setter. The method provides safe "exception" function.
     *
     * @param source      A class where the field is located.
     * @param field_name  The field's name.
     * @param field_type  The field's type class.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The field's type.
     * @return A function that will handle setter of the field.
     */
    @ApiStatus.AvailableSince("0.2")
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
                if (onException != null)
                    onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    /**
     * A method that generates a function of field's setter.
     *
     * @param source     A class where the field is located.
     * @param field_name The field's name.
     * @param field_type The field's type class.
     * @param <T>        The field's type.
     * @return A function that will handle setter of the field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull BiConsumer<@NotNull Object, @Nullable T> generateFieldSetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    ) {
        return generateFieldSetter(source, field_name, field_type, null);
    }

    /**
     * A method that generates a function of static field's getter. The method provides safe "exception" function.
     *
     * @param source      A class where the static field is located.
     * @param field_name  The static field's name.
     * @param field_type  The static field's type class.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The static field's type.
     * @return A function that will handle getter of the static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull NulliFunction<@Nullable T> generateStaticFieldGetter(
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
                if (onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of static field's getter.
     *
     * @param source     A class where the static field is located.
     * @param field_name The static field's name.
     * @param field_type The static field's type class.
     * @param <T>        The static field's type.
     * @return A function that will handle getter of the static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull NulliFunction<@Nullable T> generateStaticFieldGetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    ) {
        return generateStaticFieldGetter(source, field_name, field_type, null);
    }

    /**
     * A method that generates a function of static field's setter. The method provides safe "exception" function.
     *
     * @param source      A class where the static field is located.
     * @param field_name  The static field's name.
     * @param field_type  The static field's type class.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The static field's type.
     * @return A function that will handle setter of the static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull Consumer<@Nullable T> generateStaticFieldSetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type,
            @Nullable Consumer<Throwable> onException
    ) {
        return t -> {
            try {
                getLookup().findStaticSetter(source, field_name, field_type).invoke(t);
            } catch (Throwable e) {
                if (onException != null) onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    /**
     * A method that generates a function of static field's setter. The method provides safe "exception" function.
     *
     * @param source     A class where the static field is located.
     * @param field_name The static field's name.
     * @param field_type The static field's type class.
     * @param <T>        The static field's type.
     * @return A function that will handle setter of the static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull Consumer<@Nullable T> generateStaticFieldSetter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    ) {
        return generateStaticFieldSetter(source, field_name, field_type, null);
    }

    /**
     * A method that generates a function of method's accessor. The method provides safe "exception" function.
     *
     * @param source      A class where the method is located.
     * @param method_name The method's name.
     * @param method_type The method's return type class.
     * @param method_args The method's var arguments.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The method's return type.
     * @return A function that will handle accessor of the method.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<@Nullable T> generateMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> method_type,
            Class<?>[] method_args,
            @Nullable Function<Throwable, T> onException
    ) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findVirtual(source, method_name, MethodType.methodType(method_type, method_args)).invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                if (onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of method's accessor.
     *
     * @param source      A class where the method is located.
     * @param method_name The method's name.
     * @param method_type The method's return type class.
     * @param method_args The method's var arguments.
     * @param <T>         The method's return type.
     * @return A function that will handle accessor of the method.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<@Nullable T> generateMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> method_type,
            Class<?>[] method_args
    ) {
        return generateMethod(source, method_name, method_type, method_args, null);
    }

    /**
     * A method that generates a function of static method's accessor. The method provides safe "exception" function.
     *
     * @param source      A class where the static method is located.
     * @param method_name The static method's name.
     * @param method_type The static method's return type class.
     * @param method_args The static method's var arguments.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The static method's return type.
     * @return A function that will handle accessor of the static method.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<@Nullable T> generateStaticMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> method_type,
            Class<?>[] method_args,
            @Nullable Function<Throwable, T> onException
    ) {
        return o -> {
            try {
                MethodHandle a = getLookup().findStatic(source, method_name, MethodType.methodType(method_type, method_args));
                //noinspection unchecked
                return (T) a.invokeWithArguments(Arrays.asList(o));
            } catch (Throwable e) {
                if (onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of static method's accessor.
     *
     * @param source      A class where the static method is located.
     * @param method_name The static method's name.
     * @param method_type The static method's return type class.
     * @param method_args The static method's var arguments.
     * @param <T>         The static method's return type.
     * @return A function that will handle accessor of the static method.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<@Nullable T> generateStaticMethod(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> method_type,
            Class<?>[] method_args
    ) {
        return generateStaticMethod(source, method_name, method_type, method_args, null);
    }

    /**
     * A method that generates a function of class's constructor accessor. The method provides safe "exception" function.
     *
     * @param source      A class where the constructor is located.
     * @param params      The constructor's arguments.
     * @param onException A function that will be executed when if <tt>Throwable</tt> is caught.
     * @param <T>         The constructor's class type.
     * @return A function that will handle class generation via constructor.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<T> generateConstructor(
            @NotNull Class<T> source,
            Class<?>[] params,
            @Nullable Function<Throwable, T> onException
    ) {
        return objectTypeArgs -> {
            try {
                //noinspection unchecked
                return (T) getLookup().findConstructor(source, MethodType.methodType(source, params)).invokeWithArguments(Arrays.asList(objectTypeArgs));
            } catch (Throwable e) {
                if (onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of class's constructor accessor.
     *
     * @param source A class where the constructor is located.
     * @param params The constructor's arguments.
     * @param <T>    The constructor's class type.
     * @return A function that will handle class generation via constructor.
     */
    @ApiStatus.AvailableSince("0.2")
    public static <T> @NotNull ObjectsFunction<T> generateConstructor(
            @NotNull Class<T> source,
            Class<?>[] params
    ) {
        return generateConstructor(source, params, null);
    }
}
