package com.flowsoft.sidebarcomponent;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.domain.Article;
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
		articleRecommender.setHeight("150px");

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

	public void setMostRencentArticles(List<Article> mostRecentArticles,
			List<Article> mostPopularArticles,
			List<Article> mostRecommendedArticles) {
		Hashtable<String, ExternalResource> recentList = new Hashtable<String, ExternalResource>();
		Hashtable<String, ExternalResource> recommendedList = new Hashtable<String, ExternalResource>();
		Hashtable<String, ExternalResource> popularList = new Hashtable<String, ExternalResource>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm dd.MM.yyyy.");
		for (Article a : mostRecentArticles) {
			recentList.put(
					a.getTitle() + " (" + dateFormat.format(a.getCreatedTS())
							+ ")", new ExternalResource("#!"
							+ CreateArticleView.NAME));
		}
		for (Article a : mostRecommendedArticles) {
			recommendedList.put(
					a.getTitle() + " (" + dateFormat.format(a.getCreatedTS())
							+ ")", new ExternalResource("#!"
							+ CreateArticleView.NAME));
		}
		for (Article a : mostPopularArticles) {
			popularList.put(
					a.getTitle() + " (" + dateFormat.format(a.getCreatedTS())
							+ ")", new ExternalResource("#!"
							+ CreateArticleView.NAME));
		}

		recentArticlesList = new CssLinkListComponent(null, recentList);

		recommendedArticlesList = new CssLinkListComponent(null,
				recommendedList);
		popularArticlesList = new CssLinkListComponent(null, popularList);

		articleRecommender.addTab(recentArticlesList, "Recent");
		articleRecommender.addTab(recommendedArticlesList, "Recommended");
		articleRecommender.addTab(popularArticlesList, "Popular");
		if (getComponentCount() < 1) {
			addComponent(articleRecommender);
		}
	}

}
