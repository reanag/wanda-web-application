package com.flowsoft.sidebarcomponent;

import java.util.List;

import com.flowsoft.aviews.SearchResultView;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public class Sidebar extends Panel implements View {

	private static final long serialVersionUID = 1L;
	private SearchTool searchTool;
	private OptionSelectorComponent optionSelector;
	private ArticleRecommenderComponent articleRecommender;
	private TagCloudComponent tagCloud;
	private static Navigator navigator;
	private static UserDetailsService controller;

	public Sidebar(Navigator n, UserDetailsService c) {
		this.setWidth("100px");
		navigator = n;
		controller = c;

		searchTool = new SearchTool();
		optionSelector = new OptionSelectorComponent(n);
		optionSelector.setStyleName("optionSelStyle");
		articleRecommender = new ArticleRecommenderComponent();
		articleRecommender.setStyleName("recommenderStyle");

		tagCloud = new TagCloudComponent();
		tagCloud.setStyleName("tagCloudStyle");
		addComponent(searchTool);
		addComponent(optionSelector);
		addComponent(articleRecommender);
		addComponent(tagCloud);

	}

	public void initTagList(List<Tag> list) {
		tagCloud.init(list);

	}

	public void initUserCategories(List<Category> categoryList) {
		optionSelector.init(categoryList);
		optionSelector.requestRepaint();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void initArticleBlokk(List<Article> mostRecentArticles,
			List<Article> mostPopularArticles,
			List<Article> mostRecommendedArticles) {
		articleRecommender.setMostRencentArticles(mostRecentArticles,
				mostPopularArticles, mostRecommendedArticles);
	}

	public static void searchByTitle(String s, Boolean b) {

		navigator.addView(SearchResultView.NAME + "." + s,
				new SearchResultView(controller.findArticleByTitle(s, b)));
		// new SearchResultView(controller.getMostPopularArticle(3)));
		navigator.navigateTo(SearchResultView.NAME + "." + s);

	}
}
