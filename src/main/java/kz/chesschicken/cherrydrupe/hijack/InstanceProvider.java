package kz.chesschicken.cherrydrupe.hijack;

import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * Small utility class, which provides working instances of {@link Unsafe} and {@link MethodHandles.Lookup} (low-level).
 * @author ChessChicken-KZ
 */
public class InstanceProvider {

    /**
     * A method that returns unsafe instance.
     * @return the unsafe instance.
     */
    public static @NotNull Unsafe getUnsafe() {
        return UNSAFE_INSTANCE;
    }

    /**
     * A method that returns impl_lookup instance.
     * @return the impl_lookup instance.
     */
    public static @NotNull MethodHandles.Lookup getLookup() {
        return IMPL_LOOKUP;
    }

    static Unsafe UNSAFE_INSTANCE;
    static MethodHandles.Lookup IMPL_LOOKUP;

    static {
        try {
            Field u = Unsafe.class.getDeclaredField("theUnsafe");
            u.setAccessible(true);
            UNSAFE_INSTANCE = (Unsafe) u.get(null);

            Field i = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            IMPL_LOOKUP = (MethodHandles.Lookup) UNSAFE_INSTANCE.getObject(UNSAFE_INSTANCE.staticFieldBase(i), UNSAFE_INSTANCE.staticFieldOffset(i));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
