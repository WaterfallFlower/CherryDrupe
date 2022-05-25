package kz.chesschicken.cherrydrupe.hijack.api;


import kz.chesschicken.cherrydrupe.function.FunctionEmpty;
import kz.chesschicken.cherrydrupe.function.FunctionObjectArray;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Set of various abusing methods to hijack into stuff you shouldn't.
 * @author ChessChicken-KZ
 */
public interface IMethodGenerator {

    /**
     * Returns/generates a getter function for a provided field.
     * @param source a class, where the field is located.
     * @param field_name the field's name.
     * @param field_type the field's type class.
     * @param <T> the field's type.
     * @return A getter function for the field.
     */
    <T> @Nullable Function<Object, T> field$Getter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    );

    /**
     * Returns/generates a setter function for a provided field.
     * @param source a class, where the field is located.
     * @param field_name the field's name.
     * @param field_type the field's type class.
     * @param <T> the field's type.
     * @return A setter function for the field.
     */
    <T> @Nullable BiConsumer<Object, T> field$Setter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    );

    /**
     * Returns/generates a getter function for a provided static field.
     * @param source a class, where the static field is located.
     * @param field_name the static field's name.
     * @param field_type the static field's type class.
     * @param <T> the static field's type.
     * @return A getter function for the static field.
     */
    <T> @Nullable FunctionEmpty<T> fieldStatic$Getter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    );

    /**
     * Returns/generates a setter function for a provided static field.
     * @param source a class, where the static field is located.
     * @param field_name the static field's name.
     * @param field_type the static field's type class.
     * @param <T> the static field's type.
     * @return A setter function for the static field.
     */
    <T> @Nullable Consumer<T> fieldStatic$Setter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    );

    /**
     * Returns/generates a function that invokes a provided method.
     * @param source a class, where the method is located.
     * @param method_name the method's name.
     * @param returnType the method's return type class.
     * @param argumentsType the method's arguments types classes.
     * @param <T> the method's return type.
     * @return An invoker function for the method.
     */
    <T> @Nullable FunctionObjectArray<T> method$Virtual(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> returnType,
            @NotNull Class<?>... argumentsType
    );

    /**
     * Returns/generates a function that invokes a provided static method.
     * @param source a class, where the static method is located.
     * @param method_name the static method's name.
     * @param returnType the static method's return type class.
     * @param argumentsType the static method's arguments types classes.
     * @param <T> the static method's return type.
     * @return An invoker function for the static method.
     */
    <T> @Nullable FunctionObjectArray<T> method$Static(
            @NotNull Class<?> source,
            @NotNull String method_name,
            @NotNull Class<T> returnType,
            @NotNull Class<?>... argumentsType
    );

    /**
     * Returns/generates a function that invokes a provided constructor.
     * @param source a class, where the constructor is located.
     * @param params the constructor's arguments types classes.
     * @param <T> the constructor's return type.
     * @return An invoker function for the constructor.
     */
    <T> @Nullable FunctionObjectArray<T> constructor$(
            @NotNull Class<?> source,
            @NotNull Class<?>[] params
    );
}
