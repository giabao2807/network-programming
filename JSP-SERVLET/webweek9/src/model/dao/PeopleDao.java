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
import model.bean.People;

public class PeopleDao {
	private Connection connection;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public PeopleDao() {
		connection = ConnectionProvider.getConnection();
	}

	public List<People> getAllPeople() {
		List<People> listpp = new ArrayList<>();
		String query = "Select * from people";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				People people = new People(rs.getString("id"), rs.getString("name"), rs.getString("hobbies"),
						rs.getString("gender"));
				listpp.add(people);
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
		return listpp;
	}

	public People getPeopleByID(String id) {
		String query = "select * from  people  where id = ? ;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new People(rs.getString("id"), rs.getString("name"), rs.getString("hobbies"),
						rs.getString("gender"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean addPeople(String id,String name,String hobbies,String gender) {
		String query = "insert into people(id,name,hobbies,gender) values (?,?,?,?);";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, hobbies);
			pst.setString(4, gender);
			if (pst.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
