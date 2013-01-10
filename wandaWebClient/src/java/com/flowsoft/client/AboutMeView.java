package com.flowsoft.client;

import java.io.Serializable;

import com.flowsoft.aviews.GeneralView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

//TODO(Gergo): ne legyenek szovegek a kodban
public class AboutMeView extends GeneralView implements View, Serializable {

	public final static String NAME = "AboutMe";
	private CssLayout cssLayout;
	private static final long serialVersionUID = 1L;

	@Override
	public void generateBody() {
		cssLayout = new CssLayout();
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");
		Label l = new Label("About me.. \n Under Construction");
		cssLayout.addComponent(l);
		mainLayout.addComponent(cssLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}
}
