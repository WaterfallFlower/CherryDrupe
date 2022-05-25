package kz.chesschicken.cherrydrupe.hijack.api;


import kz.chesschicken.cherrydrupe.function.FunctionEmpty;
import kz.chesschicken.cherrydrupe.function.FunctionObjectArray;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Set of various abusing methods to hijack into stuff you shouldn't.
 * <br>
 * The lookup is at lowest level, so you can possibly change {@link java.lang.reflect.Field#modifiers} stuff.
 * @author ChessChicken-KZ
 */
public interface IMethodGenerator {

    /**
     * Returns/generates a getter function for a provided field.
     * @param source a class, where field is located.
     * @param field_name the field name.
     * @param field_type the field type class.
     * @param <T> the field's type.
     * @return A getter function for the field.
     */
    <T> Function<Object, T> field$Getter(
            Class<?> source,
            String field_name,
            Class<T> field_type
    );

    /**
     * Return/generates a setter function for a provided field.
     * @param source a class, where field is located.
     * @param field_name the field name.
     * @param field_type the field type class.
     * @param <T> the field's type.
     * @return A setter function for the field.
     */
    <T> BiConsumer<Object, T> field$Setter(
            Class<?> source,
            String field_name,
            Class<T> field_type
    );

    <T> FunctionEmpty<T> fieldStatic$Getter(
            Class<?> source,
            String field_name,
            Class<T> field_type
    );

    <T> Consumer<T> fieldStatic$Setter(
            Class<?> source,
            String field_name,
            Class<T> field_type
    );

    <T> FunctionObjectArray<T> method$Virtual(
            Class<?> source,
            String method_name,
            Class<T> returnType,
            Class<?>... argumentsType
    );

    <T> FunctionObjectArray<T> method$Static(
            Class<?> source,
            String method_name,
            Class<T> returnType,
            Class<?>... argumentsType
    );

    <T> FunctionObjectArray<T> constructor$(
            Class<?> source,
            Class<?>[] params
    );
}
