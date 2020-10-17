package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
	public String value(); // 문자열로 구분함
	

}
