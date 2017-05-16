package com.collab.dao;

import java.util.List;

import com.collab.model.Job;

public interface JobDao {
	
	public Job getJobById(int id);

	public List<Job> getAllJobDetails();

	public void saveJobDetails(Job job);

	public List list();
}
