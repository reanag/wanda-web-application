package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

@WebService(name = "userDetailsService", targetNamespace = "http://com.flowsoft.wanda.UserDetailsServiceImpl", endpointInterface = "com.flowsoft.wanda.UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

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
	public Boolean exist(String username) {
		return userDao.exist(username);
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
	public com.flowsoft.domain.Article findArticleByTitle(String title) {
		return articleDao.getArticleByTitle(title);
	}

	@Override
	public void deleteCategory(String name, String aktuser) {
		// articleDao.createCategory(name, aktuser);
	}

	@Override
	public void createArticle(WandaUser owner, String category, String title,
			String content, String[] taglist) {

		articleDao.createArticle(owner, category, title, content, taglist);
		logger.debug("create article with title: " + title);
	}

	@Override
	public void commitArticle(Article a) {
		articleDao.createArticle(a);
		logger.debug("create article from domain object with title: "
				+ a.getTitle());
	}

	@Override
	public String deleteArticle(String title, String aktUsername) {
		return "d";
		// return articleDao.deleteArticle(title, aktUsername);

	}

	@Override
	public void editArticle(String title, String newContent) {
		// articleDao.editArticle(title, newContent);

	}

	@Override
	@WebResult(name = "articleHeaderList")
	public List<ArticleHeader> findAllArticleHeader() {
		return articleDao.listArticleHeaders();
	}

	@Override
	@WebResult(name = "wandaUser")
	public WandaUser findUserByUsername(
			@WebParam(name = "username") String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(username).convertToDomain();
	}

	@Override
	@WebResult(name = "articleList")
	public List<Article> findAllArticleByUsername(
			@WebParam(name = "username") String username) {
		return articleDao.findAllArticle(username);
	}

	@Override
	@WebResult(name = "categoryCreationSuccess")
	public Boolean createCategory(
			@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "categoryName") String name) {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	@WebResult(name = "category")
	public Category findCategoryByName(
			@WebParam(name = "categoryName") String name) {
		// TODO Auto-generated method stub
		return articleDao.findCategoryByName(name).convertToDomain();
	}

	@Override
	@WebResult(name = "categoryList")
	public List<Category> findAllExistingCategory() {
		// TODO Auto-generated method stub
		return articleDao.findAllExistingCategory();
	}

}
