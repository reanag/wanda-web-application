package com.flowsoft.aviews;

import java.io.Serializable;

import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.client.AboutMeView;
import com.flowsoft.client.AboutSiteView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.sidebarcomponent.Sidebar;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Footer&Header";
	public static Logger logger = LoggerFactory.getLogger(MainView.class);
	protected static VerticalLayout baseLayout;
	protected static HorizontalLayout headerLayout;
	protected static HorizontalLayout bodyLayout;
	protected VerticalLayout mainLayout;
	protected static HorizontalLayout footerLayout;
	protected static Sidebar sidebar;
	protected TextField usernameLabel;
	static protected Navigator navigator;
	public static WandaUser aktUser;
	@WebServiceRef
	static protected UserDetailsService controller;

	public void init() {
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
	}

	public GeneralView() {
		init();
		baseLayout = new VerticalLayout();
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("600px");
		resizeMainLayout();
		bodyLayout = new HorizontalLayout();
	}

	protected void generateSideBar() {
		sidebar = new Sidebar(navigator);
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

		headerLayout = new HorizontalLayout();
		headerLayout.setHeight("105px");
		headerLayout.setWidth("100%");

		Label l = new Label(WandaVaadinClient.captions.getString("HeaderTitle"));
		l.setHeight("80px");
		l.setStyleName("title");

		usernameLabel = new TextField();
		usernameLabel.setEnabled(false);
		usernameLabel.setStyleName("mydiv");
		usernameLabel.setImmediate(true);

		headerLayout.addComponent(l);
		headerLayout.setComponentAlignment(l, Alignment.MIDDLE_LEFT);

		headerLayout.addComponent(usernameLabel);
		headerLayout.setComponentAlignment(usernameLabel,
				Alignment.BOTTOM_RIGHT);

	}

	public abstract void generateBody();

	public static void fetchSession() {
		try {
			if (WandaVaadinClient.getHttpSession().getAttribute("username") != null) {
				setAktUser(controller.findByUsername(WandaVaadinClient
						.getHttpSession().getAttribute("username").toString()));

				logger.debug("Aktuser: " + aktUser.getId()
						+ aktUser.getUsername());
			}
		} catch (NullPointerException e) {
		}

	}

	public static void fetchSidebarData() {
		sidebar.initTagList(controller.getAllTag());
		// TODO: ws-s
		sidebar.initArticleBlokk(controller.getRecentArticle(3),
				controller.getMostPopularArticle(3),
				controller.getMostRecommendedArticle(3));

		if (aktUser != null) {

			sidebar.initUserCategories(controller.getUserCategories(aktUser
					.getId()));
		}
	}

	public void generateFooter() {
		footerLayout = new HorizontalLayout();
		footerLayout.setHeight("85px");
		footerLayout.setWidth("100%");

		Link lnk = new Link(
				WandaVaadinClient.captions.getString("footer.home"),
				new ExternalResource("#!" + MainView.NAME));

		Link lnk2 = new Link(
				WandaVaadinClient.captions.getString("footer.about.site"),
				new ExternalResource("#!" + AboutMeView.NAME));

		Link lnk3 = new Link(
				WandaVaadinClient.captions.getString("footer.about.me"),
				new ExternalResource("#!" + AboutSiteView.NAME));

		footerLayout.addComponent(lnk);
		footerLayout.addComponent(lnk2);
		footerLayout.addComponent(lnk3);
		footerLayout.setComponentAlignment(lnk, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk2, Alignment.BOTTOM_LEFT);
		footerLayout.setComponentAlignment(lnk3, Alignment.BOTTOM_LEFT);

	}

	public void enter(ViewChangeEvent event) {
		// if (baseLayout.getComponentCount() > 0) {
		// baseLayout.removeAllComponents();
		// mainLayout.removeAllComponents();
		// }

		// if (mainLayout.getComponentCount() > 1) {
		// mainLayout.removeAllComponents();
		// }
		// if (bodyLayout.getComponentCount() > 2) {
		// bodyLayout.removeAllComponents();
		// bodyLayout.addComponent(mainLayout);
		// bodyLayout
		// .setComponentAlignment(mainLayout, Alignment.MIDDLE_RIGHT);
		// bodyLayout.addComponent(sidebar);
		// bodyLayout
		// .setComponentAlignment(mainLayout, Alignment.MIDDLE_RIGHT);
		// }
		// if (baseLayout.getComponentCount() > 3) {
		// baseLayout.removeAllComponents();
		// baseLayout.addComponent(headerLayout);
		// baseLayout.addComponent(bodyLayout);
		// baseLayout.addComponent(footerLayout);
		// }
		// if (getComponentCount() > 0) {
		// removeAllComponents();
		// addComponent(baseLayout);
		// }

		generateHeader();
		generateBody();
		generateSideBar();
		generateFooter();

		if (getComponentCount() == 0) {
			bodyLayout.addStyleName("horizontalright");
			bodyLayout.addComponent(mainLayout);
			bodyLayout
					.setComponentAlignment(mainLayout, Alignment.MIDDLE_RIGHT);
			bodyLayout.addComponent(sidebar);
			bodyLayout
					.setComponentAlignment(mainLayout, Alignment.MIDDLE_RIGHT);
			baseLayout.addComponent(headerLayout);
			baseLayout.addComponent(bodyLayout);
			baseLayout.addComponent(footerLayout);
			baseLayout.setExpandRatio(bodyLayout, 1);
			baseLayout.setSizeUndefined();
			addComponent(baseLayout);

		}
		logger.debug("GeneralView construct done: " + this.getClass());

		usernameLabel.setValue(aktUser.getUsername());
		// resizeMainLayout();
	}

	public static void setAktUser(WandaUser u) {
		aktUser = u;
		logger.debug("Aktuser: " + aktUser.toString());
	}

	public static void setNavigator(Navigator n) {
		navigator = n;

	}
}
