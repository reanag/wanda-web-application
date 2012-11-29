package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.WandaUser;

@Service("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	private EntityManager em;

	Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

	private com.flowsoft.entities.WandaUser user;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createArticle(WandaUser owner, String c, String title,
			String content, String[] taglist) {

		ArrayList<String> tags = new ArrayList<String>();
		for (String s : taglist) {
			tags.add(s);
		}

		com.flowsoft.entities.Category category = findCategoryByName(c);

		com.flowsoft.entities.WandaUser user = new com.flowsoft.entities.WandaUser(
				owner);
		if (category == null) {
			category = new com.flowsoft.entities.Category(user, c);
			em.persist(category);
			em.flush();
			logger.debug("New category created: " + c);
		}

		com.flowsoft.entities.Article a = new com.flowsoft.entities.Article(
				user, title, category, content, tags);
		em.persist(a);
		em.flush();
		logger.debug("New article created: " + title);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createArticle(com.flowsoft.domain.Article a) {
		com.flowsoft.entities.Article art = new com.flowsoft.entities.Article(a);
		if (findCategoryByName(a.getCategory().getCategoryName()) == null) {
			em.persist(art.getCategory());
			em.flush();
		} else {
			art.setCategory(findCategoryByName(a.getCategory()
					.getCategoryName()));
		}

		em.persist(art);
		em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public com.flowsoft.entities.Category findCategoryByName(String categoryName) {
		logger.debug("findCategory by name start");
		Query q = em.createQuery(
				"Select c from Category c where c.categoryName =:categoryName")
				.setParameter("categoryName", categoryName);
		logger.debug("findCategory by name done");
		try {
			com.flowsoft.entities.Category a = (com.flowsoft.entities.Category) q
					.getSingleResult();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ArticleHeader> listArticleHeaders() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT e FROM Article e ");
		List<com.flowsoft.entities.Article> articles = query.getResultList();
		List<ArticleHeader> headerList = new ArrayList<ArticleHeader>();
		for (com.flowsoft.entities.Article a : articles) {
			headerList.add(new ArticleHeader(a.convertToDomain()));
		}
		return headerList;
	}

	@Override
	public List<ArticleHeader> getArticlesByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleHeader> getRecommendedArticles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleHeader> getMostPopularArticles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public com.flowsoft.domain.Article getArticleByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e WHERE e.title =:title").setParameter(
				"title", title);
		com.flowsoft.entities.Article a = (com.flowsoft.entities.Article) query
				.getSingleResult();
		return a.convertToDomain();

	}

	@Override
	public List<Article> findAllArticle(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentFor(String article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCategory(String owner, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategory(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String deleteArticle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<com.flowsoft.domain.Category> findAllExistingCategory() {
		Query query = em.createQuery("SELECT c FROM Category c ");
		List<com.flowsoft.entities.Category> category = query.getResultList();
		List<com.flowsoft.domain.Category> domainCategory = new ArrayList<com.flowsoft.domain.Category>();
		for (com.flowsoft.entities.Category c : category) {
			domainCategory.add(c.convertToDomain());
		}
		return domainCategory;
	}

}
