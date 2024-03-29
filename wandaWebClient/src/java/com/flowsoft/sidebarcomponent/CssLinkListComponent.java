package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.Map;
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
			Map<String, ExternalResource> resources) {
		setTitle(title);
		setList(resources);
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		if (titleLabel != null) {
			titleLabel.setStyleName("title-label-style");
			titleLabel.setHeight("30px");
		}
		if (listElements != null) {
			for (Link l : listElements) {
				l.setStyleName(style);
			}
		}

	}

	public void setTitle(String string) {
		if (titleLabel == null && string == null) {
			return;
		} else {
			if (titleLabel == null) {
				titleLabel = new Link(string, null);
				titleLabel.setEnabled(false);
				titleLabel.setSizeFull();
				addComponent(titleLabel);
			} else {
				titleLabel.setCaption(string);
			}
		}

	}

	public void setList(Map<String, ExternalResource> list) {
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
