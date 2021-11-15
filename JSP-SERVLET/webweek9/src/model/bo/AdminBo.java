package model.bo;

import java.util.List;

import model.bean.Admin;
import model.dao.AdminDao;

public class AdminBo {
	private AdminDao dao;
	public AdminBo() {
		dao = new AdminDao();
	}
	
	public boolean isExist(String username,String password) {
		return dao.isExist(username, password);
	}
	
	public Admin getAdmin(String username, String password) {
		return dao.getAdmin(username, password);
	}
	
	public boolean addAdmin(String username,String password, String id) {
		return dao.addAdmin(username,password,id);
	}
}
