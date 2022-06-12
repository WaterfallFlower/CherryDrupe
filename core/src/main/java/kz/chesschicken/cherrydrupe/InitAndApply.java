package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.NotNull;

/**
 * Another cursed way to one line a code of initialization and load of an instance.
 * @author ChessChicken-KZ
 */
public class InitAndApply {

    /**
     * Just a lighter version of {@link java.util.function.Consumer}.
     * @param <A> The instance's type.
     */
    public interface Acceptor<A> {
        void apply(@NotNull A instance);
    }

    /**
     * The main method of this class.<br>
     * An example of usage:<br>
     * <pre>{@code
     * Map<Integer, String> aMap = InitAndApply.apply(new HashMap<>(), instance -> {
     *      instance.put(0, "Sky");
     *      instance.put(5, "Cloud");
     * });
     * }</pre>
     *
     * @param instance An instance to be applied, should not be null.
     * @param acceptor {@link Acceptor} A consumer that applies some specific code to the instance.
     * @param <A> The instance's type.
     * @return The instance with executed consumer.
     */
    public static<A> @NotNull A get(@NotNull A instance, @NotNull InitAndApply.Acceptor<A> acceptor) {
        acceptor.apply(instance);
        return instance;
    }
}