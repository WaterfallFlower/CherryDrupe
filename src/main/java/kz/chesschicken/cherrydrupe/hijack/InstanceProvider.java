package kz.chesschicken.cherrydrupe.hijack;

import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class InstanceProvider {

    public static @NotNull Unsafe getUnsafe() {
        return UNSAFE_INSTANCE;
    }

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
