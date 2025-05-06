package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.FuncionarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;

public class controllerMain implements Initializable {

    @FXML
    private Button btApagar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField txtConfirmaSenha;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtNivel;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtcargo;

    @FXML
    void actionApagar(ActionEvent event) {
    	int i = tableFuncionarios.getSelectionModel().getSelectedIndex();
    	if (i== -1) {
    		Alert msg = new Alert(AlertType.ERROR);
    		msg.setHeaderText("ERRO!");
    		msg.setContentText("ERRO! Selecione Funcionário antes de Excluir.");
    		msg.show();
    		
    	}else {
    	Funcionario func = new Funcionario();
    	FuncionarioDAO funcDAO = new FuncionarioDAO();
    	
    	func = tableFuncionarios.getItems().get(i);
    	Alert msg = new Alert(AlertType.CONFIRMATION);
    	msg.setContentText("Deseja Realmente excuir o funcionário " + func.getNome());
    	Optional<ButtonType> resultado = msg.showAndWait();
    	
    	if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
    		funcDAO.delete(func.getCpf());
    		carregarTable();
    		
    	}
    	}
    		
    	}

    

    @FXML
    void actionEditar(ActionEvent event) {
    	
    	

    }

    @FXML
    void actionSalvar(ActionEvent event) {
    if (txtSenha.getText().equals(txtConfirmaSenha.getText())) {

	
    if (txtNome.getText().equals("")||txtcargo.getText().equals("")||txtCpf.getText().equals("")||txtNivel.getText().equals("")||txtSenha.getText().equals("")) {
		Alert msg = new Alert(AlertType.ERROR);
		msg.setHeaderText("ERRO! Informações incompletas");
		msg.setContentText("Falha ao cadastrar! Verique as informações e tente novamente!");
		msg.show();
    } else {
    	Funcionario funcionario = new Funcionario();
    	FuncionarioDAO funcDAO = new FuncionarioDAO();
    	funcionario.setNome(txtNome.getText());
    	funcionario.setCargo(txtcargo.getText());
    	funcionario.setCpf(txtCpf.getText());
    	funcionario.setNivel(txtNivel.getText());
    	funcionario.setSenha(txtSenha.getText());
    	
    	funcDAO.create(funcionario);
    	carregarTable();
    	
    	txtNome.setText("");
    	txtcargo.setText("");
    	txtCpf.setText("");
    	txtNivel.setText("");
    	txtSenha.setText("");
    	txtConfirmaSenha.setText("");
    }
    } else {
		Alert msg = new Alert(AlertType.ERROR);
		msg.setHeaderText("Senhas Diferentes");
		msg.setContentText("Falha ao cadastrar! Você deve digitar as senhas iguais");
		msg.show();	
    	    	
    }
    	
    }

    @FXML
    private TableColumn<Funcionario, String> columnCargo;

    @FXML
    private TableColumn<Funcionario, String> columnCpf;

    @FXML
    private TableColumn<Funcionario, String> columnId;

    @FXML
    private TableColumn<Funcionario, String> columnNivel;

    @FXML
    private TableColumn<Funcionario, String> columnNome;

    @FXML
    private TableColumn<Funcionario, String> columnSenha;

    @FXML
    private TableView<Funcionario> tableFuncionarios;

    
    private ObservableList<Funcionario> funcionarios;
    private FuncionarioDAO funcDAO = new FuncionarioDAO();
    void carregarTable() {
    	
    	funcionarios = FXCollections.observableArrayList(funcDAO.read());
    	
    	columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
    	columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	columnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
    	columnNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
    	
    	tableFuncionarios.setItems(funcionarios);
    
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarTable();
	}
    
    
    
}
