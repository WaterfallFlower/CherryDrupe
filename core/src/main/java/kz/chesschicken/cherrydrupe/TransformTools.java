package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A set of useful tools for transforming various things into simple arrays.
 * @author ChessChicken-KZ
 */
public class TransformTools {

    /**
     * Simply generates an array by given transforming interface.
     * @param t An empty array with fixed size.
     * @param transformer A transforming interface.
     * @param <T> Return type.
     * @return The array with transformed data.
     */
    public static <T> @NotNull T[] transformIntuitive(@NotNull T[] t, @NotNull Function<Integer, T> transformer) {
        for (int i = 0; i < t.length; i++)
            t[i] = transformer.apply(i);
        return t;
    }

    /**
     * Transforms map into array by given transforming interface.
     * @param array An empty array with fixed size.
     * @param map A given map to transform.
     * @param transformer A transforming interface.
     * @param <T> Return type.
     * @param <A> The map's key type.
     * @param <B> The map's value type.
     * @return The array with transformed data.
     */
    public static <T, A, B> @Nullable T @NotNull [] transformFromMap(@Nullable T @NotNull [] array, @NotNull Map<A, B> map, @NotNull Function<Map.@NotNull Entry<A, B>, @Nullable T> transformer) {
        int q = 0;
        for(Map.Entry<A, B> e : map.entrySet()) {
            array[q] = transformer.apply(e);
            q++;
        }
        return array;
    }

    /**
     * Transforms list into array by given transforming interface.
     * @param array An empty array with fixed size.
     * @param list A given list to transform.
     * @param transformer A transforming interface.
     * @param <T> Return type.
     * @param <K> The list's type.
     * @return The array with transformed data.
     */
    public static <T, K> @Nullable T @NotNull [] transformFromList(@Nullable T @NotNull [] array, @NotNull List<K> list, @NotNull BiFunction<K, Integer, T> transformer) {
        for(int q = 0; q < list.size(); q++)
            array[q] = transformer.apply(list.get(q), q);
        return array;
    }
}
