package com.flowsoft.client;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("vaadinclienttheme")
public class WandaVaadinClient extends UI implements Serializable {

	private static final long serialVersionUID = 1L;
	private static HttpSession httpSession;
	static Navigator navigator;
	static public ResourceBundle captions;

	protected static UI page;
	static Logger logger = LoggerFactory.getLogger(WandaVaadinClient.class);

	@Override
	public void init(VaadinRequest request) {

		logger.debug("Init UI request: " + request.getRequestPathInfo());
		captions = ResourceBundle.getBundle("i18n/Messages", getLocale());

		navigator = new Navigator(this, getContent());
		GeneralView.setNavigator(navigator);
		navigator.addView(LoginView.NAME, new LoginView(navigator));
		navigator.addView(CreateArticleView.NAME, new CreateArticleView());
		navigator.addView(AboutSiteView.NAME, new AboutMeView());
		navigator.addView(AboutMeView.NAME, new AboutSiteView());
		navigator.navigateTo(LoginView.NAME);
		navigator.setErrorView(new ErrorView(navigator));
	}

	public static HttpSession getHttpSession() {
		return httpSession;
	}

	public static void setHttpSession(HttpSession httpSession) {
		WandaVaadinClient.httpSession = httpSession;
	}

}
