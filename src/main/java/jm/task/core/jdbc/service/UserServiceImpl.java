package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        try {
            Statement statement = Util.ConnectionUtils.getConnection().createStatement();
            String sql = "CREATE TABLE USERS " +
                    "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) not NULL, " +
                    " lastName VARCHAR(255) not NULL, " +
                    " age TINYINT UNSIGNED not NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.ConnectionUtils.getConnection().createStatement();
            String sql = "DROP TABLE IF EXISTS USERS";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = Util.ConnectionUtils.getConnection().createStatement();
            String sql = "INSERT INTO USERS VALUES (DEFAULT, '" + name + "', '" + lastName + "', " + age + ")";
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = Util.ConnectionUtils.getConnection().createStatement();
            String sql = "SELECT id, name, lastName, age FROM USERS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.ConnectionUtils.getConnection().createStatement();
            String sql = "TRUNCATE TABLE USERS";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}