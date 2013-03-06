package com.flowsoft.aviews;

import java.io.Serializable;

import com.flowsoft.codesnippet.CodeLabel;
import com.flowsoft.codesnippet.SnippetReader;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class AboutSiteView extends GeneralView implements View, Serializable {

	private CssLayout cssLayout;
	private static final long serialVersionUID = 1L;

	public AboutSiteView() {
		super();
		this.NAME = "AboutSite";

	}

	@Override
	public void generateBody() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		cssLayout = new CssLayout();
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");
		SnippetReader sr = new SnippetReader();
		Label l = new CodeLabel(sr.read("aboutSite.snip"));
		cssLayout.addComponent(l);
		mainLayout.addComponent(cssLayout);
	}

}
