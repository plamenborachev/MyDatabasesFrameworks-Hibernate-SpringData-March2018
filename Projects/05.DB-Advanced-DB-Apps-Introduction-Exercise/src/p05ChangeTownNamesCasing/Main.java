package p05ChangeTownNamesCasing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String country = reader.readLine();

        String updateTownsNamesQuery = "UPDATE towns AS t\n" +
                "SET t.name = UPPER(t.name)\n" +
                "WHERE t.country = ?;";

        String selectTownsQuery = "SELECT t.name\n" +
                "FROM towns AS t\n" +
                "WHERE t.country = ?;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        PreparedStatement preparedStatement = connection.prepareStatement(updateTownsNamesQuery);
        preparedStatement.setString(1, country);
        int numberOfTownsAffected = preparedStatement.executeUpdate();

        if (numberOfTownsAffected == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        preparedStatement = connection.prepareStatement(selectTownsQuery);
        preparedStatement.setString(1, country);
        ResultSet selectTownsResult = preparedStatement.executeQuery();

        List<String> townsAffected = new ArrayList<>();
        while (selectTownsResult.next()) {
            townsAffected.add(selectTownsResult.getString("name"));
        }


        System.out.println(String.format("%d town name(s) were affected.", numberOfTownsAffected));
        System.out.println(townsAffected.toString());

        selectTownsResult.close();
        preparedStatement.closeOnCompletion();
        connection.close();
    }
}
