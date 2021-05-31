package com.codechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class OutOfTimeRange extends RuntimeException {

	private static final long serialVersionUID = -2118452619668734566L;

	public OutOfTimeRange() {
		super();
	}

	public OutOfTimeRange(String message) {
		super(message);
	}
}
