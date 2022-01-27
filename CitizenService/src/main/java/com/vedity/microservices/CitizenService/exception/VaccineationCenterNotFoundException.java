package com.vedity.microservices.CitizenService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.NOT_FOUND)
public class VaccineationCenterNotFoundException extends RuntimeException {
	public VaccineationCenterNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
