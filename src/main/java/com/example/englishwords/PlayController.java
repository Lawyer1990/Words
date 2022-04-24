package com.example.englishwords;

import com.example.englishwords.models.MeaningsModel;
import com.example.englishwords.sql.SqlHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.englishwords.MainPageController.receiveSelectedItem;

public class PlayController implements Initializable {
    public Label inputTop;
    public Label inputMiddle;
    public Label inputBottom;
    public VBox vb;
    public Button buttonGo;
    public CheckBox checkboxReverse;
    public Label wordsLeft;
    private ObservableList<MeaningsModel> meanings = FXCollections.observableArrayList();
    private ObservableList<MeaningsModel> meaningsTwo = FXCollections.observableArrayList();
    private SqlHelper sqlHelper = new SqlHelper();
    private int count;
    private int step = 0;
    private int randomNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (meanings.size() == 0) {
            meanings.addAll(sqlHelper.receiveMeaning(receiveSelectedItem()));
            meaningsTwo = meanings;
            count = meaningsTwo.size();
        }
        inputTop.setMaxWidth(Double.MAX_VALUE);
        inputMiddle.setMaxWidth(Double.MAX_VALUE);
        inputBottom.setMaxWidth(Double.MAX_VALUE);
        inputTop.setAlignment(Pos.CENTER);
        inputMiddle.setAlignment(Pos.CENTER);
        inputBottom.setAlignment(Pos.CENTER);
        wordsLeft.setAlignment(Pos.BOTTOM_RIGHT);
    }

    public void onClickGoButton() {
        wordsLeft.setText(meaningsTwo.size() + " words left");
        Random random = new Random();
        if (step == 0 && count != 0) randomNumber = random.nextInt(count);
        if (checkboxReverse.isSelected()) {
            if (meaningsTwo.size() != 0) {
                if (step == 1) {
                    inputTop.setText(meaningsTwo.get(randomNumber).getMeaningOne());
                    buttonGo.setText("Next");
                    meaningsTwo.remove(randomNumber);
                    count--;
                    step = -1;
                }
                if (step == 0) {
                    inputTop.setText("");
                    inputMiddle.setText(meaningsTwo.get(randomNumber).getTranscription());
                    inputBottom.setText(meaningsTwo.get(randomNumber).getMeaningTwo());
                    buttonGo.setText("Show");
                }
                step++;
            } else {
                inputTop.setText("That's all");
                inputMiddle.setText("");
                inputBottom.setText("");
                initialize(null, null);
                meaningsTwo = meanings;
                count = meaningsTwo.size();
                buttonGo.setText("Go!");
                step = 0;
            }
        } else {
            if (meaningsTwo.size() != 0) {
                if (step == 0) {
                    inputTop.setText(meaningsTwo.get(randomNumber).getMeaningOne());
                    inputMiddle.setText("");
                    inputBottom.setText("");
                    buttonGo.setText("Show");
                }
                if (step == 1) {
                    inputMiddle.setText(meaningsTwo.get(randomNumber).getTranscription());
                    inputBottom.setText(meaningsTwo.get(randomNumber).getMeaningTwo());
                    buttonGo.setText("Next");
                    meaningsTwo.remove(randomNumber);
                    count--;
                    step = -1;
                }
                step++;
            } else {
                inputTop.setText("That's all");
                inputMiddle.setText("");
                inputBottom.setText("");
                initialize(null, null);
                meaningsTwo = meanings;
                count = meaningsTwo.size();
                buttonGo.setText("Go!");
                step = 0;
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onClickGoButton();
        }
    }

    public void onChangeCheckbox() {
        inputTop.setText("");
        inputMiddle.setText("");
        inputBottom.setText("");
        initialize(null, null);
        meaningsTwo = meanings;
        count = meaningsTwo.size();
        buttonGo.setText("Go!");
        step = 0;
    }
}
