package com.flowsoft.codesnippet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class SnippetWindow extends Window {
	static Logger logger = LoggerFactory.getLogger(SnippetWindow.class);
	private static final long serialVersionUID = 1L;

	// private TextArea c = new TextArea();

	public SnippetWindow(String source) {
		this.setWidth("900px");

		this.setStyleName(Reindeer.WINDOW_BLACK);
		Label c = new CodeLabel(source);
		c.setStyleName("labelstyle");
		this.setHeight(c.getHeight(), c.getHeightUnits());
		// c.setValue(source);
		// c.setStyleName("labelstyle");
		// c.setWidth((float) (this.getWidth() * 0.9), this.getWidthUnits());
		//
		// c.setSizeFull();
		// c.setHeight((float) (this.getHeight() * 0.8), this.getHeightUnits());
		// c.setReadOnly(true);
		// c.setImmediate(true);
		// c.setStyleName("black");
		addComponent(c);

	}

}
