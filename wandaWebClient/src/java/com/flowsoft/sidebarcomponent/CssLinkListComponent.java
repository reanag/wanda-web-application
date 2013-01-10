package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;

public class CssLinkListComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private Link titleLabel;
	private ArrayList<Link> listElements;

	public CssLinkListComponent() {
	}

	public CssLinkListComponent(String title,
			Hashtable<String, ExternalResource> resources) {
		setTitle(title);
		setList(resources);
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		if (titleLabel != null) {
			titleLabel.setStyleName("16-style");
			titleLabel.setHeight("30");

			for (Link l : listElements) {
				l.setStyleName(style);
			}
		}
	}

	public void setTitle(String string) {
		if (titleLabel == null) {
			titleLabel = new Link(string, null);
			titleLabel.setEnabled(false);
			titleLabel.setSizeFull();
			addComponent(titleLabel);
		}

	}

	public void setList(Hashtable<String, ExternalResource> list) {
		listElements = new ArrayList<Link>();

		for (Entry<String, ExternalResource> e : list.entrySet()) {
			Link l = new Link(e.getKey(), e.getValue());
			listElements.add(l);
		}

		for (Link l : listElements) {
			addComponent(l);
		}

	}
}
