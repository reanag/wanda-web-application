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
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@WebService(name = "wandaService", targetNamespace = "http://com.flowsoft.wanda.WandaServiceImpl", endpointInterface = "com.flowsoft.wanda.WandaService")
public class WandaServiceImpl implements WandaService {

	Logger logger = LoggerFactory.getLogger(WandaServiceImpl.class);

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

	// @Override
	// public List<Comment> findAllCommentFor(String articleTitle) {
	// return articleDao.findAllCommentFor(articleTitle);
	// }

	// @Override
	// public Boolean createUser(String username, String password,
	// String firstName, String lastName) {
	// if (userDao.createUser(username, password, firstName, lastName) != null)
	// {
	// return true;
	// }
	// return false;
	//
	// }

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
	public String deleteArticle(Integer id) {
		return articleDao.deleteArticle(id);

	}

	@Override
	public void editArticle(Integer id, String newContent) {
		articleDao.editArticle(id, newContent);

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
	public Article commitArticle(Article a) {
		return articleDao.persistArticle(a);

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

	@Override
	public List<Article> getRecentArticle(Integer numberOfArticles) {
		List<Article> mostRecentArticle = articleDao
				.getMostRecentArticle(numberOfArticles);

		return mostRecentArticle;
	}

	@Override
	public List<Article> getMostPopularArticle(Integer numberOfArticles) {
		return articleDao.getMostPopularArticle(numberOfArticles);

	}

	@Override
	public List<Article> getMostRecommendedArticle(Integer numberOfArticles) {
		return articleDao.getMostRecommendedArticle(numberOfArticles);

	}

	@Override
	public List<Article> findAllArticle(String username) {
		return articleDao.findAllArticle(username);
	}

	@Override
	public List<Article> findArticleByTitle(String title, Boolean isAccurate) {
		return articleDao.findArticleByTitle(title, isAccurate);
	}

	@Override
	public List<Article> findArticleByAuthor(String username, Boolean isAccurate) {
		return articleDao.findArticleByAuthor(username, isAccurate);
	}

	@Override
	public List<Article> findArticleByContent(String content) {
		return articleDao.findArticleByContent(content);
	}

	@Override
	public Double getRank(Integer articleId) {

		return articleDao.getRank(articleId);
	}

	@Override
	public Double setRank(Integer articleId, Double newRank) {

		return articleDao.setRank(articleId, newRank);
	}

	@Override
	@WebResult(name = "articles")
	public List<Article> findArticleByTag(String tagname) {

		return articleDao.findArticleByTag(tagname);
	}

	@Override
	public void createUser(@WebParam(name = "wandaUser") WandaUser w) {
		articleDao.persistUser(w);

	}

	// @Override
	// public String getCategoryDescriptionByName(String categoryName) {
	// return articleDao.findCategoryByName(categoryName);
	// }

	@Override
	public List<Article> findArticleByCategory(String categoryName) {
		return articleDao.findArticleByCategory(categoryName, true);
	}

	@Override
	@WebResult(name = "topCategoryList")
	public List<Category> getTopCategories(
			@WebParam(name = "count") Integer count) {
		return articleDao.findTopCategories(count);

	}

	@Override
	public Category findCategoryByName(String name) {

		return articleDao.findCategoryByName(name);
	}

}
