package com.flowsoft.forms;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.component.CommentBox;
import com.flowsoft.domain.Comment;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class CommentForm extends CssLayout {
	Logger logger = LoggerFactory.getLogger(CommentForm.class);
	@PropertyId("commentContent")
	private TextArea commentContentTextfield;
	private Button submitButton;
	private Label label;
	private VerticalLayout vl;
	private List<Comment> list;

	public CommentForm(List<Comment> commentList) {
		this.list = commentList;
		this.setStyleName("comment");
	}

	public void enter() {
		vl = new VerticalLayout();
		label = new Label();
		label.setValue("Comments:");
		label.setStyleName(Reindeer.LABEL_H2);

		commentContentTextfield = new TextArea();
		commentContentTextfield.setStyleName("comment");
		commentContentTextfield.setImmediate(true);
		commentContentTextfield.setWidth("450px");

		submitButton = new Button("Submit");
		submitButton.setStyleName("comment");
		submitButton.setImmediate(true);
		submitButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					CommentForm.this.commit();

				} catch (InvalidValueException e) {

				}

			}
		});
		vl.addComponent(label);
		vl.addComponent(commentContentTextfield);

		vl.addComponent(submitButton);
		vl.setComponentAlignment(submitButton, Alignment.BOTTOM_CENTER);

		if (list != null) {
			for (Comment c : list) {
				vl.addComponent(new CommentBox(c.getId(), c.getOwner()
						.getUsername(), c.getCommentContent(), c.getCreatedTS()
						.toLocaleString()));
			}
		}
		addComponent(vl);
	}

	protected void commit() {
		try {
			ArticleView.setNeedToRefresh(true);
			ArticleView.commit();

		} catch (InvalidValueException e) {

		}

	}

	public void refreshList(List<Comment> cList) {
		list = cList;
		vl.removeAllComponents();
		vl.addComponent(label);
		vl.addComponent(commentContentTextfield);

		vl.addComponent(submitButton);
		vl.setComponentAlignment(submitButton, Alignment.BOTTOM_CENTER);

		if (list != null) {
			for (Comment c : list) {
				vl.addComponent(new CommentBox(c.getId(), c.getOwner()
						.getUsername(), c.getCommentContent(), c.getCreatedTS()
						.toLocaleString()));
			}
		}
	}

}
