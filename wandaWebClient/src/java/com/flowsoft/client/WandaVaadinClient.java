package com.flowsoft.client;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.flowsoft.aviews.GeneralView;
import com.flowsoft.aviews.LoginView;
import com.flowsoft.aviews.RegistrationView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServiceSession;
import com.vaadin.ui.UI;

@Theme("vaadinclienttheme")
@PreserveOnRefresh
@Component
@Scope("prototype")
public class WandaVaadinClient extends UI implements Serializable {
	private static final long serialVersionUID = 1L;
	private static HttpSession httpSession;
	static Navigator navigator;
	static public ResourceBundle captions;
	public static Vector<String> viewNames = new Vector<String>();

	@Autowired
	private transient ApplicationContext applicationContext;

	protected static UI page;
	static Logger logger = LoggerFactory.getLogger(WandaVaadinClient.class);

	@Override
	public void init(VaadinRequest request) {
		logger.debug("Init UI request: " + request.getRequestPathInfo());
		captions = ResourceBundle.getBundle("i18n/Messages", getLocale());

		// Navigator navigator = new DiscoveryNavigator(this, this);

		initNavigator(this);
	}

	public static HttpSession getHttpSession() {
		return httpSession;
	}

	public static void setHttpSession(HttpSession httpSession) {
		WandaVaadinClient.httpSession = httpSession;
	}

	public static void fetchSession() {
		GeneralView.fetchSession();

	}

	public static void destroySession() {
		navigator.navigateTo(LoginView.NAME);

		SecurityContextHolder.clearContext();
		SecurityContextHolder.createEmptyContext();
		VaadinServiceSession.getCurrent().cleanupInactiveUIs();
		initNavigator((WandaVaadinClient.getCurrent().getUI()));
		// httpSession.invalidate();

	}

	private static void initNavigator(UI ui) {
		navigator = new Navigator(ui, ui.getContent());
		viewNames = new Vector<String>();
		GeneralView.setNavigator(navigator);

		viewNames.add(LoginView.NAME);
		navigator.addView(LoginView.NAME, new LoginView(navigator));
		if (!viewNames.contains(RegistrationView.NAME)) {
			viewNames.add(RegistrationView.NAME);
			navigator.addView(RegistrationView.NAME, new RegistrationView());
			RegistrationView.setNavigator(navigator);
		}

		navigator.navigateTo(LoginView.NAME);
		navigator.setErrorView(new ErrorView());

	}
}
