package com.flowsoft.aviews;

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

public class TagView extends GeneralView {

	private static final long serialVersionUID = -8116691770439511775L;
	public String NAME = "tagView";
	public static Logger logger = LoggerFactory.getLogger(TagView.class);
	private Label l, l2;
	private GridLayout layout;
	private List<Article> aList;

	public TagView(String tagName) {
		super();
		this.NAME += "." + tagName;
		initTagForm(tagName);

	}

	private void initTagForm(String tagName) {

		layout = new GridLayout(1, 50);
		layout.setWidth("550px");
		l = new Label();
		l.setValue(tagName);
		l.setStyleName(Reindeer.LABEL_H1);
		l2 = new Label();
		l2.setValue("Articles tagged by " + tagName + ": ");

	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		layout.removeAllComponents();

		aList = controller.findArticleByTag(l.getValue());

		l2.setHeight("50px");
		layout.addComponent(l, 0, 0);
		layout.addComponent(l2, 0, 1);

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
	}

}
