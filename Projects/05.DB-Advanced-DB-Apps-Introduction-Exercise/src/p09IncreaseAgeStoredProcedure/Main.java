package p09IncreaseAgeStoredProcedure;

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

        int minionId = Integer.parseInt(reader.readLine());

        String callUpdateAgeProcedure = "CALL usp_get_older(?);";

        String selectMinionQuery = "SELECT m.name, m.age\n" +
                "FROM minions AS m\n" +
                "WHERE m.minion_id = ?;";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        CallableStatement callableStatement = connection.prepareCall(callUpdateAgeProcedure);
        callableStatement.setInt(1, minionId);
        callableStatement.execute();
        callableStatement.close();

        PreparedStatement selectMinionStatement = connection.prepareStatement(selectMinionQuery);
        selectMinionStatement.setInt(1, minionId);
        ResultSet minionResult = selectMinionStatement.executeQuery();

        if (minionResult.next()) {
            String minionName = minionResult.getString("name");
            int minionAge = minionResult.getInt("age");

            System.out.println(minionName + " " + minionAge);
        }

        minionResult.close();
        selectMinionStatement.closeOnCompletion();
        connection.close();
    }
}
