
package org.mone.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import org.mone.dao.DAOLi;
import org.mone.dao.DAOLiImpl;
import org.mone.model.Livro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewBookController {

    @FXML private TextField cTitle;
    @FXML private TextField cAuthor;
    @FXML private DatePicker cCreationDate;
    @FXML private TextField cEditor;
    @FXML private TextField cStock;
    @FXML private Button cNLivro;

    @FXML
    private void initialize() {
        
    }

    @FXML
    private void inserirLivro (ActionEvent event) {

        String title = cTitle.getText();
        String author = cAuthor.getText();
        LocalDate creationDate = cCreationDate.getValue();
        String editor = cEditor.getText();

        try {
            int stock = Integer.parseInt(cStock.getText());
            DAOLi daoLi = new DAOLiImpl();
            Livro novoLivro = new Livro(0, title, author, creationDate, editor, stock);
            daoLi.insert(novoLivro);
            System.out.println("Book Inserted.");
            } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + cStock.getText());
            } catch (SQLException e) {
            System.err.println("Error inserting book: " + e.getMessage());
        }
    }
}