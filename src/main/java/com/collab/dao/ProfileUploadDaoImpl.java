package com.collab.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.model.ProfilePicture;

@Repository
public class ProfileUploadDaoImpl implements ProfileUploadDao{
	@Autowired
private SessionFactory sessionFactory;
	
	@Transactional
	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profilePicture);
		session.flush();
		session.close();
		
	}
	@Transactional
	public ProfilePicture getProfilePic(String username) {
		Session session=sessionFactory.openSession();
		ProfilePicture profilePic=(ProfilePicture)
				session.get(ProfilePicture.class, username);
		session.close();
		return profilePic;
	}

}