package controller;

import java.util.ArrayList;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Funcionario;

public class controllerLogin {

	@FXML
	private Button btLogin;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUser;

	@FXML
	void actionLogin(ActionEvent event) {

		if(txtUser.getText().equals("") || txtPassword.getText().equals("")) {
			Alert msg = new Alert(AlertType.ERROR);
			msg.setHeaderText("Falha no Login!");
			msg.setContentText("Usuario ou senha incorretos! Verique as informações e tente novamente!");
			msg.show();
		}else {
			FuncionarioDAO funcDAO = new FuncionarioDAO();
			Funcionario func = new Funcionario();
			ArrayList<Funcionario> funcionarios = new ArrayList<>();
			funcionarios = funcDAO.autenticarUser(txtUser.getText(), txtPassword.getText());

			if(funcionarios.size()!= 0) {
				func = funcionarios.get(0);

				if( txtUser.getText().equals(func.getCpf()) && txtPassword.getText().equals(func.getSenha())) {
					Alert msg = new Alert(AlertType.INFORMATION);
					msg.setHeaderText("Bem vindo!");
					msg.setContentText("Seja bem vindo de volta "+ func.getNome() + "!");
					msg.show();
					Main.changeScreen("main");
				}
			}else {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setHeaderText("Falha no Login!");
				msg.setContentText("Usuario ou senha incorretos! Verique as informações e tente novamente!");
				msg.show();
			}

		}

	}

}
