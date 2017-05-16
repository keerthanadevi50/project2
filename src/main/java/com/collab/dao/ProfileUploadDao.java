package com.collab.dao;

import com.collab.model.ProfilePicture;

public interface ProfileUploadDao {
void save(ProfilePicture profilePicture);
ProfilePicture getProfilePic(String username);
}
