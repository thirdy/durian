package qic.util;

/**
 *
 * @author thirdy
 */
public class BlackmarketException extends Exception {

	private static final long serialVersionUID = 1L;

	public BlackmarketException(Exception ex) {
        super(ex);
    }

	public BlackmarketException(String msg) {
		super(msg);
	}
	
	public BlackmarketException(String msg, Exception ex) {
		super(msg, ex);
	}
    
}