package com.flowsoft.wanda;

import java.util.List;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

public interface ArticleDao {

	public void createArticle(WandaUser owner, String category, String title,
			String content, String[] taglist);

	public com.flowsoft.entities.Category findCategoryByName(String name);

	// --

	public List<ArticleHeader> listArticleHeaders();

	public List<ArticleHeader> getArticlesByUsername(String username);

	public List<ArticleHeader> getRecommendedArticles(String username);

	public List<ArticleHeader> getMostPopularArticles();

	public com.flowsoft.domain.Article getArticleByTitle(String title);

	List<Article> findAllArticle(String username);

	List<Comment> findAllCommentFor(String article);

	void createCategory(String owner, String name);

	void deleteCategory(String name);

	String deleteArticle(String title);

	public void createArticle(Article a);

	public List<Category> findAllExistingCategory();

}
