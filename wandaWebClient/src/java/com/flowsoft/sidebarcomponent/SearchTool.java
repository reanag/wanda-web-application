package com.flowsoft.sidebarcomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.SearchView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class SearchTool extends GridLayout {
	Logger logger = LoggerFactory.getLogger(SearchTool.class);
	private static final long serialVersionUID = 1L;
	private TextField searchField;
	private Button submitButton;
	private Link advancedSearch;

	public SearchTool() {
		super(2, 2);
		searchField = new TextField();
		searchField.setWidth("190");
		searchField.setImmediate(true);
		searchField.setInputPrompt("Search..");
		submitButton = new Button();
		submitButton.setCaption("OK");
		submitButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 3378831040295729624L;

			@Override
			public void buttonClick(ClickEvent event) {
				search(searchField.getValue());
			}
		});

		advancedSearch = new Link("Advanced search", new ExternalResource("#!"
				+ SearchView.NAME));

		advancedSearch.setHeight("30");

		addComponent(searchField, 0, 0);
		addComponent(submitButton, 1, 0);
		addComponent(advancedSearch, 0, 1);
	}

	private void search(String value) {
		logger.debug("Search for: " + value);
		// TODO: webservice to search
	}
}
