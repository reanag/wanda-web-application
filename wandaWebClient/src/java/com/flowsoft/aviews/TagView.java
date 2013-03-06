package com.flowsoft.aviews;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.forms.ReadMoreForm;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class TagView extends GeneralView {

	private static final long serialVersionUID = -8116691770439511775L;

	public static Logger logger = LoggerFactory.getLogger(TagView.class);
	private Label l, l2;
	private GridLayout layout;
	private String tagName;
	private List<Article> aList;

	public TagView(String t) {
		super();
		tagName = t;
		StringBuffer sb = new StringBuffer();
		for (Character c : t.toCharArray()) {
			if (c.isJavaLetterOrDigit(c)) {
				sb.append(c);
			}
		}

		this.NAME = "tagView." + sb.toString();
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

		aList = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().findArticleByTag(l.getValue());

		l2.setHeight("50px");
		layout.addComponent(l, 0, 0);
		layout.addComponent(l2, 0, 1);

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

	public String getTagName() {
		return tagName;
	}

}
