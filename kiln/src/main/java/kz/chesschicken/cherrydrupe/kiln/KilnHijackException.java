package kz.chesschicken.cherrydrupe.kiln;

/**
 * A Kiln processor's special exception which is used when the processor needs to throw an exception towards any hijack issues.
 * @author ChessChicken-KZ
 * @since 0.3
 */
public class KilnHijackException extends RuntimeException {

    public KilnHijackException(String s, Exception e) {
        super(s, e);
    }

    public KilnHijackException(String s) {
        super(s);
    }

}
