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
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@Service("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	private EntityManager em;

	Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Article> findAllArticle(String username) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where username = :username")
				.setParameter("username", username);
		return WandaUtil.convertArticleListToDomain(query.getResultList());
	}

	@Override
	public List<Comment> findAllCommentFor(Integer articleID) {
		com.flowsoft.entity.Article actArticle = em.find(
				com.flowsoft.entity.Article.class, articleID);
		Query query = em.createQuery(
				"SELECT e FROM Comment e where commentedArticle = :article")
				.setParameter("article", actArticle);

		return WandaUtil.convertCommentListToDomain(query.getResultList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistArticle(Article a) {
		logger.debug("category value in serverside: " + a);
		// if (em.find(com.flowsoft.entity.Category.class,
		// a.getCategory().getId()) != null) {
		//
		// em.persist(WandaUtil.convertCategoryToEntity(a.getCategory()));
		// em.flush();
		// }
		em.persist(WandaUtil.convertArticleToEntity(a));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistCategory(Category c) {
		em.persist(WandaUtil.convertCategoryToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistTag(Tag c) {
		em.persist(WandaUtil.convertTagToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistComment(Comment c) {

		em.persist(WandaUtil.convertCommentToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Article findArticleByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where title = :title").setParameter(
				"title", title);
		return WandaUtil
				.convertArticleToDomain((com.flowsoft.entity.Article) query
						.getSingleResult());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String getArticleContentByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where title = :title").setParameter(
				"title", title);
		return ((Article) query.getSingleResult()).getContent();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCategory(WandaUser owner, String name) {
		com.flowsoft.entity.Category c = new com.flowsoft.entity.Category(
				WandaUtil.convertWandaUserToEntity(owner), name);
		em.persist(c);
		em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCategory(String name, String aktuser) {
		com.flowsoft.entity.Category c = (com.flowsoft.entity.Category) em
				.createQuery(
						"SELECT c FROM Category c where categoryName = :name")
				.setParameter("name", name).getSingleResult();
		List<com.flowsoft.entity.Article> arc = em
				.createQuery(
						"SELECT a FROM Article a where category = :category")
				.setParameter("category", c).getResultList();

		for (com.flowsoft.entity.Article a : arc) {
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
	@Transactional(propagation = Propagation.REQUIRED)
	public void createArticle(WandaUser owner, String title, String content) {
		com.flowsoft.entity.Article a = new com.flowsoft.entity.Article(
				WandaUtil.convertWandaUserToEntity(owner), title, content);
		em.persist(a);
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteArticle(String title, String aktUser) {
		Article article = findArticleByTitle(title);
		if (article.getOwner().getUsername().equals(aktUser)) {
			em.remove(article);
			return "OK";
		}
		return "Cannot delete";

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editArticle(String title, String newContent) {
		Article a = findArticleByTitle(title);
		a.setContent(newContent);
		em.merge(WandaUtil.convertArticleToEntity(a));
		em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Tag> getListTags() {
		List<com.flowsoft.entity.Tag> tagList = em.createQuery(
				"SELECT t FROM Tag t").getResultList();

		return WandaUtil.convertTagListToDomain(tagList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Category> findAllCategory() {
		// List<com.flowsoft.entity.Category> categoryList = new
		// ArrayList<com.flowsoft.entity.Category>();
		// List<com.flowsoft.entity.WandaUser> userList = em.createQuery(
		// "SELECT w FROM WandaUser w").getResultList();
		//
		// for (com.flowsoft.entity.WandaUser w : userList) {
		// categoryList.addAll(w.getCategories());
		// }

		List<com.flowsoft.entity.Category> categoryList = em.createQuery(
				"SELECT c FROM Category c").getResultList();
		return WandaUtil.convertCategoryListToDomain(categoryList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Category> findCategoryBy(Integer userid) {
		com.flowsoft.entity.WandaUser w = em.find(
				com.flowsoft.entity.WandaUser.class, userid);
		if (w != null) {
			List<com.flowsoft.entity.Category> categoryList = em
					.createQuery(
							"SELECT c FROM Category c where c.owner = :owner")
					.setParameter("owner", w).getResultList();
			return WandaUtil.convertCategoryListToDomain(categoryList);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Article findArticleById(Integer headerId) {
		return WandaUtil.convertArticleToDomain(em.find(
				com.flowsoft.entity.Article.class, headerId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ArticleHeader> findAllArticle() {
		List<Article> articleList = WandaUtil.convertArticleListToDomain(em
				.createQuery("select a from Article a").getResultList());

		List<ArticleHeader> headerList = new ArrayList<ArticleHeader>();
		for (Article a : articleList) {
			headerList.add(new ArticleHeader(a));
		}
		return headerList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteComment(Integer id) {
		com.flowsoft.entity.Comment c = em.find(
				com.flowsoft.entity.Comment.class, id);
		logger.debug("Remove: " + c.getId());
		em.remove(c);
		em.flush();

	}
}
