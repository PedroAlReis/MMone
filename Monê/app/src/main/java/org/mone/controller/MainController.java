package org.mone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mone.dao.DAOLi;
import org.mone.dao.DAOLiImpl;
import org.mone.dao.DAOPro;
import org.mone.dao.DAOProImpl;
import org.mone.dao.DAOUsoImpl;
import org.mone.dao.DAOUsu;
import org.mone.model.Livro;
import org.mone.model.Processo;
import org.mone.model.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class MainController {
    
    @FXML private StackPane main;
    @FXML private TextField barraUsuField;
    @FXML private TextField barraLivroField;
    @FXML private TextField barraProField;

    @FXML private TableView <Usuario> tabelaUsuario;
    @FXML private TableView <Livro> tabelaLivros;
    @FXML private TableView <Processo> tabelaProcesso;

    @FXML private AnchorPane painelUsuarios;
    @FXML private AnchorPane painelMenu;
    @FXML private AnchorPane painelLivros;
    @FXML private AnchorPane painelProcessos;
    private AnchorPane initialPane;

    @FXML private Button novoUsuario;
    @FXML private Button deletarUsuario;
    @FXML private Button novoLivro;

    @FXML private TableColumn <Usuario, String> nomeUsuTabela;
    @FXML private TableColumn <Usuario, String> nomeUUsuTabela;
    @FXML private TableColumn <Usuario, String> nivelInfoTabela;
    @FXML private TableColumn <Usuario, String> statusUsuTabela;
    @FXML private TableColumn <Usuario, Integer> idUsuTabela;

    @FXML private TableColumn <Livro, String>  tituloLivroTabela;
    @FXML private TableColumn <Livro, String>  autorLivroTabela;
    @FXML private TableColumn <Livro, String>  editoraLivroTabela;
    @FXML private TableColumn <Livro, String> dataCriacaoLivTabela;
    @FXML private TableColumn <Livro, Integer> estoqueLivroTabela;
    @FXML private TableColumn <Livro, Integer> idLivroTabela;

    @FXML private TableColumn <Processo, Integer> idProTabela;
    @FXML private TableColumn <Processo, String> dataProTabela;
    @FXML private TableColumn <Processo, String> nomeProTabela;
    @FXML private TableColumn <Processo, String> tipoProTabela;

    private final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private final FilteredList<Usuario> filteredData = new FilteredList<>(usuarios, p -> true);

    private final ObservableList<Livro> livros = FXCollections.observableArrayList();
    private final FilteredList<Livro> dataFiltered = new FilteredList<>(livros, p -> true);

    private final ObservableList<Processo> processos = FXCollections.observableArrayList();
    private final FilteredList<Processo> dataFilt = new FilteredList<>(processos, p -> true);

    @FXML private void initialize() {

      initialPane = painelMenu;
      show(initialPane);

      idProTabela.setCellValueFactory(new PropertyValueFactory<Processo, Integer>("id"));
      tipoProTabela.setCellValueFactory(new PropertyValueFactory<Processo, String>("type"));
      dataProTabela.setCellValueFactory(new PropertyValueFactory<Processo, String>("dataProcess"));
      nomeProTabela.setCellValueFactory(new PropertyValueFactory<Processo, String>("name"));
      loadProcessos();

      barraProField.textProperty().addListener(
              (observable, oldValue, newValue) -> {
                  dataFilt.setPredicate(processo -> {
                      if (newValue == null || newValue.isEmpty()) {
                          return true;
                      }
                      String term = newValue.toLowerCase();
                      return processo.getBookName().toLowerCase().contains(term)
                              || processo.getType().toLowerCase().contains(term);
                  });
              }
      );

      tituloLivroTabela.setCellValueFactory(new PropertyValueFactory<>("title"));
      autorLivroTabela.setCellValueFactory(new PropertyValueFactory<>("author"));
      editoraLivroTabela.setCellValueFactory(new PropertyValueFactory<>("editor"));
      dataCriacaoLivTabela.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
      estoqueLivroTabela.setCellValueFactory(new PropertyValueFactory<>("stock"));
      idLivroTabela.setCellValueFactory(new PropertyValueFactory<>("id"));
      tabelaLivros.setItems(dataFiltered);
      loadLivros();

      barraLivroField.textProperty().addListener(
       (observable, oldValue, newValue) -> {
           dataFiltered.setPredicate(livro -> {
               if (newValue == null || newValue.isEmpty()) {
                   return true;
               }
               String term = newValue.toLowerCase();
               return livro.getTitle().toLowerCase().contains(term)
                       || livro.getAuthor().toLowerCase().contains(term);
           });
       });

      nomeUsuTabela.setCellValueFactory(new PropertyValueFactory<>("name"));
      nomeUUsuTabela.setCellValueFactory(new PropertyValueFactory<>("username"));
      nivelInfoTabela.setCellValueFactory(new PropertyValueFactory<>("role"));
      statusUsuTabela.setCellValueFactory(new PropertyValueFactory<>("status"));
      idUsuTabela.setCellValueFactory(new PropertyValueFactory<>("id"));
      tabelaUsuario.setItems(filteredData);
      loadUsuarios();

      barraUsuField.textProperty().addListener(
        (observable, oldValue, newValue) -> {
            filteredData.setPredicate(usuario -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String termo = newValue.toLowerCase();
                return usuario.getName().toLowerCase().contains(termo)
                    || usuario.getUsername().toLowerCase().contains(termo);
            });
        }
    );

}


    @FXML private void showUsuarios(ActionEvent event) {
    show(painelUsuarios);

    }

    @FXML private void showMenu(ActionEvent event) {
    show(painelMenu);

    }

    @FXML private void showProcessos(ActionEvent event) {
    show(painelProcessos);

    }

    @FXML private void showLivros(ActionEvent event) {
    show(painelLivros);

    }

    @FXML private void showConfiguracao(ActionEvent event) {
    System.out.println("Configurações clicado!");

    }

    @FXML private void loadUsuarios(){

    DAOUsu daoUso = new DAOUsoImpl();

        try {
            List<Usuario> listaUsuarios = daoUso.getAll();
            usuarios.setAll(listaUsuarios);

        }catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            new Alert(Alert.AlertType.ERROR, "Não foi possível carregar os usuários.").show();
        }
    }

        @FXML private void criarLivro (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/xml/NewBook.fxml")
    );
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Book Registration - Monê");
        stage.setScene(new Scene(root));
        stage.show();
}

    @FXML private void criarUsuario (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/xml/NewUser.fxml")
    );
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("User Registration - Monê");
        stage.setScene(new Scene(root));
        stage.show();
}

    @FXML private void apagarUsuario (ActionEvent event) {
        System.out.println("Deletar clicado!");
    }

    private void show(Node ativo) {
    for (Node p : List.of(painelMenu, painelUsuarios, painelLivros,painelProcessos)) {
        boolean visivel = (p == ativo);
        p.setVisible(visivel);
        p.setManaged(visivel);
    }
}
    
    @FXML private void loadLivros(){

        DAOLi daoLi = new DAOLiImpl();

        try {
            List<Livro> listaLivros = daoLi.getAll();
            livros.setAll(listaLivros);

        }catch (SQLException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            new Alert(Alert.AlertType.ERROR, "Não foi possível carregar os livros.").show();
        }
    }

    @FXML private void loadProcessos(){

    DAOPro daoPro = new DAOProImpl();

    try {
        List<Processo> listaProcessos = daoPro.getAll();
        processos.setAll(listaProcessos);

    } catch (Exception e) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        new Alert(Alert.AlertType.ERROR, "Não foi possível carregar os processos.").show();
      }
    }

    }
    


