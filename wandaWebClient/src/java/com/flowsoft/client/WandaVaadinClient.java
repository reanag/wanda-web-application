package com.flowsoft.client;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import userdetailsserviceimpl.wanda.flowsoft.com.WandaServiceImplService;

import com.flowsoft.aviews.ErrorView;
import com.flowsoft.aviews.GeneralView;
import com.flowsoft.aviews.LoginView;
import com.flowsoft.aviews.RegistrationView;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.wanda.WandaService;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServiceSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@Theme("vaadinclienttheme")
@PreserveOnRefresh
public class WandaVaadinClient extends UI implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_VIEW = "main";
	public static final String ERROR_VIEW = "errow";

	private LoginView loginView;
	private Navigator navigator;
	private WandaUser aktUser;
	public Vector<String> existingViewNames;
	static public ResourceBundle captions;

	@WebServiceRef
	protected WandaService controller;

	protected static UI page;
	static Logger logger = LoggerFactory.getLogger(WandaVaadinClient.class);

	@Override
	public void init(VaadinRequest request) {
		logger.debug("Init UI request: " + request.getRequestPathInfo());
		Locale bLocale = new Locale("en", "EN");
		captions = ResourceBundle.getBundle("i18n/Messages", bLocale);
		initNavigator(this);

		if (controller == null) {
			WandaServiceImplService ss = new WandaServiceImplService();
			controller = ss.getWandaServicePort();
		}
	}

	public void destroySession() {
		navigator.navigateTo(LoginView.NAME);
		SecurityContextHolder.clearContext();
		SecurityContextHolder.createEmptyContext();
		VaadinServiceSession.getCurrent().cleanupInactiveUIs();
		initNavigator((WandaVaadinClient.getCurrent().getUI()));

	}

	private void initNavigator(UI ui) {
		navigator = new Navigator(ui, ui.getContent());
		existingViewNames = new Vector<String>();
		loginView = new LoginView();

		navigator.addView(LoginView.NAME, loginView);
		navigator.addView("", loginView);
		RegistrationView r = new RegistrationView();
		navigator.addView(RegistrationView.NAME, r);

		navigator.navigateTo(LoginView.NAME);
		navigator.setErrorView(new ErrorView());

	}

	// public void goToMainPage() {
	// navigator.navigateTo(DEFAULT_VIEW);
	// }

	public void goToMainPage(Integer errorCode) {

		navigator.navigateTo(DEFAULT_VIEW);

		if (errorCode == 1) {
			new Notification(
					WandaVaadinClient.captions.getString("article.deleted"))
					.show(Page.getCurrent());

		}

	}

	public void goToErrorPage() {
		navigator.navigateTo(ERROR_VIEW);
	}

	public void goToLoginPage() {
		navigator.navigateTo(LoginView.NAME);
	}

	public void initView(GeneralView view) {
		if (view.getNAME().equals(DEFAULT_VIEW)) {
			removeView(DEFAULT_VIEW);
		}
		if (!((WandaVaadinClient) WandaVaadinClient.getCurrent()).existingViewNames
				.contains(view.getNAME())) {
			navigator.addView(view.getNAME(), view);
			existingViewNames.add(view.getNAME());
		}
	}

	public void removeView(String state) {
		if (((WandaVaadinClient) WandaVaadinClient.getCurrent()).existingViewNames
				.contains(state)) {
			navigator.removeView(state);
			existingViewNames.remove(state);
		}
	}

	public WandaUser getAktUser() {
		return aktUser;
	}

	public void setAktUser(WandaUser aktUser) {
		this.aktUser = aktUser;
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	public WandaService getController() {
		return controller;
	}

	public void setController(WandaService controller) {
		this.controller = controller;
	}

	public LoginView getLoginView() {
		return this.loginView;
	}

	public void refreshPage() {
		navigator.navigateTo(navigator.getState());
	}

}
