package com.example.englishwords;

import com.example.englishwords.sql.TopicsSqlController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Button buttonAdd;
    private Stage windowMain;
    private final TopicsSqlController topicsSql = new TopicsSqlController();
    public Button buttonChange;
    private ObservableList<String> topics;
    private static MultipleSelectionModel<String> model;
    @FXML
    private ListView<String> listViewMain;
    @FXML
    private Button buttonPlay;

    private enum Option {
        SELECTED,
        NOT_SELECTED
    }

    public void clickChangeButton() {
        openScene("views/ChangeView.fxml", "Change", Option.SELECTED);
    }

    public void clickAddTopicButton() {
        openScene("views/AddTopicView.fxml", "Add topic", Option.NOT_SELECTED);
    }

    public void clickAddWordsButton() {
        openScene("views/AddWordsView.fxml", "Add words", Option.SELECTED);
    }

    public static String receiveSelectedItem() {
        return model.getSelectedItem();
    }

    public void clickDeleteButton() {
        openScene("views/ModalWindowView.fxml", "Are you sure you want to delete topic?", Option.SELECTED);
    }


    public void clickPlayButton() {
        model = listViewMain.getSelectionModel();
        topicsSql.updateTopicDate(receiveSelectedItem());
        openScene("views/PlayView.fxml", "Play mode", Option.SELECTED);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topics = FXCollections.observableArrayList();
        topicsSql.receiveTopics().forEach(s -> {
            topics.add(s.getName());
        });
        listViewMain.setItems(topics);
    }

    private void openScene(String locationOfScene, String title, Option option) {
        model = listViewMain.getSelectionModel();
        switch (option) {
            case SELECTED: {
                if (model.getSelectedItem() != null) {
                    windowMain = HelloApplication.getStageMain();
                    buttonChange.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        loader.setLocation(getClass().getResource(locationOfScene));
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent change = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle(title);
                    stage.setScene(new Scene(change));
                    stage.showAndWait();
                    windowMain.show();
                    initialize(null, null);
                }
                break;
            }
            case NOT_SELECTED: {
                windowMain = HelloApplication.getStageMain();
                buttonChange.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource(locationOfScene));
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent change = loader.getRoot();
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle(title);
                stage.setScene(new Scene(change));
                stage.showAndWait();
                windowMain.show();
                initialize(null, null);
                break;
            }
        }
    }
}