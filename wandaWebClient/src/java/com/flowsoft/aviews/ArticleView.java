package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Comment;
import com.flowsoft.forms.CommentForm;
import com.flowsoft.forms.ReadArticleForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;

public class ArticleView extends GeneralView implements View, Serializable {

	private static final long serialVersionUID = -8116691770439511775L;

	public static Logger logger = LoggerFactory.getLogger(ArticleView.class);
	private ReadArticleForm readArticleForm;
	private Link backLink;
	public FieldGroup binder;
	public Comment newComment;
	private Article article;
	private CommentForm commentForm;

	private static Boolean needToRefresh = false;

	public ArticleView(ArticleHeader a) {
		// logger.debug("ID: " + viewId + " - " + this.getClass() + "/"
		// + a.getTitle());
		this.NAME = "articleView." + a.getTitle().replace(' ', '.');
		initArticle(a);
		generateSurface();

	}

	public ArticleView(Article a) {
		article = a;
		this.NAME = "articleView." + a.getTitle().replace(' ', '.');
		generateSurface();
	}

	private void generateSurface() {
		readArticleForm = new ReadArticleForm(this, article);
		readArticleForm.setWidth("550px");
		readArticleForm.setStyleName("mydiv");
		commentForm = new CommentForm(this,
				((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.getController().findAllCommentFor(article.getId()));
		commentForm.setWidth("550px");
		commentForm.setStyleName("mydiv");
		backLink = new Link("Back", new ExternalResource("#!main"));

	}

	private void initArticle(ArticleHeader a) {
		article = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().findArticleByHeader(a.getId());
	}

	@Override
	public void generateBody() {

	}

	private boolean exist(Article article2) {
		if ((((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().findArticleByHeader(article2.getId())) == null) {
			return false;
		}
		return true;

	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (!exist(article)) {
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.goToMainPage(1);
			return;

		}
		if (needToRefresh) {

			commentForm.refreshList(((WandaVaadinClient) WandaVaadinClient
					.getCurrent()).getController().findAllCommentFor(
					article.getId()));

			setNeedToRefresh(false);
		}

		if (mainLayout.getComponentCount() == 0) {
			readArticleForm.enter();
			commentForm.enter();
			mainLayout.addComponent(readArticleForm);
			mainLayout.addComponent(commentForm);
			mainLayout.addComponent(backLink);
		}
		newComment = new Comment(aktUser, article, "");

		BeanItem<Comment> item = new BeanItem<Comment>(newComment);
		binder = new FieldGroup();
		binder.setItemDataSource(item);
		binder.bindMemberFields(commentForm);
		resizeMainLayout();

	}

	public void commit() {
		try {
			binder.commit();
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.getController().commitComment(newComment);
			((WandaVaadinClient) WandaVaadinClient.getCurrent()).refreshPage();

		} catch (CommitException e) {
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.goToErrorPage();
			e.printStackTrace();
		}

	}

	public Boolean getNeedToRefresh() {
		return needToRefresh;
	}

	public static void setNeedToRefresh(boolean b, Integer id) {
		needToRefresh = b;
	}

	public static void setNeedToRefresh(Boolean nf) {
		needToRefresh = nf;
	}

	public void edit(Article article2) {
		logger.debug("edit: " + article2.toString());
		// navigator.removeView(CreateArticleView.NAME);
		// navigator.addView(CreateArticleView.NAME, new CreateArticleView());

		CreateArticleView c = new CreateArticleView(article2.getTitle());
		c.setArticle(article2);
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(c);
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).getNavigator()
				.navigateTo(c.NAME);
	}

	public void delete(Article article2) {
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).getController()
				.deleteArticle(article2.getId());
		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.removeView(((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.getNavigator().getState());
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).goToMainPage(0);

	}
}