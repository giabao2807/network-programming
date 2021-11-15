package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionProvider;
import model.bean.Admin;

public class AdminDao {
	private Connection connection;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public AdminDao() {
		connection = ConnectionProvider.getConnection();
	}

	public List<Admin> getAllAdmin() {
		List<Admin> listad = new ArrayList<>();
		String query = "Select * from admin";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Admin admin = new Admin(rs.getString("username"), rs.getString("password"), rs.getString("id"),
						rs.getInt("key"));
				listad.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listad;
	}

	public Admin getAdmin(String username, String password) {
		String query = "select * from  admin  where username = ? and password =? ;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Admin(rs.getString("username"), rs.getString("password"), rs.getString("id"),
						rs.getInt("key"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isExist(String username, String password) {
		String query = "select * from  admin  where username = ? and password =? ;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
