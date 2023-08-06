package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVWriter;

public class Main {

    public static void main(String[] args) {
        String host = "192.162.77.162";
        String port = "56789";
        String username = "LDAPN";
        String password = "MO^%$*)(UJ";
        String url = "jdbc:sap://" + host + ":" + port;

        String schema = "HGHBJ";
        String tableName = "MKPLDR";
        String query = "SELECT * FROM \"" + schema + "\".\"" + tableName + "\"";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection to HANA successful!");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (!resultSet.next()) {
                    System.out.println("The table is empty.");
                    return;
                }

                int columnCount = resultSet.getMetaData().getColumnCount();
                try (CSVWriter writer = new CSVWriter(new FileWriter("/root/output.csv"))) {
                    // Write the column names to the CSV file
                    String[] columnNames = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames[i - 1] = resultSet.getMetaData().getColumnName(i);
                    }
                    writer.writeNext(columnNames);

                    // Write the data rows to the CSV file
                    do {
                        String[] rowData = new String[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            rowData[i - 1] = resultSet.getString(i);
                        }
                        writer.writeNext(rowData);
                    } while (resultSet.next());

                    System.out.println("Data stored in /root/output.csv file.");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                System.err.println("Error executing query: " + query);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}

