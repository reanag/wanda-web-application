package com.flowsoft.client;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.MainView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class ErrorView extends Panel implements View, Serializable {

	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "error";

	public ErrorView() {
		Label l = new Label("Sorry! Some error happened.");
		l.setHeight("500px");
		addComponent(l);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
