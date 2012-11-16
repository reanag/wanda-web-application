package com.flowsoft.client;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;

public class CountView extends Panel implements View {

	private static final long serialVersionUID = -8116691770439511775L;

	public static final String NAME = "count";

	private static int count = 1;

	public CountView() {
		addComponent(new Label("Created: " + count++));
		Link lnk2 = new Link("Back",
				new ExternalResource("#!" + IntroView.NAME));
		addComponent(lnk2);
	}

	public void enter(ViewChangeEvent event) {

	}

}