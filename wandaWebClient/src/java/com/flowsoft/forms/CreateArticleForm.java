package com.flowsoft.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.createArticleComponent.PopupWindow;
import com.flowsoft.createArticleComponent.TagSelectorBox;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
import com.flowsoft.util.ValidatorUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class CreateArticleForm extends GridLayout implements
		Property.ValueChangeListener, BlurListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String componentWidth = "500px";
	private FieldGroup binder;
	private Window popupWindow;
	protected Logger logger = LoggerFactory.getLogger(CreateArticleForm.class);

	private Label title;

	private ComboBox categorySelectComboBox;

	@PropertyId("title")
	private TextField articleTitle;
	@PropertyId("content")
	private TextArea articleContent;
	private TagSelectorBox tagSelector;
	private Button submitButton;
	private Link backLink;
	private List<Category> categoryList;
	private Category selectedCategory;
	private String lastInserted;
	private CreateArticleView createArticleView;

	public CreateArticleForm(CreateArticleView v) {
		this.createArticleView = v;
		// ------------TITLELABEL------------
		title = new Label();
		addComponent(title);
		title.setWidth(componentWidth);
		title.setHeight("90px");

		// ------------CATEGORY SELECTOR------------
		categorySelectComboBox = new ComboBox("Categories: ");
		categorySelectComboBox
				.addValueChangeListener((Property.ValueChangeListener) this);

		categorySelectComboBox.setNewItemsAllowed(true);
		categorySelectComboBox.setNullSelectionAllowed(false);
		categorySelectComboBox.setImmediate(true);
		categorySelectComboBox.setNewItemHandler(new NewItemHandler() {

			private static final long serialVersionUID = 1L;

			@Override
			public void addNewItem(String newItemCaption) {
				if (!categorySelectComboBox.containsId(newItemCaption)) {

					popupWindow = new PopupWindow(CreateArticleForm.this);

					binder = new FieldGroup();
					createArticleView.getCategoryBean().setCategoryName(
							newItemCaption);
					BeanItem<Category> item = new BeanItem<Category>(
							createArticleView.getCategoryBean());
					binder.setItemDataSource(item);
					binder.bindMemberFields(popupWindow);

					CreateArticleForm.this.getUI().addWindow(popupWindow);
					lastInserted = newItemCaption;
					categorySelectComboBox.addItem(newItemCaption);
					categorySelectComboBox.setValue(newItemCaption);

				}

			}
		});
		categorySelectComboBox
				.addValueChangeListener(new ValueChangeListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						if (categorySelectComboBox.getValue() == null) {
							selectedCategory = null;
							return;
						}
						for (Category c : categoryList) {

							if (c.getCategoryName().equals(
									categorySelectComboBox.getValue()
											.toString())) {
								selectedCategory = c;
								break;
							}
						}
					}
				});
		addComponent(categorySelectComboBox);
		categorySelectComboBox.setWidth(componentWidth);
		categorySelectComboBox.setImmediate(true);

		articleTitle = new TextField("Title:");

		articleTitle.setImmediate(true);
		articleContent = new TextArea("Content:");

		articleContent.setHeight("300px");
		articleContent.setImmediate(true);

		tagSelector = new TagSelectorBox();

		tagSelector.setTagList(((WandaVaadinClient) WandaVaadinClient
				.getCurrent()).getController().getAllTag());
		submitButton = new Button("Submit");
		submitButton.setImmediate(true);
		if (articleTitle.getValue() == null
				|| articleTitle.getValue().equals("")) {
			submitButton.setEnabled(false);
		} else {
			submitButton.setEnabled(true);
		}
		submitButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					CreateArticleForm.this.commit();
					CreateArticleForm.this.clearFields();

				} catch (InvalidValueException e) {
					logger.debug("Validation example: " + e);
				}

			}
		});

		backLink = new Link("Back", new ExternalResource("#!main"));

		addComponent(articleTitle);

		articleTitle.setWidth(componentWidth);
		addComponent(articleContent);
		articleContent.setWidth(componentWidth);

		addComponent(tagSelector);
		tagSelector.setWidth(componentWidth);
		addComponent(submitButton);

		addComponent(backLink);

		ValidatorUtils.table.put(articleTitle, "title");
		ValidatorUtils.table.put(articleContent, "content");
		articleTitle.addBlurListener(this);
		articleContent.addBlurListener(this);
		categorySelectComboBox.addBlurListener(this);
	}

	protected void clearFields() {
		if (selectedCategory != null) {
			categorySelectComboBox.unselect(selectedCategory.getCategoryName());
		}
		tagSelector.unselectAll();

	}

	protected void commit() {
		try {
			createArticleView.commit();
			clearFields();
		} catch (InvalidValueException e) {
			logger.debug("Validation example: " + e);
		}

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (categorySelectComboBox.getValue() == null) {
			return;
		}

		for (Category c : categoryList) {
			if (c.getCategoryName().equals(
					categorySelectComboBox.getValue().toString())) {
				selectedCategory = c;
				break;
			}
		}

	}

	public void setCategoryList(List<Category> existingCategories) {
		categoryList = existingCategories;
	}

	public void initCategoryField() {
		if (categoryList == null || categoryList.isEmpty()) {
			categoryList = new ArrayList<Category>();
			categoryList.addAll(((WandaVaadinClient) WandaVaadinClient
					.getCurrent()).getController().findAllExistingCategory());
			for (Category c : categoryList) {
				categorySelectComboBox.addItem(c.getCategoryName());
			}
		}
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void commit(boolean commitable) {

		if (commitable) {
			try {
				binder.commit();

			} catch (CommitException e) {
				e.printStackTrace();
			}
		} else {
			categorySelectComboBox.removeItem(lastInserted);
			categorySelectComboBox.select(null);
		}

	}

	public void createNewSelected() {
		selectedCategory = createArticleView.getCategoryBean();
		// selectedCategory = new Category(
		// ((WandaVaadinClient) WandaVaadinClient.getCurrent())
		// .getAktUser(),
		// categorySelectComboBox.getValue().toString());
		categoryList.add(selectedCategory);

	}

	@Override
	public void blur(BlurEvent event) {

		if (event.getSource().equals(categorySelectComboBox)) {
			categorySelectComboBox.addValidator(new BeanValidator(
					Article.class, "category"));
			return;
		}
		ValidatorUtils.installSingleValidator(event.getComponent(),
				Article.class);
		if (!createArticleView.binder.isValid()) {
			submitButton.setEnabled(false);
		} else {
			submitButton.setEnabled(true);
		}
	}

	public Set<Tag> getSelectedTags() {
		return tagSelector.getSelectedTags();
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setSelectedCategory(Category category) {

		if (category == null) {
			selectedCategory = null;
		} else {
			selectedCategory = category;
			categorySelectComboBox.select(category.getCategoryName());
		}
	}

	public void setSelectedTags(Set<Tag> tagList) {
		tagSelector.setSelectedTags(tagList);
	}

	public void setComponentErrors() {
		articleTitle.removeAllValidators();
		articleContent.removeAllValidators();
		categorySelectComboBox.removeAllValidators();
		categorySelectComboBox.setComponentError(null);
		articleTitle.setComponentError(null);
		articleContent.setComponentError(null);
		submitButton.setEnabled(false);
	}
}
