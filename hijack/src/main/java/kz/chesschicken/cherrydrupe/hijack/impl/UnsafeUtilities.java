/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2022 ChessChicken-KZ

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

import kz.chesschicken.cherrydrupe.function.FunctionRET;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static kz.chesschicken.cherrydrupe.hijack.InstanceProvider.getUnsafe;

/**
 * The more specified and easy to use wrapper, designed specially for {@link sun.misc.Unsafe} class.
 *
 * <p>
 *     The direct methods of this class with re-throw <tt>NoSuchFieldException</tt>,
 *     as it relies to {@link Class#getDeclaredField(String)} when scanning available fields.
 * </p>
 *
 * <p>
 *     After a heavy refactor of the library methods became static and now you can access without creating "dynamic context".
 *     Methods which generate functions are provided with and without safe "exception process" function.
 * </p>
 *
 * <p>
 *     List of direct methods:
 *     <ul>
 *         <li>Getting field: {@link UnsafeUtilities#getField(Class, String, Object)}.</li>
 *         <li>Getting static field: {@link UnsafeUtilities#getStaticField(Class, String)}.</li>
 *         <li>Setting field: {@link UnsafeUtilities#setField(Class, String, Object, Object)}.</li>
 *         <li>Setting static field: {@link UnsafeUtilities#setStaticField(Class, String, Object)}.</li>
 *     </ul>
 * </p>
 * 
 * <p>
 *     List of methods generators:
 *     <ul>
 *         <li>Getting field (exception process): {@link UnsafeUtilities#generateFieldGetter(Class, String, Function)}</li>
 *         <li>Getting field: {@link UnsafeUtilities#generateFieldGetter(Class, String)}.</li>
 *         <li>Getting static field (exception process): {@link UnsafeUtilities#generateStaticFieldGetter(Class, String, Function)}.</li>
 *         <li>Getting static field: {@link UnsafeUtilities#generateStaticFieldGetter(Class, String)}.</li>
 *         <li>Setting field (exception process): {@link UnsafeUtilities#generateFieldSetter(Class, String, Consumer)}.</li>
 *         <li>Setting field: {@link UnsafeUtilities#generateFieldSetter(Class, String)}.</li>
 *         <li>Setting static field (exception process): {@link UnsafeUtilities#generateStaticFieldSetter(Class, String, Consumer)}.</li>
 *         <li>Setting static field: {@link UnsafeUtilities#generateStaticFieldSetter(Class, String)}.</li>
 *     </ul>
 * </p>
 *
 * @author ChessChicken-KZ
 * @see MethodHandleUtilities Wrapper for MethodHandle.Lookup.
 * @since 0.2
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
    @ApiStatus.AvailableSince("0.2")
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
    @ApiStatus.AvailableSince("0.2")
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
    @ApiStatus.AvailableSince("0.2")
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
    @ApiStatus.AvailableSince("0.2")
    public static <T> void setStaticField(@NotNull Class<?> source, @NotNull String field_name, @Nullable T value) throws NoSuchFieldException {
        Field f = source.getDeclaredField(field_name);
        getUnsafe().putObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f), value);
    }

    /**
     * A method that generates a function of field's getter. The method provides safe "exception" function.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param onException A function that will be executed when if <tt>NoSuchFieldException</tt> is caught.
     * @param <T> The getter value's type.
     * @return A function that will handle getter of field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> Function<@NotNull Object, @Nullable T> generateFieldGetter(@NotNull Class<?> source, @NotNull String field_name, @Nullable Function<NoSuchFieldException, T> onException) {
        return o -> {
            try {
                //noinspection unchecked
                return (T) getUnsafe().getObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)));
            } catch (NoSuchFieldException e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of field's getter.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param <T> The getter value's type.
     * @return A function that will handle getter of field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> Function<@NotNull Object, @Nullable T> generateFieldGetter(@NotNull Class<?> source, @NotNull String field_name) {
        return generateFieldGetter(source, field_name, null);
    }

    /**
     * A method that generates a function of field's setter. The method provides safe "exception" function.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param onException A function that will be executed when if <tt>NoSuchFieldException</tt> is caught.
     * @param <T> The setter value's type.
     * @return A function that will handle setter of field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> BiConsumer<@NotNull Object, @Nullable  T> generateFieldSetter(@NotNull Class<?> source, @NotNull String field_name, @Nullable Consumer<NoSuchFieldException> onException) {
        return (o, t) -> {
            try {
                getUnsafe().putObject(o, getUnsafe().objectFieldOffset(source.getDeclaredField(field_name)), t);
            } catch (NoSuchFieldException e) {
                if(onException != null) onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    /**
     * A method that generates a function of field's setter.
     * @param source Class where the field is located.
     * @param field_name Field's name.
     * @param <T> The setter value's type.
     * @return A function that will handle setter of field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> BiConsumer<@NotNull Object, @Nullable  T> generateFieldSetter(@NotNull Class<?> source, @NotNull String field_name) {
        return generateFieldSetter(source, field_name, null);
    }

    /**
     * A method that generates a function of static field's getter. The method provides safe "exception" function.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param onException A function that will be executed when if <tt>NoSuchFieldException</tt> is caught.
     * @param <T> The return value's type.
     * @return A function that will handle getter of static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> FunctionRET<@Nullable T> generateStaticFieldGetter(@NotNull Class<?> source, @NotNull String field_name, @Nullable Function<NoSuchFieldException, T> onException) {
        return () -> {
            try {
                Field f = source.getDeclaredField(field_name);
                //noinspection unchecked
                return (T) getUnsafe().getObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f));
            } catch (NoSuchFieldException e) {
                if(onException != null)
                    return onException.apply(e);
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     * A method that generates a function of static field's getter.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param <T> The return value's type.
     * @return A function that will handle getter of static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> FunctionRET<@Nullable T> generateStaticFieldGetter(@NotNull Class<?> source, @NotNull String field_name) {
        return generateStaticFieldGetter(source, field_name, null);
    }

    /**
     * A method that generates a function of static field's setter. The method provides safe "exception" function.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param onException A function that will be executed when if <tt>NoSuchFieldException</tt> is caught.
     * @param <T> The setter value's type.
     * @return A function that will handle setter of static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> Consumer<@Nullable T> generateStaticFieldSetter(@NotNull Class<?> source, @NotNull String field_name, @Nullable Consumer<NoSuchFieldException> onException) {
        return t -> {
            try {
                Field f = source.getDeclaredField(field_name);
                getUnsafe().putObject(getUnsafe().staticFieldBase(f), getUnsafe().staticFieldOffset(f), t);
            } catch (NoSuchFieldException e) {
                if(onException != null) onException.accept(e);
                else e.printStackTrace();
            }
        };
    }

    /**
     * A method that generates a function of static field's setter.
     * @param source Class where the static field is located.
     * @param field_name Static field's name.
     * @param <T> The setter value's type.
     * @return A function that will handle setter of static field.
     */
    @ApiStatus.AvailableSince("0.2")
    public static @NotNull <T> Consumer<@Nullable T> generateStaticFieldSetter(@NotNull Class<?> source, @NotNull String field_name) {
        return generateStaticFieldSetter(source, field_name, null);
    }
}
