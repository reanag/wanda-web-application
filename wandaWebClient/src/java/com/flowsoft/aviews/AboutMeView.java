package com.flowsoft.aviews;

import com.flowsoft.codesnippet.CodeLabel;
import com.flowsoft.codesnippet.SnippetReader;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

//
public class AboutMeView extends GeneralView {

	private CssLayout cssLayout;
	private static final long serialVersionUID = 1L;

	public AboutMeView() {
		super();
		this.NAME = "AboutMe";
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
		Label l = new CodeLabel(sr.read("aboutMe.txt"));
		cssLayout.addComponent(l);
		mainLayout.addComponent(cssLayout);
	}
}
