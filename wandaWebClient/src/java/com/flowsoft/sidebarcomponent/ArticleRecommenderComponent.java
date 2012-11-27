package com.flowsoft.sidebarcomponent;

import java.util.Hashtable;

import com.flowsoft.client.CreateArticleView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;

public class ArticleRecommenderComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private TabSheet articleRecommender;
	private CssLinkListComponent recommendedArticlesList;
	private CssLinkListComponent recentArticlesList;
	private CssLinkListComponent popularArticlesList;

	public ArticleRecommenderComponent() {
		articleRecommender = new TabSheet();

		recommendedArticlesList = new CssLinkListComponent(null,
				demoListCreator("Recommended Article"));
		recentArticlesList = new CssLinkListComponent(null,
				demoListCreator("Recent Article"));
		popularArticlesList = new CssLinkListComponent(null,
				demoListCreator("Popular Article"));

		articleRecommender.addTab(recentArticlesList, "Recent");
		articleRecommender.addTab(recommendedArticlesList, "Recommended");
		articleRecommender.addTab(popularArticlesList, "Popular");
		addComponent(articleRecommender);
	}

	// TODO: A: Webservice: getRecommendedArticles --> Hashtable(String,
	// Resource)
	public Hashtable<String, ExternalResource> demoListCreator(String content) {
		Hashtable<String, ExternalResource> list = new Hashtable<String, ExternalResource>();
		list.put(content + " 1", new ExternalResource("#!"
				+ CreateArticleView.NAME));
		list.put(content + " 2", new ExternalResource("#!"
				+ CreateArticleView.NAME));
		return list;
	}

}
