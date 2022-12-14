package model;

import java.util.List;

public class LoginLogic {
	
	public boolean execute(User user, List<User> userList) {
		for(User listedUser : userList) {
			if(user.getName().equals(listedUser.getName())) {
				if(user.getPass().equals(listedUser.getPass())){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean executeAdmin(User user, List<User> userList) {
		if(!user.getName().equals("admin")) { return false; }
		for(User listedUser : userList) {
			if(user.getName().equals(listedUser.getName())) {
				if(user.getPass().equals(listedUser.getPass())){
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
