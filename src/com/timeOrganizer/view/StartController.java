package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.model.Person;
import com.timeOrganizer.util.DataBaseConnection;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;

public class StartController {
    @FXML
    private Button UserLogInButton;
    @FXML
    private Button OrganizerLogInButton;
    @FXML
    private TextField UserLogInTextField;
    @FXML
    private Label UserLogInLabel;

    @FXML
    private void UserLogInButtonClicked(Event event) throws IOException {
        System.out.println("Udalo sie");
        DataBaseConnection.Connect();
        Connection conn = DataBaseConnection.getConnection();
        ObservableList<Person> Persons = DataBaseConnection.ExecuteQuery();
        for (Person person : Persons) {
            if (person.getFirstName().replaceAll("\\s", "").equals(UserLogInTextField.getText())) {
                //showPersonOverviewPage(event);
                main.showPersonOverview();
            }
        }
    }

    private Main main;

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table

    }

    private void showPersonOverviewPage(Event event) {
        Stage stage = null;
        Parent root = null;
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("PersonOverview.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
