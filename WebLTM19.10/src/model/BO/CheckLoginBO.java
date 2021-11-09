package model.BO;

import model.Bean.Account;
import model.DAO.CheckLoginDAO;

public class CheckLoginBO {

	public static Account CheckLogin(String id, String pw) {
		// TODO Auto-generated method stub
		return CheckLoginDAO.CheckLogin(id, pw);
	}

}
