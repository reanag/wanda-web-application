package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@WebService
// @SOAPBinding(style = Style.DOCUMENT, use = Use.ENCODED)
public interface UserDetailsService {

	@WebResult(name = "exist")
	Boolean exist(@WebParam(name = "username") String username);

	@WebResult(name = "wandaUserList")
	List<WandaUser> findAllUser();

	@WebResult(name = "wandaUser")
	WandaUser findByUsername(@WebParam(name = "username") String username);

	@WebResult(name = "articleContent")
	String getArticleContentByTitle(
			@WebParam(name = "articleTitle") String title);

	// @WebResult(name = "commentList")
	// List<Comment> findAllCommentFor(
	// @WebParam(name = "articleTitle") String articleTitle);

	void createUser(@WebParam(name = "wandaUser") WandaUser w);

	void createCategory(@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "categoryName") String name);

	void deleteCategory(@WebParam(name = "categoryName") String name,
			@WebParam(name = "aktUsername") String aktuser);

	void createArticle(@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "articleTitle") String title,
			@WebParam(name = "articleContent") String content);

	String deleteArticle(@WebParam(name = "articleId") Integer id);

	void editArticle(@WebParam(name = "articleId") Integer id,
			@WebParam(name = "newContent") String newContent);

	@WebResult(name = "tagList")
	public List<Tag> getAllTag();

	@WebResult(name = "categoryList")
	public List<Category> findAllExistingCategory();

	public Article commitArticle(@WebParam(name = "article") Article a);

	@WebResult(name = "userCategoryList")
	public List<Category> getUserCategories(
			@WebParam(name = "userid") Integer userid);

	@WebResult(name = "article")
	public Article findArticleByHeader(
			@WebParam(name = "headerId") Integer headerId);

	@WebResult(name = "commentList")
	public List<Comment> findAllCommentFor(
			@WebParam(name = "articleId") Integer articleId);

	@WebResult(name = "articleHeaderList")
	public List<ArticleHeader> findAllArticleHeader();

	public void commitComment(@WebParam(name = "comment") Comment c);

	public void removeComment(@WebParam(name = "commentID") Integer id);

	@WebResult(name = "recentArticles")
	public List<Article> getRecentArticle(
			@WebParam(name = "articleNumber") Integer numberOfArticles);

	@WebResult(name = "popularArticles")
	public List<Article> getMostPopularArticle(
			@WebParam(name = "articleNumber") Integer numberOfArticles);

	@WebResult(name = "recommendedArticles")
	public List<Article> getMostRecommendedArticle(
			@WebParam(name = "articleNumber") Integer numberOfArticles);

	@WebResult(name = "articleList")
	List<Article> findAllArticle(@WebParam(name = "username") String username);

	@WebResult(name = "article")
	List<Article> findArticleByTitle(
			@WebParam(name = "articleTitleSegment") String title,
			@WebParam(name = "isAccurateSearch") Boolean isAccurate);

	@WebResult(name = "articles")
	List<Article> findArticleByAuthor(@WebParam(name = "author") String title,
			@WebParam(name = "isAccurateSearch") Boolean isAccurate);

	@WebResult(name = "articles")
	List<Article> findArticleByContent(
			@WebParam(name = "contentSegment") String content);

	@WebResult(name = "rank")
	Double getRank(@WebParam(name = "articleId") Integer articleId);

	@WebResult(name = "newRank")
	Double setRank(@WebParam(name = "articleId") Integer articleId,
			@WebParam(name = "newRank") Double newRank);

	@WebResult(name = "articles")
	List<Article> findArticleByTag(@WebParam(name = "tagName") String tagname);

	// @WebResult(name = "description")
	// String getCategoryDescriptionByName(
	// @WebParam(name = "categoryName") String categoryName);

	@WebResult(name = "articles")
	List<Article> findArticleByCategory(
			@WebParam(name = "categoryName") String categoryName);

	@WebResult(name = "topCategoryList")
	public java.util.List<Category> getTopCategories(
			@WebParam(name = "count") Integer userid);
}
