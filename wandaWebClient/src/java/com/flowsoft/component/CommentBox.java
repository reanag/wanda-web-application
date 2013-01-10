package com.flowsoft.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class CommentBox extends GridLayout {

	private static final long serialVersionUID = 1L;
	private Label username;
	private Label commentContent;
	private Label date;
	private Button removeButton;
	private Embedded image;

	public CommentBox(String usernameText, String content, String pdate) {
		super(4, 4);
		this.setStyleName("comment");
		this.setWidth("450px");
		username = new Label();
		username.setValue(usernameText);
		// username.setStyleName("black");
		// username.setEnabled(false);
		commentContent = new Label();
		commentContent.setValue(content);
		// commentContent.setEnabled(false);
		date = new Label();
		date.setValue(pdate);
		// date.setStyleName("mydiv");
		// date.setEnabled(false);

		image = new Embedded("", new ThemeResource("img/line.png"));
		image.setHeight("2px");
		// image.setWidth(this.getWidth() - 10, Unit.PIXELS);
		addComponent(username, 0, 0, 2, 1);
		addComponent(date, 3, 0);
		// addComponent(image);
		addComponent(commentContent, 1, 2, 3, 2);
		// addComponent(image, 0, 2);
		this.setColumnExpandRatio(1, 1);
		this.setColumnExpandRatio(2, 1);
		this.setColumnExpandRatio(3, 1);
		this.setColumnExpandRatio(4, 1);
		this.setRowExpandRatio(1, 1);

	}

	public Button getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(Button removeButton) {
		this.removeButton = removeButton;
	}

}
