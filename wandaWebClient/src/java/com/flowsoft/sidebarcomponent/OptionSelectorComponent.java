package com.flowsoft.sidebarcomponent;

import java.util.Hashtable;

import com.flowsoft.client.CreateArticleView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;

public class OptionSelectorComponent extends GridLayout {
	private static final long serialVersionUID = 1L;

	private CssLinkComponent createArticleLink;
	private CssLinkListComponent favoriteCategoryGroup;
	private CssLinkListComponent ownCategoryGroup;
	private CssLinkComponent settingsLink;

	public OptionSelectorComponent() {
		// TODO: A: CreateArticleView design
		createArticleLink = new CssLinkComponent("Write new article..",
				new ExternalResource("#!" + CreateArticleView.NAME));

		// TODO: A: Webservice: getFavoriteCategories() -->
		// Hashtable(CategoryName,
		// Resource)
		// TODO: A: View creation for list category details & articles
		Hashtable<String, ExternalResource> list = new Hashtable<String, ExternalResource>();
		list.put("Category1", new ExternalResource("#!"
				+ CreateArticleView.NAME));
		list.put("Category2", new ExternalResource("#!"
				+ CreateArticleView.NAME));
		favoriteCategoryGroup = new CssLinkListComponent(
				"Favorite categories:", list);
		ownCategoryGroup = new CssLinkListComponent("Own categories:",
				(Hashtable<String, ExternalResource>) list.clone());

		addComponent(createArticleLink);
		addComponent(ownCategoryGroup);
		addComponent(favoriteCategoryGroup);
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		createArticleLink.setStyleName(style);
		favoriteCategoryGroup.setStyleName(style);
		ownCategoryGroup.setStyleName(style);
	}
}
