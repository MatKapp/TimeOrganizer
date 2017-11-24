package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.model.Person;
import com.timeOrganizer.util.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;

    @FXML
    private Button allPersonsButton;

    @FXML
    private Button myPersonsButton;

    @FXML
    private Button backButton;

    private Main main;

    public PersonOverviewController() {

    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            emailLabel.setText(person.getEmailAddress());
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailLabel.setText("");
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    @FXML
    private void backButtonClicked() {
        main.showStartPage();
    }

    @FXML
    private void allPersonsButtonClicked() {
        personTable.setItems(main.getPersonData());
    }

    @FXML
    private void myPersonsButtonClicked() {
        personTable.setItems(main.getFriendsData());
    }


    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        personTable.setItems(main.getPersonData());
    }
}
