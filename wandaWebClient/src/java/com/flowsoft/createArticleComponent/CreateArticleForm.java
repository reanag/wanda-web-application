package com.flowsoft.createArticleComponent;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.aviews.MainView;
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
	private static FieldGroup binder;
	private Window popupWindow;
	protected Logger logger = LoggerFactory.getLogger(CreateArticleForm.class);

	private Label title;

	private static ComboBox categorySelectComboBox;

	@PropertyId("title")
	private TextField articleTitle;
	@PropertyId("content")
	private TextArea articleContent;
	private TagSelectorBox tagSelector;
	private Button submitButton;
	private Link backLink;
	private static List<Category> categoryList;
	private static Category selectedCategory;
	private static String lastInserted;

	public CreateArticleForm() {

		// ------------TITLELABEL------------
		title = new Label();
		addComponent(title);
		title.setWidth(componentWidth);
		title.setHeight("90px");

		// ------------CATEGORY SELECTOR------------
		categorySelectComboBox = new ComboBox("Categories: ");
		categorySelectComboBox.addListener((Property.ValueChangeListener) this);

		categorySelectComboBox.setNewItemsAllowed(true);
		categorySelectComboBox.setNullSelectionAllowed(false);
		categorySelectComboBox.setImmediate(true);
		categorySelectComboBox.setNewItemHandler(new NewItemHandler() {

			@Override
			public void addNewItem(String newItemCaption) {
				if (!categorySelectComboBox.containsId(newItemCaption)) {

					logger.debug("Create window white caption: "
							+ newItemCaption);

					popupWindow = new PopupWindow();

					binder = new FieldGroup();
					CreateArticleView.getCategoryBean().setCategoryName(
							newItemCaption);
					BeanItem<Category> item = new BeanItem<Category>(
							CreateArticleView.getCategoryBean());
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

		articleTitle = new TextField("Title:");

		articleTitle.setImmediate(true);
		articleContent = new TextArea("Content:");

		articleContent.setHeight("300px");
		articleContent.setImmediate(true);

		// TODO: tagselector.setDATA + getALLTag ws
		tagSelector = new TagSelectorBox();
		// HashSet<Tag> temp = new HashSet<Tag>();

		tagSelector.setTagList(CreateArticleView.getTagList());
		submitButton = new Button("Submit");
		submitButton.setImmediate(true);
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

		backLink = new Link("Back", new ExternalResource("#!" + MainView.NAME));

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
		categorySelectComboBox.unselect(selectedCategory.getCategoryName());
		tagSelector.unselectAll();

	}

	protected void commit() {
		try {
			CreateArticleView.commit();
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
		logger.debug("Value changed: ");
		for (Category c : categoryList) {
			if (c.getCategoryName().equals(
					categorySelectComboBox.getValue().toString())) {
				selectedCategory = c;
				break;
			}
		}

		logger.debug("selectedCategory is: " + selectedCategory);

	}

	public void setCategoryList(List<Category> existingCategories) {
		categoryList = existingCategories;
		for (Category c : existingCategories) {
			categorySelectComboBox.addItem(c.getCategoryName());
		}
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public static void commit(boolean commitable) {

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

	public static void createNewSelected() {
		selectedCategory = new Category(CreateArticleView.aktUser,
				categorySelectComboBox.getValue().toString());
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
		if (!CreateArticleView.binder.isValid()) {
			submitButton.setEnabled(false);
		} else {
			submitButton.setEnabled(true);
		}
	}

	public Set<Tag> getSelectedTags() {
		return tagSelector.getSelectedTags();
	}

	public void refreshForm(Article a) {
		logger.debug(a.toString());
		articleTitle.setValue(a.getTitle());
		articleContent.setValue(a.getContent());
		selectedCategory = a.getCategory();
		logger.warn("s" + a.getCategory());
		if (find(selectedCategory.getCategoryName()) == null) {
			categorySelectComboBox.select(null);
		} else {
			categorySelectComboBox.select(find(selectedCategory
					.getCategoryName()));
		}

		tagSelector.setSelectedTags(a.getTagList());
	}

	private Object find(String categoryName) {
		for (Object s : categorySelectComboBox.getItemIds()) {
			logger.warn(s.toString());
			if (s.toString().equals(categoryName)) {
				return s;
			}
		}
		return null;
	}

	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return categoryList;
	}
}
