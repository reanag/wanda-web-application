package com.flowsoft.sidebarcomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Link;

public class CssLinkComponent extends CssLayout implements
		Comparable<CssLinkComponent> {

	private static final long serialVersionUID = 1L;
	private Link link;
	Logger logger = LoggerFactory.getLogger(CssLinkComponent.class);

	public CssLinkComponent() {
	}

	public CssLinkComponent(String linkText, ExternalResource resource) {
		link = new Link(linkText, resource);
		this.addComponent(link);
		this.setSizeFull();

	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
	}

	public String getLinkText() {
		return link.getCaption();
	}

	@Override
	public int compareTo(CssLinkComponent o) {
		if (o == null) {
			return 1;
		}
		if (o.getLinkText().length() > this.getLinkText().length()) {
			return -1;
		} else {
			return 1;
		}
	}

	public void setRank(Integer value) {
		link.setStyleName(value + "-style");
	}
}
