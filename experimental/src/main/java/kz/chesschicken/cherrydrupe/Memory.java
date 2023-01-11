package kz.chesschicken.cherrydrupe;

import kz.chesschicken.cherrydrupe.hijack.InstanceProvider;
import org.jetbrains.annotations.ApiStatus;

/**
 * This is a really weird way to make the malloc usage way simpler and comfortable.
 * <br>
 * In a nutshell, this is a wrapper for some of {@link sun.misc.Unsafe} methods for native memory manipulation.
 *
 * @author ChessChicken-KZ
 * @since 0.3
 */
@ApiStatus.Experimental
public class Memory {
    public static final byte BYTE = 1;
    public static final byte SHORT = 2;
    public static final byte INT = 4;
    public static final byte CHAR = 4;


    public static void free(long address) {
        if(address == 0) {
            throw new RuntimeException("SAFE BELT -> memory at 0x" + Long.toHexString(address).toUpperCase() + " cannot be freed.");
        }
        InstanceProvider.getUnsafe().freeMemory(address);
    }

    public static void put(long address, long pointer) {
        if(address == 0) {
            throw new RuntimeException("SAFE BELT -> memory at 0x" + Long.toHexString(address).toUpperCase() + " cannot be changed.");
        }
        InstanceProvider.getUnsafe().putAddress(address, pointer);
    }

    public static void memset(long address, byte data, long bytes) {
        if(address == 0) {
            throw new RuntimeException("SAFE BELT -> memory at 0x" + Long.toHexString(address).toUpperCase() + " cannot be changed.");
        }
        InstanceProvider.getUnsafe().setMemory(address, bytes, data);
    }

    public static byte read(long address) {
        return InstanceProvider.getUnsafe().getByte(address);
    }

    public static long realloc(long address, long size) {
        if(size < 0) {
            throw new RuntimeException("SAFE BELT -> allocated memory at 0x" + Long.toHexString(address).toUpperCase() + " cannot be " + size + " bytes.");
        }
        return InstanceProvider.getUnsafe().reallocateMemory(address, size);
    }

    public static long malloc(long size) {
        if(size < 0) {
            throw new RuntimeException("SAFE BELT -> allocated memory cannot be " + size + " bytes.");
        }
        return InstanceProvider.getUnsafe().allocateMemory(size);
    }
}
