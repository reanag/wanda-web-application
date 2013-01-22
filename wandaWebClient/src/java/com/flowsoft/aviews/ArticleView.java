package com.flowsoft.aviews;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public static final String NAME = "articleView";
	public static Logger logger = LoggerFactory.getLogger(ArticleView.class);
	private ReadArticleForm readArticleForm;
	private Link backLink;
	public static FieldGroup binder;
	public static Comment newComment;
	private Article article;
	private CommentForm commentForm;
	private List<Comment> commentList;
	private static Boolean needToRefresh = false;

	public ArticleView(ArticleHeader a) {
		logger.debug("ID: " + viewId + " - " + this.getClass() + "/"
				+ a.getTitle());
		initArticle(a);
		generateSurface();

	}

	public ArticleView(Article a) {
		article = a;
		generateSurface();
	}

	private void generateSurface() {
		readArticleForm = new ReadArticleForm(article, controller);
		readArticleForm.setWidth("550px");
		readArticleForm.setStyleName("mydiv");
		commentForm = new CommentForm(commentList);
		commentForm.setWidth("550px");
		commentForm.setStyleName("mydiv");
		backLink = new Link("Back", new ExternalResource("#!" + MainView.NAME));

	}

	private void initArticle(ArticleHeader a) {
		article = controller.findArticleByHeader(a.getId());
		commentList = controller.findAllCommentFor(a.getId());
	}

	@Override
	public void generateBody() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (needToRefresh) {
			commentList = (controller.findAllCommentFor(article.getId()));
			commentForm.refreshList(commentList);
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

	public static void commit() {
		try {
			binder.commit();
			controller.commitComment(newComment);
			navigator.navigateTo(navigator.getState());
		} catch (CommitException e) {
			navigator.navigateTo("error");
			e.printStackTrace();
		}

	}

	public static void refreshPage() {
		navigator.navigateTo(navigator.getState());
	}

	public Boolean getNeedToRefresh() {
		return needToRefresh;
	}

	public static void setNeedToRefresh(boolean b, Integer id) {
		needToRefresh = b;
		controller.removeComment(id);
	}

	public static void setNeedToRefresh(Boolean nf) {
		needToRefresh = nf;
	}

	public static void edit(Article article2) {
		logger.debug("edit: " + article2.toString());
		// navigator.removeView(CreateArticleView.NAME);
		// navigator.addView(CreateArticleView.NAME, new CreateArticleView());
		CreateArticleView.setArticle(article2);
		navigator.navigateTo(CreateArticleView.NAME);
	}

	public static void delete(Article article2) {
		controller.deleteArticle(article2.getId());
		navigator.removeView(navigator.getState());
		navigator.navigateTo(MainView.NAME);

	}
}