package model;

import java.util.List;

public class RegisterLogic {
	public boolean execute(String name, List<User> userList) {
		for(User listedUser : userList) {
			if(name.equals(listedUser.getName())) {
				return false;
			}
		}
		return true;
	}
}
