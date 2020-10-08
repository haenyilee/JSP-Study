package com.sist.model;
import javax.servlet.http.HttpServletRequest;
/*
 *    execute() 
 *  handlerRequest()
 */
public interface Model {
   public String handlerRequest(HttpServletRequest request);
}