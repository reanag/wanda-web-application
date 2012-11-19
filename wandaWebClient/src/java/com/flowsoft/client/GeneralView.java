package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralView extends Panel implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Footer&Header";
	Logger logger = LoggerFactory.getLogger(LoginView.class);

	protected HorizontalLayout headerLayout;
	protected VerticalLayout mainLayout;
	protected HorizontalLayout footerLayout;

	public GeneralView() {
		mainLayout = new VerticalLayout();
		generateHeader();
		generateBody();
		generateFooter();

		addComponent(headerLayout);
		addComponent(mainLayout);
		addComponent(footerLayout);

		logger.debug("GeneralView construct done");

	}

	public void generateHeader() {
		headerLayout = new HorizontalLayout();
		headerLayout.setHeight("105px");
		headerLayout.setWidth("100%");

		Embedded image = new Embedded("", new ThemeResource("img/logo.gif"));
		image.setHeight("70px");
		image.setWidth("70px");

		Label l = new Label("  Wanda Knowledge Base");
		l.setHeight("40px");
		l.setStyleName("title");

		headerLayout.addComponent(image);
		headerLayout.setComponentAlignment(image, Alignment.TOP_RIGHT);
		headerLayout.addComponent(l);
		headerLayout.setComponentAlignment(l, Alignment.MIDDLE_LEFT);
	}

	public abstract void generateBody();

	public void generateFooter() {
		footerLayout = new HorizontalLayout();
		footerLayout.setHeight("85px");
		footerLayout.setWidth("100%");
		Link lnk = new Link("HOME", new ExternalResource("#!" + LoginView.NAME));
		addComponent(lnk);
		Link lnk2 = new Link("ABOUT ME", new ExternalResource("#!"
				+ LoginView.NAME));
		addComponent(lnk);
		Link lnk3 = new Link("ABOUT SITE", new ExternalResource("#!"
				+ LoginView.NAME));
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
}
