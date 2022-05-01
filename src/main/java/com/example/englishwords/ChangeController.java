package com.example.englishwords;

import com.example.englishwords.models.MeaningsModel;
import com.example.englishwords.sql.SqlHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.englishwords.MainPageController.receiveSelectedItem;

public class ChangeController implements Initializable {
    @FXML
    private TableColumn<MeaningsModel, String> columnTranscription;
    @FXML
    private TextField inputTranslation;
    @FXML
    private TableView<MeaningsModel> tableView;
    @FXML
    private TableColumn<MeaningsModel, String> columnFirst;
    @FXML
    private TableColumn<MeaningsModel, String> columnSecond;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputFirstMeaning;
    @FXML
    private TextField inputSecondMeaning;
    private SqlHelper sqlHelper = new SqlHelper();
    private String oldMeaningOne;
    private String oldMeaningTwo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MeaningsModel> meanings = FXCollections.observableArrayList();
        meanings.addAll(sqlHelper.receiveMeaning(receiveSelectedItem()));
        columnFirst.setCellValueFactory(
                f -> new SimpleStringProperty(f.getValue().getMeaningOne()));
        columnSecond.setCellValueFactory(
                f -> new SimpleStringProperty(f.getValue().getMeaningTwo()));
        columnTranscription.setCellValueFactory(
                f -> new SimpleStringProperty(f.getValue().getTranscription()));
        tableView.setItems(meanings);
        ObservableList<MeaningsModel> meanings1 = tableView.getItems();
        if (meanings1.size() == 0) {
            inputFirstMeaning.setText("");
            inputSecondMeaning.setText("");
            inputTranslation.setText("");
        } else {
            inputFirstMeaning.setText(meanings1.get(0).getMeaningOne());
            inputSecondMeaning.setText(meanings1.get(0).getMeaningTwo());
            inputTranslation.setText(meanings1.get(0).getTranscription());
        }
        inputName.setText(receiveSelectedItem());
    }

    public void onClickChangeButton() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        oldMeaningOne = tableView.getSelectionModel().getSelectedItem().getMeaningOne();
        oldMeaningTwo = tableView.getSelectionModel().getSelectedItem().getMeaningTwo();
        if (!inputName.getText().isEmpty()) {
            if (!inputFirstMeaning.getText().isEmpty() || !inputSecondMeaning.getText().isEmpty()) {
                sqlHelper.changeWords(oldMeaningOne, oldMeaningTwo, inputFirstMeaning.getText(),
                        inputSecondMeaning.getText(), inputName.getText());
                initialize(null, null);
                tableView.getSelectionModel().select(selectedIndex);
                inputFirstMeaning.setText(tableView.getSelectionModel().getSelectedItem().getMeaningOne());
                inputSecondMeaning.setText(tableView.getSelectionModel().getSelectedItem().getMeaningTwo());
                inputTranslation.setText(tableView.getSelectionModel().getSelectedItem().getTranscription());
            }
        }
    }

    public void onSelectRow() {
        inputFirstMeaning.setText(tableView.getSelectionModel().getSelectedItem().getMeaningOne());
        inputSecondMeaning.setText(tableView.getSelectionModel().getSelectedItem().getMeaningTwo());
        inputTranslation.setText(tableView.getSelectionModel().getSelectedItem().getTranscription());
    }

    public void onKeyPressedOnTable(KeyEvent event) {
        switch (event.getCode()) {
            case DELETE: {
                onClickDeleteButton();
                break;
            }
            default: {
                onSelectRow();
            }
        }
    }

    public void onClickDeleteButton() {
        sqlHelper.deleteWords(inputFirstMeaning.getText(), inputSecondMeaning.getText(), inputName.getText());
        initialize(null, null);
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER: {
                onClickChangeButton();
                break;
            }
            case DELETE: {
                onClickDeleteButton();
                break;
            }
        }
    }
}
