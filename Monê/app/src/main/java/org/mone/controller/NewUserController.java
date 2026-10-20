package org.mone.controller;

import java.sql.SQLException;

import org.mone.dao.DAOUsoImpl;
import org.mone.dao.DAOUsu;
import org.mone.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewUserController {

@FXML private Button cNUsuario;

@FXML private TextField cNameField;

@FXML private TextField cUsernameField;

@FXML private ComboBox<Integer> cRoleBox;

@FXML private TextField cPasswordField;

@FXML private void initialize() {
    cRoleBox.getItems().addAll(1, 2, 3); 

}

@FXML private void inserirUsuario (ActionEvent event){

    String name = cNameField.getText();
    String username = cUsernameField.getText();
    String password = cPasswordField.getText();
    Integer role = cRoleBox.getValue();

    try {
        DAOUsu daoUsu = new DAOUsoImpl();
        Usuario novoUsuario = new Usuario(0, name, username, password, "ativo", role);
        daoUsu.insert(novoUsuario);
        System.out.println("User Inserted.");
    } catch (SQLException e) {
        System.err.println("Error inserting user: " + e.getMessage());
    }

}

}

