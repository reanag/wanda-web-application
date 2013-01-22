package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
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
		logger.debug("ID: " + viewId + " - " + this.getClass());
		generateArticles(a);
	}

	private void generateArticles(List<Article> a) {

		noResultLabel = new Label();
		noResultLabel.setValue("Sorry! No articles found.");
		noResultLabel.setWidth("550px");
		if (articles == null) {
			articles = new Vector<CssLayout>();
		}
		for (Article h : a) {
			if (!WandaVaadinClient.viewNames.contains(ArticleView.NAME + "."
					+ h.getTitle().replace(' ', '.'))) {
				WandaVaadinClient.viewNames.add(ArticleView.NAME + "."
						+ h.getTitle().replace(' ', '.'));
				navigator.addView(ArticleView.NAME + "."
						+ h.getTitle().replace(' ', '.'), new ArticleView(h));
			}
			articles.add(new ReadMoreForm(new ArticleHeader(h), navigator));
		}

	}

	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (articles.isEmpty()) {
			mainLayout.addComponent(noResultLabel);
		}

		for (CssLayout p : articles) {

			mainLayout.addComponent(p);
			mainLayout.setComponentAlignment(p, Alignment.TOP_CENTER);
		}

		resizeMainLayout();

	}

	@Override
	public void generateBody() {
		articles = new Vector<CssLayout>();
	}

	public static UserDetailsService getController() {
		return controller;
	}

	public static void setController(UserDetailsService c) {
		controller = c;
	}
}
