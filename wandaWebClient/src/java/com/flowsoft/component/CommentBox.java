package com.flowsoft.component;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	private Integer id;
	private UserDetailsService controller;

	public CommentBox(Integer id, String usernameText, String content,
			String pdate, UserDetailsService c) {
		super(6, 4);
		this.controller = c;
		this.id = id;
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

		if (usernameText.equals(((WandaVaadinClient) WandaVaadinClient
				.getCurrent()).getAktUser().getUsername())) {
			removeButton = new Button();
			removeButton.setCaption("x");
			removeButton.setStyleName("black");
			// eltunteti a furcsa kek hatteret..
			removeButton.setHeight("0px");
			removeButton.setWidth("0px");
			removeButton.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					ArticleView.setNeedToRefresh(true);
					controller.removeComment(CommentBox.this.id);
					ArticleView.refreshPage();
				}
			});
			addComponent(removeButton, 5, 0);
		}
		addComponent(username, 0, 0, 2, 0);
		addComponent(date, 3, 0, 4, 0);
		// addComponent(image);
		addComponent(commentContent, 1, 2, 4, 3);
		// addComponent(image, 0, 2);
		this.setColumnExpandRatio(1, 5);
		this.setColumnExpandRatio(2, 5);
		this.setColumnExpandRatio(3, 5);
		this.setColumnExpandRatio(4, 5);
		this.setColumnExpandRatio(5, 1);
		this.setRowExpandRatio(1, 1);

	}

	public Button getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(Button removeButton) {
		this.removeButton = removeButton;
	}

}
