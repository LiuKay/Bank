package kay.com.exception;


/**
 * 
 * @author kay
 *	账户状态不合法，已挂失 ， 已注销
 */
public class IllegalAccountStateException extends CheckInOutException {

	public IllegalAccountStateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public IllegalAccountStateException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
}
