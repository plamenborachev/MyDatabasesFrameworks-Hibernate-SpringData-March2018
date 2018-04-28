package p03GetMinionNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int villainId = Integer.parseInt(reader.readLine());

        String selectVillainByIdQuery = "SELECT *\n" +
                "FROM villains AS v\n" +
                "WHERE v.villain_id = ?;";

        String selectVillainMinionsQuery = "SELECT v.name AS 'villain', m.name AS 'minion', m.age\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv ON v.villain_id = mv.villain_id\n" +
                "JOIN minions AS m ON mv.minion_id = m.minion_id\n" +
                "WHERE v.villain_id = ?;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        PreparedStatement preparedStatement = connection.prepareStatement(selectVillainByIdQuery);
        preparedStatement.setInt(1, villainId);
        ResultSet villainResult = preparedStatement.executeQuery();

        if (!villainResult.next()) {
            System.out.println(String.format("No villain with ID %d exists in the database.", villainId));
            return;
        }

        String villainName = villainResult.getString("name");

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Villain: %s", villainName)).append(System.lineSeparator());

        preparedStatement = connection.prepareStatement(selectVillainMinionsQuery);
        preparedStatement.setInt(1, villainId);
        ResultSet servingMinionsResult = preparedStatement.executeQuery();

        int count = 1;

        while (servingMinionsResult.next()) {
            String minionName = servingMinionsResult.getString("minion");
            int minionAge = servingMinionsResult.getInt("age");
            sb.append(String.format("%d. %s %d", count++, minionName, minionAge))
                    .append(System.lineSeparator());
        }

        System.out.println(sb.toString().trim());

        villainResult.close();
        servingMinionsResult.close();
        preparedStatement.closeOnCompletion();
        connection.close();
    }
}

