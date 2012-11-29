package com.flowsoft.client;

import java.io.Serializable;

import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.domain.WandaUser;
import com.flowsoft.sidebarcomponent.Sidebar;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Footer&Header";
	static Logger logger = LoggerFactory.getLogger(MainView.class);

	protected static HorizontalLayout headerLayout;
	protected static HorizontalLayout mainHorizontalLayout;
	protected VerticalLayout mainLayout;
	protected static HorizontalLayout footerLayout;
	protected static Sidebar sidebar;
	protected TextField usernameLabel;
	static protected Navigator navigator;
	public static WandaUser aktUser;
	@WebServiceRef
	static protected UserDetailsService controller;

	public void init() {
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
	}

	public GeneralView() {
		init();
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("600px");
		resizeMainLayout();
		mainHorizontalLayout = new HorizontalLayout();
		generateHeader();
		generateBody();
		generateSideBar();
		generateFooter();
		try {
			fetchSession();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		mainHorizontalLayout.addStyleName("horizontalright");
		mainHorizontalLayout.addComponent(mainLayout);
		mainHorizontalLayout.setComponentAlignment(mainLayout,
				Alignment.MIDDLE_RIGHT);
		mainHorizontalLayout.addComponent(sidebar);
		mainHorizontalLayout.setComponentAlignment(mainLayout,
				Alignment.MIDDLE_RIGHT);
		addComponent(headerLayout);
		addComponent(mainHorizontalLayout);
		addComponent(footerLayout);

		logger.debug("GeneralView construct done: " + this.getClass());

	}

	private void generateSideBar() {
		sidebar = new Sidebar(navigator);
		sidebar.setStyleName("myblack");
		sidebar.setSizeFull();
		resizeMainLayout();
	}

	protected void resizeMainLayout() {
		float height = 0;
		if (sidebar == null && mainLayout == null) {
			height = 0;
		}
		if (sidebar == null) {
			height = mainLayout.getWidth();
		}
		if (mainLayout == null) {
			height = sidebar.getWidth();
		}
		if (sidebar != null && mainLayout != null) {
			height = Math.max(sidebar.getWidth(), mainLayout.getWidth());
			sidebar.setHeight(height, Unit.PIXELS);
			mainLayout.setHeight(height, Unit.PIXELS);
			mainHorizontalLayout.setHeight(height, Unit.PIXELS);
		}

	}

	public void generateHeader() {

		headerLayout = new HorizontalLayout();
		headerLayout.setHeight("105px");
		headerLayout.setWidth("100%");

		// Embedded image = new Embedded("", new ThemeResource("img/logo.gif"));
		// image.setHeight("70px");
		// image.setWidth("70px");

		Label l = new Label(WandaVaadinClient.captions.getString("HeaderTitle"));
		l.setHeight("80px");
		l.setStyleName("title");

		usernameLabel = new TextField();
		usernameLabel.setEnabled(false);
		usernameLabel.setStyleName("mydiv");
		usernameLabel.setImmediate(true);
		try {
			usernameLabel.setValue(aktUser.getUsername());
		} catch (NullPointerException e) {
			logger.error("USER is Null");
		}

		//
		// headerLayout.addComponent(image);
		// headerLayout.setComponentAlignment(image, Alignment.TOP_RIGHT);
		headerLayout.addComponent(l);
		headerLayout.setComponentAlignment(l, Alignment.MIDDLE_LEFT);

		headerLayout.addComponent(usernameLabel);
		headerLayout.setComponentAlignment(usernameLabel,
				Alignment.BOTTOM_RIGHT);

	}

	public abstract void generateBody();

	public void fetchSession() {
		try {
			setAktUser(controller.findUserByUsername(WandaVaadinClient
					.getHttpSession().getAttribute("username").toString()));

		} catch (NullPointerException e) {
		}

	}

	public void generateFooter() {
		footerLayout = new HorizontalLayout();
		footerLayout.setHeight("85px");
		footerLayout.setWidth("100%");
		Link lnk = new Link(
				WandaVaadinClient.captions.getString("footer.home"),
				new ExternalResource("#!" + MainView.NAME));
		addComponent(lnk);
		Link lnk2 = new Link(
				WandaVaadinClient.captions.getString("footer.about.site"),
				new ExternalResource("#!" + AboutMeView.NAME));
		addComponent(lnk);
		Link lnk3 = new Link(
				WandaVaadinClient.captions.getString("footer.about.me"),
				new ExternalResource("#!" + AboutSiteView.NAME));
		addComponent(lnk);

		footerLayout.addComponent(lnk);
		footerLayout.addComponent(lnk2);
		footerLayout.addComponent(lnk3);
		footerLayout.setComponentAlignment(lnk, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk2, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk3, Alignment.BOTTOM_LEFT);

	}

	public void enter(ViewChangeEvent event) {

	}

	public void setAktUser(WandaUser u) {
		aktUser = u;
		usernameLabel.setValue(aktUser.getUsername());
		logger.debug("New username: " + usernameLabel.getValue());
	}

	public static void setNavigator(Navigator n) {
		navigator = n;

	}
}
