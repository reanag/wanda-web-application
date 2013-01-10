package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.GeneralView;
import com.flowsoft.aviews.MainView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class ErrorView extends GeneralView {
	protected MainView main;

	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "error";
	private Navigator navigator;

	public ErrorView(Navigator navigator) {
		this.navigator = navigator;

	}

	@Override
	public void generateBody() {
		Label l = new Label("error happened");
		addComponent(l);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}
