package in.codegram.ppmtoolapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProjectNotFoundException() {
		super();
	}
	public ProjectNotFoundException(String errMsg) {
		super(errMsg);
	}
}
