package model.bo;

import java.util.List;

import model.bean.People;
import model.dao.PeopleDao;

public class PeopleBo {
	private PeopleDao dao;
	
	public PeopleBo() {
		dao= new PeopleDao();
	}
	
	public People getDetailById(String id) {
		return dao.getPeopleByID(id);
	}
}	
