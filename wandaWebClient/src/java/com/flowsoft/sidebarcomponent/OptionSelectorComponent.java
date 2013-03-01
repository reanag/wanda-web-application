package com.flowsoft.sidebarcomponent;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.CategoryView;
import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.domain.Category;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;

public class OptionSelectorComponent extends GridLayout {
	private static final long serialVersionUID = 1L;

	private CssLinkComponent createArticleLink;
	private CssLinkListComponent topCategoryGroup;
	private CssLinkListComponent ownCategoryGroup;
	// private CssLinkComponent settingsLink;
	private GridLayout layout;
	static Logger logger = LoggerFactory
			.getLogger(OptionSelectorComponent.class);

	public OptionSelectorComponent() {

		layout = new GridLayout(2, 4);
		ownCategoryGroup = new CssLinkListComponent();
		topCategoryGroup = new CssLinkListComponent();
		CreateArticleView c = new CreateArticleView();
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(c);
		createArticleLink = new CssLinkComponent("Write new article..",
				new ExternalResource("#!" + c.NAME));
		SnippetReader sr = new SnippetReader();
		SnippetButton snip = new SnippetButton(sr.read("category.snip"),
				"Category representation");
		createArticleLink.setHeight("40px");
		layout.addComponent(createArticleLink, 0, 1, 1, 1);
		createArticleLink.setStyleName("title-style");
		layout.addComponent(snip, 1, 2);
		layout.addComponent(ownCategoryGroup, 0, 2);
		layout.addComponent(topCategoryGroup, 0, 3);
		layout.setComponentAlignment(snip, Alignment.TOP_RIGHT);
		addComponent(layout);

	}

	// @Override
	// public void setStyleName(String style) {
	// super.setStyleName(style);
	// // createArticleLink.setStyleName(style);
	// if (topCategoryGroup != null) {
	// topCategoryGroup.setStyleName(style);
	// }
	// if (ownCategoryGroup != null) {
	// ownCategoryGroup.setStyleName(style);
	// }
	// }

	public void initOwnCategoryList(List<Category> categoryList) {

		// logger.debug("Init category list: " + categoryList.size());
		Hashtable<String, ExternalResource> list = new Hashtable<String, ExternalResource>();
		for (Category c : categoryList) {

			CategoryView cv = new CategoryView(c);
			((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(cv);

			list.put(c.getCategoryName(), new ExternalResource("#!" + cv.NAME));
		}

		// ownCategoryGroup = new CssLinkListComponent("Own categories:", list);
		ownCategoryGroup.setTitle("Own categories");
		ownCategoryGroup.setList(list);
		ownCategoryGroup.setStyleName(this.getStyleName());
		ownCategoryGroup.requestRepaintAll();

	}

	public void initTopCategoryList(List<Category> topCategories) {
		Hashtable<String, ExternalResource> list = new Hashtable<String, ExternalResource>();
		for (Category c : topCategories) {

			CategoryView cv = new CategoryView(c);
			((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(cv);

			list.put(c.getCategoryName(), new ExternalResource("#!" + cv.NAME));
		}

		topCategoryGroup.setTitle("Categories");
		topCategoryGroup.setList(list);
		topCategoryGroup.setStyleName(this.getStyleName());
		topCategoryGroup.requestRepaintAll();

	}
}
