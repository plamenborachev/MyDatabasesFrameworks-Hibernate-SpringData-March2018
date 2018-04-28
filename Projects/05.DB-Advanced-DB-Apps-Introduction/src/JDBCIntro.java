import java.sql.*;

public class JDBCIntro {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbcdemo?createDatabaseIfNotExist=true";

    private static final String USER = "root";

    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
            System.out.println("The connection is successful! Well done bro!");

            try(Statement statement = connection.createStatement()){
                String query = "CREATE TABLE IF NOT EXISTS users(\n" +
                        "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "username VARCHAR(20),\n" +
                        "`password` VARCHAR(20)\n" +
                        ");";
                statement.executeUpdate(query);
            }

//            try(Statement statement = connection.createStatement()){
//                String query = "INSERT INTO users(username, `password`)\n" +
//                        "VALUES('Ivan', '123'), ('Gosho', 'abc');";
//                statement.executeUpdate(query);
//            }

            if(login(connection, "Ivan", "123")){
                System.out.println("Ivan logged in successfully!");
            }

            if(login(connection, "Gosho", "123")){
                System.out.println("Ivan logged in successfully!");
            }

            if(login(connection, " ' or 1=1 #", "whatever")){
                System.out.println("Hacker logged in successfully!");
            }

            if(login(connection, "'; INSERT INTO users(username, passord) VALUES('hacker','') #", "whatever")){
                System.out.println("Hacker not logged in successfully!");
            } else {
                System.out.println("Hacker not logged in, but has brand new user!");
            }
        }
    }

    private static boolean login(Connection connection, String user, String password) throws SQLException {
        String query = "SELECT COUNT(id)" +
                         "FROM users " +
                        "WHERE username = '" + user + "' AND password = '" + password + "'";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int usersCount = resultSet.getInt(1);
            return usersCount > 0;
        }
    }
}
