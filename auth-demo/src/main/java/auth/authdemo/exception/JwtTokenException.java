package auth.authdemo.exception;

import javax.naming.AuthenticationException;

public class JwtTokenException extends AuthenticationException {
    
    private static final long serialVersionUID = 1L;

	public JwtTokenException(String msg) {
		super(msg);
	}
}
