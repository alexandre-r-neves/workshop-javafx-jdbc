package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	
	//===========================================================
	
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	
	//===========================================================
	
	
	public List<Department> findAll() {              //método que "puxará" todos os departamentos do banco de dados e os enviará para o método "updateTableView()" da Classe "DepartmentListController"
		
		return dao.findAll();
		
	}
}
