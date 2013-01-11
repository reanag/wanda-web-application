package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class SearchResultView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "searchResult";
	private Label noResultLabel;
	private Vector<CssLayout> articles;

	public SearchResultView(List<Article> a) {
		generateArticles(a);
	}

	private void generateArticles(List<Article> a) {
		noResultLabel = new Label();
		noResultLabel.setValue("Sorry! No articles found.");
		if (articles == null) {
			articles = new Vector<CssLayout>();
		}
		if (a.isEmpty()) {
			mainLayout.addComponent(noResultLabel);
		}
		for (Article h : a) {
			navigator.addView(
					ArticleView.NAME + "." + h.getTitle().replace(' ', '.'),
					new ArticleView(h));
			articles.add(new ReadMoreForm(new ArticleHeader(h), navigator));
		}
		for (CssLayout p : articles) {

			mainLayout.addComponent(p);
			mainLayout.setComponentAlignment(p, Alignment.TOP_CENTER);
		}

	}

	public void enter(ViewChangeEvent event) {
		super.enter(event);

		// mainLayout.setHeight(articles.size() * 180, Unit.PIXELS);
		resizeMainLayout();

	}

	@Override
	public void generateBody() {
		articles = new Vector<CssLayout>();
	}

	public UserDetailsService getController() {
		return this.controller;
	}

	public void setController(UserDetailsService controller) {
		this.controller = controller;
	}
}
