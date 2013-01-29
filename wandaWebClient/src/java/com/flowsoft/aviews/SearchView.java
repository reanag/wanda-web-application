package com.flowsoft.aviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.forms.SearchForm;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;

public class SearchView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(SearchView.class);
	private static final long serialVersionUID = 1L;

	private SearchForm searchForm;

	public SearchView() {
		super();
		this.NAME = "search";
		// logger.debug("ID: " + viewId + " - " + this.getClass());
	}

	@Override
	public void generateBody() {

	}

	public void enter(ViewChangeEvent event) {
		super.enter(event);

		if (mainLayout.getComponentCount() < 1) {
			searchForm = new SearchForm(navigator, controller);
			mainLayout.addComponent(searchForm);
			mainLayout.setComponentAlignment(searchForm, Alignment.MIDDLE_LEFT);
			mainLayout.addComponent(new Link("Back", new ExternalResource(
					"#!main")));
		}
		resizeMainLayout();
	}

}
