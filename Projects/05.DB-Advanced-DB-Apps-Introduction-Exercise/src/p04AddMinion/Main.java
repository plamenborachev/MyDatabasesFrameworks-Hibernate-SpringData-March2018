package p04AddMinion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws IOException, SQLException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] minionTokens = reader.readLine().split("\\s+");
        String minionName = minionTokens[1];
        int minionAge = Integer.parseInt(minionTokens[2]);
        String townName = minionTokens[3];

        String[] villainTokens = reader.readLine().split("\\s+");
        String villainName = villainTokens[1];

        String selectTownQuery = "SELECT t.town_id\n" +
                "FROM towns AS t\n" +
                "WHERE t.name = ?;";

        String insertTownQuery = "INSERT INTO towns(name)\n" +
                "VALUE(?);";

        String selectVillainQuery = "SELECT v.villain_id\n" +
                "FROM villains AS v\n" +
                "WHERE v.name = ?;";

        String insertVillainQuery = "INSERT INTO villains(name, evilness_factor)\n" +
                "VALUE(?, 'evil');";

        String insertMinionQuery = "INSERT INTO minions(name, age, town_id)\n" +
                "VALUE(?, ?, ?);";

        String selectMinionQuery = "SELECT *\n" +
                "FROM minions AS m\n" +
                "WHERE m.name = ? AND m.age = ? AND m.town_id = ?;";

        String addToMapTableQuery = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
                "VALUE(?, ?);";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(selectTownQuery);
            preparedStatement.setString(1, townName);
            ResultSet townResult = preparedStatement.executeQuery();

            if (!townResult.next()) {
                preparedStatement = connection.prepareStatement(insertTownQuery);
                preparedStatement.setString(1, townName);
                preparedStatement.executeUpdate();
                System.out.println(String.format("Town %s was added to the database.", townName));
            }

            preparedStatement = connection.prepareStatement(selectTownQuery);
            preparedStatement.setString(1, townName);
            townResult = preparedStatement.executeQuery();
            townResult.next();
            int town_id = townResult.getInt("town_id");

            preparedStatement = connection.prepareStatement(selectVillainQuery);
            preparedStatement.setString(1, villainName);
            ResultSet villainResult = preparedStatement.executeQuery();

            if (!villainResult.next()) {
                preparedStatement = connection.prepareStatement(insertVillainQuery);
                preparedStatement.setString(1, villainName);
                preparedStatement.executeUpdate();
                System.out.println(String.format("Villain %s was added to the database.", villainName));
            }

            preparedStatement = connection.prepareStatement(selectVillainQuery);
            preparedStatement.setString(1, villainName);
            villainResult = preparedStatement.executeQuery();
            villainResult.next();
            int villain_id = villainResult.getInt("villain_id");

            preparedStatement = connection.prepareStatement(insertMinionQuery);
            preparedStatement.setString(1, minionName);
            preparedStatement.setInt(2, minionAge);
            preparedStatement.setInt(3, town_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(selectMinionQuery);
            preparedStatement.setString(1, minionName);
            preparedStatement.setInt(2, minionAge);
            preparedStatement.setInt(3, town_id);
            ResultSet minionResult = preparedStatement.executeQuery();
            minionResult.next();
            int minion_id = minionResult.getInt("minion_id");

            preparedStatement = connection.prepareStatement(addToMapTableQuery);
            preparedStatement.setInt(1, minion_id);
            preparedStatement.setInt(2, villain_id);
            preparedStatement.executeUpdate();
            System.out.println(String.format("Successfully added %s to be minion of %s.",
                    minionName, villainName));


            townResult.close();
            villainResult.close();
            minionResult.close();
            preparedStatement.closeOnCompletion();
            // If there is no error
            connection.commit();


        } catch (SQLException se) {
            // If there is any error
            System.out.println(se.getMessage());
            connection.rollback();
        }
        connection.close();
    }
}
