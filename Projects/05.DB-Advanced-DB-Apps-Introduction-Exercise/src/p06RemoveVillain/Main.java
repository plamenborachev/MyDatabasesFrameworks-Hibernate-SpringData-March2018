package p06RemoveVillain;

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

        int villainId = Integer.parseInt(reader.readLine());

        String selectVillainQuery = "SELECT v.name\n" +
                "FROM villains AS v\n" +
                "WHERE v.villain_id = ?;";

        String releaseMinionsQuery = "DELETE FROM minions_villains\n" +
                "WHERE villain_id = ?;";

        String removeVillainQuery = "DELETE FROM villains\n" +
                "WHERE villain_id = ?;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(selectVillainQuery);
            preparedStatement.setInt(1, villainId);
            ResultSet villainResult = preparedStatement.executeQuery();

            if (!villainResult.next()) {
                System.out.println("No such villain was found");
                return;
            }

            preparedStatement = connection.prepareStatement(releaseMinionsQuery);
            preparedStatement.setInt(1, villainId);
            int minionsReleased = preparedStatement.executeUpdate();

            String villainName = villainResult.getString("name");

            preparedStatement = connection.prepareStatement(removeVillainQuery);
            preparedStatement.setInt(1, villainId);
            preparedStatement.executeUpdate();

            System.out.println(String.format("%s was deleted", villainName));
            System.out.println(String.format("%d minion(s) released", minionsReleased));

            villainResult.close();
            preparedStatement.closeOnCompletion();
            // If there is no error
            connection.commit();

        } catch (SQLException e) {
            // If there is any error
            System.out.println(e.getMessage());
            connection.rollback();
        }
        connection.close();
    }
}
