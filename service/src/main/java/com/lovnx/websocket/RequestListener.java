package com.lovnx.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


@WebListener
public class RequestListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent sre) {
		((HttpServletRequest) sre.getServletRequest()).getSession();
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

}
