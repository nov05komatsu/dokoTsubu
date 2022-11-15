package model;

import java.util.List;

import dao.UserDAO;

public class GetUserListLogic {
	public List<User> execute() {
		UserDAO dao = new UserDAO();
		List<User> userList = dao.findAll();
		return userList;
	}
}
