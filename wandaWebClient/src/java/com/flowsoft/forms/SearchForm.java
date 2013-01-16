package com.flowsoft.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.SearchResultView;
import com.flowsoft.aviews.SearchView;
import com.flowsoft.sidebarcomponent.SearchTool;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class SearchForm extends GridLayout {
	Logger logger = LoggerFactory.getLogger(SearchTool.class);
	private static final long serialVersionUID = 1L;
	private TextField searchField;
	private CheckBox isAccurateSearch;
	private Button submitButton;
	private Link advancedSearch;
	private Label label;
	private CheckBox articleCheckBox;
	private CheckBox contentCheckBox;
	private CheckBox authorCheckBox;

	protected static Navigator navigator;
	protected static UserDetailsService controller;

	public SearchForm(Navigator n, UserDetailsService u) {
		super(3, 8);
		this.setWidth("500px");
		this.setHeight("300px");
		navigator = n;
		controller = u;
		generateSurface();

	}

	private void generateSurface() {
		searchField = new TextField();
		searchField.setWidth("390");
		searchField.setImmediate(true);
		searchField.setInputPrompt("Search in title..");
		submitButton = new Button();
		submitButton.setCaption("OK");
		submitButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 3378831040295729624L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (articleCheckBox.getValue()) {

					navigator.addView(
							SearchResultView.NAME + "."
									+ searchField.getValue(),
							new SearchResultView(controller.findArticleByTitle(
									searchField.getValue(),
									isAccurateSearch.getValue())));

					navigator.navigateTo(SearchResultView.NAME + "."
							+ searchField.getValue());

				}
				if (contentCheckBox.getValue()) {
					navigator.addView(
							SearchResultView.NAME + "."
									+ searchField.getValue(),
							new SearchResultView(controller
									.findArticleByContent(searchField
											.getValue())));

					navigator.navigateTo(SearchResultView.NAME + "."
							+ searchField.getValue());
				}
				if (authorCheckBox.getValue()) {
					navigator.addView(
							SearchResultView.NAME + "."
									+ searchField.getValue(),
							new SearchResultView(controller
									.findArticleByAuthor(
											searchField.getValue(),
											isAccurateSearch.getValue())));

					navigator.navigateTo(SearchResultView.NAME + "."
							+ searchField.getValue());
				}

			}
		});
		isAccurateSearch = new CheckBox("Accurate search");
		advancedSearch = new Link("Advanced search", new ExternalResource("#!"
				+ SearchView.NAME));

		advancedSearch.setHeight("30");

		label = new Label();
		label.setValue("Search in: ");
		label.setWidth("200px");

		articleCheckBox = new CheckBox("Articles's Title");
		articleCheckBox.setImmediate(true);
		articleCheckBox.setValue(true);
		articleCheckBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (articleCheckBox.getValue()) {
					searchField.setInputPrompt("Search in title..");
					contentCheckBox.setValue(false);
					authorCheckBox.setValue(false);
				}
			}
		});

		contentCheckBox = new CheckBox("Articles's content)");
		contentCheckBox.setImmediate(true);
		contentCheckBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (contentCheckBox.getValue()) {
					searchField.setInputPrompt("Search in content..");
					articleCheckBox.setValue(false);
					authorCheckBox.setValue(false);
				}
			}
		});

		authorCheckBox = new CheckBox("Author");
		authorCheckBox.setImmediate(true);
		authorCheckBox.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (authorCheckBox.getValue()) {
					searchField.setInputPrompt("Search by author..");
					contentCheckBox.setValue(false);
					articleCheckBox.setValue(false);
				}
			}
		});

		addComponent(searchField, 0, 0, 2, 0);
		addComponent(isAccurateSearch, 0, 1, 2, 1);
		addComponent(label, 0, 2);
		addComponent(articleCheckBox, 1, 3);

		addComponent(contentCheckBox, 1, 4);
		addComponent(authorCheckBox, 1, 5);

		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		setRowExpandRatio(0, 1);
		setRowExpandRatio(1, 2);
		setRowExpandRatio(2, 1);
		setRowExpandRatio(3, 1);
		setRowExpandRatio(4, 1);
		setRowExpandRatio(5, 3);

		addComponent(submitButton, 1, 6);

	}
}