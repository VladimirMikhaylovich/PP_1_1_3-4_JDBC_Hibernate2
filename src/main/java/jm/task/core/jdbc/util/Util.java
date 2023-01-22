package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Util {
    // реализуйте настройку соеденения с БД
    public static final String url = "jdbc:mysql://localhost:3306/test19";
    private static final String username = "root";
    private static final String password = "Anna2012";
    private static final String driverDB = "com.mysql.cj.jdbc.Driver";
    private static final SessionFactory sessionFactory;

    static {
        try {
            Class.forName(driverDB);

        } catch ( Throwable e) {
            e.printStackTrace();

        }
    }

    private Util() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    static {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test19")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "Anna2012");
            sessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory();
        }catch (Throwable e){
            throw new ExceptionInInitializerError("Util isn't work");
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

}
