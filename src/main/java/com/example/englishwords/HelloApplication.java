package com.example.englishwords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static Stage stageMain;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Words");
        stage.setScene(scene);
        stageMain = stage;
        stage.show();
    }

    public static Stage getStageMain(){
        return stageMain;
    }

    public static void main(String[] args) {
        launch();
    }
}