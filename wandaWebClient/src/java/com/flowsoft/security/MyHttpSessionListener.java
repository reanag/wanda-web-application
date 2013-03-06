package com.flowsoft.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpSessionListener implements HttpSessionListener {
	Logger logger = LoggerFactory.getLogger(getClass());

	// private HttpSession session;

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		logger.debug("Session start");
		logger.debug("Session: " + httpSessionEvent.getSession().isNew() + " "
				+ httpSessionEvent.getSession().getId());
		// this.session = httpSessionEvent.getSession();
		// WandaVaadinClient.setCurrent(new WandaVaadinClient());
		// setSession();
	}

	// public void setSession() {
	//
	// }

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

		String user = (String) httpSessionEvent.getSession().getAttribute(
				"username");

		logger.debug(String.format(
				"Session destroyed for session: %s, user: %s", httpSessionEvent
						.getSession().getId(), user));
		if (user != null) {
			logger.debug("Logout user");
		}

		// sessionCreated(new HttpSessionEvent(httpSessionEvent.getSession()));
		// WandaVaadinClient.setHttpSession(httpSessionEvent.getSession());

	}

}
