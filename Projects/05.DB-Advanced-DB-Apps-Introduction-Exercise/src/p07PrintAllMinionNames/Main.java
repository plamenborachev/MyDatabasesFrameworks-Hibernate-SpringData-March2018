package p07PrintAllMinionNames;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {

        String selectMinionNamesQuery = "SELECT m.name\n" +
                "FROM minions AS m;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        Statement selectMinionNamesStatement = connection.createStatement();
        ResultSet minionsResult = selectMinionNamesStatement.executeQuery(selectMinionNamesQuery);
        List<String> minions = new LinkedList<>();
        while (minionsResult.next()) {
            minions.add(minionsResult.getString("name"));
        }

        List<String> orderedMinions = new LinkedList<>();
        for (int i = 0; i < minions.size() / 2; i++) {
            orderedMinions.add(minions.get(i));
            orderedMinions.add(minions.get(minions.size() - 1 - i));
        }
        if (minions.size() % 2 == 1) {
            orderedMinions.add(minions.get(minions.size() / 2));
        }
        for (String minion : orderedMinions) {
            System.out.println(minion);
        }

        minionsResult.close();
        selectMinionNamesStatement.closeOnCompletion();
        connection.close();
    }
}
