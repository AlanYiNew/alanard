package alanard.models.mappers;

import alanard.models.User;

public interface Usermapper {
	
	  void insert(User user);
	  void updatePassword(User user);
}
