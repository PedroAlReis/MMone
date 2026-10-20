package org.mone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mone.dao.DAOLogin;
import org.mone.dao.DAOLoginImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    public TextField usernameLogin;

    @FXML
    public PasswordField senhaLogin;

    @FXML
    public Button botaoLogin;

    @FXML
    private void fazerLogin(ActionEvent event) {
        String username = usernameLogin.getText();
        String password = senhaLogin.getText();

        DAOLogin daoLogin = new DAOLoginImpl();

        try {
            boolean loginValido = daoLogin.authenticate(username, password);

            if (loginValido) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/Menu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) botaoLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("Login inválido. Por favor, verifique seu nome de usuário e senha.");
            }
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Erro no banco ao autenticar", e);
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Erro ao carregar Menu.fxml", e);
        }
    }
}

    

