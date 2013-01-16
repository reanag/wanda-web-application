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
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Footer&Header";
	public static Logger logger = LoggerFactory.getLogger(MainView.class);
	protected static VerticalLayout baseLayout;
	protected static HorizontalLayout headerLayout;
	protected static GridLayout bodyLayout;
	protected static VerticalLayout mainLayout = new VerticalLayout();
	protected static HorizontalLayout footerLayout;
	protected static Sidebar sidebar;
	protected static Link usernameLabel;
	static protected Navigator navigator;
	public static WandaUser aktUser;
	@WebServiceRef
	static protected UserDetailsService controller;

	public static void init() {
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
		baseLayout = new VerticalLayout();
		// mainLayout = new VerticalLayout();
		mainLayout.setWidth("600px");
		resizeMainLayout();
		bodyLayout = new GridLayout(2, 1);
	}

	public GeneralView() {
		init();
	}

	protected static void generateSideBar() {
		sidebar = new Sidebar(navigator, controller);
		sidebar.setStyleName("myblack");
		sidebar.setSizeFull();
		fetchSidebarData();
		resizeMainLayout();
	}

	protected static void resizeMainLayout() {
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

	public static void generateHeader() {

		// final ThemeResource flashResource = new ThemeResource(
		// "script/piecemaker.swf");
		// FileResource flashResource = new FileResource(
		// new File(
		// "C://Users//Andika//Desktop//Color-Changeable-Navigation-Menus//4xmlmenusrg.swf"));
		// // // new File(
		// "C://Users//Andika//Desktop//wanda//workspace//workspace-sts-cfx-3.1.0.RELEASE//wandaWebClient//WebContent//WEB-INF//script//first.swf"));
		// final Embedded embedded = new Embedded(null, flashResource);

		// This is the default type, but we set it anyway.
		// embedded.setType(Embedded.TYPE_OBJECT);
		// embedded.setWidth("600px");

		// embedded.setStyleName("bordered");
		// embedded.setHeight("180px");
		// // This is recorgnized automatically, but set it anyway.
		// embedded.setMimeType("application/x-shockwave-flash");

		headerLayout = new HorizontalLayout();
		headerLayout.setHeight("105px");
		headerLayout.setWidth("100%");

		// Label l = new
		// Label(WandaVaadinClient.captions.getString("HeaderTitle"));
		// l.setHeight("80px");
		// l.setStyleName("title");

		usernameLabel = new Link();
		// usernameLabel.setEnabled(false);
		usernameLabel.setStyleName("username");
		usernameLabel.setImmediate(true);

		Embedded embedded = new Embedded("", new ThemeResource("img/long.gif"));
		embedded.setHeight("120px");
		headerLayout.setHeight("120px");
		headerLayout.addComponent(embedded);
		// headerLayout.addComponent(l);
		// headerLayout.setComponentAlignment(l, Alignment.MIDDLE_LEFT);

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
		sidebar.initArticleBlokk(controller.getRecentArticle(3),
				controller.getMostPopularArticle(3),
				controller.getMostRecommendedArticle(3));
		if (aktUser != null) {
			sidebar.initUserCategories(controller.getUserCategories(aktUser
					.getId()));
		}
	}

	public static void generateFooter() {
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

		generateHeader();
		generateBody();
		generateSideBar();
		generateFooter();
		if (mainLayout.getComponentCount() > 0) {
			mainLayout.removeAllComponents();
		}
		if (getComponentCount() == 0) {
			if (bodyLayout.getComponentCount() == 0) {
				bodyLayout.addStyleName("horizontalright");
				bodyLayout.addComponent(mainLayout, 0, 0);
				mainLayout.setStyleName("main");
				bodyLayout.setComponentAlignment(mainLayout,
						Alignment.MIDDLE_LEFT);
				sidebar.setStyleName("sidebar");
				bodyLayout.addComponent(sidebar, 1, 0);
				sidebar.setWidth("150px");
				bodyLayout.setComponentAlignment(sidebar,
						Alignment.MIDDLE_RIGHT);
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

		}
		logger.debug("GeneralView construct done: " + this.getClass());

		usernameLabel.setCaption(aktUser.getUsername());
		// TODO:
		// usernameLabel.setExternalLink
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
