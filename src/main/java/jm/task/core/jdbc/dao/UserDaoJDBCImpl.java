package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.net.IDN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE USERS " +
                    "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) not NULL, " +
                    " lastName VARCHAR(255) not NULL, " +
                    " age TINYINT UNSIGNED not NULL)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("INSERT INTO USERS VALUES (DEFAULT, '" + name + "', '" +
                    lastName + "', " + age + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM USERS WHERE ID = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM USERS");
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
        try (Statement statement = Util.ConnectionUtils.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE USERS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
