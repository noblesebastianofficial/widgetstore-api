package com.miro.widgetstore.exception;
/**
 * @author Noble Sebastian
 * @version 1.0
 */
public class WidgetNotFoundException extends Exception {

	private static final long serialVersionUID = -1230811354721164754L;

	public WidgetNotFoundException(String s) {
		super(s);
	}
	
	public WidgetNotFoundException(String s, Throwable cause) {
		super(s,cause);
	}

}
