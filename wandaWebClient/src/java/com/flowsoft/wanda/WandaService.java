package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;

/**
 * This class was generated by Apache CXF 2.7.0 2013-01-10T09:59:35.725+01:00
 * Generated source version: 2.7.0
 * 
 */
@WebService(targetNamespace = "http://wanda.flowsoft.com/", name = "UserDetailsService")
// @XmlSeeAlso({ ObjectFactory.class })
public interface WandaService {

	@WebResult(name = "wandaUser", targetNamespace = "")
	@RequestWrapper(localName = "findByUsername", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindByUsername")
	@WebMethod
	@ResponseWrapper(localName = "findByUsernameResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindByUsernameResponse")
	public com.flowsoft.domain.WandaUser findByUsername(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username);

	@WebResult(name = "articleHeaderList", targetNamespace = "")
	@RequestWrapper(localName = "findAllArticleHeader", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllArticleHeader")
	@WebMethod
	@ResponseWrapper(localName = "findAllArticleHeaderResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllArticleHeaderResponse")
	public java.util.List<com.flowsoft.domain.ArticleHeader> findAllArticleHeader();

	@WebResult(name = "articleContent", targetNamespace = "")
	@RequestWrapper(localName = "getArticleContentByTitle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetArticleContentByTitle")
	@WebMethod
	@ResponseWrapper(localName = "getArticleContentByTitleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetArticleContentByTitleResponse")
	public java.lang.String getArticleContentByTitle(
			@WebParam(name = "articleTitle", targetNamespace = "") java.lang.String articleTitle);

	@RequestWrapper(localName = "createArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateArticle")
	@WebMethod
	@ResponseWrapper(localName = "createArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateArticleResponse")
	public void createArticle(
			@WebParam(name = "wandaUser", targetNamespace = "") com.flowsoft.domain.WandaUser wandaUser,
			@WebParam(name = "articleTitle", targetNamespace = "") java.lang.String articleTitle,
			@WebParam(name = "articleContent", targetNamespace = "") java.lang.String articleContent);

	@WebResult(name = "userCategoryList", targetNamespace = "")
	@RequestWrapper(localName = "getUserCategories", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetUserCategories")
	@WebMethod
	@ResponseWrapper(localName = "getUserCategoriesResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetUserCategoriesResponse")
	public java.util.List<com.flowsoft.domain.Category> getUserCategories(
			@WebParam(name = "userid", targetNamespace = "") java.lang.Integer userid);

	@WebResult(name = "articleList", targetNamespace = "")
	@RequestWrapper(localName = "findAllArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllArticle")
	@WebMethod
	@ResponseWrapper(localName = "findAllArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllArticleResponse")
	public java.util.List<com.flowsoft.domain.Article> findAllArticle(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username);

	@RequestWrapper(localName = "editArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.EditArticle")
	@WebMethod
	@ResponseWrapper(localName = "editArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.EditArticleResponse")
	public void editArticle(
			@WebParam(name = "articleTitle", targetNamespace = "") java.lang.String articleTitle,
			@WebParam(name = "newContent", targetNamespace = "") java.lang.String newContent);

	@RequestWrapper(localName = "commitComment", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CommitComment")
	@WebMethod
	@ResponseWrapper(localName = "commitCommentResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CommitCommentResponse")
	public void commitComment(
			@WebParam(name = "comment", targetNamespace = "") com.flowsoft.domain.Comment comment);

	@RequestWrapper(localName = "createUser", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateUser")
	@WebMethod
	@ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateUserResponse")
	public void createUser(
			@WebParam(name = "wandaUser", targetNamespace = "") com.flowsoft.domain.WandaUser wandaUser);

	@WebResult(name = "article", targetNamespace = "")
	@RequestWrapper(localName = "findArticleByHeader", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindArticleByHeader")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByHeaderResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindArticleByHeaderResponse")
	public com.flowsoft.domain.Article findArticleByHeader(
			@WebParam(name = "headerId", targetNamespace = "") java.lang.Integer headerId);

	@WebResult(name = "exist", targetNamespace = "")
	@RequestWrapper(localName = "exist", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.Exist")
	@WebMethod
	@ResponseWrapper(localName = "existResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.ExistResponse")
	public java.lang.Boolean exist(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username);

	@WebResult(name = "commentList", targetNamespace = "")
	@RequestWrapper(localName = "findAllCommentFor", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllCommentFor")
	@WebMethod
	@ResponseWrapper(localName = "findAllCommentForResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllCommentForResponse")
	public java.util.List<com.flowsoft.domain.Comment> findAllCommentFor(
			@WebParam(name = "articleId", targetNamespace = "") java.lang.Integer articleId);

	@RequestWrapper(localName = "deleteCategory", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.DeleteCategory")
	@WebMethod
	@ResponseWrapper(localName = "deleteCategoryResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.DeleteCategoryResponse")
	public void deleteCategory(
			@WebParam(name = "categoryName", targetNamespace = "") java.lang.String categoryName,
			@WebParam(name = "aktUsername", targetNamespace = "") java.lang.String aktUsername);

	@WebResult(name = "wandaUserList", targetNamespace = "")
	@RequestWrapper(localName = "findAllUser", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllUser")
	@WebMethod
	@ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllUserResponse")
	public java.util.List<com.flowsoft.domain.WandaUser> findAllUser();

	@RequestWrapper(localName = "commitArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CommitArticle")
	@WebMethod
	@ResponseWrapper(localName = "commitArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CommitArticleResponse")
	public Article commitArticle(
			@WebParam(name = "article", targetNamespace = "") com.flowsoft.domain.Article article);

	@RequestWrapper(localName = "createCategory", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateCategory")
	@WebMethod
	@ResponseWrapper(localName = "createCategoryResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.CreateCategoryResponse")
	public void createCategory(
			@WebParam(name = "wandaUser", targetNamespace = "") com.flowsoft.domain.WandaUser wandaUser,
			@WebParam(name = "categoryName", targetNamespace = "") java.lang.String categoryName);

	@WebResult(name = "categoryList", targetNamespace = "")
	@RequestWrapper(localName = "findAllExistingCategory", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllExistingCategory")
	@WebMethod
	@ResponseWrapper(localName = "findAllExistingCategoryResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.FindAllExistingCategoryResponse")
	public java.util.List<com.flowsoft.domain.Category> findAllExistingCategory();

	@WebResult(name = "tagList", targetNamespace = "")
	@RequestWrapper(localName = "getAllTag", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetAllTag")
	@WebMethod
	@ResponseWrapper(localName = "getAllTagResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetAllTagResponse")
	public java.util.List<com.flowsoft.domain.Tag> getAllTag();

	@WebResult(name = "article", targetNamespace = "")
	@RequestWrapper(localName = "findArticleByTitle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByTitle")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByTitleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByTitleResponse")
	public java.util.List<com.flowsoft.domain.Article> findArticleByTitle(
			@WebParam(name = "articleTitleSegment", targetNamespace = "") java.lang.String articleTitleSegment,
			@WebParam(name = "isAccurateSearch", targetNamespace = "") java.lang.Boolean isAccurateSearch);

	@RequestWrapper(localName = "editArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.EditArticle")
	@WebMethod
	@ResponseWrapper(localName = "editArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.EditArticleResponse")
	public void editArticle(
			@WebParam(name = "articleId", targetNamespace = "") java.lang.Integer articleId,
			@WebParam(name = "newContent", targetNamespace = "") java.lang.String newContent);

	@RequestWrapper(localName = "removeComment", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.RemoveComment")
	@WebMethod
	@ResponseWrapper(localName = "removeCommentResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.RemoveCommentResponse")
	public void removeComment(
			@WebParam(name = "commentID", targetNamespace = "") java.lang.Integer commentID);

	@WebResult(name = "recentArticles", targetNamespace = "")
	@RequestWrapper(localName = "getRecentArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetRecentArticle")
	@WebMethod
	@ResponseWrapper(localName = "getRecentArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetRecentArticleResponse")
	public java.util.List<com.flowsoft.domain.Article> getRecentArticle(
			@WebParam(name = "articleNumber", targetNamespace = "") java.lang.Integer articleNumber);

	@WebResult(name = "popularArticles", targetNamespace = "")
	@RequestWrapper(localName = "getMostPopularArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetMostPopularArticle")
	@WebMethod
	@ResponseWrapper(localName = "getMostPopularArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetMostPopularArticleResponse")
	public java.util.List<com.flowsoft.domain.Article> getMostPopularArticle(
			@WebParam(name = "articleNumber", targetNamespace = "") java.lang.Integer articleNumber);

	@WebResult(name = "recommendedArticles", targetNamespace = "")
	@RequestWrapper(localName = "getMostRecommendedArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetMostRecommendedArticle")
	@WebMethod
	@ResponseWrapper(localName = "getMostRecommendedArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetMostRecommendedArticleResponse")
	public java.util.List<com.flowsoft.domain.Article> getMostRecommendedArticle(
			@WebParam(name = "articleNumber", targetNamespace = "") java.lang.Integer articleNumber);

	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "deleteArticle", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.DeleteArticle")
	@WebMethod
	@ResponseWrapper(localName = "deleteArticleResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.DeleteArticleResponse")
	public java.lang.String deleteArticle(
			@WebParam(name = "articleId", targetNamespace = "") java.lang.Integer articleId);

	@WebResult(name = "articles", targetNamespace = "")
	@RequestWrapper(localName = "findArticleByAuthor", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByAuthor")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByAuthorResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByAuthorResponse")
	public java.util.List<com.flowsoft.domain.Article> findArticleByAuthor(
			@WebParam(name = "author", targetNamespace = "") java.lang.String author,
			@WebParam(name = "isAccurateSearch", targetNamespace = "") java.lang.Boolean isAccurateSearch);

	@WebResult(name = "articles", targetNamespace = "")
	@RequestWrapper(localName = "findArticleByContent", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByContent")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByContentResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByContentResponse")
	public java.util.List<com.flowsoft.domain.Article> findArticleByContent(
			@WebParam(name = "contentSegment", targetNamespace = "") java.lang.String contentSegment);

	@WebResult(name = "rank", targetNamespace = "")
	@RequestWrapper(localName = "getRank", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetRank")
	@WebMethod
	@ResponseWrapper(localName = "getRankResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.GetRankResponse")
	public java.lang.Double getRank(
			@WebParam(name = "articleId", targetNamespace = "") java.lang.Integer articleId);

	@RequestWrapper(localName = "setRank", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.SetRank")
	@WebMethod
	@ResponseWrapper(localName = "setRankResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.SetRankResponse")
	public void setRank(
			@WebParam(name = "articleId", targetNamespace = "") java.lang.Integer articleId,
			@WebParam(mode = WebParam.Mode.INOUT, name = "newRank", targetNamespace = "") java.lang.Double newRank);

	@WebResult(name = "articles")
	@RequestWrapper(localName = "findArticleByTag", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByTag")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByTagResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByTagResponse")
	List<Article> findArticleByTag(@WebParam(name = "tagName") String tagname);

	@WebResult(name = "articles")
	@RequestWrapper(localName = "findArticleByCategory", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByCategory")
	@WebMethod
	@ResponseWrapper(localName = "findArticleByCategoryResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.wanda.FindArticleByCategoryResponse")
	List<Article> findArticleByCategory(
			@WebParam(name = "categoryName") String categoryName);

	@WebResult(name = "topCategoryList", targetNamespace = "")
	@RequestWrapper(localName = "getTopCategories", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetTopCategories")
	@WebMethod
	@ResponseWrapper(localName = "getTopCategoriesResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetTopCategoriesResponse")
	public java.util.List<com.flowsoft.domain.Category> getTopCategories(
			@WebParam(name = "count", targetNamespace = "") java.lang.Integer userid);

	@WebResult(name = "category", targetNamespace = "")
	@RequestWrapper(localName = "getCategoryByName", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetCategoryByName")
	@WebMethod
	@ResponseWrapper(localName = "getCategoryByNameResponse", targetNamespace = "http://wanda.flowsoft.com/", className = "com.flowsoft.domain.GetCategoryByNameResponse")
	public Category findCategoryByName(
			@WebParam(name = "name", targetNamespace = "") java.lang.String name);

}