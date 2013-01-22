package com.flowsoft.aviews;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.createArticleComponent.CreateArticleForm;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public class CreateArticleView extends GeneralView {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "createArticleView";
	protected static Logger logger = LoggerFactory
			.getLogger(CreateArticleView.class);
	private static CreateArticleForm createForm;
	private static Article article;
	protected static Category category;
	public static FieldGroup binder;

	public CreateArticleView() {
		super();
		logger.debug("ID: " + viewId + " - " + this.getClass());
		setSizeFull();
		createForm = new CreateArticleForm();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		if (createForm.getCategoryList() == null
				|| createForm.getCategoryList().isEmpty()) {

			createForm.setCategoryList(controller.findAllExistingCategory());
		}
		if (article != null) {
			createForm.setSelectedTags(article.getTagList());
		}
		binder = new FieldGroup();
		if (article == null || article.getCategory() == null) {
			article = new Article(aktUser, "", "");
			category = new Category();
			category.setOwner(aktUser);
			category.setDescription("");
		} else {
			category = article.getCategory();
			createForm.setSelectedCategory(article.getCategory());
		}
		BeanItem<Article> item = new BeanItem<Article>(article);
		binder.setItemDataSource(item);
		binder.bindMemberFields(createForm);

		mainLayout.addComponent(createForm);

		resizeMainLayout();
	}

	@Override
	public void generateBody() {

	}

	public static void commit() {
		try {

			article.setCategory(createForm.getSelectedCategory());
			article.setTagList(createForm.getSelectedTags());
			binder.commit();
			logger.debug("category value after set: " + article.getCategory());
			article = controller.commitArticle(article);

		} catch (CommitException e) {
			navigator.navigateTo("error");
			e.printStackTrace();
		}
		if (!WandaVaadinClient.viewNames.contains(ArticleView.NAME + "."
				+ article.getTitle().replace(' ', '.'))) {
			WandaVaadinClient.viewNames.add(ArticleView.NAME + "."
					+ article.getTitle().replace(' ', '.'));
			navigator.addView(ArticleView.NAME + "."
					+ article.getTitle().replace(' ', '.'), new ArticleView(
					article));
		}

		navigator.navigateTo(ArticleView.NAME + "."
				+ article.getTitle().replace(' ', '.'));
		setArticle(null);

	}

	public static Category getCategoryBean() {
		if (category == null) {
			category = new Category(aktUser, "", "");
		}
		return category;
	}

	public static List<Tag> getTagList() {
		return controller.getAllTag();

	}

	public static void setArticle(Article article2) {
		article = article2;

	}
}
