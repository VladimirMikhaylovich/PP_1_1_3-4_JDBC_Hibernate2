package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            StringBuilder queryCreate = new StringBuilder("create TABLE IF not EXISTS User");
            queryCreate.append(" ( id int auto_increment not null, ");
            queryCreate.append(" name varchar(20), ");
            queryCreate.append(" lastName VARCHAR(25), ");
            queryCreate.append(" age int, ");
            queryCreate.append(" PRIMARY KEY ( id ))");
            session.createSQLQuery(queryCreate.toString()).executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            String queryDelete = "DROP TABLE IF EXISTS User ";
            session.createSQLQuery(queryDelete).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            List<User> list = (List<User>) session.createQuery("from User", User.class).list();
            session.close();
            return list;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

