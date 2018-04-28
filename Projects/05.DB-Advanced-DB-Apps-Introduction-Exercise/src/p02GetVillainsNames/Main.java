package p02GetVillainsNames;

import java.sql.*;
import java.util.Properties;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {

        String query = "SELECT v.name, count(mv.minion_id) AS MinionsCount\n" +
                "FROM minions_villains AS mv\n" +
                "JOIN villains AS v ON mv.villain_id = v.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING MinionsCount > 3\n" +
                "ORDER BY MinionsCount DESC;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        StringBuilder sb = new StringBuilder();

        while (resultSet.next()) {
            sb.append(String.format("%s %d",
                    resultSet.getString("name"),
                    resultSet.getInt("MinionsCount")))
                    .append(System.lineSeparator());
        }


        System.out.println(sb.toString());

        resultSet.close();
        statement.closeOnCompletion();
        connection.close();
    }
}
