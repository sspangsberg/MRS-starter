package easv.mrs.Util;

/**
 * @author smsj
 * Application wide exception to contain an exception in the MRS System
 */
public class MRSException extends Exception {

    public MRSException() {}

    public MRSException(String msg) { super(msg); }

    public MRSException(String msg, Throwable cause) { super(msg, cause); }

    public MRSException(Throwable cause) { super(cause); }
}
