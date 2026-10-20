package org.mone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class App extends Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {

    primaryStage.setTitle("Monê");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/Login.fxml"));
    Parent loginRoot = loader.load();
    Scene loginScene = new Scene(loginRoot);
    primaryStage.setScene(loginScene);
    primaryStage.show();
    }
}
