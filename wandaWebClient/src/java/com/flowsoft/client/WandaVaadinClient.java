package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

//@Theme("hello-theme")
@Theme("vaadinclienttheme")
public class WandaVaadinClient extends UI {

	private static final long serialVersionUID = 1L;
	Navigator navigator;
	Logger logger = LoggerFactory.getLogger(WandaVaadinClient.class);

	@Override
	public void init(VaadinRequest request) {

		// MyFirstController controller = new MyFirstController();
		Navigator navigator = new Navigator(this, getContent());
		navigator.addView(IntroView.NAME, new IntroView());
		navigator.addView(CountView.NAME, CountView.class);
		navigator.addView(MainView.NAME, MainView.class);
		navigator.addView(LoginView.NAME, new LoginView(navigator));
		navigator.addView(UserListView.NAME, new UserListView());
		navigator.addView(CreateUserView.NAME, new CreateUserView());
		navigator.navigate();

	}
}
