package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@WebService(endpointInterface = "com.flowsoft.wanda.UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private WandaUserDao userDao;
	@Autowired
	private ArticleDao articleDao;

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public WandaUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(WandaUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void initTestDb() {
		// Create users:
		WandaUser w = new WandaUser("erwin", "1234", "Erwin", "Booley");
		WandaUser w2 = new WandaUser("carl", "1234", "Carl", "Bricks");
		userDao.createUser(w);
		userDao.createUser(w2);
		// Create categories:
		Category c = new Category(w2, "Soup");
		Category c2 = new Category(w2, "Meat-food");
		Category c3 = new Category(w, "Cake");

		articleDao.persistCategory(c);
		articleDao.persistCategory(c2);
		articleDao.persistCategory(c3);

		// Create tags:
		Tag t = new Tag("Cake");
		Tag t2 = new Tag("Recipe");
		Tag t3 = new Tag("Chocolate");
		Tag t4 = new Tag("Milk");
		Tag t5 = new Tag("Water");

		// Create articles:
		Article a = new Article(w, "Chocolate cake",
				"Recipe of chocolate cake, and nommy pictures");
		Article a2 = new Article(w, "Tomato soup", "Recipe of tomato soup");

		// Set article category:
		a.setCategory(c3);
		a2.setCategory(c);

		articleDao.persistArticle(a);
		articleDao.persistArticle(a2);

		// Tagging articles:
		t.setTaggedArticle(a);
		t.setTaggedArticle(a2);

		articleDao.persistTag(t);
		articleDao.persistTag(t2);
		articleDao.persistTag(t3);
		articleDao.persistTag(t4);
		articleDao.persistTag(t5);

		// Create comments:
		Comment com = new Comment(w2, a, "jummy");
		com.setCommentedArticle(a);
		Comment com2 = new Comment(w, a, ":)");
		com2.setCommentedArticle(a);

		// Persist comments:
		articleDao.persistComment(com);
		articleDao.persistComment(com2);
	}

	@Override
	public Boolean exist(String username) {
		return userDao.exist(username);
	}

	@Override
	public List<WandaUser> findAllUser() {
		return userDao.findAllUser();
	}

	@Override
	public WandaUser findByUsername(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	public List<Article> findAllArticle(String username) {
		return articleDao.findAllArticle(username);
	}

	@Override
	public List<Comment> findAllCommentFor(String articleTitle) {
		return articleDao.findAllCommentFor(articleTitle);
	}

	@Override
	public Boolean createUser(String username, String password,
			String firstName, String lastName) {
		if (userDao.createUser(username, password, firstName, lastName) != null) {
			return true;
		}
		return false;

	}

	@Override
	public Article findArticleByTitle(String title) {
		return articleDao.findArticleByTitle(title);
	}

	@Override
	public String getArticleContentByTitle(String title) {
		return articleDao.getArticleContentByTitle(title);
	}

	@Override
	public void createCategory(WandaUser owner, String name) {
		articleDao.createCategory(owner, name);

	}

	@Override
	public void deleteCategory(String name, String aktuser) {
		articleDao.deleteCategory(name, aktuser);
	}

	@Override
	public void createArticle(WandaUser owner, String title, String content) {
		articleDao.createArticle(owner, title, content);
	}

	@Override
	public String deleteArticle(String title, String aktUsername) {
		return articleDao.deleteArticle(title, aktUsername);

	}

	@Override
	public void editArticle(String title, String newContent) {
		articleDao.editArticle(title, newContent);

	}
}
