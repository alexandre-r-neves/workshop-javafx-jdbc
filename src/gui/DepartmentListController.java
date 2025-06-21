package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	
	private ObservableList<Department> obsList;
	
	
	//===========================================================
	
	
	@FXML
	public void onBtNewAction() {
		System.out.println("Botão New clicado!");
	}
	
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	
	//===========================================================
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {                            //esse método é o primeiro a ser executado ao abrir a cena
		
		initializeNodes();                                                          //chama e executa o método "initializeNodes()" assim que a cena é aberta	
	}

	
	//===========================================================
	

	private void initializeNodes() {

		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));        //padrão do JavaFX para iniciar o comportamento das colunas ("id" da Classe Department)
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));    //padrão do JavaFX para iniciar o comportamento das colunas ("name" da Classe Department)
		
		Stage stage = (Stage) Main.getMainScene().getWindow();                      //acessa a referência do Stage principal
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());      //faz com que a tebleView fique da altura da cena principal (acompanha a altura da cena principal)
	}
	
	
	public void updateTableView() {                                            //acessa o atributo "serviço", carrega os departamentos e os coloca na "obsList"
		
		if (service == null) {                                                 //"estoura" uma excessão caso esqueça de injetar uma dependência para o atributo "Service" ("Service" não instanciado)
			throw new IllegalStateException("Service was null");
		}
		
		List<Department> list = service.findAll();                             //armazena todos os departamentos que o método "findAll()" da Classe DepartmentService "puxou" do banco de dados 
		obsList = FXCollections.observableArrayList(list);                     //instancia o obsList e carrega os departamentos da lista "list"
		tableViewDepartment.setItems(obsList);                                 //carrega os departamentos da "obsList" na tableViewDepartment e os mostra na tela
		
	}
}
