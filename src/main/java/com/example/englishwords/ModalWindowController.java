package com.example.englishwords;

import com.example.englishwords.sql.TopicsSqlController;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static com.example.englishwords.MainPageController.receiveSelectedItem;

public class ModalWindowController {
    public Button buttonNo;
    private final TopicsSqlController topicsSql = new TopicsSqlController();

    public void onClickButtonNo() {
        Stage stage = (Stage) buttonNo.getScene().getWindow();
        stage.close();
    }

    public void onClickButtonYes() {
        topicsSql.deleteTopic(receiveSelectedItem());
        Stage stage = (Stage) buttonNo.getScene().getWindow();
        stage.close();
    }
}
