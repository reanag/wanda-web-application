package com.flowsoft.aviews;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class CategoryView extends GeneralView {

	private static final long serialVersionUID = -8116691770439511775L;

	public static Logger logger = LoggerFactory.getLogger(TagView.class);
	private Label l, l2, l3;
	private GridLayout layout;
	private List<Article> aList;

	public CategoryView(String categoryName) {
		super();

		this.NAME += "categoryView." + categoryName;
		initCategoryForm(categoryName);

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

		aList = controller.findArticleByCategory(l.getValue());
		// logger.debug("Article by category size: " + aList.size());
		l2.setHeight("50px");
		layout.addComponent(l, 0, 0);
		layout.addComponent(l3, 0, 1);
		layout.addComponent(l2, 0, 2);

		if (aList != null) {
			for (Article a : aList) {
				ReadMoreForm rmf = new ReadMoreForm(new ArticleHeader(a),
						navigator);
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
		// TODO Auto-generated method stub

	}

	public void setDescriptionText(String description) {
		// TODO Auto-generated method stub

	}

	public void setCategoryOwnerText(String username) {
		// TODO Auto-generated method stub

	}

	public void setCreatedTSText(Date createdTS) {
		// TODO Auto-generated method stub

	}

}
