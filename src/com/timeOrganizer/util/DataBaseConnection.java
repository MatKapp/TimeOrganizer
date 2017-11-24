package com.timeOrganizer.util;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Observable;

import com.timeOrganizer.model.ActualSessionInfo;
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

    public static ObservableList<Person> GetFriends() {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        ObservableList<Person> personData = FXCollections.observableArrayList();
        try {
            stmt = connection.createStatement();
            System.out.print(ActualSessionInfo.getInstance().getActualUserEmail().toString());
            String sql = "select Email, FirstName, LastName from dbo.FriendsTable friends left outer join dbo.UsersTable users on friends.email2 = users.Email where friends.Email1 =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());
            rs = preparedStatement.executeQuery();
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
            String sql = "select AdventureId, Date, Name, Address from dbo.ParticipantsTable participant left outer join dbo.AdventuresTable adventures on participant.AdventureId = adventures.Id where participant.UserEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());

            rs = preparedStatement.executeQuery();
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

    public static void addFriend(String newFriendEmail) {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO dbo.FriendsTable VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());
            preparedStatement.setString(2, newFriendEmail);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFriend(String friendEmail) {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "Delete from dbo.FriendsTable WHERE Email1 = ? AND Email2 = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());
            preparedStatement.setString(2, friendEmail);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addAdventure(Integer adventureId) {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO dbo.ParticipantsTable VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());
            preparedStatement.setString(2, adventureId.toString());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createAdventure(Date adventureDate, String adventureName, String adventureAddress) {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO dbo.AdventuresTable VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, adventureDate);
            preparedStatement.setString(2, adventureName);
            preparedStatement.setString(3, adventureAddress);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAdventure(Integer adventureId) {
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "Delete from dbo.ParticipantsTable WHERE UserEmail = ? AND AdventureId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ActualSessionInfo.getInstance().getActualUserEmail());
            preparedStatement.setString(2, adventureId.toString());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public DataBaseConnection() {

        Connect();


    }
}