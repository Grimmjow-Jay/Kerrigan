package com.jay.kerrigan.common.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.exception.KerriganException;
import com.jay.kerrigan.common.model.ResponseModel;

@RestController
@ControllerAdvice
public class ErrorHandlerController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error")
	public ResponseModel<String> errorHandler(HttpServletResponse resp, HttpServletRequest req) {
		return new ResponseModel<String>(false, "error", HttpStatus.valueOf(resp.getStatus()), null);
	}

	@ExceptionHandler(value = KerriganException.class)
	public ResponseModel<String> handleBaseException(HttpServletRequest req, KerriganException e) throws Exception {
		return ResponseModel.error(e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseModel<String> handleException(HttpServletRequest req, Exception e) throws Exception {
		return new ResponseModel<String>(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	@ExceptionHandler(value = BindException.class)
	public ResponseModel<String> handleBindException(HttpServletRequest req, BindException e) throws Exception {
		StringBuilder builder = new StringBuilder();
		Iterator<ObjectError> iterator = e.getAllErrors().iterator();
		while (iterator.hasNext()) {
			builder.append(iterator.next().getDefaultMessage());
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return new ResponseModel<String>(false, builder.toString(), HttpStatus.BAD_REQUEST, null);
	}
}