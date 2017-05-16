package com.collab.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;




import com.collab.model.Job;
import com.collab.model.User;

@Repository
public class JobDaoImpl implements JobDao{
@Autowired
private SessionFactory sessionFactory;
@Transactional
public void saveJobDetails(Job job) {
Session session=sessionFactory.openSession();
session.save(job);
session.flush();
session.close();

}
@Transactional
public List<Job> getAllJobDetails() {
Session session=sessionFactory.openSession();
Query query=session.createQuery("from Job");
List<Job> jobs=query.list();
session.close();
return jobs;
}

@Transactional
public Job getJobById(int id) {
Session session=sessionFactory.openSession();
Job job=(Job)session.get(Job.class, id);
session.close();
return job;
}

	@Transactional
	public List list() {
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Job> listJob = (List<Job>)
		sessionFactory.getCurrentSession().createCriteria(Job.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listJob;
	}
	
}


