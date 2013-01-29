package com.flowsoft.client;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flowsoft.aviews.GeneralView;
import com.flowsoft.aviews.LoginView;
import com.flowsoft.aviews.RegistrationView;
import com.flowsoft.domain.WandaUser;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServiceSession;
import com.vaadin.ui.UI;

@Theme("vaadinclienttheme")
@PreserveOnRefresh
public class WandaVaadinClient extends UI implements Serializable {

	private static final long serialVersionUID = 1L;
	private LoginView loginView;
	private HttpSession httpSession;
	private Navigator navigator;
	private WandaUser aktUser;
	public Vector<String> existingViewNames;
	static public ResourceBundle captions;
	// public Vector<String> viewNames = new Vector<String>();

	@Autowired
	private transient ApplicationContext applicationContext;

	protected static UI page;
	static Logger logger = LoggerFactory.getLogger(WandaVaadinClient.class);

	@Override
	public void init(VaadinRequest request) {
		logger.debug("Init UI request: " + request.getRequestPathInfo());
		captions = ResourceBundle.getBundle("i18n/Messages", getLocale());
		existingViewNames = new Vector<String>();
		// Navigator navigator = new DiscoveryNavigator(this, this);

		initNavigator(this);
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).httpSession = httpSession;
	}

	public void destroySession() {
		navigator.navigateTo(LoginView.NAME);

		SecurityContextHolder.clearContext();
		SecurityContextHolder.createEmptyContext();
		VaadinServiceSession.getCurrent().cleanupInactiveUIs();
		initNavigator((WandaVaadinClient.getCurrent().getUI()));
		// httpSession.invalidate();

	}

	private void initNavigator(UI ui) {
		navigator = new Navigator(ui, ui.getContent());

		GeneralView.setNavigator(navigator);
		loginView = new LoginView(navigator);

		navigator.addView(LoginView.NAME, loginView);
		RegistrationView r = new RegistrationView();
		navigator.addView(RegistrationView.NAME, r);
		r.setNavigator(navigator);

		navigator.navigateTo(LoginView.NAME);
		navigator.setErrorView(new ErrorView());

	}

	public void initView(GeneralView view) {
		if (((WandaVaadinClient) WandaVaadinClient.getCurrent()).existingViewNames
				.contains(view.getNAME())) {

		} else {
			logger.debug("Add view: " + view.getNAME());
			navigator.addView(view.getNAME(), view);
			existingViewNames.add(view.getNAME());
		}
	}

	public WandaUser getAktUser() {
		return aktUser;
	}

	public void setAktUser(WandaUser aktUser) {
		this.aktUser = aktUser;
	}

	public LoginView getLoginView() {
		return loginView;
	}

}
