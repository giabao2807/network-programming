package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Admin;
import model.bean.People;
import model.bo.AdminBo;
import model.bo.PeopleBo;
import model.dao.AdminDao;
import model.dao.PeopleDao;

public class TestConnection {
	public static void main(String[] args) {
	AdminBo bo = new AdminBo();
	PeopleBo bo1 = new PeopleBo();
	
	Admin a = bo.getAdmin("giabao", "2807");
	System.out.println(a.toString());
	
	People b = bo1.getDetailById(a.getId());
	System.out.println(b.toString());
	
	}
}
