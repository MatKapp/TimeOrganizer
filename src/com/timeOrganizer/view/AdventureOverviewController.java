package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.model.Adventure;
import com.timeOrganizer.model.Person;
import com.timeOrganizer.util.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdventureOverviewController {


    @FXML
    private Label adventureDateLabel;

    @FXML
    private Label adventureNameLabel;

    @FXML
    private Label adventureAddressLabel;

    @FXML
    private Label adventureIdLabel;

    @FXML
    public TableView<Adventure> adventureTable;
    @FXML
    private TableColumn<Adventure, String> adventureNameColumn;
    @FXML
    private TableColumn<Adventure, String> adventureAddressColumn;

    @FXML
    private Button myAdventuresOverviewButton;

    @FXML
    private Button addAdventureButton;

    @FXML
    private Button deleteAdventureButton;


    @FXML
    private Button allAdventuresOverview;

    @FXML
    private Button backButton;

    @FXML
    private Button adventureCretorButton;


    @FXML
    private TextField adventureIdTextField;

    private Main main;

    public AdventureOverviewController() {
    }


    @FXML
    private void adventureCretorButtonClicked() {
        main.showAdventureCretorOverview();

    }

    @FXML
    private void addAdventureButtonClicked() {
        DataBaseConnection.addAdventure(Integer.parseInt(adventureIdTextField.getText()));
        main.myAdventureData = DataBaseConnection.GetMyAdventures();
        adventureTable.setItems(main.getMyAdventureData());

    }


    @FXML
    private void deleteAdventureButtonClicked() {
        DataBaseConnection.deleteAdventure(Integer.parseInt(adventureIdTextField.getText()));
        main.myAdventureData = DataBaseConnection.GetMyAdventures();
        adventureTable.setItems(main.getMyAdventureData());

    }
    private void showAdventureDetails(Adventure adventure) {
        if (adventure != null) {
            // Fill the labels with info from the person object.
            adventureDateLabel.setText(adventure.getAdventureDateTime().toString());
            adventureNameLabel.setText(adventure.getName());
            adventureAddressLabel.setText(adventure.getAddress());
            adventureIdLabel.setText(adventure.getAdventureId().toString());
        } else {
            // Person is null, remove all the text.
            adventureDateLabel.setText("");
            adventureNameLabel.setText("");
            adventureAddressLabel.setText("");
            adventureIdLabel.setText("");
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        adventureNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        adventureAddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        // Clear person details.
        showAdventureDetails(null);

        // Listen for selection changes and show the person details when changed.
        adventureTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAdventureDetails(newValue));
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = adventureTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            adventureTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a adventure in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void allAdventuresButtonClicked() {
        adventureTable.setItems(main.getAllAdventureData());
    }

    @FXML
    private void backButtonClicked() {
        main.showStartPage();
    }


    @FXML
    private void myAdventuresButtonClicked() {
        adventureTable.setItems(main.getMyAdventureData());
    }

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        adventureTable.setItems(main.getAllAdventureData());
    }
}
