package com.flowsoft.aviews;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

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
	@Transient
	protected final static Logger logger = LoggerFactory
			.getLogger(CreateArticleView.class);
	private CreateArticleForm createForm;
	private Article article;
	protected Category category;
	public FieldGroup binder;

	public CreateArticleView() {
		super();
		setSizeFull();
		createForm = new CreateArticleForm(this);
	}

	public CreateArticleView(Article a) {
		super();
		this.article = a;
		this.NAME = "createArticleView" + a.getId();
		setSizeFull();
		createForm = new CreateArticleForm(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		initFields();

		binder = new FieldGroup();
		BeanItem<Article> item = new BeanItem<Article>(article);
		binder.setItemDataSource(item);
		binder.bindMemberFields(createForm);

		mainLayout.addComponent(createForm);
		resizeMainLayout();
	}

	private void initCategoryField() {
		createForm.initCategoryField();
	}

	private void initFields() {
		if (createForm.getCategoryList() == null
				|| createForm.getCategoryList().isEmpty()) {
			initCategoryField();
		}
		if (article == null) {
			createForm.setComponentErrors();
			article = new Article(aktUser, "", "");
			category = new Category();
			category.setOwner(aktUser);
			category.setDescription("");
		} else {
			if (article.getCategory() != null) {
				category = article.getCategory();
				createForm.setSelectedCategory(article.getCategory());
			} else {

				category = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.getController().findCategoryByArticleId(
								article.getId());
				if (category == null) {
					logger.debug("CATEGORY NULL");
				}
				createForm.setSelectedCategory(category);
			}
			if (article.getTagList() != null) {
				createForm.setSelectedTags(article.getTagList());
			}
		}
	}

	@Override
	public void generateBody() {

	}

	public void commit() {
		try {

			article.setCategory(createForm.getSelectedCategory());
			logger.debug("DESC "
					+ createForm.getSelectedCategory().getDescription());
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.initView(new CategoryView(article.getCategory()));

			article.setTagList(createForm.getSelectedTags());
			if (article.getCreatedTS() == null) {
				article.setCreatedTS(new Date());
			}
			if (article.getModifiedTS() == null) {
				article.setModifiedTS(new Date());
			}
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
		this.article = article2;

	}
}
