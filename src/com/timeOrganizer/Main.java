package com.timeOrganizer;

import java.io.IOException;

import com.timeOrganizer.model.Adventure;
import com.timeOrganizer.model.Person;
import com.timeOrganizer.util.DataBaseConnection;
import com.timeOrganizer.view.AdventureOverviewController;
import com.timeOrganizer.view.PersonOverviewController;
import com.timeOrganizer.view.StartController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private ObservableList<Adventure> allAdventureData = FXCollections.observableArrayList();
    private ObservableList<Adventure> myAdventureData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Main() {
        DataBaseConnection dbHandle = new DataBaseConnection();
        personData = DataBaseConnection.GetUsers();
        allAdventureData = DataBaseConnection.GetAdventures();
        myAdventureData = DataBaseConnection.GetMyAdventures();
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }


    public ObservableList<Adventure> getAllAdventureData() {
        return allAdventureData;
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        initRootLayout();
//        showStartPage();
        showAdventureOverview();
    }

    private void showStartPage() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Start.fxml"));
            AnchorPane Start = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(Start);

            // Give the controller access to the main app.
            StartController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showAdventureOverview() {
        try {
            // Load adventure overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AdventureOverview.fxml"));
            AnchorPane adventureOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(adventureOverview);

            // Give the controller access to the main app.
            AdventureOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Adventure> getMyAdventureData() {
        return myAdventureData;
    }
}