package com.flowsoft.wanda;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flowsoft.domain.WandaUser;

@Service("userDao")
public class WandaUserDaoImpl implements WandaUserDao {

	private EntityManager em;
	Logger logger = LoggerFactory.getLogger(WandaUserDaoImpl.class);

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	@Cacheable(value = "wandaUser")
	@Transactional(propagation = Propagation.REQUIRED)
	public WandaUser findUserByName(String username) {
		logger.debug("Cache miss: " + username);
		if (username != null) {

			Query query = em.createQuery(
					"SELECT e FROM WandaUser e where username = :username")
					.setParameter("username", username);
			return WandaUtil
					.convertWandaUserToDomain((com.flowsoft.entity.WandaUser) query
							.getSingleResult());

		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public WandaUser createUser(String username, String password,
			String firstname, String lastname) {
		com.flowsoft.entity.WandaUser w = new com.flowsoft.entity.WandaUser(
				username, password, firstname, lastname);
		em.persist(w);
		em.flush();
		return WandaUtil.convertWandaUserToDomain(w);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public WandaUser createUser(WandaUser w) {
		em.persist(WandaUtil.convertWandaUserToEntity(w));
		em.flush();
		return w;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<WandaUser> findAllUser() {
		return WandaUtil.convertWandaUserListToDomain(em.createQuery(
				"Select e From WandaUser e").getResultList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean exist(String username) {
		if (findUserByName(username) != null) {
			return true;
		}
		return false;
	}
}
