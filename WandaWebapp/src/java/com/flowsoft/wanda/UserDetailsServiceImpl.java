package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
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

	// @Override
	// public List<Comment> findAllCommentFor(String articleTitle) {
	// return articleDao.findAllCommentFor(articleTitle);
	// }

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

	@Override
	@WebResult(name = "tagList")
	public List<Tag> getAllTag() {
		return articleDao.getListTags();
	}

	@Override
	public List<Category> findAllExistingCategory() {
		return articleDao.findAllCategory();
	}

	@Override
	public void commitArticle(Article a) {
		articleDao.persistArticle(a);

	}

	@Override
	public List<Category> getUserCategories(Integer userid) {
		return articleDao.findCategoryBy(userid);
	}

	@Override
	public Article findArticleByHeader(Integer headerId) {
		return articleDao.findArticleById(headerId);
	}

	@Override
	public List<Comment> findAllCommentFor(Integer articleId) {
		return articleDao.findAllCommentFor(articleId);
	}

	@Override
	public List<ArticleHeader> findAllArticleHeader() {
		return articleDao.findAllArticle();

	}

	@Override
	public void commitComment(Comment c) {
		articleDao.persistComment(c);

	}

	@Override
	public void removeComment(Integer id) {
		articleDao.deleteComment(id);

	}
}
