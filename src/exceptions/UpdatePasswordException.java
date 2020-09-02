package exceptions;

public class UpdatePasswordException extends Exception {
	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -2049396333613387800L;

	public UpdatePasswordException(String errorMessage) {
		super(errorMessage);
	}
}