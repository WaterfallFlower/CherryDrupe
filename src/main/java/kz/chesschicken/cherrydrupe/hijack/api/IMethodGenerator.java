package kz.chesschicken.cherrydrupe.hijack.api;

import kz.chesschicken.cherrydrupe.function.FunctionARRSETRET;
import org.jetbrains.annotations.NotNull;

public interface IMethodGenerator {
    /**
     * Returns/generates a function that invokes a provided method.
     * @param source a class, where the method is located.
     * @param method_name the method's name.
     * @param returnType the method's return type class.
     * @param argumentsType the method's arguments types classes.
     * @param <T> the method's return type.
     * @return An invoker function for the method.
     */
    <T> @NotNull FunctionARRSETRET<T> method$Virtual(
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
    <T> @NotNull FunctionARRSETRET<T> method$Static(
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
    <T> @NotNull FunctionARRSETRET<T> constructor$(
            @NotNull Class<?> source,
            @NotNull Class<?>[] params
    );
}
