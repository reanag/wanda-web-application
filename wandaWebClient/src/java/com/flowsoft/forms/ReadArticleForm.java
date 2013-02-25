package com.flowsoft.forms;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.aviews.TagView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Tag;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.themes.Reindeer;

public class ReadArticleForm extends GridLayout {

	private static final long serialVersionUID = 1L;
	protected org.slf4j.Logger logger = LoggerFactory
			.getLogger(ReadArticleForm.class);
	private ArrayList<Link> tags;
	private Article article;
	private Button editButton, deleteButton;
	private ArticleRatingForm ratingForm;
	private ArticleView articleView;

	public ReadArticleForm(ArticleView v, Article a) {
		super(4, 5);
		this.articleView = v;
		this.article = a;
		ratingForm = new ArticleRatingForm(article.getId());
	}

	public void enter() {
		removeAllComponents();
		tags = new ArrayList<Link>();
		Label content = new Label(article.getContent());

		Label title = new Label(article.getTitle());
		title.setStyleName(Reindeer.LABEL_H1);
		Label auth = new Label(article.getOwner().getFirstName() + " "
				+ article.getOwner().getLastName());
		auth.setStyleName(Reindeer.LABEL_H2);

		ratingForm.setSizeUndefined();
		ratingForm.refreshAverage();

		addComponent(title, 0, 0, 1, 0);

		if (article
				.getOwner()
				.getUsername()
				.equals(((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.getAktUser().getUsername())) {
			editButton = new Button();
			editButton.setCaption("Edit");
			editButton.addClickListener(new Button.ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// logger.debug("button click");
					articleView.edit(article);

				}
			});
			deleteButton = new Button();
			deleteButton.setCaption("Delete");
			deleteButton.addClickListener(new Button.ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					logger.debug("button click");
					articleView.delete(article);

				}
			});
			this.setColumnExpandRatio(1, 1);
			this.setColumnExpandRatio(0, 15);
			this.setColumnExpandRatio(2, 1);
			addComponent(editButton, 2, 0);
			addComponent(deleteButton, 3, 0);

		}
		addComponent(auth, 0, 1, 2, 1);
		addComponent(content, 0, 2, 3, 2);
		if (article.getTagList() != null) {

			for (Tag s : article.getTagList()) {
				((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.initView(new TagView(s.getTagName()));
				Link l = new Link(s.getTagName(), new ExternalResource(
						"#!tagView." + s.getTagName()));
				tags.add(l);
			}
		}

		if (!tags.isEmpty()) {
			CssLayout tagsForm = new CssLayout();
			for (Link l : tags) {
				tagsForm.addComponent(l);
			}
			addComponent(tagsForm, 0, 3, 1, 3);

		}
		addComponent(ratingForm, 1, 4, 2, 4);

	}
}
