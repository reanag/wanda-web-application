package com.flowsoft.aviews;

import java.io.Serializable;

import com.flowsoft.codesnippet.CodeLabel;
import com.flowsoft.codesnippet.SnippetReader;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class AnonymousAboutSiteView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	private VerticalLayout cssLayout;
	public String NAME;

	public AnonymousAboutSiteView() {
		this.NAME = "AboutPage";
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();

		cssLayout = new VerticalLayout();
		cssLayout.setWidth("800px");
		cssLayout.setStyleName("mydiv");
		SnippetReader sr = new SnippetReader();
		Label l = new CodeLabel(sr.read("anonymousAboutSite_part1.snip"));
		cssLayout.addComponent(l);
		Embedded e1 = new Embedded("", new ThemeResource("img/a2.png"));
		cssLayout.addComponent(e1);
		Label l2 = new CodeLabel(sr.read("anonymousAboutSite_part2.snip"));
		cssLayout.addComponent(l2);
		Embedded e3 = new Embedded("", new ThemeResource("img/a3.png"));
		cssLayout.addComponent(e3);
		Label l3 = new CodeLabel(sr.read("anonymousAboutSite_part3.snip"));
		cssLayout.addComponent(l3);
		Embedded e2 = new Embedded("", new ThemeResource("img/a.png"));
		cssLayout.addComponent(e2);
		Label l4 = new CodeLabel(sr.read("anonymousAboutSite_part4.snip"));
		cssLayout.addComponent(l4);
		e1.setWidth("500px");
		e2.setWidth("500px");
		e3.setWidth("300px");

		cssLayout.addComponent(new Link("Register", new ExternalResource(
				"#!registration")));
		cssLayout.setComponentAlignment(l, Alignment.TOP_CENTER);
		cssLayout.setComponentAlignment(l2, Alignment.TOP_CENTER);
		cssLayout.setComponentAlignment(l3, Alignment.TOP_CENTER);
		// cssLayout.setComponentAlignment(l4, Alignment.TOP_CENTER);
		cssLayout.setComponentAlignment(e1, Alignment.TOP_CENTER);
		cssLayout.setComponentAlignment(e2, Alignment.TOP_CENTER);
		cssLayout.setComponentAlignment(e3, Alignment.TOP_CENTER);
		addComponent(cssLayout);

	}

}
