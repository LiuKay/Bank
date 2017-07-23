package kay.com.exception;

/**
 * 
 * @author kay
 *	余额不足异常
 */
public class LessMoneyException extends CheckInOutException {

	public LessMoneyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LessMoneyException(String arg0) {
		super(arg0);
	}

	
}
