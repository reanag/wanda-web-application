package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.sidebarcomponent.Sidebar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralView extends Panel implements View, Serializable {

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	private static final long serialVersionUID = 1L;
	public static Integer viewId = 0;
	public String NAME = "Footer&Header";
	public static Logger logger = LoggerFactory.getLogger(MainView.class);
	protected VerticalLayout baseLayout;
	protected GridLayout headerLayout;
	protected GridLayout bodyLayout;
	protected VerticalLayout mainLayout = new VerticalLayout();
	protected HorizontalLayout footerLayout;
	protected Sidebar sidebar;
	protected Link usernameLabel;

	public WandaUser aktUser;
	public Button logout;

	public void init() {

		baseLayout = new VerticalLayout();
		resizeMainLayout();
		bodyLayout = new GridLayout(2, 1);
	}

	public GeneralView() {
		init();
		fetchSession();
		if (usernameLabel != null) {
			usernameLabel.setCaption(aktUser.getUsername());
		}
		viewId++;
	}

	protected void generateSideBar() {
		sidebar = new Sidebar();
		sidebar.setStyleName("myblack");
		sidebar.setSizeFull();
		fetchSidebarData();
		resizeMainLayout();
	}

	protected void resizeMainLayout() {
		if (baseLayout != null) {
			baseLayout.setSizeUndefined();
		}
		if (bodyLayout != null) {
			bodyLayout.setSizeUndefined();
		}
		if (sidebar != null) {
			sidebar.setSizeUndefined();
		}
		if (mainLayout != null) {
			mainLayout.setSizeUndefined();
		}

	}

	public void generateHeader() {

		headerLayout = new GridLayout(4, 1);
		headerLayout.setHeight("145px");
		headerLayout.setWidth("100%");

		usernameLabel = new Link();
		usernameLabel.setStyleName("username");
		usernameLabel.setImmediate(true);
		SnippetReader sr = new SnippetReader();
		SnippetButton snip = new SnippetButton(sr.read("wandaUser.snip"),
				"User representation");

		logout = new Button("Logout");
		logout.setImmediate(true);

		logout.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// logger.debug("EVENT: " + event.getSource().toString());

				((WandaVaadinClient) (WandaVaadinClient.getCurrent()))
						.destroySession();

			}
		});
		Embedded embedded = new Embedded("", new ThemeResource("img/long.gif"));
		embedded.setHeight("100px");

		headerLayout.addComponent(embedded);

		headerLayout.addComponent(usernameLabel);
		headerLayout.addComponent(snip);
		headerLayout.addComponent(logout);

		headerLayout.setComponentAlignment(embedded, Alignment.TOP_LEFT);

		headerLayout.setComponentAlignment(usernameLabel, Alignment.TOP_RIGHT);
		headerLayout.setComponentAlignment(logout, Alignment.TOP_RIGHT);

		headerLayout.setComponentAlignment(snip, Alignment.TOP_RIGHT);
		headerLayout.setColumnExpandRatio(0, 1000);
		headerLayout.setColumnExpandRatio(1, 1);
		headerLayout.setColumnExpandRatio(2, 1);
		headerLayout.setColumnExpandRatio(3, 1);

	}

	public abstract void generateBody();

	public void fetchSession() {
		try {
			setAktUser(((WandaVaadinClient) (WandaVaadinClient.getCurrent()))
					.getAktUser());

			// logger.debug("Aktuser: " + aktUser.getId() +
			// aktUser.getUsername());

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public void fetchSidebarData() {

		if (aktUser != null) {

			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.initView(new PersonalView(aktUser.getUsername()));

			sidebar.initUserCategories(((WandaVaadinClient) WandaVaadinClient
					.getCurrent()).getController().getUserCategories(
					aktUser.getId()));
			sidebar.initTopCategories(((WandaVaadinClient) WandaVaadinClient
					.getCurrent()).getController().getTopCategories(4));
		}
	}

	public void generateFooter() {
		footerLayout = new HorizontalLayout();
		footerLayout.setHeight("85px");
		footerLayout.setWidth("100%");

		Link lnk = new Link(
				WandaVaadinClient.captions.getString("footer.home"),
				new ExternalResource("#!main"));

		Link lnk2 = new Link(
				WandaVaadinClient.captions.getString("footer.about.site"),
				new ExternalResource("#!AboutSite"));

		Link lnk3 = new Link(
				WandaVaadinClient.captions.getString("footer.about.me"),
				new ExternalResource("#!AboutMe"));

		footerLayout.addComponent(lnk);
		footerLayout.addComponent(lnk2);
		footerLayout.addComponent(lnk3);
		footerLayout.setComponentAlignment(lnk, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk2, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk3, Alignment.BOTTOM_LEFT);

	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		generateHeader();
		generateBody();
		generateSideBar();
		generateFooter();
		if (mainLayout.getComponentCount() > 0) {
			mainLayout.removeAllComponents();
		}

		if (bodyLayout.getComponentCount() == 0) {
			bodyLayout.addStyleName("horizontalright");
			bodyLayout.addComponent(mainLayout, 0, 0);
			mainLayout.setStyleName("main");
			bodyLayout.setComponentAlignment(mainLayout, Alignment.TOP_LEFT);
			sidebar.setStyleName("sidebar");
			bodyLayout.addComponent(sidebar, 1, 0);
			bodyLayout.setComponentAlignment(sidebar, Alignment.TOP_LEFT);
			bodyLayout.setColumnExpandRatio(0, 10);
			bodyLayout.setColumnExpandRatio(1, 1);
		}
		if (baseLayout.getComponentCount() == 0) {
			baseLayout.addComponent(headerLayout);
			baseLayout.addComponent(bodyLayout);
			baseLayout.addComponent(footerLayout);
			baseLayout.setExpandRatio(bodyLayout, 1);
			baseLayout.setSizeUndefined();
		}
		addComponent(baseLayout);

		if (usernameLabel == null) {
			usernameLabel = new Link();
		}
		if (aktUser != null) {
			usernameLabel.setCaption(aktUser.getUsername());
			usernameLabel.setResource(new ExternalResource("#!auth="
					+ aktUser.getUsername()));
		}
	}

	public void setAktUser(WandaUser u) {
		aktUser = u;
	}

}
