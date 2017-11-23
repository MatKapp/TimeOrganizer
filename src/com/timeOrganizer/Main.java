package com.timeOrganizer;

import java.io.IOException;

import com.timeOrganizer.model.Person;
import com.timeOrganizer.util.DataBaseConnection;
import com.timeOrganizer.view.PersonOverviewController;
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
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Main() {
        personData.add(new Person("Hans", "Muster", "email"));
        personData.add(new Person("Ruth", "Mueller", "email"));
        personData.add(new Person("Heinz", "Kurz", "email"));
        personData.add(new Person("Cornelia", "Meier", "email"));
        personData.add(new Person("Werner", "Meyer", "email"));
        personData.add(new Person("Lydia", "Kunz", "email"));
        personData.add(new Person("Anna", "Best", "email"));
        personData.add(new Person("Stefan", "Meier", "email"));
        personData.add(new Person("Martin", "Mueller", "email"));
        DataBaseConnection dbHandle = new DataBaseConnection();

    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
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
}