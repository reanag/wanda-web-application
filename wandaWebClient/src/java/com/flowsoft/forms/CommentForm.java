package com.flowsoft.forms;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.component.CommentBox;
import com.flowsoft.domain.Comment;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class CommentForm extends CssLayout {

	private static final long serialVersionUID = 1L;
	@Transient
	Logger logger = LoggerFactory.getLogger(CommentForm.class);
	@PropertyId("commentContent")
	private TextArea commentContentTextfield;
	private Button submitButton;
	private Label label;
	private GridLayout vl;
	private VerticalLayout commentLayout;
	private List<Comment> list;
	private ArticleView parent;

	public CommentForm(ArticleView parent, List<Comment> commentList) {

		this.list = commentList;
		this.parent = parent;
		this.setStyleName("comment");
		this.label = new Label();
		this.commentContentTextfield = new TextArea();
		this.submitButton = new Button("Submit");
	}

	public void enter() {
		if (getComponentCount() != 0) {
			removeAllComponents();
		}
		vl = new GridLayout(3, 5);
		commentLayout = new VerticalLayout();

		label.setValue("Comments:");
		label.setStyleName(Reindeer.LABEL_H2);

		commentContentTextfield.setStyleName("comment");
		commentContentTextfield.setImmediate(true);
		commentContentTextfield.setWidth("450px");

		submitButton.setStyleName("comment");
		submitButton.setImmediate(true);
		submitButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					CommentForm.this.commit();

				} catch (InvalidValueException e) {

				}

			}
		});
		vl.addComponent(label, 0, 0);
		SnippetReader sr = new SnippetReader();
		SnippetButton snip = new SnippetButton(sr.read("findComment.snip"),
				"Server side implementation of comment searching");
		vl.addComponent(snip, 1, 0);
		vl.addComponent(commentContentTextfield, 0, 1, 2, 1);

		vl.addComponent(submitButton, 2, 2);
		SnippetButton snip4 = new SnippetButton(sr.read("commentBind.snip"),
				"Comment form binding & commit mechanism");
		vl.addComponent(snip4, 2, 3);
		vl.setComponentAlignment(submitButton, Alignment.BOTTOM_RIGHT);

		if (list != null) {
			for (Comment c : list) {
				commentLayout.addComponent(new CommentBox(c.getId(), c
						.getOwner().getUsername(), c.getCommentContent(), c
						.getCreatedTS().toLocaleString()));
			}
			vl.addComponent(commentLayout, 0, 4, 2, 4);
		}
		addComponent(vl);
	}

	protected void commit() {
		try {
			ArticleView.setNeedToRefresh(true);
			if (commentContentTextfield.getValue().length() > 0) {
				parent.commit();
			}

		} catch (InvalidValueException e) {

		}

	}

	public void refreshList(List<Comment> cList) {
		if (vl == null) {
			vl = new GridLayout(3, 5);
		}
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
