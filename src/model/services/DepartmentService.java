package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	
	public List<Department> findAll() {              //método que "puxará" todos os departamentos do banco de dados e os enviará para o método "updateTableView()" da Classe "DepartmentListController"
		
		List<Department> list = new ArrayList<>();   //lista criada somente para testar o retorno dos departamentos sem o banco de dados
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computers"));
		list.add(new Department(3, "Electronics"));
		return list;
		
	}
}
