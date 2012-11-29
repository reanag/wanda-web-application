package com.flowsoft.popupForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.ValidatorUtils;
import com.flowsoft.component.CreateArticleForm;
import com.flowsoft.domain.Category;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PopupTextField extends Window implements BlurListener {

	@PropertyId("categoryName")
	private TextField nameField;
	@PropertyId("description")
	private TextField descriptionField = new TextField("Category description");
	private Button okButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");
	private HorizontalLayout buttonSpace = new HorizontalLayout();
	private VerticalLayout root = new VerticalLayout();
	public Logger logger = LoggerFactory.getLogger(PopupTextField.class);

	public PopupTextField() {
		nameField = new TextField("Category name");

		logger.debug("In namefield: " + nameField.getValue());
		setPositionX(180);
		setPositionY(230);

		setWidth("400px");
		setHeight("350px");

		cancelButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				PopupTextField.this.commit(false);
				new CloseEvent(PopupTextField.this);
				PopupTextField.this.close();

			}
		});

		okButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				PopupTextField.this.commit(true);
				CreateArticleForm.createNewSelected();
				new CloseEvent(PopupTextField.this);
				PopupTextField.this.close();

			}
		});

		root.setSizeUndefined();
		root.setSpacing(true);
		root.setMargin(true);
		root.addComponent(new Label(
				"You are creating new Category. Please add description: "));

		root.addComponent(nameField);
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
		CreateArticleForm.commit(b);

	}

	@Override
	public void blur(BlurEvent event) {
		ValidatorUtils.installSingleValidator(event.getComponent(),
				Category.class);

	}

}