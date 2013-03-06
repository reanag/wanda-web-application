package com.flowsoft.aviews;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.forms.ReadMoreForm;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class CategoryView extends GeneralView {

	private static final long serialVersionUID = -8116691770439511775L;

	public final static Logger logger = LoggerFactory.getLogger(TagView.class);
	private Label l, l2, l3;
	private GridLayout layout;
	private List<Article> aList;

	public CategoryView(Category c) {
		super();

		this.NAME = "category" + c.getId();
		initCategoryForm(c.getCategoryName());

	}

	private void initCategoryForm(String categoryName) {
		layout = new GridLayout(1, 50);
		layout.setWidth("550px");
		l = new Label();
		l.setValue(categoryName);
		l.setStyleName(Reindeer.LABEL_H1);
		l2 = new Label();
		l2.setValue("Articles belongs to  " + categoryName + ": ");
		l3 = new Label();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		layout.removeAllComponents();

		aList = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().findArticleByCategory(l.getValue());
		// logger.debug("Article by category size: " + aList.size());
		l2.setHeight("50px");
		layout.addComponent(l, 0, 0);
		layout.addComponent(l3, 0, 1);
		layout.addComponent(l2, 0, 2);

		if (aList != null) {
			for (Article a : aList) {
				ReadMoreForm<ArticleHeader> rmf = new ReadMoreForm<ArticleHeader>(
						new ArticleHeader(a));
				layout.addComponent(rmf);
			}
		} else {
			layout.addComponent(new Label("No such article."));
		}

		mainLayout.addComponent(layout);
		resizeMainLayout();
	}

	@Override
	public void generateBody() {

	}

}
