package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.component.CreateArticleForm;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public class CreateArticleView extends GeneralView {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "createArticleView";
	protected Logger logger = LoggerFactory.getLogger(CreateArticleView.class);
	private static CreateArticleForm createForm;
	private static Article article;
	protected static Category category;
	protected static FieldGroup binder;

	public CreateArticleView() {
		super();
		logger.debug("Create Article form done");
		setSizeFull();
		createForm = new CreateArticleForm(controller);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		super.enter(event);
		binder = new FieldGroup();

		article = new Article(aktUser, "", "");
		category = new Category();
		category.setOwner(aktUser);
		category.setDescription("");
		BeanItem<Article> item = new BeanItem<Article>(article);
		binder.setItemDataSource(item);
		binder.bindMemberFields(createForm);

		createForm.setCategoryList(controller.findAllExistingCategory());

		mainLayout.addComponent(createForm);
		mainLayout.setSizeFull();
	}

	@Override
	public void generateBody() {

	}

	public static void commit() {
		try {

			article.setCategory(createForm.getSelectedCategory());
			binder.commit();
			controller.commitArticle(article);
		} catch (CommitException e) {
			navigator.navigateTo("error");
			e.printStackTrace();
		}
		navigator.addView(ArticleView.NAME, new ArticleView(article));
		navigator.navigateTo(ArticleView.NAME);

	}

	public static Category getCategoryBean() {
		if (category == null) {
			category = new Category(aktUser, "", "");
		}
		return category;
	}
}
