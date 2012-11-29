package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

@WebService
public interface UserDetailsService {

	void createArticle(@WebParam(name = "owner") WandaUser owner,
			@WebParam(name = "categoryName") String category,
			@WebParam(name = "articleTitle") String title,
			@WebParam(name = "articleContent") String content,
			@WebParam(name = "taglist") String[] taglist);

	void commitArticle(Article a);

	Category findCategoryByName(@WebParam(name = "categoryName") String name);

	@WebResult(name = "categoryList")
	List<Category> findAllExistingCategory();

	//
	@WebResult(name = "exist")
	Boolean exist(@WebParam(name = "username") String username);

	@WebResult(name = "wandaUser")
	WandaUser findUserByUsername(@WebParam(name = "username") String username);

	@WebResult(name = "articleList")
	List<Article> findAllArticleByUsername(
			@WebParam(name = "username") String username);

	@WebResult(name = "articleHeaderList")
	List<ArticleHeader> findAllArticleHeader();

	@WebResult(name = "article")
	com.flowsoft.domain.Article findArticleByTitle(
			@WebParam(name = "articleTitle") String title);

	@WebResult(name = "commentList")
	List<Comment> findAllCommentFor(
			@WebParam(name = "articleTitle") String articleTitle);

	@WebResult(name = "userCreationSuccess")
	Boolean createUser(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "firstName") String firstName,
			@WebParam(name = "lastName") String lastName);

	@WebResult(name = "categoryCreationSuccess")
	Boolean createCategory(@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "categoryName") String name);

	void deleteCategory(@WebParam(name = "categoryName") String name,
			@WebParam(name = "aktUsername") String aktuser);

	String deleteArticle(@WebParam(name = "articleTitle") String title,
			@WebParam(name = "aktUser") String aktuser);

	void editArticle(@WebParam(name = "articleTitle") String title,
			@WebParam(name = "newContent") String newContent);

}