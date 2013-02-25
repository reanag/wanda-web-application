package com.flowsoft.codesnippet;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class SnippetButton extends Button {

	private static final long serialVersionUID = 1L;

	public SnippetButton(final String source) {
		super();
		// this.setHeight("10px");
		// this.setWidth("10px");
		this.setIcon(new ThemeResource("img/ico4n.png"));
		this.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				SnippetButton.this.getUI().addWindow(new SnippetWindow(source));
			}
		});
	}
}
