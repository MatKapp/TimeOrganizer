package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.util.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class addAdventureController {

    private Main main;

    @FXML
    private Button backButton;

    @FXML
    private Button addAdventureButton;

    @FXML
    private TextField addressAdventureTextField;

    @FXML
    private TextField dateAdventureTextField;

    @FXML
    private TextField nameAdventureTextField;


    @FXML
    private void addAdventureButtonClicked() {

        DataBaseConnection.createAdventure(java.sql.Date.valueOf(dateAdventureTextField.getText()), nameAdventureTextField.getText(), addressAdventureTextField.getText());
        addressAdventureTextField.setText("");
        dateAdventureTextField.setText("");
        nameAdventureTextField.setText("");


    }

    @FXML
    private void backButtonClicked() {
        main.showStartPage();
    }

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
//        adventureTable.setItems(main.getAllAdventureData());
    }
}
