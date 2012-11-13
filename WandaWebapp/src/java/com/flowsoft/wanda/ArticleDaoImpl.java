package com.flowsoft.wanda;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@Service("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Article> findAllArticle(String username) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where username = :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public List<Comment> findAllCommentFor(String article) {
		Query query = em.createQuery(
				"SELECT e FROM Comment e where title = :title").setParameter(
				"title", article);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistArticle(Article a) {
		em.persist(a);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistCategory(Category c) {
		em.persist(c);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistTag(Tag c) {
		em.persist(c);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistComment(Comment c) {
		em.persist(c);
	}

	@Override
	public Article findArticleByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where title = :title").setParameter(
				"title", title);
		return (Article) query.getSingleResult();
	}

	@Override
	public String getArticleContentByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where title = :title").setParameter(
				"title", title);
		return ((Article) query.getSingleResult()).getContent();
	}

	@Override
	public void createCategory(WandaUser owner, String name) {
		Category c = new Category(owner, name);
		em.persist(c);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCategory(String name, String aktuser) {
		Category c = (Category) em
				.createQuery(
						"SELECT c FROM Category c where categoryName = :name")
				.setParameter("name", name).getSingleResult();
		List<Article> arc = em
				.createQuery(
						"SELECT a FROM Article a where category = :category")
				.setParameter("category", c).getResultList();

		for (Article a : arc) {
			a.setCategory(null);
			em.merge(a);
		}
		em.flush();
		if (c.getOwner().getUsername().equals(aktuser)) {
			c.setOwner(null);
			em.remove(em.merge(c));
		}
	}

	@Override
	public void createArticle(WandaUser owner, String title, String content) {
		Article a = new Article(owner, title, content);
		em.persist(a);

	}

	@Override
	public String deleteArticle(String title, String aktUser) {
		Article article = findArticleByTitle(title);
		if (article.getOwner().getUsername().equals(aktUser)) {
			em.remove(article);
			return "OK";
		}
		return "Cannot delete";

	}

	@Override
	public void editArticle(String title, String newContent) {
		Article a = findArticleByTitle(title);
		a.setContent(newContent);
		em.merge(a);

	}

}
