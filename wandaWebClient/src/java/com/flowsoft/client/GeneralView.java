package com.flowsoft.client;

import java.io.Serializable;

import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.component.ComboBoxContains;
import com.flowsoft.wanda.UserDetailsService;
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
	static Logger logger = LoggerFactory.getLogger(LoginView.class);

	protected static HorizontalLayout headerLayout;
	protected VerticalLayout mainLayout;
	protected static HorizontalLayout footerLayout;
	protected TextField usernameLabel;

	@WebServiceRef
	static protected UserDetailsService controller;

	public void init() {
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
	}

	public GeneralView() {
		init();
		mainLayout = new VerticalLayout();
		generateHeader();
		generateBody();
		generateFooter();
		try {
			fetchSession();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		addComponent(headerLayout);
		addComponent(mainLayout);
		addComponent(footerLayout);

		logger.debug("GeneralView construct done: " + this.getClass());

	}

	public void generateHeader() {

		headerLayout = new HorizontalLayout();
		headerLayout.setHeight("105px");
		headerLayout.setWidth("100%");

		// Embedded image = new Embedded("", new ThemeResource("img/logo.gif"));
		// image.setHeight("70px");
		// image.setWidth("70px");
		ComboBoxContains cbc = new ComboBoxContains();
		cbc.setWidth("80px");

		Label l = new Label(WandaVaadinClient.captions.getString("HeaderTitle"));
		l.setHeight("80px");
		l.setStyleName("title");

		usernameLabel = new TextField();
		usernameLabel.setEnabled(false);
		usernameLabel.setStyleName("mydiv");
		usernameLabel.setImmediate(true);

		//
		// headerLayout.addComponent(image);
		// headerLayout.setComponentAlignment(image, Alignment.TOP_RIGHT);
		headerLayout.addComponent(l);
		headerLayout.setComponentAlignment(l, Alignment.MIDDLE_LEFT);

		headerLayout.addComponent(usernameLabel);
		headerLayout.setComponentAlignment(usernameLabel,
				Alignment.BOTTOM_RIGHT);
		headerLayout.addComponent(cbc);
		headerLayout.setComponentAlignment(cbc, Alignment.TOP_LEFT);
	}

	public abstract void generateBody();

	public void fetchSession() {
		logger.debug("fetch session data");
		setUsername(""
				+ WandaVaadinClient.getHttpSession().getAttribute("username"));
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

	// TODO(Gergo): A bejelentkezett user objektumat szokas tarolni, es abbol
	// kesziteni szoveget, ahova epp kell
	public void setUsername(String s) {
		usernameLabel.setValue(WandaVaadinClient.captions
				.getString("usernameLabelTitle") + s);
		logger.debug("New username: " + usernameLabel.getValue());
	}
}
