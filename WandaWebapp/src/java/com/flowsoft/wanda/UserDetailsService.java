package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

@WebService
// @SOAPBinding(style = Style.DOCUMENT, use = Use.ENCODED)
public interface UserDetailsService {

	void initTestDb();

	@WebResult(name = "exist")
	Boolean exist(@WebParam(name = "username") String username);

	@WebResult(name = "wandaUserList")
	List<WandaUser> findAllUser();

	@WebResult(name = "wandaUser")
	WandaUser findByUsername(@WebParam(name = "username") String username);

	@WebResult(name = "articleList")
	List<Article> findAllArticle(@WebParam(name = "username") String username);

	@WebResult(name = "article")
	Article findArticleByTitle(@WebParam(name = "articleTitle") String title);

	@WebResult(name = "articleContent")
	String getArticleContentByTitle(
			@WebParam(name = "articleTitle") String title);

	@WebResult(name = "commentList")
	List<Comment> findAllCommentFor(
			@WebParam(name = "articleTitle") String articleTitle);

	@WebResult(name = "creationSucces")
	Boolean createUser(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "firstName") String firstName,
			@WebParam(name = "lastName") String lastName);

	void createCategory(@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "categoryName") String name);

	void deleteCategory(@WebParam(name = "categoryName") String name,
			@WebParam(name = "aktUsername") String aktuser);

	void createArticle(@WebParam(name = "wandaUser") WandaUser owner,
			@WebParam(name = "articleTitle") String title,
			@WebParam(name = "articleContent") String content);

	String deleteArticle(@WebParam(name = "articleTitle") String title,
			@WebParam(name = "aktUser") String aktuser);

	void editArticle(@WebParam(name = "articleTitle") String title,
			@WebParam(name = "newContent") String newContent);

}