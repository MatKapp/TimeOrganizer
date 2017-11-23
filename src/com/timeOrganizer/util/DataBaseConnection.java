package com.timeOrganizer.util;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.*;

public class DataBaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public DataBaseConnection() {

        String connectionString = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=TimeOrganizer_db;user=Mateusz;password=password;";
        // Declare the JDBC objects.
        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM dbo.UserTable";
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String FirstName = rs.getString(1);
                String LastName = rs.getString(2);

                //Display values
                System.out.print("FirstName: " + FirstName);
                System.out.print(", LastName: " + LastName);

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}