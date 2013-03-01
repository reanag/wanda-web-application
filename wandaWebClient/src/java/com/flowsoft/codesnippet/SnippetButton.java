package com.flowsoft.codesnippet;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class SnippetButton extends Button {

	private static final long serialVersionUID = 1L;
	private String tooltip;

	public SnippetButton(final String source, String label) {
		super();
		// this.setHeight("10px");
		// this.setWidth("10px");
		tooltip = label;
		this.setIcon(new ThemeResource("img/ico4n.png"));
		this.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				SnippetButton.this.getUI().addWindow(new SnippetWindow(source));
			}
		});
	}

	@Override
	public void attach() {
		super.attach();
		// add the tooltip:
		setDescription(tooltip);
	}
}
