package com.flowsoft.wanda;

import java.util.List;

import javax.jws.WebService;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

@WebService
public interface UserDetailsService {

	void initTestDb();

	Boolean exist(String username);

	List<WandaUser> findAllUser();

	WandaUser findByUsername(String username);

	List<Article> findAllArticle(String username);

	Article findArticleByTitle(String title);

	String getArticleContentByTitle(String title);

	List<Comment> findAllCommentFor(String articleTitle);

	Boolean createUser(String username, String password, String firstName,
			String lastName);

	void createCategory(WandaUser owner, String name);

	void deleteCategory(String name, String aktuser);

	void createArticle(WandaUser owner, String title, String content);

	String deleteArticle(String title, String aktuser);

	void editArticle(String title, String newContent);

}