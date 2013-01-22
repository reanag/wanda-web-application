package com.flowsoft.codesnippet;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class SnippetButton extends Button {

	private static final long serialVersionUID = 1L;
	private Integer id;

	public SnippetButton(Integer i) {
		super();
		this.id = i;
		this.setIcon(new ThemeResource("img/icon.png"));
		this.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				SnippetButton.this.getUI().addWindow(new SnippetWindow(id));
			}
		});
	}
}
