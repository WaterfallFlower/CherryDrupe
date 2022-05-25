package kz.chesschicken.cherrydrupe.hijack.api;


import kz.chesschicken.cherrydrupe.function.FunctionEmpty;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Set of various abusing methods to hijack into stuff you shouldn't.
 * @author ChessChicken-KZ
 */
public interface IFieldGenerator {

    /**
     * Returns/generates a getter function for a provided field.
     * @param source a class, where the field is located.
     * @param field_name the field's name.
     * @param field_type the field's type class.
     * @param <T> the field's type.
     * @return A getter function for the field.
     */
    <T> @NotNull Function<Object, T> field$Getter(
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
    <T> @NotNull BiConsumer<Object, T> field$Setter(
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
    <T> @NotNull FunctionEmpty<T> fieldStatic$Getter(
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
    <T> @NotNull Consumer<T> fieldStatic$Setter(
            @NotNull Class<?> source,
            @NotNull String field_name,
            @NotNull Class<T> field_type
    );
}
