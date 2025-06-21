package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
		
	//===================================================
	
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("testeSeller");
	}
	
	@FXML
	public void onmenuItemDepartmentAction() {
		//loadView("/gui/DepartmentList.fxml");
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onmenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	//===================================================
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {		
	}
	
	
	//===================================================
	
	
	private synchronized void loadView(String absoluteName) {        //método para abrir uma nova tela à partir da tela principal (synchronized garante que nada será interrompido durante a multi thread)
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();                                   //acesso à cena principal
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();  //acesso ao conteúdo do ScrollPane da cena principal
			
			Node mainMenu = mainVBox.getChildren().get(0);          //acessa o primeiro filho do VBox da cena principal
			mainVBox.getChildren().clear();                         //limpa todos os filhos do VBox da cena principal
			mainVBox.getChildren().add(mainMenu);                   //adiciona o mainMenu no VBox da cena principal
			mainVBox.getChildren().addAll(newVBox.getChildren());   //adiciona os filhos do newVBox no VBox da cena principal
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	//=====================================================================================================================================================================================================
	
	
	private synchronized void loadView2(String absoluteName) {        //método para abrir uma nova tela à partir da tela principal (synchronized garante que nada será interrompido durante a multi thread)
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();                                   //acesso à cena principal
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();  //acesso ao conteúdo do ScrollPane da cena principal
			
			Node mainMenu = mainVBox.getChildren().get(0);                  //acessa o primeiro filho do VBox da cena principal
			mainVBox.getChildren().clear();                                 //limpa todos os filhos do VBox da cena principal
			mainVBox.getChildren().add(mainMenu);                           //adiciona o mainMenu no VBox da cena principal
			mainVBox.getChildren().addAll(newVBox.getChildren());           //adiciona os filhos do newVBox no VBox da cena principal
			
			DepartmentListController controller = loader.getController();   //acessa o "Controller" da view (acessa a Classe "DepartmentListController" que é a Classe Controller da view DepartmentList.fxml)
			controller.setDepartmentService(new DepartmentService());       //injeta uma dependência para o atributo "Service" da Classe "DepartmentListController"
			controller.updateTableView();                                   //"chama" o método "updateTableView()" que contém todos os departamentos do banco de dados
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
