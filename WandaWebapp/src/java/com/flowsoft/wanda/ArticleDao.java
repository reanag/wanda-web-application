package com.flowsoft.wanda;

import java.util.List;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

public interface ArticleDao {

	List<Article> findAllArticle(String username);

	// List<Comment> findAllCommentFor(String article);

	Article persistArticle(Article a);

	void persistCategory(Category c);

	void persistTag(Tag t);

	void persistComment(Comment c);

	String getArticleContentByTitle(String title);

	void createCategory(WandaUser owner, String name);

	void deleteCategory(String name, String aktuser);

	void createArticle(WandaUser owner, String title, String content);

	String deleteArticle(Integer id);

	void editArticle(Integer id, String newContent);

	List<Tag> getListTags();

	List<Category> findAllCategory();

	List<Category> findCategoryBy(Integer userid);

	Article findArticleById(Integer headerId);

	List<Comment> findAllCommentFor(Integer articleId);

	List<ArticleHeader> findAllArticle();

	void deleteComment(Integer id);

	List<Article> getMostRecentArticle(Integer numberOfArticles);

	List<Article> getMostPopularArticle(Integer numberOfArticles);

	List<Article> getMostRecommendedArticle(Integer numberOfArticles);

	List<Article> findArticleByTitle(String title, Boolean isAccurate);

	List<Article> findArticleByAuthor(String username, Boolean isAccurate);

	List<Article> findArticleByCategory(String categoryName, Boolean isAccurate);

	List<Article> findArticleByContent(String contentSegment);

	Double getRank(Integer articleId);

	Double setRank(Integer articleId, Double newRank);

	List<Article> findArticleByTag(String tagname);

	void persistUser(WandaUser w);

	Category findCategoryByName(String categoryName);

	List<Category> findTopCategories(Integer count);

	Category findCategoryByArticleId(Integer id);

}
