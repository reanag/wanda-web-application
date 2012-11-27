package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

public class CssLinkListComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private Label titleLabel;
	private ArrayList<Link> listElements;

	public CssLinkListComponent() {
	}

	public CssLinkListComponent(String title,
			Hashtable<String, ExternalResource> resources) {
		if (title != null) {
			titleLabel = new Label(title);
			titleLabel.setSizeFull();
			addComponent(titleLabel);
		}
		listElements = new ArrayList<Link>();

		for (Entry<String, ExternalResource> e : resources.entrySet()) {
			Link l = new Link(e.getKey(), e.getValue());
			listElements.add(l);
		}

		for (Link l : listElements) {
			addComponent(l);
		}
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		titleLabel.setStyleName(style);
		for (Link l : listElements) {
			l.setStyleName(style);
		}
	}
}
