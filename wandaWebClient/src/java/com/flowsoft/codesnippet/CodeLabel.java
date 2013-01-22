package com.flowsoft.codesnippet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

//@ClientWidget(VCodeLabel.class)
@SuppressWarnings("serial")
public class CodeLabel extends Label {

	public CodeLabel() {
		setContentMode(ContentMode.PREFORMATTED);
	}

	public CodeLabel(String content) {
		super(content, ContentMode.PREFORMATTED);
	}

	@Override
	public void setContentMode(ContentMode contentMode) {

		super.setContentMode(contentMode);
		if (contentMode != ContentMode.PREFORMATTED) {
			throw new UnsupportedOperationException(
					"Only preformatted content supported");
		}
		super.setContentMode(ContentMode.PREFORMATTED);
	}

}