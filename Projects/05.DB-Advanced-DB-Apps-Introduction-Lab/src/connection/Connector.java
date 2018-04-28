package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {

    private static Connection connection = null;

    public static void initConnection(String driver, String username, String password, String host,
                                      String port, String dbname) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        connection = DriverManager.getConnection("jdbc:" + driver + "://" + host + ":" + port
                                                        + "/" + dbname, connectionProps);

    }

    public static Connection getConnection(){
        return connection;
    }
}
