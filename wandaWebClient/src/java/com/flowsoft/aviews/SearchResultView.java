package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class SearchResultView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	private Label noResultLabel;
	private Vector<CssLayout> articles;

	public SearchResultView(String name, List<Article> a) {
		// logger.debug("ID: " + viewId + " - " + this.getClass());
		this.NAME = "searchResult." + name;
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

			ArticleView articleView = new ArticleView(h);

			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.initView(articleView);
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

}
