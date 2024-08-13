package com.integrosys.cms.host.eai;

/**
 * Exception to be raised when the process of message is failed at the end,
 * caller <b>must</b> provide the cause to construct this exception. So it will
 * be meaningful.
 * 
 * @author Chong Jun Yong
 * @since 10 Nov 2008
 */
public class EAIProcessFailedException extends EAIMessageException {

	private static final long serialVersionUID = 956195860412898533L;

	private static final String EAI_PROCESS_FAILED_ERROR_CODE = "TECHNICAL_ERROR";

	/**
	 * Default Constructor to provide error message and throwable cause.
	 * 
	 * @param msg error message for this exception
	 * @param cause exception that cause this exception to be thrown
	 */
	public EAIProcessFailedException(String msg, Throwable cause) {
		super(msg, cause);
		setErrorCode(EAI_PROCESS_FAILED_ERROR_CODE);
	}
}