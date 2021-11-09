package model.DAO;

import java.util.ArrayList;
import java.util.List;

import model.Bean.Account;

public class CheckLoginDAO {

	public static Account CheckLogin(String id, String pw) {
		//KetNoi CSDL
		
		//Hard Code!!
		List<Account> l = new ArrayList<Account>();
		Account a1 = new Account();
		a1.setId("pmtuan");
		a1.setPass("123456");
		a1.setName("Minh Tuan Pham");
		a1.setSex("Nam");
		a1.setSt("Vui tinh!!");
		
		Account a2 = new Account();
		a2.setId("cpheo");
		a2.setPass("123");
		a2.setName("Chi Pheo");
		a2.setSex("Nam");
		a2.setSt("Nhau len la quay!!");
		
		Account a3 = new Account();
		a3.setId("tno");
		a3.setPass("chaohanh");
		a3.setName("Thi No");
		a3.setSex("Nu");
		a3.setSt("Yeu tham Chi Pheo!!");
		
		l.add(a1);
		l.add(a2);
		l.add(a3);
		
		for (Account a : l) {
			if (a.getId().equals(id) && a.getPass().equals(pw)){
				return a;
			}
		}
		
		return null;
	}

}
