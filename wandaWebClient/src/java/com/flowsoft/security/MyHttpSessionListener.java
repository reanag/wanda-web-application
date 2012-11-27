package com.flowsoft.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;

public class MyHttpSessionListener implements HttpSessionListener {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		logger.debug("Session start");
		WandaVaadinClient.setHttpSession(httpSessionEvent.getSession());
		if (WandaVaadinClient.getHttpSession() == null) {
			logger.debug("Error in sessionCreated method: new Session is null");
		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		String user = (String) httpSessionEvent.getSession().getAttribute(
				"username");
		// String user = (String)
		// VaadinServiceSession.getCurrent().getAttribute(
		// "username");

		logger.debug(String.format(
				"Session destroyed for session: %s, user: %s", httpSessionEvent
						.getSession().getId(), user));
		if (user != null) {
			logger.debug("Logout user");
			// TODO: LOGOUT USER
		}

	}

}
