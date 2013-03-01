package com.flowsoft.aviews;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
import com.flowsoft.forms.CreateArticleForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public class CreateArticleView extends GeneralView {

	private static final long serialVersionUID = 1L;
	protected static Logger logger = LoggerFactory
			.getLogger(CreateArticleView.class);
	private CreateArticleForm createForm;
	private Article article;
	protected Category category;
	public FieldGroup binder;

	public CreateArticleView() {
		super();
		// logger.debug("ID: " + viewId + " - " + this.getClass());
		setSizeFull();
		createForm = new CreateArticleForm(this);
	}

	public CreateArticleView(String title) {
		super();
		this.NAME = "createArticleView" + title;
		setSizeFull();
		createForm = new CreateArticleForm(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		if (createForm.getCategoryList() == null
				|| createForm.getCategoryList().isEmpty()) {

			CreateArticleForm
					.setCategoryList(((WandaVaadinClient) WandaVaadinClient
							.getCurrent()).getController()
							.findAllExistingCategory());
			createForm.initCategoryField();
		}
		if (article != null) {
			createForm.setSelectedTags(article.getTagList());
			createForm.setSelectedCategory(article.getCategory());
		}
		binder = new FieldGroup();
		if (article == null || article.getCategory() == null) {
			createForm.setComponentErrors();
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

	public void commit() {
		try {

			article.setCategory(createForm.getSelectedCategory());
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.initView(new CategoryView(article.getCategory()));

			article.setTagList(createForm.getSelectedTags());
			binder.commit();
			article = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.getController().commitArticle(article);

		} catch (CommitException e) {
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.goToErrorPage();
			e.printStackTrace();
		}

		ArticleView articleView = new ArticleView(article);
		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(articleView);
		((WandaVaadinClient) WandaVaadinClient.getCurrent()).getNavigator()
				.navigateTo(articleView.getNAME());
		setArticle(null);

	}

	public Category getCategoryBean() {
		if (category == null) {
			category = new Category(aktUser, "", "");
		}
		return category;
	}

	public List<Tag> getTagList() {
		return ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().getAllTag();

	}

	public void setArticle(Article article2) {
		article = article2;

	}
}
