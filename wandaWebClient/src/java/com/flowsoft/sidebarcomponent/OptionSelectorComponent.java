package com.flowsoft.sidebarcomponent;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.domain.Category;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;

public class OptionSelectorComponent extends GridLayout {
	private static final long serialVersionUID = 1L;

	private CssLinkComponent createArticleLink;
	private CssLinkListComponent favoriteCategoryGroup;
	private CssLinkListComponent ownCategoryGroup;
	private CssLinkComponent settingsLink;
	static Logger logger = LoggerFactory
			.getLogger(OptionSelectorComponent.class);

	public OptionSelectorComponent(Navigator navigator) {

		ownCategoryGroup = new CssLinkListComponent();
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
				"Favorite categories:",
				(Hashtable<String, ExternalResource>) list.clone());

		settingsLink = new CssLinkComponent("SETTINGS..", new ExternalResource(
				"#!" + CreateArticleView.NAME));

		settingsLink.setHeight("30");
		addComponent(settingsLink);
		createArticleLink.setHeight("30");
		addComponent(createArticleLink);
		createArticleLink.setStyleName("16-style");
		settingsLink.setStyleName("16-style");
		addComponent(ownCategoryGroup);
		addComponent(favoriteCategoryGroup);

	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		// createArticleLink.setStyleName(style);
		favoriteCategoryGroup.setStyleName(style);
		if (ownCategoryGroup != null) {
			ownCategoryGroup.setStyleName(style);
		}
	}

	public void init(List<Category> categoryList) {

		logger.debug("Init category list: " + categoryList.size());
		Hashtable<String, ExternalResource> list = new Hashtable<String, ExternalResource>();
		for (Category c : categoryList) {
			// TODO: create category page
			list.put(c.getCategoryName(), new ExternalResource("#!"
					+ CreateArticleView.NAME));
		}

		// ownCategoryGroup = new CssLinkListComponent("Own categories:", list);
		ownCategoryGroup.setTitle("Own categories");
		ownCategoryGroup.setList(list);
		ownCategoryGroup.setStyleName(this.getStyleName());
		ownCategoryGroup.requestRepaintAll();

	}
}
