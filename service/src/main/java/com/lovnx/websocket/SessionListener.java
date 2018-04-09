package com.lovnx.websocket;

import java.io.IOException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.Session;


@WebListener
public class SessionListener implements HttpSessionListener {
	
	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			HttpSession session = event.getSession();
			Session wsession = ((Session) session.getAttribute("wsSession"));
			if(wsession != null) {
				wsession.close();
				System.out.println(session.getId().substring(26) + "超时退出");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}