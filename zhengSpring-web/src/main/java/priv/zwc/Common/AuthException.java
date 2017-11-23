package priv.zwc.Common;

/**
 * Created by admin on 2016/6/2.
 */
public class AuthException extends RuntimeException {
    public AuthException(int code){
        super(Integer.toString(code));
    }
}
