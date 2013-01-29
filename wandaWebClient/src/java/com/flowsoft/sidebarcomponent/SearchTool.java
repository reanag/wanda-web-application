package com.flowsoft.sidebarcomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.SearchResultView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class SearchTool extends GridLayout {
	Logger logger = LoggerFactory.getLogger(SearchTool.class);
	private static final long serialVersionUID = 1L;
	private TextField searchField;
	private CheckBox isAccurateSearch;
	private Button submitButton;
	private Link advancedSearch;
	private UserDetailsService controller;
	private Navigator navigator;

	public SearchTool(Navigator n, UserDetailsService c) {

		super(2, 3);
		navigator = n;
		controller = c;
		searchField = new TextField();
		searchField.setWidth("190");
		searchField.setImmediate(true);
		searchField.setInputPrompt("Search in title..");
		searchField.setStyleName("sidebarStyle");
		submitButton = new Button();
		submitButton.setCaption("OK");
		submitButton.setStyleName("sidebarStyle");
		submitButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 3378831040295729624L;

			@Override
			public void buttonClick(ClickEvent event) {
				searchByTitle(searchField.getValue(),
						isAccurateSearch.getValue());

			}
		});

		isAccurateSearch = new CheckBox("Accurate search");

		isAccurateSearch.setStyleName("sidebarStyle");
		advancedSearch = new Link("Advanced search", new ExternalResource(
				"#!search"));
		// + SearchView.NAME));

		advancedSearch.setHeight("30");
		advancedSearch.setStyleName("sidebarStyle");
		addComponent(searchField, 0, 0);
		addComponent(submitButton, 1, 0);
		addComponent(isAccurateSearch, 0, 1);
		addComponent(advancedSearch, 0, 2);
	}

	protected void searchByTitle(String value, Boolean b) {
		SearchResultView sv = new SearchResultView(value,
				controller.findArticleByTitle(value, b));
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(sv);

		navigator.navigateTo(sv.NAME);

	}
}
