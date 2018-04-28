package p01InitialSetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {

        String createMinionsTableQuery = "CREATE TABLE IF NOT EXISTS minions(\n" +
                "minion_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(50),\n" +
                "age INT,\n" +
                "town_id INT,\n" +
                "CONSTRAINT fk_minions_towns\n" +
                "FOREIGN KEY (town_id) REFERENCES towns(town_id)\n" +
                ");";

        String createTownsTableQuery = "CREATE TABLE IF NOT EXISTS towns(\n" +
                "town_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(50),\n" +
                "country VARCHAR(50)\n" +
                ");";

        String createVilliansTableQuery = "CREATE TABLE IF NOT EXISTS villains(\n" +
                "villain_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(50),\n" +
                "evilness_factor VARCHAR(10)\n" +
                ");";

        String createMapTableQuery = "CREATE TABLE IF NOT EXISTS minions_villains(\n" +
                "minion_id INT,\n" +
                "villain_id INT,\n" +
                "CONSTRAINT  pk_minions_villains\n" +
                "PRIMARY KEY (minion_id, villain_id),\n" +
                "CONSTRAINT fk_minions\n" +
                "FOREIGN KEY (minion_id) REFERENCES minions(minion_id),\n" +
                "CONSTRAINT fk_villain\n" +
                "FOREIGN KEY (villain_id) REFERENCES villains(villain_id)\n" +
                ");";

        String insertTownsQuery = "INSERT INTO towns(name, country)\n" +
                "VALUES('Sofia', 'Bulgaria')," +
                "('Paris', 'France')," +
                "('London', 'UK')," +
                "('New York', 'USA')," +
                "('Berlin', 'Germany');";

        String insertMnnionsQuery = "INSERT INTO minions(name, age, town_id)\n" +
                "VALUES('Rob', 5, 1)," +
                "('Sturd', 4, 3)," +
                "('Bob', 7, 5)," +
                "('Mat', 2, 4)," +
                "('Buffy', 1, 2);";

        String insertVillainsQuery = "INSERT INTO villains(name, evilness_factor)\n" +
                "VALUES('Grue', 'good')," +
                "('Ivo', 'bad')," +
                "('John', 'evil')," +
                "('Jen', 'bad')," +
                "('Horse', 'super evil');";

        String insertMapQuery = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
                "VALUES(1, 3), (2, 4), (5, 1), (3, 4), (4, 2), (1, 4), (4, 4)," +
                "(5, 5), (4, 5), (3, 5), (2, 5), (1, 5);";

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connection = DriverManager.getConnection(URL, props);

        Statement statement = connection.createStatement();

        statement.executeUpdate(createTownsTableQuery);
        statement.executeUpdate(createMinionsTableQuery);
        statement.executeUpdate(createVilliansTableQuery);
        statement.executeUpdate(createMapTableQuery);

        statement.executeUpdate(insertTownsQuery);
        statement.executeUpdate(insertMnnionsQuery);
        statement.executeUpdate(insertVillainsQuery);
        statement.executeUpdate(insertMapQuery);

        statement.closeOnCompletion();
        connection.close();
    }
}
