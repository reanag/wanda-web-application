package com.flowsoft.createArticleComponent;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Category;
import com.flowsoft.forms.CreateArticleForm;
import com.flowsoft.util.ValidatorUtils;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class PopupWindow extends Window implements BlurListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Category checkedCategory;
	@PropertyId("categoryName")
	private TextField nameField;
	@PropertyId("description")
	private TextArea descriptionField;
	private Button okButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");
	private HorizontalLayout buttonSpace = new HorizontalLayout();
	private VerticalLayout root = new VerticalLayout();
	@Transient
	public Logger logger = LoggerFactory.getLogger(PopupWindow.class);
	private CreateArticleForm createArticleForm;

	public PopupWindow(CreateArticleForm c) {
		setStyleName(Reindeer.WINDOW_LIGHT);
		this.createArticleForm = c;
		nameField = new TextField("Category name");
		descriptionField = new TextArea("Category description");
		setPositionX(180);
		setPositionY(230);

		setWidth("450px");
		setHeight("420px");

		cancelButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				PopupWindow.this.commit(false);
				if (checkedCategory != null) {
					createArticleForm.setSelectedCategory(checkedCategory);
				}
				new CloseEvent(PopupWindow.this);
				PopupWindow.this.close();

			}
		});

		okButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				PopupWindow.this.commit(true);
				createArticleForm.createNewSelected();
				new CloseEvent(PopupWindow.this);
				PopupWindow.this.close();

			}
		});

		root.setSizeUndefined();
		root.setSpacing(true);
		root.setMargin(true);
		root.addComponent(new Label(
				"You are creating new Category. Please add description: "));

		root.addComponent(nameField);
		nameField.addValidator(new Validator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				checkedCategory = ((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController().findCategoryByName(
						nameField.getValue());
				if (checkedCategory != null) {

					okButton.setEnabled(false);
					nameField.setComponentError(new ErrorMessage() {

						private static final long serialVersionUID = 1L;

						@Override
						public String getFormattedHtmlMessage() {
							return "Category already exist!";
						}

						@Override
						public ErrorLevel getErrorLevel() {
							return ErrorLevel.ERROR;
						}
					});
				} else {
					nameField.setComponentError(null);
					okButton.setEnabled(true);
				}
			}
		});
		nameField.setWidth("300px");
		nameField.setImmediate(true);
		nameField.addBlurListener(this);
		root.addComponent(descriptionField);
		descriptionField.setWidth("300px");
		descriptionField.setHeight("100px");
		descriptionField.setImmediate(true);
		descriptionField.addBlurListener(this);
		buttonSpace.addComponent(okButton);
		buttonSpace.addComponent(cancelButton);
		buttonSpace.setWidth("300px");
		root.addComponent(buttonSpace);
		this.addComponent(root);

		ValidatorUtils.table.put(nameField, "categoryName");
		ValidatorUtils.table.put(descriptionField, "description");
	}

	protected void commit(boolean b) {
		createArticleForm.commit(b);
	}

	@Override
	public void blur(BlurEvent event) {
		ValidatorUtils.installSingleValidator(event.getComponent(),
				Category.class);

	}

}