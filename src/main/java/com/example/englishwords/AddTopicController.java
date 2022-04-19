package com.example.englishwords;

import com.example.englishwords.sql.SqlHelper;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddTopicController {

    private final SqlHelper sqlHelper = new SqlHelper();
    public TextField inputField;

    public void onClickEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onClickAddTopicButton();
        }
    }

    public void onClickAddTopicButton() {
        sqlHelper.addTopic(inputField.getText());
        Stage stage = (Stage) inputField.getScene().getWindow();
        stage.close();
    }
}
