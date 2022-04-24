package com.example.englishwords;

import com.example.englishwords.models.MeaningsModel;
import com.example.englishwords.sql.SqlHelper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.englishwords.MainPageController.receiveSelectedItem;

public class AddWordsController implements Initializable {
    public TextField inputTranscription;
    @FXML
    private TableColumn<MeaningsModel, String> columnTranscription;
    private SqlHelper sqlHelper = new SqlHelper();
    public Button buttonAddWords;
    public TextField inputFirstMeaning;
    public TextField inputSecondMeaning;
    @FXML
    private TableView<MeaningsModel> tableView;
    @FXML
    private TableColumn<MeaningsModel, String> columnFirst;
    @FXML
    private TableColumn<MeaningsModel, String> columnSecond;

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
        inputFirstMeaning.requestFocus();
    }

    public void onClickAddWordsButton() {
        if (!inputFirstMeaning.getText().isEmpty() || !inputSecondMeaning.getText().isEmpty()) {
            sqlHelper.addWords(inputFirstMeaning.getText(), inputSecondMeaning.getText(), receiveSelectedItem(),
                    inputTranscription.getText());
            initialize(null, null);
            inputFirstMeaning.setText("");
            inputSecondMeaning.setText("");
            inputTranscription.setText("");
            inputFirstMeaning.requestFocus();
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            onClickAddWordsButton();
        }
    }
}
