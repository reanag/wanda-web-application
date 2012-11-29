package com.flowsoft.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.CreateArticleView;
import com.flowsoft.client.MainView;
import com.flowsoft.client.ValidatorUtils;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.popupForm.PopupTextField;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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
	private TextField articleContent;
	private TagSelector tagSelector;
	private Button submitButton;
	private Link backLink;
	private UserDetailsService controller;
	private static List<Category> categoryList;
	private static Category selectedCategory;
	private static String lastInserted;

	public CreateArticleForm(UserDetailsService controller) {
		this.controller = controller;

		// ------------TITLELABEL------------
		title = new Label();
		addComponent(title);
		title.setWidth(componentWidth);
		title.setHeight("90px");

		// ------------CATEGORY SELECTOR------------
		categorySelectComboBox = new ComboBox("Categories: ");
		categorySelectComboBox.addListener((Property.ValueChangeListener) this);

		// TODO: webservice: getAllCategory
		// categorySelectComboBox.addItem("Java");
		// categorySelectComboBox.add
		categorySelectComboBox.setNewItemsAllowed(true);
		categorySelectComboBox.setNullSelectionAllowed(false);
		categorySelectComboBox.setImmediate(true);
		categorySelectComboBox.setNewItemHandler(new NewItemHandler() {

			@Override
			public void addNewItem(String newItemCaption) {
				if (!categorySelectComboBox.containsId(newItemCaption)) {
					// TODO: pop-up window for category description setting
					// getWindow().showNotification(
					// "Added city: " + newItemCaption);
					logger.debug("Create window white caption: "
							+ newItemCaption);

					popupWindow = new PopupTextField();

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
		addComponent(categorySelectComboBox);
		categorySelectComboBox.setWidth(componentWidth);

		articleTitle = new TextField("Title:");

		articleTitle.setImmediate(true);
		articleContent = new TextField("Content:");

		articleContent.setHeight("300px");
		articleContent.setImmediate(true);

		// TODO: tagselector.setDATA + getALLTag ws
		tagSelector = new TagSelector();
		submitButton = new Button("Submit");
		submitButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				CreateArticleForm.this.commit();
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

	protected void commit() {
		// categorySelectComboBox.addValidator(new BeanValidator(Article.class,
		// "category"));
		// articleTitle.addValidator(new BeanValidator(Article.class, "title"));
		// articleContent
		// .addValidator(new BeanValidator(Article.class, "content"));
		CreateArticleView.commit();

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
				// TODO Auto-generated catch block
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
	}
}
