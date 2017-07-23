package kay.com.exception;

public class CheckInOutException extends RuntimeException {

	public CheckInOutException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CheckInOutException(String arg0) {
		super(arg0);
	}
	
}
