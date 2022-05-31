package kz.chesschicken.cherrydrupe.hijack;

/**
 * An enum with set of generator's behaviour when catching an exception.
 * @author ChessChicken-KZ
 */
public enum EnumExceptionBehaviour {
    /**
     * Skips all caught exceptions, straightly going forward.
     */
    SKIP_EXCEPTION,
    /**
     * Executes {@link Exception#printStackTrace()} for all caught exceptions.
     */
    PRINT_EXCEPTION,
    /**
     * When catching an exception, it stores it in {@link AbstractGenerator#EXCEPTION_HANDLE}, that can be accessed later by {@link AbstractGenerator#throwable()} method.
     */
    CATCH_EXCEPTION,
    /**
     * Throws a caught exception as a {@link RuntimeException}, making the thread to stop.
     */
    THROW_EXCEPTION
}
