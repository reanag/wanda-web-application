package com.flowsoft.client;

import java.io.Serializable;

import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class AboutSiteView extends GeneralView implements View, Serializable {

	public final static String NAME = "AboutSite";
	private CssLayout cssLayout;
	private static final long serialVersionUID = 1L;

	@Override
	public void generateBody() {
		cssLayout = new CssLayout();
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");
		Label l = new Label("About site.. \n Under Construction");
		cssLayout.addComponent(l);
		mainLayout.addComponent(cssLayout);

	}

}