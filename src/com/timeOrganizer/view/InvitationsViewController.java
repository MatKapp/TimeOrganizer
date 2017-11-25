package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.model.ActualSessionInfo;
import com.timeOrganizer.model.Invitation;
import com.timeOrganizer.util.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InvitationsViewController {


    @FXML
    public TableView<Invitation> invitationTable;
    @FXML
    private TableColumn<Invitation, String> senderInvitationColumn;
    @FXML
    private TableColumn<Invitation, String> nameInvitationColumn;

    @FXML
    private Button backButton;

    @FXML
    private Label senderInvitationLabel;

    @FXML
    private Label adventureNameInvitationLabel;

    @FXML
    private Label idInvitationLabel;

    @FXML
    private TextField invitationIdToDeleteTextField;

    @FXML
    private TextField invitationReceiverTextField;

    @FXML
    private TextField invitationAdventureIdTextField;

    private Main main;

    public InvitationsViewController() {
    }


    @FXML
    private void sendInvitationButtonClicked() {
//        invitationTable.setItems(main.getInvitationsData());
        if (ActualSessionInfo.getInstance().getFriendsEmailList().contains(invitationReceiverTextField.getText())) {
            DataBaseConnection.addInvitation(invitationReceiverTextField.getText(), Integer.parseInt(invitationAdventureIdTextField.getText()));
            main.setInvitationsData(DataBaseConnection.GetMyInvitations());
            invitationTable.setItems(main.getInvitationsData());
        } else {
            System.out.println("Przykro mi nie masz przyjaciol");
        }


    }


    @FXML
    private void deleteInvitationButtonClicked() {
//        invitationTable.setItems(main.getInvitationsData());
        DataBaseConnection.deleteInvitation(Integer.parseInt(invitationIdToDeleteTextField.getText()));
        main.setInvitationsData(DataBaseConnection.GetMyInvitations());
        invitationTable.setItems(main.getInvitationsData());

    }

    private void showInvitationDetails(Invitation invitation) {
        if (invitation != null) {
            // Fill the labels with info from the person object.
            senderInvitationLabel.setText(invitation.getSender());
            adventureNameInvitationLabel.setText(invitation.getAdventureName());
            idInvitationLabel.setText(invitation.getInvitationId().toString());
        } else {
            // Person is null, remove all the text.
            senderInvitationLabel.setText("");
            adventureNameInvitationLabel.setText("");
            idInvitationLabel.setText("");
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        senderInvitationColumn.setCellValueFactory(cellData -> cellData.getValue().senderProperty());
        nameInvitationColumn.setCellValueFactory(cellData -> cellData.getValue().adventureNameProperty());
        // Clear person details.
        showInvitationDetails(null);

        // Listen for selection changes and show the person details when changed.
        invitationTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showInvitationDetails(newValue));
    }

    /**
     * Called when the user clicks on the delete button.
     */


    @FXML
    private void backButtonClicked() {
        main.showStartPage();
    }


    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        invitationTable.setItems(main.getInvitationsData());
    }
}
