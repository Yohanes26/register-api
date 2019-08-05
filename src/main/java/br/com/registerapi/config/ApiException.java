package br.com.registerapi.config;

public class ApiException extends RuntimeException {
	
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private int status;

	    private Object httpResponseBody;

	    public ApiException(String message, int status) {
	        this(message, status, null);
	    }

	    public ApiException(String message, int status, Exception cause) {
	        super(message, cause);
	        this.status = status;
	    }

	    public ApiException(String message) {
	        this(message, 400);
	    }

	    public int getStatus() {
	        return status;
	    }

	    public Object getHttpResponseBody() {
	        return httpResponseBody;
	    }

	    public ApiException setHttpResponseBody(Object httpResponseBody) {
	        this.httpResponseBody = httpResponseBody;
	        return this;
	    }
}