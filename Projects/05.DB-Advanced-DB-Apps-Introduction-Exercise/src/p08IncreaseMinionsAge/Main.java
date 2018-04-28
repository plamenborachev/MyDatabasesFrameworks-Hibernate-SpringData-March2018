package p08IncreaseMinionsAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] minionIds = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String selectMinionQuery = "SELECT m.name\n" +
                "FROM minions AS m\n" +
                "WHERE m.minion_id = ?;";

        String updateMinionQuery = "UPDATE minions AS m\n" +
                "SET m.age = m.age + 1, m.name = ?\n" +
                "WHERE m.minion_id = ?;";

        String selectAllMinionsQuery = "SELECT m.minion_id, m.name, m.age\n" +
                "FROM minions AS m;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        for (int i = 0; i < minionIds.length; i++) {
            int currentId = minionIds[i];

            PreparedStatement selectMinionStatement = connection.prepareStatement(selectMinionQuery);
            selectMinionStatement.setInt(1, currentId);
            ResultSet minionResult = selectMinionStatement.executeQuery();
            minionResult.next();
            String[] minionNames = minionResult.getString("name").split("\\s+");
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < minionNames.length; j++) {
                String currentName = minionNames[j];
                sb.append(currentName.substring(0, 1).toUpperCase())
                        .append(currentName.substring(1))
                        .append(" ");
            }

            String minionNameTitleCase = sb.toString().trim();
            selectMinionStatement.close();

            PreparedStatement preparedStatement = connection.prepareStatement(updateMinionQuery);
            preparedStatement.setString(1, minionNameTitleCase);
            preparedStatement.setInt(2, currentId);
            preparedStatement.executeUpdate();

            preparedStatement.closeOnCompletion();
        }

        Map<Integer, String> minionsByIdName = new LinkedHashMap<>();
        Map<Integer, Integer> minionsByIdAge = new LinkedHashMap<>();

        PreparedStatement preparedStatement = connection.prepareStatement(selectAllMinionsQuery);
        ResultSet minionsResult = preparedStatement.executeQuery();

        while (minionsResult.next()) {
            int minionId = minionsResult.getInt("minion_id");
            String minionName = minionsResult.getString("name");
            int minionAge = minionsResult.getInt("age");

            minionsByIdName.put(minionId, minionName);
            minionsByIdAge.put(minionId, minionAge);
        }



        for (Map.Entry<Integer, String> minion : minionsByIdName.entrySet()) {
            System.out.println(minion.getValue() + " " + minionsByIdAge.get(minion.getKey()));
        }

        minionsResult.close();
        preparedStatement.closeOnCompletion();
        connection.close();
    }
}
