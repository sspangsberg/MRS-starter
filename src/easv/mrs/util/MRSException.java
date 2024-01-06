package easv.mrs.util;

/**
 * @author smsj
 * Application wide exception to contain an exception in the MRS System
 */
public class MRSException extends Exception {

    public MRSException() {}

    public MRSException(String msg) { super(msg); }

    public MRSException(String msg, Exception cause) { super(msg, cause); }

    public MRSException(Throwable cause) { super(cause); }

    public MRSException(String msg, Throwable cause, boolean enableSuppresion, boolean writableStackTrace) {
        super(msg, cause, enableSuppresion, writableStackTrace);
    }
}
