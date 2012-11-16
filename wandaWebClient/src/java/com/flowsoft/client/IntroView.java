package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;

public class IntroView extends Panel implements View {

	private static final long serialVersionUID = -9174756039960699699L;
	public static final String NAME = "";
	Logger logger = LoggerFactory.getLogger(IntroView.class);

	public IntroView() {

		Link lnk = new Link("Login",
				new ExternalResource("#!" + LoginView.NAME));
		Link lnk2 = new Link("userList", new ExternalResource("#!"
				+ UserListView.NAME));
		Link lnk3 = new Link("createUser", new ExternalResource("#!"
				+ CreateUserView.NAME));
		Link lnk4 = new Link("Count", new ExternalResource("#!"
				+ CountView.NAME));
		addComponent(lnk);
		addComponent(lnk2);
		addComponent(lnk3);
		addComponent(lnk4);

	}

	public void enter(ViewChangeEvent event) {

	}
}