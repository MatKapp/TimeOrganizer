package com.timeOrganizer.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.timeOrganizer.model.Adventure;
import com.timeOrganizer.model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class DataBaseConnection {

    public static Connection connection;


    public static void Connect() {
        String connectionString = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=TimeOrganizer_db;user=Mateusz;password=password;";
        // Declare the JDBC objects.

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        DataBaseConnection.connection = connection;
    }

    public static ObservableList<Person> GetUsers() {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<Person> personData = FXCollections.observableArrayList();
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM dbo.UsersTable";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                personData.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3)));
                String Email = rs.getString(1);
                String FirstName = rs.getString(2);
                String LastName = rs.getString(3);
                //Display values
                System.out.print("Email: " + Email);
                System.out.print("FirstName: " + FirstName);
                System.out.print(", LastName: " + LastName);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personData;
    }


    public static ObservableList<Adventure> GetAdventures() {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<Adventure> adventureData = FXCollections.observableArrayList();
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM dbo.AdventuresTable";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Adventure adventure = new Adventure(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4));
                int AdventureId = rs.getInt(1);
                Date AdventureDate = rs.getDate(2);
                String Name = rs.getString(3);
                String Address = rs.getString(4);
                //Display values
                System.out.print("AdventureId: " + AdventureId);
                System.out.print("AdventureDate: " + AdventureDate.toString());
                System.out.print(", Name: " + Name);
                System.out.print(", Address: " + Address);
                adventureData.add(adventure);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adventureData;
    }


    public static ObservableList<Adventure> GetMyAdventures() {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<Adventure> myAdventureData = FXCollections.observableArrayList();
        try {
            stmt = connection.createStatement();
            String sql = "select AdventureId, Date, Name, Address from dbo.ParticipantsTable participant left outer join dbo.AdventuresTable adventures on participant.AdventureId = adventures.Id where participant.UserEmail = 'Kapiszewski@gmail.com'";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Adventure adventure = new Adventure(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4));
                int AdventureId = rs.getInt(1);
                Date AdventureDate = rs.getDate(2);
                String Name = rs.getString(3);
                String Address = rs.getString(4);
                //Display values
                System.out.print("AdventureId: " + AdventureId);
                System.out.print("AdventureDate: " + AdventureDate.toString());
                System.out.print(", Name: " + Name);
                System.out.print(", Address: " + Address);
                myAdventureData.add(adventure);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdventureData;
    }

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public DataBaseConnection() {

        Connect();


    }
}