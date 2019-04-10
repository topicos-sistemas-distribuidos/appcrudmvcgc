package br.ufpi.es.appcrud.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		return "erros/404";
	}

	@RequestMapping(value = { "/404" }, method = RequestMethod.GET)
	public String NotFoudPage() {
		return "erros/404";
	}

}