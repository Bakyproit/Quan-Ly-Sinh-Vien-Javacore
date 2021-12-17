package exception;

public class MyException extends Exception {
     String errorMessage ;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public MyException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	} 
     
     
}
