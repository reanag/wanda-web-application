package com.flowsoft.sidebarcomponent;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.Reindeer;

public class ArticleRecommenderComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private TabSheet articleRecommender;
	private CssLinkListComponent recommendedArticlesList;
	private CssLinkListComponent recentArticlesList;
	private CssLinkListComponent popularArticlesList;
	public static final int ARTICLE_COUNT = 3;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"kk:mm dd.MM.yyyy.");

	public ArticleRecommenderComponent() {
		articleRecommender = new TabSheet();
		articleRecommender.setStyleName(Reindeer.TABSHEET_MINIMAL);
		// articleRecommender.setHeight("150px");

	}

	public void init() {
		removeAllComponents();
		articleRecommender.addTab(
				initArticles(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController().getRecentArticle(
						ARTICLE_COUNT)), "Recent");

		articleRecommender.addTab(
				initArticles(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController()
						.getMostRecommendedArticle(ARTICLE_COUNT)), "Advised");

		articleRecommender.addTab(
				initArticles(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController().getMostPopularArticle(
						ARTICLE_COUNT)), "Popular");
		addComponent(articleRecommender);
	}

	private CssLinkListComponent initArticles(List<Article> articleList) {

		Hashtable<String, ExternalResource> recentList = new Hashtable<String, ExternalResource>();

		for (Article a : articleList) {
			String s;
			if (a.getTitle().length() > 20) {
				s = a.getTitle().substring(0, 17) + "...";
			} else {
				s = a.getTitle();
			}

			recentList.put(s + " (" + DATE_FORMAT.format(a.getCreatedTS())
					+ ")", new ExternalResource("#!articleView."
					+ a.getTitle().replace(' ', '.')));
		}
		CssLinkListComponent component = new CssLinkListComponent(null,
				recentList);
		component.setStyleName("list");
		return component;
	}

}
