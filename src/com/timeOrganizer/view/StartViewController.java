package com.timeOrganizer.view;

import com.timeOrganizer.Main;
import com.timeOrganizer.model.ActualSessionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartViewController {

    @FXML
    private Button friendsButton;

    @FXML
    private Button adventuresButton;

    @FXML
    private Button logOutButton;

    @FXML
    private void logOutButtonClicked() {
        ActualSessionInfo.getInstance().setActualUserEmail("");
        main.showLogInPage();
    }

    @FXML
    private void adventureCreatorButtonClicked() {
        main.showAdventureCretorOverview();
    }

    @FXML
    private void adventuresButtonClicked() {
        main.showAdventureOverview();
    }

    @FXML
    private void friendsButtonClicked() {
        main.showPersonOverview();
    }

    private Main main;

    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table

    }


}
