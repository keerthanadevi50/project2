package com.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public User registerUser(User user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		return user;
	}

	@Transactional
	public User login(User user) {

		String hql = "from User where username=" + "'" + user.getUsername() + "'   and password = " + "'"
				+ user.getPassword() + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked") 
		User validuser= (User) query.uniqueResult();

		
		return validuser;

	}

	@Transactional
	public User saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	@Transactional
	public List list() {
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
	}

	@Transactional
	public User getbyUsername(String username) {
		String hql = "from User where username =" + "'" + username + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;

	}

	@Transactional
	public User delete(int id) {
		User usertoDelete = new User();
		usertoDelete.setId(id);
		sessionFactory.getCurrentSession().delete(usertoDelete);
		return usertoDelete;
	}

	// TODO Auto-generated method stub
	@Transactional
	public User getUser(int id) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		session.close();
		return user;
	}

	@Transactional
	public User update(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

}
