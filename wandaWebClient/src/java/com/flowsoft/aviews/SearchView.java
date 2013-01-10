package com.flowsoft.aviews;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class SearchView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(SearchView.class);
	private static final long serialVersionUID = 1L;
	public static final String NAME = "search";

	private GridLayout searchForm;
	private TextField searchField;
	private Button submitButton;
	private Label label;
	private CheckBox articleCheckBox;
	private CheckBox categoryCheckBox;
	private CheckBox contentCheckBox;
	private CheckBox authorCheckBox;

	public SearchView() {

	}

	@Override
	public void generateBody() {

	}

	private void generateCheckbox() {
		label = new Label();
		label.setValue("Search in: ");

		articleCheckBox = new CheckBox("Articles's Title");
		articleCheckBox.setImmediate(true);

		categoryCheckBox = new CheckBox("Categories");
		categoryCheckBox.setImmediate(true);

		contentCheckBox = new CheckBox("Articles's content)");
		contentCheckBox.setImmediate(true);

		authorCheckBox = new CheckBox("Author");
		authorCheckBox.setImmediate(true);
		searchForm.setSizeUndefined();
		searchForm.addComponent(label, 1, 2);
		searchForm.addComponent(articleCheckBox, 1, 3);
		searchForm.addComponent(categoryCheckBox, 1, 4);
		searchForm.addComponent(contentCheckBox, 1, 5);
		searchForm.addComponent(authorCheckBox, 1, 6);

	}

	private void generateSearchForm() {
		searchForm = new GridLayout(3, 8);
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
		generateCheckbox();

		searchForm.addComponent(searchField, 0, 0, 2, 1);
		searchForm.addComponent(submitButton, 2, 7);

	}

	public void enter(ViewChangeEvent event) {
		super.enter(event);

		if (mainLayout.getComponentCount() < 1) {
			generateSearchForm();
			mainLayout.addComponent(searchForm);
			mainLayout.addComponent(new Link("Back", new ExternalResource("#!"
					+ MainView.NAME)));
		}
		// resizeMainLayout();
	}

	private void search(String value) {
		ArrayList<String> searchParam = new ArrayList<String>();

		if (articleCheckBox.getValue()) {
			searchParam.add("articleTitle");
		}
		if (categoryCheckBox.getValue()) {
			searchParam.add("category");
		}
		if (contentCheckBox.getValue()) {
			searchParam.add("articleContent");
		}
		if (authorCheckBox.getValue()) {
			searchParam.add("author");
		}
		logger.debug("Search for: " + value);
		for (String s : searchParam) {
			logger.debug("in: " + s);
		}
		// TODO: webservice to search
	}
}
