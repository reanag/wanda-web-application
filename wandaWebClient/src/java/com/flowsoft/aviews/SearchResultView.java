package com.flowsoft.aviews;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.component.ArticlePanel;
import com.flowsoft.domain.Article;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

public class SearchResultView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	private Label noResultLabel;
	// private Vector<CssLayout> articles;
	private ArticlePanel articlePanel;

	public SearchResultView(String name, List<Article> a) {

		this.NAME = "searchResult." + name;
		generateArticles(a);
	}

	private void generateArticles(List<Article> a) {
		if (a == null || a.isEmpty()) {
			noResultLabel = new Label();
			noResultLabel.setValue("Sorry! No articles found.");
			noResultLabel.setWidth("550px");
		} else {
			articlePanel = new ArticlePanel(a);
		}

	}

	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (articlePanel == null) {
			mainLayout.addComponent(noResultLabel);
		} else {
			mainLayout.addComponent(articlePanel);
		}

		resizeMainLayout();

	}

	@Override
	public void generateBody() {

	}

}
