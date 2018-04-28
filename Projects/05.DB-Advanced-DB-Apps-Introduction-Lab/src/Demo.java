import connection.Connector;
import entities.DbContext;
import entities.EntityManager;
import entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.initConnection("mysql", "root", "1234", "localhost", "3306", "school");
        Connection connection = Connector.getConnection();
        DbContext em = new EntityManager(connection);

        User user = new User("Joro", 23, new Date());

        em.persist(user);

        em.findFirst(User.class);
    }
}
